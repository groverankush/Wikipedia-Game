package com.ankushgrover.letswiki.widget;

import android.app.job.JobParameters;
import android.support.annotation.NonNull;

import com.ankushgrover.letswiki.ui.scoreglobalrank.ScoreGlobalRankViewModel;
import com.firebase.jobdispatcher.JobService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * Created by Ankush Grover on 19-11-2018.
 */
public class ScoreGlobalRankJobService extends JobService implements ValueEventListener {
    private DatabaseReference reference;

    @Override
    public boolean onStartJob(com.firebase.jobdispatcher.JobParameters job) {

        reference = FirebaseDatabase.getInstance().getReference(ScoreGlobalRankViewModel.KEY_SCORE);
        reference.orderByValue().addValueEventListener(this);

        return true;
    }

    @Override
    public boolean onStopJob(com.firebase.jobdispatcher.JobParameters job) {
        if (reference != null)
            reference.removeEventListener(this);
        return true;
    }


    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        try {

            HashMap<String, Long> map = (HashMap<String, Long>) dataSnapshot.getValue();
            String userKey = FirebaseAuth.getInstance().getUid();
            if (map == null || !map.containsKey(userKey))
                return;
            Long currentRank = (long) map.size();

            for (DataSnapshot data : dataSnapshot.getChildren()) {


                if (data.getKey().equals(userKey)) {
                    ScoreGlobalRankWidget.updateWidget(this, (Long) data.getValue(), currentRank);
                    break;
                }
                currentRank--;

            }

        } catch (Exception e) {
            ScoreGlobalRankWidget.updateWidget(this, -1L, -1L);
        }

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        ScoreGlobalRankWidget.updateWidget(this, -1L, -1L);
    }
}
