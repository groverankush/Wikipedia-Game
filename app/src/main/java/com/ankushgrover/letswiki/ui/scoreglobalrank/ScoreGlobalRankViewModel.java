package com.ankushgrover.letswiki.ui.scoreglobalrank;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

/**
 * Created by Ankush Grover on 18-11-2018.
 */
public class ScoreGlobalRankViewModel extends ViewModel {

    private static final String TAG = ScoreGlobalRankViewModel.class.getSimpleName();
    public static final String KEY_SCORE = "score";

    MutableLiveData<Long> scoreLiveData = new MutableLiveData<>();
    MutableLiveData<Long> rankLiveData = new MutableLiveData<>();

    public ScoreGlobalRankViewModel() {
        updateScore(0);
        listenScoreChanges();
    }

    public void updateScore(int score) {

        getDatabase().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Long currentScore = 0L;
                if (dataSnapshot.getValue() != null)
                    currentScore = (Long) dataSnapshot.getValue();
                currentScore += score;
                getDatabase().setValue(currentScore);
                scoreLiveData.setValue(currentScore);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "Cancelled");
            }
        });

    }

    private void listenScoreChanges() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference(KEY_SCORE);
        database.orderByValue().addValueEventListener(new ValueEventListener() {
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
                            rankLiveData.setValue(currentRank);
                            scoreLiveData.setValue((Long) data.getValue());
                            break;
                        }
                        currentRank--;

                    }

                } catch (Exception e) {
                    rankLiveData.setValue(-1L);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "Cancelled");

            }
        });
    }

    private DatabaseReference getDatabase() {
        assert FirebaseAuth.getInstance().getUid() != null;
        return FirebaseDatabase.getInstance().getReference().child(KEY_SCORE).child(FirebaseAuth.getInstance().getUid());
    }

}
