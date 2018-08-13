package com.ankushgrover.letswiki.ui.launch;

import android.os.Bundle;

import com.ankushgrover.letswiki.R;
import com.ankushgrover.letswiki.base.BaseActivity;
import com.ankushgrover.letswiki.ui.score.ScoreActivity;

public class LaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(v -> switchActivity(ScoreActivity.class));

    }
}
