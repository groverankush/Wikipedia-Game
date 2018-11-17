package com.ankushgrover.letswiki.ui.score;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ankushgrover.letswiki.R;
import com.ankushgrover.letswiki.base.BaseFragment;
import com.ankushgrover.letswiki.ui.wiki.WikiFragment;

/**
 * Created by Ankush Grover(ankushgrover02@gmail.com) on 15/8/18.
 */
public class ScoreFragment extends BaseFragment {

    private ScoreListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof WikiFragment.WikiListener) {
            mListener = (ScoreListener) context;
        } else
            throw new RuntimeException("The passed context doesn't implements ScoreListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_score, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        view.findViewById(R.id.btn_results).setOnClickListener(v -> mListener.showResults());
        view.findViewById(R.id.btn_play_again).setOnClickListener(v -> mListener.playAgain());
        ((TextView) view.findViewById(R.id.tv_score)).setText(String.valueOf(mListener.getMainViewModel().getGameScore()));
    }

    public interface ScoreListener extends BaseFragmentListener {
        void showResults();

        void playAgain();
    }
}
