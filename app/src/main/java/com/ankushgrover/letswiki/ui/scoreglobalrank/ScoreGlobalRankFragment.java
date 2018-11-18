package com.ankushgrover.letswiki.ui.scoreglobalrank;


import android.arch.lifecycle.Observer;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ankushgrover.letswiki.R;
import com.ankushgrover.letswiki.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreGlobalRankFragment extends BaseFragment {


    private ScoreGlobalRankListener mListener;
    private View view;

    public ScoreGlobalRankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ScoreGlobalRankListener) {
            mListener = (ScoreGlobalRankListener) context;
        } else
            throw new RuntimeException("The passed context doesn't implements ScoreGlobalRankListener");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score_global_rank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;
        mListener.getScoreViewModel().rankLiveData.removeObservers(getViewLifecycleOwner());
        mListener.getScoreViewModel().rankLiveData.observe(getViewLifecycleOwner(), new Observer<Long>() {
            @Override
            public void onChanged(@Nullable Long rank) {
                setRank(rank);
            }
        });

        mListener.getScoreViewModel().scoreLiveData.removeObservers(getViewLifecycleOwner());
        mListener.getScoreViewModel().scoreLiveData.observe(getViewLifecycleOwner(), new Observer<Long>() {
            @Override
            public void onChanged(@Nullable Long score) {
                setScore(score);
            }
        });


    }

    private void setRank(Long rank) {

        view.findViewById(R.id.ll_rank_container).setVisibility((rank == null || rank == -1) ? View.GONE : View.VISIBLE);
        ((TextView) view.findViewById(R.id.tv_rank)).setText(String.valueOf(rank));
        setTrophyIcon(rank);


    }

    private void setTrophyIcon(Long rank) {

        ImageView trophy = view.findViewById(R.id.iv_trophy);
        trophy.setVisibility(View.VISIBLE);
        int theme = -1;

        if (rank == null || rank <= -1 || rank > 3)
            trophy.setVisibility(View.GONE);
        else if (rank == 1)
            theme = R.style.FirstPosition;
        else if (rank == 2)
            theme = R.style.SecondPosition;
        else if (rank == 3)
            theme = R.style.ThirdPosition;

        ContextThemeWrapper wrapper = new ContextThemeWrapper(view.getContext(), theme);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_trophy, wrapper.getTheme());
        trophy.setImageDrawable(drawable);
    }

    private void setScore(Long score) {
        if (score == null || score <= 0)
            score = 0L;
        ((TextView) view.findViewById(R.id.tv_score)).setText(String.valueOf(score));
    }

    public interface ScoreGlobalRankListener extends BaseFragmentListener {
    }

}
