package com.rohan.poc_mvp.ui;

import android.support.test.runner.AndroidJUnit4;

import com.rohan.poc_mvp.R;
import com.rohan.poc_mvp.ui.activities.MainActivity;
import com.rohan.poc_mvp.utils.ActivityRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by rohan on 26/5/16.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public final ActivityRule<MainActivity> rule = new ActivityRule<>(MainActivity.class);
    private MainActivity activity;

    @Before
    public void init() {
        activity = rule.get();
    }

    @Test
    public void shouldLaunchRepositoryListActivity() throws InterruptedException {
        onView(withId(R.id.et_github_username)).perform(typeText("username"));
        onView(withId(R.id.btn_search)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));

    }
}
