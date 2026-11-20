/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.thetojavapoep1;

import java.io.IOException;
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
    
    // Arrays required by Part 3
    private ArrayList<String> sentMessages = new ArrayList<>();
    private ArrayList<String> disregardedMessages = new ArrayList<>();
    private ArrayList<String> storedMessages = new ArrayList<>();
    private ArrayList<String> messageHashes = new ArrayList<>();
    private ArrayList<String> messageIDs = new ArrayList<>();
    // Full objects for search / delete / report
    private ArrayList<String[]> all = new ArrayList<>(); // [id, hash, recipient, message, flag]
    private static final String JSON_FILE = "storedMessages.json";

    public StoredMessages() {}

    // Main calls this: addMessage(id, hash, recipient, text, flag)
    public void addMessage(String id, String hash, String recipient, String text, String flag) {
        all.add(new String[]{id, hash, recipient, text, flag});
        messageIDs.add(id);
        messageHashes.add(hash);
        if (flag.equalsIgnoreCase("Sent")) sentMessages.add(text);
        else if (flag.equalsIgnoreCase("Stored")) storedMessages.add(text);
        else disregardedMessages.add(text);
    }

    public int getMessageCount() { return all.size(); }

    public ArrayList<String> getSentMessages() { return sentMessages; }
    public ArrayList<String> getDisregardedMessages() { return disregardedMessages; }
    public ArrayList<String> getStoredMessages() { return storedMessages; }
    public ArrayList<String> getMessageHash() { return messageHashes; }
    public ArrayList<String> getMessageID() { return messageIDs; }

    public int validateMenuOption(int max, Scanner sc) {
        int opt = -1;
        while (opt < 1 || opt > max) {
            try { opt = Integer.parseInt(sc.nextLine().trim()); }
            catch (Exception e) { System.out.println("Enter 1-" + max); }
        }
        return opt;
    }

    // ---- Part 3 menu a to f ----
    public void showStoredMessagesMenu(Scanner sc) {
        while (true) {
            System.out.println("\n--- STORED MESSAGES MENU ---");
            System.out.println("1) Display sender and recipient of all stored");
            System.out.println("2) Display longest stored message");
            System.out.println("3) Search by Message ID");
            System.out.println("4) Search by Recipient");
            System.out.println("5) Delete by Hash");
            System.out.println("6) Display Report");
            System.out.println("7) Back");
            System.out.print("Choose: ");
            int c = validateMenuOption(7, sc);
            if (c == 7) break;
            switch (c) {
                case 1 -> {
                    for (String[] m : all) if (m[4].equalsIgnoreCase("Stored"))
                        System.out.println("Sender: Developer | Recipient: " + m[2] + " | Message: " + m[3]);
                }
                case 2 -> {
                    String longest = "";
                    for (String[] m : all) if (m[4].equalsIgnoreCase("Stored") && m[3].length() > longest.length()) longest = m[3];
                    System.out.println("Longest: " + longest);
                }
                case 3 -> {
                    System.out.print("Enter Message ID: "); String id = sc.nextLine();
                    boolean found = false;
                    for (String[] m : all) if (m[0].equals(id)) { System.out.println("Recipient: " + m[2] + " | Message: " + m[3]); found = true; }
                    if (!found) System.out.println("Message ID not found.");
                }
                case 4 -> {
                    System.out.print("Enter Recipient: "); String r = sc.nextLine();
                    for (String[] m : all) if ((m[4].equalsIgnoreCase("Sent") || m[4].equalsIgnoreCase("Stored")) && m[2].equals(r))
                        System.out.println(m[3]);
                }
                case 5 -> {
                    System.out.print("Enter Message Hash: "); String h = sc.nextLine();
                    System.out.println(deleteByHash(h));
                }
                case 6 -> {
                    System.out.println("--- STORED MESSAGES REPORT ---");
                    for (String[] m : all) if (m[4].equalsIgnoreCase("Stored"))
                        System.out.println("Message Hash: " + m[1] + "\nRecipient: " + m[2] + "\nMessage: " + m[3] + "\n");
                }
            }
            try { saveStoredToJson(); } catch (Exception e) {}
        }
    }

    public String deleteByHash(String hash) {
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i)[1].equals(hash)) {
                String msg = all.get(i)[3];
                String flag = all.get(i)[4];
                messageIDs.remove(all.get(i)[0]); messageHashes.remove(hash);
                if (flag.equalsIgnoreCase("Sent")) sentMessages.remove(msg);
                else if (flag.equalsIgnoreCase("Stored")) storedMessages.remove(msg);
                else disregardedMessages.remove(msg);
                all.remove(i);
                return "Message: \"" + msg + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    // ---- JSON plain Java, no library ----
    public void saveStoredToJson() throws IOException {
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < all.size(); i++) {
            String[] m = all.get(i);
            if (!m[4].equalsIgnoreCase("Stored")) continue;
            sb.append("  {\"messageID\":\"").append(m[0]).append("\",\"messageHash\":\"").append(m[1])
              .append("\",\"recipient\":\"").append(m[2]).append("\",\"message\":\"").append(m[3].replace("\"","\\\""))
              .append("\",\"flag\":\"").append(m[4]).append("\"},\n");
        }
        sb.append("]");
        Files.writeString(Paths.get(JSON_FILE), sb.toString());
    }

    // Methods your tests will call - keep names simple
    public String longestMessage() {
        String longest = "";
        for (String[] m : all) if (m[4].equalsIgnoreCase("Stored") && m[3].length() > longest.length()) longest = m[3];
        return longest;
    }
    public String searchByID(String id) {
        for (String[] m : all) if (m[0].equals(id)) return m[3];
        return "Message ID not found.";
    }

    
}
