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
    public static boolean checkPassword(String password){
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
        if(Character.isDigit(0)){
            hasNum = true;
        }
        char c = 0;
        if( c == '@' || c == '$'){
            hasSpec = true;
        }
        return hasCap && hasNum && hasSpec;
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
