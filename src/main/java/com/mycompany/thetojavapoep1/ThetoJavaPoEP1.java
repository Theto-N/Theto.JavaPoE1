/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.thetojavapoep1;

import java.util.Scanner;
import java.util.ArrayList;

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
        
         Login login = new Login(firstName, lastName);
        
        //Using while loop to following conditions.
        //Enter the username.
        while (true){
            System.out.print("Enter your username: ");
            username = myInput.nextLine();
            if (login.checkUserName(username)){
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
            if (login.checkPasswordComplexity(password)){
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
            if (login.checkCellphone(cellPhone)){
                System.out.println("Cell phone number successfully added.");
                break;
            }else{
                System.out.println("Cell phone number incorrectly formatted or ");
                System.out.print("does not contain international code");
            }
        }
        //Registration 
        System.out.println(login.registerUser(username, password, cellPhone));
        
        //LOGIN and while loop to test the conditions.
        while(true){
            System.out.print("Username for user to login: ");
            String loginUsername = myInput.nextLine();
            System.out.print("Password for user to login: ");
            String loginPassword = myInput.nextLine();
            String status = login.returnLoginStatus(loginUsername, loginPassword);
            System.out.println(status);
        
            if(login.loginUser(loginUsername, loginPassword)){
            break;
             
           }
       
        }
        
      
       
       //PART 2-Sending Messages after loggedin.
       
       
       System.out.println("Welcome to QuickChart.");
       System.out.print("How many messages do you want to enter");
       int total = Integer.parseInt(myInput.nextLine().trim());
       int entered = 0;
       
       ArrayList<Message> sentMessages = new ArrayList<>();
       
       while (true){
           System.out.println("\n1) Send Message");
           System.out.println("\n2) Show recently sent message");
           System.out.println("\n3) Quit");
           System.out.print("Choose: ");
           String option = myInput.nextLine().trim();
            int menuChoice = 0;
           
           if (option == ("1")){
               System.out.print("Enter recipient number (+27...)");
               String recipient = myInput.nextLine();
               System.out.print("Enter message (max 250 chars): ");
               String text = myInput.nextLine();
               
               Message msg = new Message(recipient, text, entered);
               
               //Check the recipient
               String recipientCheck = msg.checkRecipientCell();
               System.out.println(recipientCheck);
               if (recipientCheck.contains("incorrectly")){
               continue;
           }
               //Check the length of the message
               String messagesCheck = msg.checkMessage();
               System.out.println(messagesCheck);
               if (messagesCheck.contains("exceeds")){
                   continue;  
               }
               
               System.out.println("Message Hash: " + msg.createMessageHash());
               System.out.println("\n1) Send");
               System.out.println("\n2) Disregard");
               System.out.println("\n3) Store");
               System.out.print("Select");
               int sendOption = Integer.parseInt(myInput.nextLine().trim());
               
               System.out.println(msg.SentMessage(sendOption));
               
               if (sendOption == 1){
                   sentMessages.add(msg);
                   System.out.println(msg.printMessages());
                   entered++;
               } else if (sendOption == 3){
                   msg.storeMessage();
                   entered++;
               }else if (sendOption == 2){
                   System.out.println("Message disregarded.");
           }
               
           } else if (menuChoice == 2){
               System.out.println("Coming Soon.");
           } else if (menuChoice == 3){
               System.out.println("Total messages sent: ");
               System.out.println("Quitting. Total sent in session: " );
               break;
           }
           
       }
       
       System.out.println("Total message sent: ");
       myInput.close();
    }      
}
