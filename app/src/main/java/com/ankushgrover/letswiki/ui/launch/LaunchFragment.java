package com.ankushgrover.letswiki.ui.launch;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_launch, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.btn_start).setOnClickListener(v -> mListener.startGame());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSavedInstanceState");
    }



    public interface LaunchFragmentListener {
        void startGame();
    }


}
