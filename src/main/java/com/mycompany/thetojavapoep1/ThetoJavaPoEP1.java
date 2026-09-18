/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.thetojavapoep1;

import java.util.Scanner;

/**
 *
 * @author Theto
 */
public class ThetoJavaPoEP1 {

    public static void main(String[] args) {
        //Declarations
        String firstName;
        String lastName;
        String username;
        String password;
        String cellPhone;
        
        //Scanner user inputs
        Scanner myInput = new Scanner(System.in);
        
        //Enter the first and lastname
        System.out.print("Enter your names: ");
        firstName = myInput.next();
        System.out.print("Enter your lastname: ");
        lastName = myInput.next();
        
        //Using while loop to following conditions.
        //Enter the username.
        while (true){
            System.out.print("Enter your username: ");
            username = myInput.nextLine();
            if (checkUsername(username)){
                System.out.println("Username successfully captured.");
                break;
            }else{
                System.out.println("Username is not correctly formatted; ");
                System.out.print("Please ensure that your username contains an underscore and ");
                System.out.print("Is no more than five characters in length.");
            }   
        }
        
        //Entering the password.
        while (true){
            System.out.print("Enter your password: ");
            password = myInput.nextLine();
            if (checkPassword(password)){
                System.out.println("Password successfully captured.");
                break;
            }else{
                System.out.println("Password is not correctly formatted; ");
                System.out.print("please ensure that the password contains at least eight characters, ");
                System.out.print("a capital letter, a number, and a special character.");
            }
        }
        
        //Entering your cell phone number.
        while (true){
            System.out.println("Enter your cellphone: ");
            cellPhone = myInput.nextLine();
            if (checkcCellphone(cellPhone)){
                System.out.println("Cell phone number successfully added.");
            }else{
                System.out.println("Cell phone number incorrectly formatted or ");
                System.out.print("does not contain international code");
            }
        }
        
           
        
        
        
    }
        
}
