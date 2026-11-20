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
       
       //Display welcoming and showing how many message to enter
       System.out.println("Welcome to QuickChart.");
       System.out.print("How many messages do you want to enter");
       int total = Integer.parseInt(myInput.nextLine().trim());
       int entered = 0;
       
       ArrayList<Message> sentMessages = new ArrayList<>();
       
       //While loop to choose options
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
               
               //
               System.out.println("Message Hash: " + msg.createMessageHash());
               System.out.println("\n1) Send");
               System.out.println("\n2) Disregard");
               System.out.println("\n3) Store");
               System.out.print("Select");
               int sendOption = Integer.parseInt(myInput.nextLine().trim());
               
               System.out.println(msg.SentMessage(sendOption));
               
               //Options to select sending message
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
       
       //Giving out total number of messages sent
       System.out.println("Total message sent: ");
       
       //Part 3 
       StoredMessages stored = new StoredMessages();
       System.out.println("=== QUICKCHAT - STORED MESSAGES ===");
       //Check if it run first and test
       if (stored.getMessageCount() == 0) {
           stored.addMessage("00", "00:0:DIDCAKE", "+27834557896", "Did you get the cake?", "Sent");
           stored.addMessage("01", "01:1:WHETIME", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
           stored.addMessage("02", "02:2:YOHGATE", "+27834484567", "Yohoooo, I am at your gate.", "Disregard");
           stored.addMessage("0838884567", "08:4:ITTIME", "0838884567", "It is dinner time!", "Sent");
           stored.addMessage("04", "04:4:OKYOU", "+27838884567", "Ok, I am leaving without you.", "Stored");
           System.out.println("Test data loaded: 5 messages (2 Sent, 1 Disregard, 2 Stored).");
        }
       
       int option;
       do {
           System.out.println("\n=== MAIN MENU ===");
           System.out.println("1) Add New Message");
           System.out.println("2) Open Stored Messages Menu");
           System.out.println("3) Show All 5 Arrays (for marking)");
           System.out.println("4) Quit");
           System.out.println("Choose option");
           option = stored.validateMenuOption(4, myInput);
           
           switch (option) {
               case 1:
                   addNewMessage(myInput, stored);
                   break;
               case 2:
                   stored.showStoredMessagesMenu(myInput);
                   break;
               case 3:
                   showAllArrays(stored);
                   break;
               case 4:
                   System.out.println("Goodbye!");
                   break;
           }
       } while (option != 4);
       
       
       myInput.close();
    } 
    
    //Add message so arrays update
    private static void addNewMessage(Scanner myInput, StoredMessages stored) {
        System.out.println("\n--- Add New Message ---");
        System.out.println("Enter Messsage ID: ");
        String id = myInput.nextLine();
        System.out.println("Enter Message Hash: ");
        String hash = myInput.nextLine();
        System.out.println("Enter Recipient: ");
        String recipient = myInput.nextLine();
        System.out.println("Enter Message Text: ");
        String text = myInput.nextLine();
        
        System.out.println("Enter Status:");
        System.out.println("1) Sent");
        System.out.println("2) Stored");
        System.out.println("3) Disregard");
        System.out.print("Choose option");
        int statusChoice = stored.validateMenuOption(3, myInput);
        
        String status;
        if (statusChoice == 1) {
            status = "Sent";
        } else if (statusChoice == 2) {
            status = "Stored";
        } else {
            status = "Disregard";
        }
        
        stored.addMessage(id, hash, recipient, text, status);
        System.out.println("Message successfully added as: " + status);
        System.out.println("All 5 arrays updated automatically.");
    }
    
    //Show the 5 arrays 
    private static void showAllArrays(StoredMessages stored) {
        System.out.println("\n--- ARRAYS ---");
        
        System.out.println("\nSent Messages:");
        for (String s : stored.getSentMessages()) {
            System.out.println("-" + s);
        }
        System.out.println("\nDisregard Messages:");
        for (String s : stored.getDisregardedMessages()) {
            System.out.println("-" + s);
        }
        System.out.println("\nStored Messages:");
        for (String s : stored.getStoredMessages()) {
            System.out.println("-" + s);
        }
        System.out.println("\nMessage Hashes:");
        for (String s : stored.getMessageHash()) {
            System.out.println("-" + s);
        }
        System.out.println("\nMessage IDs:");
        for (String s : stored.getMessageID()) {
            System.out.println("-" + s);
        }  
    }
}
