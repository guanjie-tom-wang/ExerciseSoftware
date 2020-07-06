package com.example.project;
import android.app.ProgressDialog;
import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

import static org.junit.Assert.*;
public class postTest {
    @Rule
    public ActivityTestRule<LoginActivity> mNoteActivityActivityTestRule =new ActivityTestRule<LoginActivity>(LoginActivity.class);
    @Before
    public void setUp() throws Exception {
    }
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.project", appContext.getPackageName());
    }
    @Test
    //test for coach
    public void postRequest() throws InterruptedException {
        onView(withId(R.id.username)).perform(typeText("11@q.ca"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("123456"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btn_login)).perform(click());
        Thread.sleep(3000);

        onView(withId(R.id.button7)).perform(click());
        onView(withId(R.id.textView)).check(matches(withText("Entry content which you want to post")));
    }
    @Test
    //test for athlete
    public void postRequest1() throws InterruptedException {
        onView(withId(R.id.username)).perform(typeText("22@q.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("123456"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btn_login)).perform(click());
        Thread.sleep(3000);

        onView(withId(R.id.button7)).perform(click());
        onView(withId(R.id.textView2)).check(matches(withText("Entry content which you want to post")));
    }
    @Test
    //test for wrong type
    public void postRequest2() throws InterruptedException {
        onView(withId(R.id.username)).perform(typeText("333@q.com"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("123456"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btn_login)).perform(click());
        Thread.sleep(3000);

        onView(withId(R.id.button7)).perform(click());
        onView(withId(R.id.textView3)).check(matches(withText("user type error, Update your information")));
    }
}
