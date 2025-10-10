package it.lordofthethesis;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;

    public Inventory() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
        System.out.println("Item aggiunto all'inventario: " + item.getName());
    }
    
    public void listItems() {
        if (items.isEmpty()) {
            System.out.println("L'inventario è vuoto.");
        } else {
            System.out.println("Inventario:");
            for (Item item : items) {
                System.out.println("- " + item.getName() + ": " + item.getDescription());
            }
        }
    }
    
    public List<Item> getItems() {
        return items;
    }
}
