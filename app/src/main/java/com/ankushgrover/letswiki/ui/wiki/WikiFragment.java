package com.ankushgrover.letswiki.ui.wiki;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ankushgrover.letswiki.R;
import com.ankushgrover.letswiki.base.BaseFragment;
import com.ankushgrover.letswiki.data.model.Word;
import com.ankushgrover.letswiki.ui.wiki.spans.ClickSpan;
import com.ankushgrover.letswiki.utils.Utils;
import com.ankushgrover.letswiki.viewmodel.GameViewModel;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

/**
 * Created by Ankush Grover(ankushgrover02@gmail.com) on 15/8/18.
 */
public class WikiFragment extends BaseFragment {

    private static final String TAG = WikiFragment.class.getSimpleName();

    private static final String KEY_IS_RESULT = "isResultKey";
    private static final String KEY_TIME_LEFT = "keyTimeLeft";

    private WikiListener mListener;
    private boolean mIsResult;
    private FloatingActionButton mSubmitFab;
    private TextView mSubmitTv;
    private CountDownTimer mTimer;
    private long mTimeLeft = 60 * 1000; // milliseconds.
    private GameViewModel mGameModel;
    private ProgressBar mProgress;
    private FrameLayout mBottomFabContainer;
    private ScrollView mScrollView;
    private TextView mPara, mTitle;
    private ImageView mIcon;

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
        mGameModel = ViewModelProviders.of(this).get(GameViewModel.class);
        observeArticle();
        if (mListener.getMainViewModel().isDifficult() && !mIsResult)
            initTimer();
    }

    private void observeArticle() {
        mGameModel.article.observe(this, completeArticle -> {
            if (completeArticle == null) {
                Toast.makeText(getActivity(), R.string.generic_error, Toast.LENGTH_SHORT).show();
                mListener.playAgain();
                return;
            }

            manageProgressBar(false);
            mTitle.setText(completeArticle.getTitle());
            Picasso.get().load(completeArticle.getImageUrl()).into(mIcon);
            mPara.setText(Utils.makeParagraph(Arrays.copyOf(completeArticle.getWords(), completeArticle.getWords().length),
                    completeArticle.getMissingWordIndexes(),
                    completeArticle.getUserWords(),
                    new ClickSpan.OnWordClickListener() {
                        @Override
                        public void onWordClick(Word word) {
                            Log.d(TAG,"WORD CLICKED");
                        }
                    }));
            mPara.setMovementMethod(new LinkMovementMethod());



        });
        if (mGameModel.article.getValue() == null) {
            manageProgressBar(true);
            mGameModel.getTitleList();
        } else manageProgressBar(false);

    }

    private void manageProgressBar(boolean isVisible) {

        mProgress.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        mScrollView.setVisibility(isVisible ? View.GONE : View.VISIBLE);
        mBottomFabContainer.setVisibility(isVisible ? View.GONE : View.VISIBLE);

    }

    private void initView(View view) {
        mTitle = view.findViewById(R.id.tv_title);
        mIcon = view.findViewById(R.id.iv_icon);
        mPara = view.findViewById(R.id.tv_para);
        mScrollView = view.findViewById(R.id.scroll_view);
        mBottomFabContainer = view.findViewById(R.id.bottom_fab);
        mProgress = view.findViewById(R.id.progress);
        mSubmitFab = view.findViewById(R.id.fab_submit);
        mSubmitTv = view.findViewById(R.id.tv_submit);

        if (mIsResult) {
            mSubmitTv.setVisibility(View.GONE);
            mSubmitFab.setImageResource(R.drawable.ic_close);
        } else if (mListener.getMainViewModel().isDifficult()) {
            mSubmitTv.setVisibility(View.VISIBLE);
            mSubmitFab.setImageResource(0);
        } else {
            mSubmitTv.setVisibility(View.GONE);
            mSubmitFab.setImageResource(R.drawable.ic_check);
        }


        mSubmitFab.setOnClickListener(v -> {

            if (mIsResult)
                mListener.closeResultWiki();
            else if (mListener.getMainViewModel().isDifficult())
                showSubmitDialog(view.getContext());
            else
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

    private void showSubmitDialog(Context context) {

        new AlertDialog.Builder(context)
                .setTitle("Submit")
                .setMessage("Do you want to submit?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    cancelTimer();
                    dialog.dismiss();
                    mListener.submit();
                }).setNegativeButton("No", ((dialog, which) -> dialog.dismiss()
        )).create().show();
    }

    private void cancelTimer() {
        if (mTimer != null)
            mTimer.cancel();
    }

    @Override
    public void onPause() {
        super.onPause();
        cancelTimer();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putLong(KEY_TIME_LEFT, mTimeLeft);
        super.onSaveInstanceState(outState);
    }

    public interface WikiListener extends BaseFragmentListener {
        void submit();

        void closeResultWiki();

        void playAgain();

    }

}
