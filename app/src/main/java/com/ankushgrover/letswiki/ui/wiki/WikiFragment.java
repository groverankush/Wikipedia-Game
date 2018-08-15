package com.ankushgrover.letswiki.ui.wiki;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ankushgrover.letswiki.R;
import com.ankushgrover.letswiki.base.BaseFragment;

/**
 * Created by Ankush Grover(ankushgrover02@gmail.com) on 15/8/18.
 */
public class WikiFragment extends BaseFragment {
    private static final String KEY_IS_RESULT = "isResultKey";
    private static final String KEY_TIME_LEFT = "keyTimeLeft";

    private WikiListener mListener;
    private boolean mIsResult;
    private FloatingActionButton mSubmitFab;
    private TextView mSubmitTv;
    private CountDownTimer mTimer;
    private long mTimeLeft = 60 * 1000; // milliseconds.

    public WikiFragment() {
    }

    public static WikiFragment newInstance(boolean isResult) {
        WikiFragment fragment = new WikiFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY_IS_RESULT, isResult);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof WikiListener) {
            mListener = (WikiListener) context;
        } else
            throw new RuntimeException("The passed context doesn't implements WikiListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mIsResult = getArguments().getBoolean(KEY_IS_RESULT);
        }
        if (savedInstanceState != null) {
            mTimeLeft = savedInstanceState.getLong(KEY_TIME_LEFT, 60000);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_wiki, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initView(view);
        //TODO if isDifficult
        initTimer();
    }

    private void initView(View view) {
        mSubmitFab = view.findViewById(R.id.fab_submit);
        mSubmitTv = view.findViewById(R.id.tv_submit);

        mSubmitTv.setVisibility(View.GONE);
        mSubmitFab.setImageResource(R.drawable.ic_check);

        mSubmitFab.setOnClickListener(v -> {
            //TODO Add Difficulty check from view model.
            //      TODO If difficult show dialog first

            mListener.submit();
        });
    }

    private void initTimer() {
        mSubmitFab.setImageResource(0);
        mSubmitTv.setVisibility(View.VISIBLE);
        //TODO Count down timer in View Model
        mTimer = new CountDownTimer(mTimeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeft = millisUntilFinished;
                mSubmitTv.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                mListener.submit();
            }
        };
        mTimer.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mTimer != null)
            mTimer.cancel();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putLong(KEY_TIME_LEFT, mTimeLeft);
        super.onSaveInstanceState(outState);
    }

    public interface WikiListener {
        void submit();
    }

}
