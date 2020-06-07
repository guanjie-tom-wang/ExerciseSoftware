package com.example.project;

import android.content.Context;
import android.view.View;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.project", appContext.getPackageName());
    }

    @Test
    public void testInterfaceElements() {
        onView(withId(R.id.username)).check(matches(withHint("Username")));
        onView(withId(R.id.email)).check(matches(withHint("Email")));
        onView(withId(R.id.button)).check(matches(withText("Sign up")));
        onView(withId(R.id.age)).check(matches(withHint("Age")));
        onView(withId(R.id.weight)).check(matches(withHint("Weight")));
        onView(withId(R.id.height)).check(matches(withHint("Height")));
        onView(withId(R.id.password)).check(matches(withHint("Password")));
        onView(withId(R.id.address)).check(matches(withHint("Address")));
    }

    @Test
    public void testMainActivity(){
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.errorInfo)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

}