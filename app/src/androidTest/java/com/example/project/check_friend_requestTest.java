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
@RunWith(AndroidJUnit4.class)

public class check_friend_requestTest  {
    @Rule
    public ActivityTestRule<LoginActivity> mNoteActivityActivityTestRule =new ActivityTestRule<LoginActivity>(LoginActivity.class);
    @Before
    public void setUp() throws Exception {
    }
    @Test
    //test for user who has friend request
    public void friendRequest() throws InterruptedException {
        onView(withId(R.id.username)).perform(typeText("ry562725@dal.ca"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("12345678"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btn_login)).perform(click());
        Thread.sleep(3000);

        onView(withId(R.id.contacts)).perform(click());
        onView(withId(R.id.message)).check(matches(withText("Hello Rui, You have a new friend request from: LDH.")));
    }


    @Test
    //test for user who has no request
    public void no_friendRequest() throws InterruptedException {
        onView(withId(R.id.username)).perform(typeText("test@dal.ca"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("12345678"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btn_login)).perform(click());
        Thread.sleep(3000);
        onView(withId(R.id.contacts)).perform(click());
        onView(withId(R.id.message)).check(matches(withText("Hello LDH, You have no friend request now. ")));
    }
    @After
    public void tearDown() throws Exception {

    }





}
