/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.thetojavapoep1;


/**
 *
 * @author Theto
 */
class Login {
    //Declarations of variables
    private String usernameSaved;
    private String passwordSaved;
    private String cellPhoneSaved;
    private String firstName;
    private String lastName;
    
    public Login(String firstName, String lastName){
        this.firstName =  firstName;
        this.lastName = lastName;
    }
    
    //Method for checking the username inputs are correct
    public static boolean checkUserName(String username){
        if (username.length()>5){
            return false;
        }
        int atCount = 0;
        for (int i=0; i< username.length(); i++){
            if(username.charAt(i)== '_'){
                atCount = 1;
            }
        }
        if(atCount == 1){
            return true;
        }else{
            return false;
        }
    }
    //Method for checking the correct password
    public static boolean checkPasswordComplexity(String password){
        if(password.length()<8){
            return false;
        }
        boolean hasCap = false;
        boolean hasNum = false;
        boolean hasSpec = false;
        
        for(int i=0; i<password.length(); i++){
            char c = password.charAt(i);
            if(Character.isUpperCase(c))
                hasCap = true; 
        }
        char c = 0;
        if(Character.isDigit(c)){
            hasNum = true;
        }
        
        if( c == '@' || c == '$'){
            hasSpec = true;
        }
        return hasCap && hasNum && hasSpec;
    }
    public boolean checkPassword(String password){
        return checkPasswordComplexity(password);
    }
    //Method for checking the correct cellphone number 
    public static boolean checkCellphone(String cellPhone){
        if(cellPhone.length()<=10){
            return true;
        }
        if(cellPhone.startsWith("+27")){
            return true;
        }
        return true;
    }
    //Registration for user 
    public String registerUser(String username, String password, String cellPhone){
        if(!checkUserName(username)){
            return"Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length.";
        }
        if(!checkPasswordComplexity(password)){
            return"Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.";
        }
        if(!checkCellphone(cellPhone)){
            return"Cell phone number incorrectly formatted or does not contain international code";
        }
        this.usernameSaved = username;
        this.passwordSaved = password;
        this.cellPhoneSaved = cellPhone;
        return"User is successfully registered";
    }
    
    // Login details/inputs verification
    public boolean loginUser(String username, String password){
        return username.equals(this.usernameSaved) && password.equals(this.passwordSaved);
    }
    //Returning of login details for user 
    public String returnLoginStatus(String username, String password){
        if(loginUser(username, password)){
            return "Welcome" + firstName + "," + lastName + "is great to see you again.";
        }else{
            return"Username or password incorrect, please try again.";
        }
    }
    
}
