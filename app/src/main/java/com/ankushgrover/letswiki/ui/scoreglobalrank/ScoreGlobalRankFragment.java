package com.ankushgrover.letswiki.ui.scoreglobalrank;


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

import com.ankushgrover.letswiki.R;
import com.ankushgrover.letswiki.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreGlobalRankFragment extends BaseFragment {


    private ScoreGlobalRankListener mListener;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score_global_rank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        setTrophyIcon(view, 3);
    }


    private void setTrophyIcon(View v, int rank) {
        ImageView trophy = v.findViewById(R.id.iv_trophy);
        int theme = -1;

        switch (rank) {
            case 1:
                theme = R.style.FirstPosition;
                break;
            case 2:
                theme = R.style.SecondPosition;
                break;
            case 3:
                theme = R.style.ThirdPosition;
                break;
            default:
                theme = R.style.OtherPositions;
                break;
        }
        ContextThemeWrapper wrapper = new ContextThemeWrapper(v.getContext(), theme);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_trophy, wrapper.getTheme());
        trophy.setImageDrawable(drawable);
    }

    public interface ScoreGlobalRankListener extends BaseFragmentListener {
    }

}
