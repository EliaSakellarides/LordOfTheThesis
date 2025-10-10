package com.lordofthethesis.model;

import java.util.*;

/**
 * Rappresenta un personaggio nel gioco (NPC)
 */
public class GameCharacter {
    private String name;
    private String description;
    private List<String> dialogues;
    private int currentDialogueIndex;
    private Map<String, String> questDialogues; // condizione -> dialogo
    
    public GameCharacter(String name, String description) {
        this.name = name;
        this.description = description;
        this.dialogues = new ArrayList<>();
        this.currentDialogueIndex = 0;
        this.questDialogues = new HashMap<>();
    }
    
    public void addDialogue(String dialogue) {
        dialogues.add(dialogue);
    }
    
    public void addQuestDialogue(String condition, String dialogue) {
        questDialogues.put(condition, dialogue);
    }
    
    public String speak() {
        if (dialogues.isEmpty()) {
            return name + ": ...";
        }
        
        String dialogue = dialogues.get(currentDialogueIndex);
        currentDialogueIndex = (currentDialogueIndex + 1) % dialogues.size();
        return name + ": \"" + dialogue + "\"";
    }
    
    public String getQuestDialogue(String condition) {
        String dialogue = questDialogues.get(condition);
        if (dialogue != null) {
            return name + ": \"" + dialogue + "\"";
        }
        return null;
    }
    
    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
}
