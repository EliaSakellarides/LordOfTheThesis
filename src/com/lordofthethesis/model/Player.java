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
    private int corruptionLevel; // Livello di corruzione per l'uso dell'Anello
    private int energy;           // Energia del giocatore
    private int maxEnergy;
    private int defense;          // Livello di difesa
    
    public Player(String name) {
        this.name = name;
        this.inventory = new ArrayList<>();
        this.maxInventorySize = 10;
        this.score = 0;
        this.corruptionLevel = 0;
        this.energy = 100;
        this.maxEnergy = 100;
        this.defense = 0;
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
    
    public void addCorruption(int points) {
        corruptionLevel += points;
    }
    
    public String getCorruptionStatus() {
        if (corruptionLevel == 0) return "🟢 Puro";
        if (corruptionLevel <= 2) return "🟡 Leggermente corrotto";
        if (corruptionLevel <= 4) return "🟠 Corrotto";
        return "🔴 Molto corrotto";
    }
    
    // Metodi per usare oggetti
    public String useItem(String itemName) {
        Item item = getItem(itemName);
        if (item == null) {
            return "❌ Non hai questo oggetto nell'inventario!";
        }
        
        if (!item.isUsable()) {
            return "❌ Non puoi usare " + item.getName() + "!";
        }
        
        if (item.getUsesRemaining() == 0) {
            return "❌ " + item.getName() + " è esaurito!";
        }
        
        // Usa l'oggetto
        if (!item.use()) {
            return "❌ Non puoi usare " + item.getName() + " in questo momento!";
        }
        
        // Applica gli effetti
        String result = item.getUsageMessage() + "\n\n";
        
        if (item.getEnergyBoost() > 0) {
            addEnergy(item.getEnergyBoost());
            result += "⚡ Energia: " + energy + "/" + maxEnergy + "\n";
        }
        
        if (item.getCorruptionCost() > 0) {
            addCorruption(item.getCorruptionCost());
            result += "💀 Corruzione: +" + item.getCorruptionCost() + " - " + getCorruptionStatus() + "\n";
        }
        
        if (item.getDefenseBoost() > 0) {
            addDefense(item.getDefenseBoost());
            result += "🛡️ Difesa: +" + item.getDefenseBoost() + " (totale: " + defense + ")\n";
        }
        
        // Rimuovi se esaurito
        if (item.getUsesRemaining() == 0) {
            inventory.remove(item);
            result += "\n" + item.getIcon() + " " + item.getName() + " è stato consumato.";
        }
        
        return result;
    }
    
    public void addEnergy(int amount) {
        energy = Math.min(energy + amount, maxEnergy);
    }
    
    public void addDefense(int amount) {
        defense += amount;
    }
    
    public String getStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════\n");
        sb.append("📊 STATO DEL PERSONAGGIO\n");
        sb.append("═══════════════════════════════════════\n");
        sb.append("👤 Nome: ").append(name).append("\n");
        sb.append("⚡ Energia: ").append(energy).append("/").append(maxEnergy).append("\n");
        sb.append("🛡️ Difesa: ").append(defense).append("\n");
        sb.append("💀 Corruzione: ").append(corruptionLevel).append(" - ").append(getCorruptionStatus()).append("\n");
        sb.append("🏆 Punteggio: ").append(score).append("\n");
        sb.append("🎒 Oggetti: ").append(inventory.size()).append("/").append(maxInventorySize).append("\n");
        sb.append("═══════════════════════════════════════");
        return sb.toString();
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
    public int getCorruptionLevel() { return corruptionLevel; }
    public int getEnergy() { return energy; }
    public int getMaxEnergy() { return maxEnergy; }
    public int getDefense() { return defense; }
    public void setDefense(int defense) { this.defense = defense; }
}
