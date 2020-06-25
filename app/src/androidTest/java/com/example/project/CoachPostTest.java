package com.example.project;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;

import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;

public class CoachPostTest {
    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    @Test
    public void testNoFriendsAdded() throws InterruptedException {
        onView(withId(R.id.username)).perform(typeText("3@q.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("123456"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btn_login)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.post)).perform(click());
        onView(withId(R.id.planets_spinner)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        Thread.sleep(300);
        onView(withId(R.id.planets_spinner)).check(matches(withSpinnerText(containsString("You have no friends yet, go add one!"))));
    }

    @Test
    public void testSpinnerFriendList() throws InterruptedException {
        onView(withId(R.id.username)).perform(typeText("lee@123.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("12345678"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btn_login)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.post)).perform(click());
        onView(withId(R.id.planets_spinner)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.planets_spinner)).check(matches(withSpinnerText(containsString("pdd"))));
    }
}
