package com.example.project;

class ValidateData {

    public String validateRegisterFields(String password, String role, String age, String weight,
                                        String height, String username, String email){

        String error = "";
        String regex_email = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
        String regex_digit = "^\\d+";

        if ( password.isEmpty()) {
            error = error+"- Invalid password!\n";
        }
        if( role.isEmpty() ){
            error = error + "- Invalid role!\n";
        }
        if(!age.matches((regex_digit))){
            error = error + "- Invalid age!\n";
        }
        if(!weight.matches((regex_digit))){
            error = error + "- Invalid weight!\n";
        }
        if(!height.matches((regex_digit))){
            error = error + "- Invalid height!\n";
        }
        if ( username.isEmpty() || username.charAt(0)== ' ' || username.charAt(username.length() - 1) == ' ') {
            error = error + "- Invalid username!\n";

        }
        if (!email.matches(regex_email)) {
            error = error + "- Invalid email format!\n";
        }

        return error;


    }
}
