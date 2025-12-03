package com.lordofthethesis.model;

/**
 * Rappresenta un oggetto che può essere raccolto e usato
 */
public class Item {
    private String name;
    private String description;
    private boolean canTake;
    private boolean isKeyItem; // oggetti importanti per la trama
    
    // Tipo di oggetto LOTR
    private ItemType type;
    
    // Effetti dell'oggetto
    private int energyBoost;      // +energia (Lembas)
    private int corruptionCost;   // corruzione da uso (Anello)
    private boolean grantsInvisibility; // invisibilità (Anello, Mantello)
    private boolean grantsLight;   // illuminazione (Fiala)
    private int defenseBoost;      // difesa (Pungolo)
    private boolean isUsable;      // può essere usato
    private int usesRemaining;     // usi rimanenti (-1 = infiniti)
    
    public enum ItemType {
        ANELLO,     // 💍 Anello Unico
        CIBO,       // 🍞 Lembas
        VESTITO,    // 🧥 Mantello Elfico
        LUCE,       // 💡 Fiala di Galadriel
        ARMA,       // ⚔️ Pungolo
        ALTRO       // Oggetti vari
    }
    
    public Item(String name, String description, boolean canTake) {
        this.name = name;
        this.description = description;
        this.canTake = canTake;
        this.isKeyItem = false;
        this.type = ItemType.ALTRO;
        this.energyBoost = 0;
        this.corruptionCost = 0;
        this.grantsInvisibility = false;
        this.grantsLight = false;
        this.defenseBoost = 0;
        this.isUsable = false;
        this.usesRemaining = -1;
    }
    
    public Item(String name, String description, boolean canTake, boolean isKeyItem) {
        this(name, description, canTake);
        this.isKeyItem = isKeyItem;
    }
    
    // Costruttore completo per oggetti speciali LOTR
    public Item(String name, String description, boolean canTake, ItemType type,
                int energyBoost, int corruptionCost, boolean grantsInvisibility,
                boolean grantsLight, int defenseBoost, int usesRemaining) {
        this.name = name;
        this.description = description;
        this.canTake = canTake;
        this.isKeyItem = true; // Oggetti speciali sono sempre key items
        this.type = type;
        this.energyBoost = energyBoost;
        this.corruptionCost = corruptionCost;
        this.grantsInvisibility = grantsInvisibility;
        this.grantsLight = grantsLight;
        this.defenseBoost = defenseBoost;
        this.isUsable = (energyBoost > 0 || grantsInvisibility || grantsLight || defenseBoost > 0);
        this.usesRemaining = usesRemaining;
    }
    
    // Metodi per uso oggetto
    public boolean use() {
        if (!isUsable) return false;
        if (usesRemaining == 0) return false;
        if (usesRemaining > 0) usesRemaining--;
        return true;
    }
    
    public String getUsageMessage() {
        String icon = getIcon();
        switch (type) {
            case CIBO:
                return icon + " Mangi il " + name + "! Ti senti rinvigorito! (+" + energyBoost + " energia)";
            case VESTITO:
                return icon + " Indossi il " + name + "! Diventi invisibile senza corruzione!";
            case LUCE:
                return icon + " Alzi la " + name + "! La luce scaccia le tenebre!";
            case ARMA:
                return icon + " Impugni " + name + "! Ti senti più sicuro! (+" + defenseBoost + " difesa)";
            case ANELLO:
                return icon + " Indossi l'" + name + "... diventi invisibile ma senti l'Occhio su di te! (+" + corruptionCost + " corruzione)";
            default:
                return "Usi " + name + ".";
        }
    }
    
    public String getIcon() {
        switch (type) {
            case ANELLO: return "💍";
            case CIBO: return "🍞";
            case VESTITO: return "🧥";
            case LUCE: return "💡";
            case ARMA: return "⚔️";
            default: return "📦";
        }
    }
    
    // Getters e Setters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public boolean canTake() { return canTake; }
    public boolean isKeyItem() { return isKeyItem; }
    public void setKeyItem(boolean keyItem) { isKeyItem = keyItem; }
    public ItemType getType() { return type; }
    public int getEnergyBoost() { return energyBoost; }
    public int getCorruptionCost() { return corruptionCost; }
    public boolean grantsInvisibility() { return grantsInvisibility; }
    public boolean grantsLight() { return grantsLight; }
    public int getDefenseBoost() { return defenseBoost; }
    public boolean isUsable() { return isUsable; }
    public int getUsesRemaining() { return usesRemaining; }
    
    @Override
    public String toString() {
        String result = getIcon() + " " + name;
        if (usesRemaining > 0) {
            result += " (" + usesRemaining + " usi)";
        }
        return result;
    }
}
