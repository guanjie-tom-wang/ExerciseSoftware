package com.example.project;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestValidation {
    @Test
    public void testRegisterFieldsError(){
        ValidateData validator = new ValidateData();
        String username = "TEST";
        String password = "";
        String height = "TEST";
        String weight = "TEST";
        String email = "TEST";
        String age = "TEST";
        String role = "TEST";

        String error = validator.validateRegisterFields(password,role,age,height,weight,username,email);

        assertEquals("- Invalid password!\n- Invalid age!\n- Invalid weight!\n- Invalid height!\n- Invalid email format!\n",
                error);
    }

    @Test
    public void testRegisterFieldsSuccess(){
        ValidateData validator = new ValidateData();
        String username = "TEST";
        String password = "testpassword";
        String height = "180";
        String weight = "140";
        String email = "TEST@test.com";
        String age = "12";
        String role = "coach";

        String error = validator.validateRegisterFields(password,role,age,height,weight,username,email);

        assertEquals("", error);
    }

}
