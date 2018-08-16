package com.ankushgrover.letswiki.ui.launch;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.ankushgrover.letswiki.R;
import com.ankushgrover.letswiki.base.BaseFragment;

/**
 * Created by Ankush Grover(ankushgrover02@gmail.com) on 15/8/18.
 */
public class LaunchFragment extends BaseFragment {

    private static final String TAG = LaunchFragment.class.getSimpleName();
    private LaunchFragmentListener mListener;


    public LaunchFragment() {
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof LaunchFragmentListener) {
            mListener = (LaunchFragmentListener) context;
        } else
            throw new RuntimeException("The passed context doesn't implements LaunchFragmentListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_launch, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RadioButton radioEasy = view.findViewById(R.id.radio_easy);
        RadioButton radioDifficult = view.findViewById(R.id.radio_difficult);

        if (mListener.getMainViewModel().isDifficult())
            radioDifficult.setChecked(true);
        else radioEasy.setChecked(true);

        radioEasy.setOnClickListener(this::onRadioClick);
        radioDifficult.setOnClickListener(this::onRadioClick);

        view.findViewById(R.id.btn_start).setOnClickListener(v -> mListener.startGame());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSavedInstanceState");
    }

    public void onRadioClick(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio_easy:
                if (checked)
                    mListener.getMainViewModel().setDifficult(false);
                break;
            case R.id.radio_difficult:
                if (checked)
                    mListener.getMainViewModel().setDifficult(true);
                break;
        }
    }


    public interface LaunchFragmentListener extends BaseFragmentListener {
        void startGame();
    }


}
