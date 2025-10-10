package com.lordofthethesis.model;

/**
 * Rappresenta un oggetto che può essere raccolto e usato
 */
public class Item {
    private String name;
    private String description;
    private boolean canTake;
    private boolean isKeyItem; // oggetti importanti per la trama
    
    public Item(String name, String description, boolean canTake) {
        this.name = name;
        this.description = description;
        this.canTake = canTake;
        this.isKeyItem = false;
    }
    
    public Item(String name, String description, boolean canTake, boolean isKeyItem) {
        this.name = name;
        this.description = description;
        this.canTake = canTake;
        this.isKeyItem = isKeyItem;
    }
    
    // Getters e Setters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public boolean canTake() { return canTake; }
    public boolean isKeyItem() { return isKeyItem; }
    public void setKeyItem(boolean keyItem) { isKeyItem = keyItem; }
    
    @Override
    public String toString() {
        return name;
    }
}
