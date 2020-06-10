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

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

import static org.junit.Assert.*;

public class check_friend_requestTest  {
   @Rule
    public ActivityTestRule<check_friend_request> mNoteActivityActivityTestRule =new ActivityTestRule<check_friend_request>(check_friend_request.class);


    @Test
    public void friendRequest(){
        /*onView(withId(R.id.username)).perform(typeText("ry562725@dal.ca"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password)).perform(typeText("12345678"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.contacts)).perform(click());
        onView(withId(R.id.message)).check(matches(withText("Hello Rui1, You have no friend request now. ")));*/
        //onView(withId(R.id.contacts)).perform(click());
        //onView(withId(R.id.message)).check(matches(withText("Hello Rui1, You have no friend request now. ")));
    }





}
