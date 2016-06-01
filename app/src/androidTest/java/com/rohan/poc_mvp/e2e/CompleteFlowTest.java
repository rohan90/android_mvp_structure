package com.rohan.poc_mvp.e2e;

import android.support.test.runner.AndroidJUnit4;

import com.rohan.poc_mvp.R;
import com.rohan.poc_mvp.ui.activities.BrandScreenActivity;
import com.rohan.poc_mvp.utils.ActivityRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

/**
 * Created by rohan on 30/5/16.
 *
 *
 *
 *
 * TODO: this should point to a local or mocked api+db configuration [for now it is pointing to a genuine api]
 */
@RunWith(AndroidJUnit4.class)
public class CompleteFlowTest {
    @Rule
    public final ActivityRule<BrandScreenActivity> rule = new ActivityRule<>(BrandScreenActivity.class);
    private BrandScreenActivity activity;

    @Before
    public void init() {
        activity = rule.get();
    }

    @Test
    public void shouldCompleteAFullFlow() throws InterruptedException {
        onView(withId(R.id.tv_moto_or_logo)).check(matches(isDisplayed()));
        Thread.sleep(1000);
        onView(withId(R.id.et_github_username)).perform(typeText("rohan90"),closeSoftKeyboard());
        Thread.sleep(2000);
        onView(withId(R.id.btn_search)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        Thread.sleep(10000);
        onView(withId(R.id.rv_repositories)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_error_no_repos)).check(matches(not(isDisplayed())));
    }

}
