package com.example.project;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class SendFriendRequestTest {
    @Rule
    public ActivityTestRule<SearchAndSend> mActivityTestRule = new ActivityTestRule<SearchAndSend>(SearchAndSend.class);


    @Test
    public void testInterfaceElements() {
        onView(withId(R.id.addEmail1)).check(matches(withHint("Enter Email")));

        onView(withId(R.id.button4)).check(matches(withText("Check")));
        onView(withId(R.id.button3)).check(matches(withText("ADD (REQUEST)")));
    }
}
