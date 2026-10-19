/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.thetojavapoep1;

import java.io.FileWriter;
import java.util.Random;

/**
 *
 * @author Theto
 */
public class Message {
    //Declarations
    private String messageID;
    private String recipient;
    private String messageText;
    private int messageNumber;
    private static int totalMessages = 0;
    
    //Constructor and match with main
    public Message(String recipient, String messageText, int number){
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageNumber = number;
        this.messageID = generateMessageID();
    }
    
    public String generateMessageID(){
            Random rand = new Random();
            String id = "";
            for (int i = 0; i < 10; i++) id += rand.nextInt(10);
            return id;
    }
    
    public String checkRecipientCell(){
        if (recipient != null && recipient.startsWith("+") && recipient.length() <= 13){
        return "Cellphone number successfully captured.";
    } else {
            return "Cellphone number is incorrectly formated or does not contain an international code. Please correct the number and try again";
            }
    }
    
    public String checkMessage(){
        if (messageText.length()>250){
            return "Message exceeds 250 characters by " + (messageText.length()-250) +", Please reduce size.";
        } else {
            return "Message ready to send.";
        }
    }
    
    public String createMessageHash(){
        String[] words = messageText.trim().split("\\s+");
        if (words.length == 1){
            return messageID.substring(0, 2) + ":" + messageNumber + ":" + words[0].toUpperCase();
        }
        String first = words [0];
        String last = words [words.length - 1];
        return messageID.substring(0, 2) + ":" + messageNumber + ":" + (first + last).toUpperCase();
    }
    
    public String SentMessage(int option){
        if (option == 1){
            totalMessages++;
            return "Message successfully sent.";
        } else if (option == 2){
            return "Press 0 to delete the message.";    
        } else if (option == 3){
            storeMessage();
            return "Message successfully stored.";
        }
        return "Invalid option";
    }
    
    public void storeMessage(){
        try {
            FileWriter fw = new FileWriter("storedMessages.json", true);
            fw.write("{\"MessageID\":\"" + messageID + "\", \" MessageHash\":\"" + createMessageHash() + "\", \"Recipient\":" + recipient + "\", \"Message\":\"" + messageText + "\"}\n");
            fw.close();
        }catch (Exception e) {
            System.out.println("Error storing message");
        }
    }
    
    public String printMessages(){
        return "MessageID: " + messageID + 
                "\nMessageHash: "  + createMessageHash() + "\n" +
                 "\nRecipient: " + recipient + 
                        "\nMessage: " + messageText;           
    }
    
    public int returnTotalMessages() {
        return totalMessages;
    }
}
    

