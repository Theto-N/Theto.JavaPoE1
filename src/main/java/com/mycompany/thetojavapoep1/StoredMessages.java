/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.thetojavapoep1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.JSONArray;

/**
 *
 * @author Theto
 */
class StoredMessages {
    

    
    private final String jsonFile = null;
    
    //The 5 arrays which are required.
    private String[] messagesSent = new String[0];
    private String[] messagesDisregarded = new String[0];
    private String[] messagesStored = new String[0];
    private String[] messageHash = new String[0];
    private String[] messageID = new String[0];
    
    //Additional arrays that are parallel to search and display details.
    private String[] recipients = new String[0];
    private String[] senders = new String[0];
    private String[] statuses = new String[0];
    
    //Complete messages details retain internal records.
    private final List<MessageRecord> messages = new ArrayList<>();
    
    public StoredMessages() {
        this("messages.json");
    }
    
    public StoredMessages(String jsonFile) {
        this.jsonFile = jsonFile;
        loadStoredMessagesFromJSON();
        populateArrays();
    }
    
    private static class MessageRecord {
        String id;
        String hash;
        String recipient;
        String text;
        String status;
        String sender;
        
        MessageRecord(String id, String hash, String recipient, String text, String status, String sender) {
            this.id = id;
            this.hash = hash;
            this.recipient = recipient;
            this.text = text;
            this.status = status;
            this.sender = sender;
        }
    }
    //Call method whenever message is created and status should be Sent, Stored or Disredard.
    public void addMessage(String id, String hash, String recipient, String text, String status, String sender) {
        messages.add(new MessageRecord(id, hash, recipient, text, status, sender));
        populateArrays();
        if ("Stored".equalsIgnoreCase(status)) {
            saveStoredMessagesToJSON();
        }
    }
    public void addStoredMessage(String id, String hash, String recipient, String text, String status, String sender) {
        addMessage(id, hash, recipient, text, status, sender);
    }
    
    //Populate 5 arrays required
    public final void populateArrays(){
        List<String> sent = new ArrayList<>();
        List<String> disregarded = new ArrayList<>();
        List<String> stored = new ArrayList<>();
        List<String> hashes = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        List<String> recipientList = new ArrayList<>();
        List<String> senderList = new ArrayList<>();
        List<String> statusList = new ArrayList<>();
        
        for (MessageRecord message : messages) {
            hashes.add(message.hash);
            ids.add(message.id);
            recipientList.add(message.recipient);
            senderList.add(message.sender);
            statusList.add(message.status);
            
            if ("Sent".equalsIgnoreCase(message.status)) {
                sent.add(message.text);
            } else if ("Disregard".equalsIgnoreCase(message.status)
                || "Disregarded".equalsIgnoreCase(message.status)) {
                disregarded.add(message.text);
            } else if ("Stored".equalsIgnoreCase(message.status)) {
                stored.add(message.text);
            }
        }
        
        messagesSent = sent.toArray(new String[0]);
        messagesDisregarded = disregarded.toArray(new String[0]);
        messagesStored = stored.toArray(new String[0]);
        messageHash = hashes.toArray(new String[0]);
        messageID = ids.toArray(new String[0]);
        recipients = recipientList.toArray(new String[0]);
        senders = senderList.toArray(new String[0]);
        statuses = statusList.toArray(new String[0]);   
    }
    
    //---GETTERS TO TESTS---
    public String[] getmessagesSent(){return messagesSent;}
    public String[] getmessagesDisregarded(){return messagesDisregarded;}
    public String[] getmessagesStored(){return messagesStored;}
    public String[] getmessageHash(){return messageHash;}
    public String[] getRecipients(){return recipients;}
    public String[] getSenders(){return senders;}
    public String[] getStatuses(){return statuses;}
    public String[] getSender(){return senders;}
    public String[] getStatus(){return statuses;}
    public int getMessageCount(){return messages.size();}
    
