package com.example.project;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    User u1=new User("wgj","123123","asdfg","1234@q.com","qwerw","coach","fadsf",180,120,12);
    @Test
    public void testFriendRequet(){
        assertEquals("fadsf",u1.getFriend_request_from(u1));
    }
    @Test
    public void testUserName(){
        assertEquals("wgj",u1.getUsername(u1));
    }
    @Test
    public void testUserTN(){
        assertEquals("123123",u1.getTelNumber(u1));
    }
    @Test
    public void testUserHad(){

        assertEquals("asdfg",u1.getHomeAddress(u1));
    }
    @Test
    public void testUserED(){

        assertEquals("1234@q.com",u1.getEmailAddress(u1));
    }
    @Test
    public void testUserPW(){
        assertEquals("qwerw",u1.getPassword(u1));

    }
    @Test
    public void testUserType(){

        assertEquals("coach",u1.getType(u1));
    }
    @Test
    public void testUserH(){

        assertEquals(180,u1.getHeight(u1));
    }
    @Test
    public void testUserW(){

        assertEquals(120,u1.getWeight(u1));
    }
    @Test
    public void testUserA(){

        assertEquals(12,u1.getAge(u1));
    }
    @Test
    public void testSetFR(){

        u1.setFriend_request_from("wer");
        assertEquals("wer",u1.getFriend_request_from(u1));
    }
    @Test
    public void testSetUN(){

        u1.setUsername("wer");
        assertEquals("wer",u1.getUsername(u1));
    }
    @Test
    public void testSetTN(){

        u1.setTelNumber("12342134");
        assertEquals("12342134",u1.getTelNumber(u1));
    }
    @Test
    public void testSetHAD(){

        u1.setHomeAddress("qwert");
        assertEquals("qwert",u1.getHomeAddress(u1));
    }
    @Test
    public void testSetEd(){

        u1.setEmailAddress("1235478@qwer.com");
        assertEquals("1235478@qwer.com",u1.getEmailAddress(u1));
    }
    @Test
    public void testSetPW(){

        u1.setPassword("wesrgasdfgdfsgs");
        assertEquals("wesrgasdfgdfsgs",u1.getPassword(u1));
    }
    @Test
    public void testSetT(){

        u1.setType("athlete");
        assertEquals("athlete",u1.getType(u1));
    }
    @Test
    public void testSetH(){

        u1.setHeight(25);
        assertEquals(25,u1.getHeight(u1));
    }
    @Test
    public void testSetW(){

        u1.setWeight(200);
        assertEquals(200,u1.getWeight(u1));
    }
    @Test
    public void testSetA(){

        u1.setAge(25);
        assertEquals(25,u1.getAge(u1));

    }

}
