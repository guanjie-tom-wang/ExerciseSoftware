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
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;

public class UpdateTest {
    @Rule
    public ActivityTestRule<Update> mActivityTestRule = new ActivityTestRule<>(Update.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.project", appContext.getPackageName());
    }

    @Test
    public void testEmptyUsername() {
        onView(withId(R.id.username)).perform(typeText(""));
        onView(withId(R.id.username)).perform(closeSoftKeyboard());
        onView(withId(R.id.Update)).perform(click());
        onView(withId(R.id.errorInfo)).check(matches(withText(containsString("- Invalid username!"))));
    }

    @Test
    public void testInvalidUsername() {
        onView(withId(R.id.username)).perform(typeText(" New User "));
        onView(withId(R.id.username)).perform(closeSoftKeyboard());
        onView(withId(R.id.Update)).perform(click());
        onView(withId(R.id.errorInfo)).check(matches(withText(containsString("- Invalid username!"))));

    }

    @Test
    public void testInvalidPassword() {
        onView(withId(R.id.password)).perform(typeText(""));
        onView(withId(R.id.password)).perform(closeSoftKeyboard());
        onView(withId(R.id.Update)).perform(click());
        onView(withId(R.id.errorInfo)).check(matches(withText(containsString("- Invalid password!"))));
    }

    @Test
    public void testInvalidEmail() {
        onView(withId(R.id.email)).perform(typeText("dadas"));
        onView(withId(R.id.email)).perform(closeSoftKeyboard());
        onView(withId(R.id.Update)).perform(click());
        onView(withId(R.id.errorInfo)).check(matches(withText(containsString("- Invalid email format!"))));
    }

    @Test
    public void testInvalidRole() {
        onView(withId(R.id.role)).perform(typeText(""));
        onView(withId(R.id.role)).perform(closeSoftKeyboard());
        onView(withId(R.id.Update)).perform(click());
        onView(withId(R.id.errorInfo)).check(matches(withText(containsString("- Invalid role!"))));
    }

    @Test
    public void testInvalidAge() {
        onView(withId(R.id.age)).perform(typeText(""));
        onView(withId(R.id.age)).perform(closeSoftKeyboard());
        onView(withId(R.id.Update)).perform(click());
        onView(withId(R.id.errorInfo)).check(matches(withText(containsString("- Invalid age!"))));
    }

    @Test
    public void testInvalidWeight() {
        onView(withId(R.id.weight)).perform(typeText(""));
        onView(withId(R.id.weight)).perform(closeSoftKeyboard());
        onView(withId(R.id.Update)).perform(click());
        onView(withId(R.id.errorInfo)).check(matches(withText(containsString("- Invalid weight!"))));
    }

    @Test
    public void testInvalidHeight() {
        onView(withId(R.id.height)).perform(typeText(""));
        onView(withId(R.id.height)).perform(closeSoftKeyboard());
        onView(withId(R.id.Update)).perform(click());
        onView(withId(R.id.errorInfo)).check(matches(withText(containsString("- Invalid height!"))));
    }
    @Test
    public void testInputNotInDatabase(){
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
        onView(withId(R.id.Update)).perform(click());
        onView(withId(R.id.errorInfo)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }
    @Test
    public void testInputInDatabase(){
        onView(withId(R.id.username)).perform(typeText("wgj"));
        onView(withId(R.id.email)).perform(typeText("dalsdafasd@q.com"));
        onView(withId(R.id.age)).perform(typeText("18"));
        onView(withId(R.id.password)).perform(typeText("B00761972"));
        onView(withId(R.id.password)).perform(closeSoftKeyboard());
        onView(withId(R.id.weight)).perform(typeText("140"));
        onView(withId(R.id.weight)).perform(closeSoftKeyboard());
        onView(withId(R.id.height)).perform(typeText("180"));
        onView(withId(R.id.height)).perform(closeSoftKeyboard());
        onView(withId(R.id.role)).perform(typeText("Athlete"));
        onView(withId(R.id.role)).perform(closeSoftKeyboard());
        onView(withId(R.id.Update)).perform(click());
        onView(withId(R.id.errorInfo)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }
    @Test
    public void testInputWithWrongUserName(){
        onView(withId(R.id.username)).perform(typeText("wfasdfagj"));
        onView(withId(R.id.email)).perform(typeText("dalsdafasd@q.com"));
        onView(withId(R.id.age)).perform(typeText("18"));
        onView(withId(R.id.password)).perform(typeText("B00761972"));
        onView(withId(R.id.password)).perform(closeSoftKeyboard());
        onView(withId(R.id.weight)).perform(typeText("140"));
        onView(withId(R.id.weight)).perform(closeSoftKeyboard());
        onView(withId(R.id.height)).perform(typeText("180"));
        onView(withId(R.id.height)).perform(closeSoftKeyboard());
        onView(withId(R.id.role)).perform(typeText("Athlete"));
        onView(withId(R.id.role)).perform(closeSoftKeyboard());
        onView(withId(R.id.Update)).perform(click());
        onView(withId(R.id.errorInfo)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }
    @Test
    public void testInputWithWrongEmailAddress(){
        onView(withId(R.id.username)).perform(typeText("wgj"));
        onView(withId(R.id.email)).perform(typeText("sdasd@q.com"));
        onView(withId(R.id.age)).perform(typeText("18"));
        onView(withId(R.id.password)).perform(typeText("B00761972"));
        onView(withId(R.id.password)).perform(closeSoftKeyboard());
        onView(withId(R.id.weight)).perform(typeText("140"));
        onView(withId(R.id.weight)).perform(closeSoftKeyboard());
        onView(withId(R.id.height)).perform(typeText("180"));
        onView(withId(R.id.height)).perform(closeSoftKeyboard());
        onView(withId(R.id.role)).perform(typeText("Athlete"));
        onView(withId(R.id.role)).perform(closeSoftKeyboard());
        onView(withId(R.id.Update)).perform(click());
        onView(withId(R.id.errorInfo)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }

}