    //---JSON---
    // ---------- JSON ----------
public void saveStoredMessagesToJSON() {
    JSONArray arr = new JSONArray();
    for (int i = 0; i < messages.size(); i++) {
        if (messages.get(i).status.equals("Stored")) {
            JSONObject obj = new JSONObject();
            obj.put("messageID", messages.get(i).id);
            obj.put("messageHash", messages.get(i).hash);
            obj.put("recipient", messages.get(i).recipient);
            obj.put("message", messages.get(i).text);
            obj.put("status", messages.get(i).status);
            obj.put("sender", messages.get(i).sender);
            arr.put(obj);
        }
    }
    try (FileWriter file = new FileWriter(jsonFile)) {
        file.write(arr.toString(4));
    } catch (IOException e) {
        System.out.println("Error storing JSON: " + e.getMessage());
    }
}

public void loadStoredMessagesFromJSON() {
    File f = new File(jsonFile);
    if (!f.exists()) return;
    try {
        String content = new String(Files.readAllBytes(Paths.get(jsonFile)));
        if (content.trim().isEmpty()) return;
        JSONArray arr = new JSONArray(content);
        messages.clear();
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            messages.add(new MessageRecord(
                obj.getString("messageID"),
                obj.getString("messageHash"),
                obj.getString("recipient"),
                obj.getString("message"),
                obj.optString("status", "Stored"),
                obj.optString("sender", "Developer")
            ));
        }
    } catch (Exception e) {
    }
}
    
   
    
    //---0PTION 1---DISPLAY SENDER
    public void displayStoredSendersAndRecipients(){
        int storedCount = 0;
        for (MessageRecord m: messages){
            if ("Stored".equalsIgnoreCase(m.status)) {
                System.out.println("Sender: " + m.sender + " | Recipient:" + m.recipient);
                storedCount++;
            }
        }
        if (storedCount ==0){
            System.out.println("There are no stored messages.");
        }
        }
    public String displaySendersAndRecipients(){
            StringBuilder sb = new StringBuilder();
            for (MessageRecord m: messages){
               if ("Stored".equalsIgnoreCase(m.status)) {
                   sb.append("Sender:").append(m.sender).append("| Recipient:").append(m.recipient).append("\n");   
               }
            }
          return sb.toString();
    }
    //---OPTION 2---LONGEST MESSAGE STORED
    public void getLongestStoredMessage(){
        String longest = " ";
        for (int i = 0; i < messages.size(); i++){
            if (messages.get(i).status.equals("Stored") && messages.get(i).text.length()> longest.length()){
                longest = messages.get(i).text;
            }
        }
        if (longest.equals(" "))System.out.println("No stored messages.");
        else System.out.println("Longest:" + longest);
    }
    public String displayLongestMessage(){
        String longest = " ";
        for (int i =0; i < messages.size(); i++){
            if (messages.get(i).status.equals("Stored") && messages.get(i).text.length()> longest.length())
                longest = messages.get(i).text;
        }
        return longest;
    }
    
    //---OPTION 3---MESSAGE ID SEARCH
    public void searchByMessageID(Scanner input) {
    System.out.print("Enter ID: ");
    String id = input.nextLine();
    System.out.println(searchByMessageID(id));
}

    public String searchByMessageID(String id) {
    for (int i = 0; i < messages.size(); i++) {
        if (messages.get(i).id.equals(id))
            return "Recipient: " + messages.get(i).recipient + "\nMessage: " + messages.get(i).text;
        }
    return "Not found.";
   }
    
    //---OPTION 4---RECIPIENT SEARCHING
    public void searchByRecipient(Scanner input) {
    System.out.print("Enter recipient: ");
    String who = input.nextLine();
    System.out.println(searchByRecipient(who));
}

    public String searchByRecipient(String who) {
    String out = "";
    for (int i = 0; i < messages.size(); i++) {
        if (messages.get(i).recipient.equals(who))
            out += messages.get(i).text + "\n";
        }
    if (out.equals("")) return "No messages for that recipient.";
    return out;
    }
    
    //--OPTION 5---DELETE BY HASH
    public void deleteByMessageHash(Scanner input) {
    System.out.print("Enter hash to delete: ");
    String hash = input.nextLine();
    System.out.println(deleteMessageByHash(hash));
}

    public String deleteMessageByHash(String hash) {
    for (int i = 0; i < messages.size(); i++) {
        if (messages.get(i).hash.equals(hash)) {
            String gone = messages.get(i).text;
            messages.remove(i);
            populateArrays();
            saveStoredMessagesToJSON();
            return "Message: \"" + gone + "\" successfully deleted.";
        }
    }
    return "Hash not found.";
}
    
    //---OPTION 6---REPORT GENERATE
    public void generateReport() {
    System.out.println(displayReport());
}

    public String displayReport() {
      String out = "--- STORED MESSAGES REPORT ---\n";
       for (int i = 0; i < messages.size(); i++) {
        if (messages.get(i).status.equals("Stored")) {
            out += "Hash: " + messages.get(i).hash + "\n";
            out += "Recipient: " + messages.get(i).recipient + "\n";
            out += "Message: " + messages.get(i).text + "\n";
            out += "ID: " + messages.get(i).id + "\n---\n";
        }
    }
    return out;
}
    // ---------- MENU - SHORT ----------
public void showmessagesStoredMenu(Scanner input) {
    int choice;
    do {
        System.out.println("\n--- Stored Menu ---");
        System.out.println("1) Sender & Recipient");
        System.out.println("2) Longest Message");
        System.out.println("3) Search by ID");
        System.out.println("4) Search by Recipient");
        System.out.println("5) Delete by Hash");
        System.out.println("6) Full Report");
        System.out.println("7) Back");
        System.out.print("Choose: ");
        choice = validateMenuOption(7, input);

        if (choice == 1) displayStoredSendersAndRecipients();
        else if (choice == 2) getLongestStoredMessage();
        else if (choice == 3) searchByMessageID(input);
        else if (choice == 4) searchByRecipient(input);
        else if (choice == 5) deleteByMessageHash(input);
        else if (choice == 6) generateReport();

    } while (choice != 7);
}

public int validateMenuOption(int max, Scanner input) {
    int choice = 0;
    while (choice < 1 || choice > max) {
        try {
            choice = Integer.parseInt(input.nextLine().trim());
            if (choice < 1 || choice > max) System.out.print("Try again: ");
        } catch (Exception e) {
            System.out.print("Enter a number: ");
            choice = 0;
        }
    }
    return choice;
}
}

    

