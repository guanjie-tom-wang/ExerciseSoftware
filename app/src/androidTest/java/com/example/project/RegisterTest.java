package com.example.project;

import android.content.Context;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

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
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;

public class RegisterTest {
    @Rule
    public ActivityTestRule<Register> mActivityTestRule = new ActivityTestRule<Register>(Register.class);

    @Test
    public void testEmptyUsername() {
        onView(withId(R.id.username)).perform(typeText(""));
        onView(withId(R.id.username)).perform(closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.errorInfo)).check(matches(withText(containsString("- Invalid username!"))));
    }

    @Test
    public void testInvalidUsername() {
        onView(withId(R.id.username)).perform(typeText(" New User "));
        onView(withId(R.id.username)).perform(closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.errorInfo)).check(matches(withText(containsString("- Invalid username!"))));

    }

    @Test
    public void testInvalidPassword() {
        onView(withId(R.id.password)).perform(typeText(""));
        onView(withId(R.id.password)).perform(closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.errorInfo)).check(matches(withText(containsString("- Invalid password!"))));
    }

    @Test
    public void testInvalidEmail() {
        onView(withId(R.id.email)).perform(typeText("dadas"));
        onView(withId(R.id.email)).perform(closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.errorInfo)).check(matches(withText(containsString("- Invalid email format!"))));
    }

    @Test
    public void testInvalidRole() {
        onView(withId(R.id.role)).perform(typeText(""));
        onView(withId(R.id.role)).perform(closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.errorInfo)).check(matches(withText(containsString("- Invalid role!"))));
    }

    @Test
    public void testInvalidAge() {
        onView(withId(R.id.age)).perform(typeText(""));
        onView(withId(R.id.age)).perform(closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.errorInfo)).check(matches(withText(containsString("- Invalid age!"))));
    }

    @Test
    public void testInvalidWeight() {
        onView(withId(R.id.weight)).perform(typeText(""));
        onView(withId(R.id.weight)).perform(closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.errorInfo)).check(matches(withText(containsString("- Invalid weight!"))));
    }

    @Test
    public void testInvalidHeight() {
        onView(withId(R.id.height)).perform(typeText(""));
        onView(withId(R.id.height)).perform(closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.errorInfo)).check(matches(withText(containsString("- Invalid height!"))));
    }


    @Test
    public void testValidInput() {
        onView(withId(R.id.username)).perform(typeText("New User"));
        onView(withId(R.id.email)).perform(typeText("abc@mail.com"));
        onView(withId(R.id.age)).perform(typeText("18"));
        onView(withId(R.id.password)).perform(typeText("B00761972"));
        onView(withId(R.id.password)).perform(closeSoftKeyboard());
        onView(withId(R.id.weight)).perform(typeText("140"));
        onView(withId(R.id.weight)).perform(closeSoftKeyboard());
        onView(withId(R.id.height)).perform(typeText("180"));
        onView(withId(R.id.height)).perform(closeSoftKeyboard());
        onView(withId(R.id.role)).perform(typeText("Athlete"));

        onView(withId(R.id.role)).perform(closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.errorInfo)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }


    @Test
    public void testInterfaceElements() {
        onView(withId(R.id.username)).check(matches(withHint("Username")));
        onView(withId(R.id.email)).check(matches(withHint("Email")));
        onView(withId(R.id.register)).check(matches(withText("SIGN UP")));
        onView(withId(R.id.age)).check(matches(withHint("Age")));
        onView(withId(R.id.weight)).check(matches(withHint("Weight")));
        onView(withId(R.id.height)).check(matches(withHint("Height")));
        onView(withId(R.id.password)).check(matches(withHint("Password")));
        onView(withId(R.id.address)).check(matches(withHint("Address")));
    }
}
