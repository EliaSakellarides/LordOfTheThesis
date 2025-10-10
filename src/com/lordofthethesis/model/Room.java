package com.lordofthethesis.model;

import java.util.*;

/**
 * Rappresenta una stanza/locazione nel gioco
 */
public class Room {
    private String name;
    private String description;
    private Map<String, Room> exits; // direzione -> stanza
    private List<Item> items;
    private List<GameCharacter> characters;
    private boolean visited;
    
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
        this.characters = new ArrayList<>();
        this.visited = false;
    }
    
    public void addExit(String direction, Room room) {
        exits.put(direction.toLowerCase(), room);
    }
    
    public Room getExit(String direction) {
        return exits.get(direction.toLowerCase());
    }
    
    public void addItem(Item item) {
        items.add(item);
    }
    
    public Item removeItem(String itemName) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(itemName)) {
                return items.remove(i);
            }
        }
        return null;
    }
    
    public void addCharacter(GameCharacter character) {
        characters.add(character);
    }
    
    public GameCharacter getCharacter(String name) {
        for (GameCharacter c : characters) {
            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }
    
    public String getFullDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ").append(name).append(" ===\n\n");
        sb.append(description).append("\n\n");
        
        if (!items.isEmpty()) {
            sb.append("Vedi qui: ");
            for (int i = 0; i < items.size(); i++) {
                sb.append(items.get(i).getName());
                if (i < items.size() - 1) sb.append(", ");
            }
            sb.append("\n\n");
        }
        
        if (!characters.isEmpty()) {
            sb.append("Personaggi presenti: ");
            for (int i = 0; i < characters.size(); i++) {
                sb.append(characters.get(i).getName());
                if (i < characters.size() - 1) sb.append(", ");
            }
            sb.append("\n\n");
        }
        
        sb.append("Uscite: ");
        if (exits.isEmpty()) {
            sb.append("nessuna");
        } else {
            String[] dirs = exits.keySet().toArray(new String[0]);
            for (int i = 0; i < dirs.length; i++) {
                sb.append(dirs[i]);
                if (i < dirs.length - 1) sb.append(", ");
            }
        }
        
        return sb.toString();
    }
    
    // Getters e Setters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public Map<String, Room> getExits() { return exits; }
    public List<Item> getItems() { return items; }
    public List<GameCharacter> getCharacters() { return characters; }
    public boolean isVisited() { return visited; }
    public void setVisited(boolean visited) { this.visited = visited; }
}
