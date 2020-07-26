package com.example.project;

import android.content.Context;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;

public class StepTest {
    @Rule
    public ActivityTestRule<StepCounter> mActivityTestRule = new ActivityTestRule<>(StepCounter.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.project", appContext.getPackageName());
    }

    @Test
    public void testStepElements(){
        onView(withId(R.id.btn_start)).check(matches(withText("Start")));
        onView(withId(R.id.btn_stop)).check(matches(withText("Stop")));
    }

    @Test
    public void testStartText(){
        onView(withId(R.id.btn_start)).perform(click());
        onView(withId(R.id.tv_steps)).check(matches(withText("Start counting...")));
    }

    @Test
    public void testStopText(){
        onView(withId(R.id.btn_start)).perform(click());
        onView(withId(R.id.btn_stop)).perform(click());
        onView(withId(R.id.tv_steps)).check(matches(withText("Training completed!")));
    }
}