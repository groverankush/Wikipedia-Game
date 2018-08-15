package com.ankushgrover.letswiki.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.ankushgrover.letswiki.R;
import com.ankushgrover.letswiki.base.BaseActivity;
import com.ankushgrover.letswiki.base.BaseFragment;
import com.ankushgrover.letswiki.ui.launch.LaunchFragment;
import com.ankushgrover.letswiki.ui.score.ScoreFragment;
import com.ankushgrover.letswiki.ui.wiki.WikiFragment;

public class MainActivity extends BaseActivity implements LaunchFragment.LaunchFragmentListener, WikiFragment.WikiListener, ScoreFragment.ScoreListener {

    private static final String KEY_CURRENT_FRAGMENT = "keyCurrentFragment";
    private BaseFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            currentFragment = (BaseFragment) getSupportFragmentManager().getFragment(savedInstanceState, KEY_CURRENT_FRAGMENT);
            replaceFragment(currentFragment, false);
        } else
            replaceFragment(new LaunchFragment(), false);
    }


    private void replaceFragment(BaseFragment fragment, boolean addTransitions) {
        replaceFragment(fragment, addTransitions, false);
    }

    private void replaceFragment(BaseFragment fragment, boolean addTransitions, boolean addToBackStack) {
        currentFragment = fragment;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (addTransitions)
            ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        ft.replace(R.id.container, fragment);
        if (addToBackStack)
            ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        getSupportFragmentManager().putFragment(outState, KEY_CURRENT_FRAGMENT, currentFragment);

    }

    @Override
    public void startGame() {
        replaceFragment(WikiFragment.newInstance(false), true);
    }

    @Override
    public void submit() {
        replaceFragment(new ScoreFragment(), true);
    }

    @Override
    public void showResults() {
        replaceFragment(WikiFragment.newInstance(true), true, true);
    }

    @Override
    public void playAgain() {
        replaceFragment(new LaunchFragment(), true);
    }
}
