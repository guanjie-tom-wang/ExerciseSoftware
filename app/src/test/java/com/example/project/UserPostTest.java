package com.example.project;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class UserPostTest {
    @Test
    public void testCreateContact() {
        //test the getter and setter of the post function
        PostFromCoach p=new PostFromCoach("Rose","Run 2000m today",30,7,2020);
        assertEquals("Rose",p.getTitle());
        assertEquals("Run 2000m today",p.getContent());
        assertEquals(30,p.getDay());
        assertEquals(7,p.getMonth());
        assertEquals(2020,p.getYear());

    }

}
