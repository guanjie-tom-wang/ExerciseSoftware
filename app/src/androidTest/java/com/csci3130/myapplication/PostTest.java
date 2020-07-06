package com.csci3130.myapplication;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class PostTest {
    @Rule
    public ActivityTestRule<AddPost> activityActivityTestRule = new ActivityTestRule<>(AddPost.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void cleanUp() {
        Intents.release();
    }

    @Test
    public void emptyTitle(){
        onView(withId(R.id.post_button)).perform(click());
        onView(withId(R.id.post_title)).check(matches(hasErrorText("Title can't be empty")));
    }

    @Test
    public void emptyContent(){
        onView(withId(R.id.post_title)).perform(click()).perform(typeText("Title"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.post_button)).perform(click());
        onView(withId(R.id.post_content)).check(matches(hasErrorText("Content can't be empty")));
    }
}
