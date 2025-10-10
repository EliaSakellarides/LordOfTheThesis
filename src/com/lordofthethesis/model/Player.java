package com.lordofthethesis.model;

import java.util.*;

/**
 * Rappresenta il giocatore
 */
public class Player {
    private String name;
    private Room currentRoom;
    private List<Item> inventory;
    private int maxInventorySize;
    private int score;
    
    public Player(String name) {
        this.name = name;
        this.inventory = new ArrayList<>();
        this.maxInventorySize = 10;
        this.score = 0;
    }
    
    public boolean addItem(Item item) {
        if (inventory.size() >= maxInventorySize) {
            return false;
        }
        inventory.add(item);
        return true;
    }
    
    public Item removeItem(String itemName) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equalsIgnoreCase(itemName)) {
                return inventory.remove(i);
            }
        }
        return null;
    }
    
    public Item getItem(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }
    
    public boolean hasItem(String itemName) {
        return getItem(itemName) != null;
    }
    
    public String getInventoryString() {
        if (inventory.isEmpty()) {
            return "Il tuo zaino è vuoto.";
        }
        
        StringBuilder sb = new StringBuilder("Nel tuo zaino hai:\n");
        for (Item item : inventory) {
            sb.append("  - ").append(item.getName()).append(": ").append(item.getDescription()).append("\n");
        }
        return sb.toString();
    }
    
    public void addScore(int points) {
        score += points;
    }
    
    // Getters e Setters
    public String getName() { return name; }
    public Room getCurrentRoom() { return currentRoom; }
    public void setCurrentRoom(Room room) { 
        this.currentRoom = room;
        if (room != null) {
            room.setVisited(true);
        }
    }
    public List<Item> getInventory() { return inventory; }
    public int getScore() { return score; }
}
