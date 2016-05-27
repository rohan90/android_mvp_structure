package com.rohan.poc_mvp.ui;

import com.rohan.poc_mvp.R;
import com.rohan.poc_mvp.model.Repository;
import com.rohan.poc_mvp.ui.activities.MainActivity;
import com.rohan.poc_mvp.ui.activities.RepositoryListActivity;
import com.rohan.poc_mvp.utils.ActivityRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

/**
 * Created by rohan on 27/5/16.
 */
public class RepositoryListActivityTest {
    private RepositoryListActivity activity;

    @Rule
    public final ActivityRule<RepositoryListActivity> rule = new ActivityRule<>(RepositoryListActivity.class);

    @Before
    public void init() {
        activity = rule.get();
    }

    @Test
    public void shouldShowRecylerViewOnReposFetched(){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.showRepositories(getMockRepoList());
            }
        });

        onView(withId(R.id.rv_repositories)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_error_no_repos)).check(matches(not(isDisplayed())));

    }

    @Test
    public void shouldShowErrorViewOnReposFetchedFailed(){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.showMessage("Error");
            }
        });

        onView(withId(R.id.tv_error_no_repos)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_repositories)).check(matches(not(isDisplayed())));

    }

    public List<Repository> getMockRepoList() {
        ArrayList<Repository> list = new ArrayList<>();
        list.add(new Repository());
        list.add(new Repository());
        return list;
    }
}
