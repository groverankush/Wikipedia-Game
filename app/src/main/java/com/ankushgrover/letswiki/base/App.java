package com.ankushgrover.letswiki.base;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Ankush Grover(ankushgrover02@gmail.com) on 16/8/18.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this))
            return;
        LeakCanary.install(this);

    }
}
