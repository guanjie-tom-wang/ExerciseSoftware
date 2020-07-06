package com.csci3130.myapplication;


import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.intent.Intents;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ViewPostTest {
    @Rule
    public ActivityTestRule<ViewPost> activityActivityTestRule = new ActivityTestRule<>(ViewPost.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @After
    public void cleanUp() {
        Intents.release();
    }

    @Test
    public void isRecyclerView(){
        ViewPost viewPost = activityActivityTestRule.getActivity();
        assertNotNull(viewPost.findViewById(R.id.view_rv));
    }

    @Test
    public void isPostShown() throws InterruptedException {
        RecyclerView recyclerView = activityActivityTestRule.getActivity().findViewById(R.id.view_rv);
        Thread.sleep(1000);
        assertTrue(Objects.requireNonNull(recyclerView.getAdapter()).getItemCount()!=0);
    }
}
