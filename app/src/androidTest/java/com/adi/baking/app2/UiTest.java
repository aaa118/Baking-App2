package com.adi.baking.app2;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;



import androidx.test.rule.ActivityTestRule;

import com.adi.baking.app2.views.ItemListActivity;


@RunWith(AndroidJUnit4.class)
@MediumTest
public class UiTest {

    @Rule
    public ActivityTestRule activityRule
            = new ActivityTestRule(ItemListActivity.class);

    @Test
    public void testRecipesClickShowsDetailsAboutIt() {
        sleep();
        onView(withText("Brownies")).perform(click());
        sleep();
        onView(withId(R.id.rl_item_detail)).check(matches(isDisplayed()));
    }

    @Test
    public void testAllRecipesAreDisplayed() {
        sleep();
        onView(withText("Brownies")).check(matches(isDisplayed()));
        onView(withText("Nutella Pie")).check(matches(isDisplayed()));
        onView(withText("Yellow Cake")).check(matches(isDisplayed()));
        onView(withText("Cheesecake")).check(matches(isDisplayed()));
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
