package com.example.project;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

public class SelectAthleteTest {
    @Rule
    public ActivityTestRule<SelectAthlete> mActivityTestRule = new ActivityTestRule<SelectAthlete>(SelectAthlete.class);

    @Test
    public void testInterfaceElements() {
        onView(withId(R.id.planets_spinner)).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(withId(R.id.planets_spinner)).check(matches(withSpinnerText(containsString("test2"))));
    }
}
