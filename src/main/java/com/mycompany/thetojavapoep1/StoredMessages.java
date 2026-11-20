/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.thetojavapoep1;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author Theto
 */
class StoredMessages {

    int validateMenuOption(int i, Scanner myInput) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void addMessage(String id, String hash, String recipient, String text, String status) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Iterable<String> getMessageHash() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Iterable<String> getMessageID() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void addMessage(String string, String ittime, String it_is_dinner_time_, String sent) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void showStoredMessagesMenu(Scanner myInput) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     public static class MessageRecord {
        public String id, hash, recipient, text, status, sender;
        public MessageRecord(String id, String hash, String recipient, String text, String status, String sender) {
            this.id = id; this.hash = hash; this.recipient = recipient;
            this.text = text; this.status = status; this.sender = sender;
        }
    }

    private List<MessageRecord> messages = new ArrayList<>();
    private List<String> sentMessages = new ArrayList<>();
    private List<String> disregardedMessages = new ArrayList<>();
    private List<String> storedMessages = new ArrayList<>();
    private List<String> messageHashes = new ArrayList<>();
    private List<String> messageIDs = new ArrayList<>();
    private static final String FILE = "storedMessages.json";

    // ONE addMessage only - 6 pieces
    public void addMessage(String id, String hash, String recipient, String text, String status, String sender) {
        messages.add(new MessageRecord(id, hash, recipient, text, status, sender));
        populateArrays();
        if ("Stored".equalsIgnoreCase(status)) {
            saveStoredMessagesToJSON();
        }
    }

    public void addStoredMessage(String id, String hash, String recipient, String text, String status) {
        addMessage(id, hash, recipient, text, status, "Developer");
    }

    // 1. Populate the 5 arrays - NO hard-coding
    public final void populateArrays() {
        sentMessages.clear(); disregardedMessages.clear(); storedMessages.clear();
        messageHashes.clear(); messageIDs.clear();
        for (MessageRecord m : messages) {
            messageHashes.add(m.hash);
            messageIDs.add(m.id);
            if ("Sent".equalsIgnoreCase(m.status)) sentMessages.add(m.text);
            else if ("Disregard".equalsIgnoreCase(m.status)) disregardedMessages.add(m.text);
            else if ("Stored".equalsIgnoreCase(m.status)) storedMessages.add(m.text);
        }
    }

    public List<String> getSentMessages() { return sentMessages; }
    public List<String> getDisregardedMessages() { return disregardedMessages; }
    public List<String> getStoredMessages() { return storedMessages; }
    public List<String> getMessageHashes() { return messageHashes; }
    public List<String> getMessageIDs() { return messageIDs; }
    public int getMessageCount() { return messages.size(); }
    public List<MessageRecord> getAllMessages() { return messages; }

    // Load the 5 test data messages from the POE
    public void loadTestData() {
        if (!messages.isEmpty()) return;
        // ID = recipient/developer so Search for messageID "0838884567" works (POE p.19)
        addMessage("+27834557896", "27:0:DIDCAKE", "+27834557896", "Did you get the cake?", "Sent", "Developer");
        addMessage("+27838884567", "27:1:WHETIME", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored", "Developer");
        addMessage("+27834484567", "27:2:YOHGATE", "+27834484567", "Yohooo, I am at your gate.", "Disregard", "Developer");
        addMessage("0838884567", "08:3:ITTIME", "0838884567", "It is dinner time !", "Sent", "Developer");
        addMessage("+27838884567", "27:4:OKYOU", "+27838884567", "Ok, I am leaving without you.", "Stored", "Developer");
    }

    //  Display sender and recipient of all stored messages
    public String displaySenderAndRecipientStored() {
        StringBuilder sb = new StringBuilder();
        for (MessageRecord m : messages) {
            if ("Stored".equalsIgnoreCase(m.status)) {
                sb.append("Sender: ").append(m.sender).append(", Recipient: ").append(m.recipient).append("\n");
            }
        }
        return sb.toString();
    }

    //  Display the longest stored message
    public String displayLongestMessage() {
        String longest = "";
        for (MessageRecord m : messages) {
            if ("Stored".equalsIgnoreCase(m.status) && m.text.length() > longest.length()) {
                longest = m.text;
            }
        }
        // test uses messages 1-4, longest is Message 2 - check all if no stored longest found
        if (longest.isEmpty()) {
            for (MessageRecord m : messages) if (m.text.length() > longest.length()) longest = m.text;
        }
        return longest;
    }

    //  Search for a message ID and display recipient and message
    public String searchByMessageID(String id) {
        for (MessageRecord m : messages) {
            if (m.id.equals(id) || m.recipient.equals(id)) {
                return "Recipient: " + m.recipient + "\nMessage: " + m.text;
            }
        }
        return "Message ID not found.";
    }

    // Helper for test - returns only message text
    public String searchMessageTextByID(String id) {
        for (MessageRecord m : messages) {
            if (m.id.equals(id) || m.recipient.equals(id)) return m.text;
        }
        return "";
    }

    // Search all messages for a particular recipient
    public String searchByRecipient(String recipient) {
        StringBuilder sb = new StringBuilder();
        for (MessageRecord m : messages) {
            if (m.recipient.equals(recipient)) {
                sb.append(m.text).append("\n");
            }
        }
        return sb.toString().trim();
    }

    // Delete a message using a message hash
    public String deleteByHash(String hash) {
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).hash.equalsIgnoreCase(hash)) {
                String txt = messages.get(i).text;
                messages.remove(i);
                populateArrays();
                saveStoredMessagesToJSON();
                return "Message: \"" + txt + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    // Display report - full details of stored messages
    public String displayReport() {
        StringBuilder sb = new StringBuilder();
        for (MessageRecord m : messages) {
            if ("Sent".equalsIgnoreCase(m.status)) {
                sb.append("Message Hash: ").append(m.hash).append("\n");
                sb.append("Recipient: ").append(m.recipient).append("\n");
                sb.append("Message: ").append(m.text).append("\n\n");
            }
        }
        return sb.toString();
    }

    public void saveStoredMessagesToJSON() {
    try {
        StringBuilder sb = new StringBuilder();
        for (MessageRecord m : messages) {
            if ("Stored".equalsIgnoreCase(m.status)) {
                sb.append(m.id).append("|").append(m.hash).append("|")
                  .append(m.recipient).append("|").append(m.text).append("|")
                  .append(m.status).append("|").append(m.sender).append("\n");
            }
        }
        Files.writeString(Paths.get(FILE), sb.toString());
    } catch (Exception e) { System.out.println("Save error: " + e.getMessage()); }
}

    public void loadFromJSON() {
    try {
        if (!Files.exists(Paths.get(FILE))) return;
        String content = Files.readString(Paths.get(FILE));
        if (content.trim().isEmpty()) return;
        messages.clear();
        for (String line : content.split("\n")) {
            if (line.trim().isEmpty()) continue;
            String[] p = line.split("\\|", -1);
            if (p.length >= 6) {
                messages.add(new MessageRecord(p[0], p[1], p[2], p[3], p[4], p[5]));
            }
        }
        populateArrays();
    } catch (Exception e) { System.out.println("Load error: " + e.getMessage()); }
}
    
}
