package com.lordofthethesis;

import com.lordofthethesis.gui.FullScreenGUI;
import javax.swing.*;

/**
 * Lord of the Thesis - An Academic Adventure
 * 
 * A narrative text adventure game inspired by The Lord of the Rings,
 * where you embark on an epic journey to complete your thesis.
 * 
 * Features:
 * - 10 story chapters with puzzles
 * - Custom pixel art images
 * - chiptune soundtrack
 * - Cinematic intro sequence
 * 
 * @author Elia Sakellarides
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        // Set native look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Use default if native fails
        }
        
        SwingUtilities.invokeLater(() -> {
            // Get player name
            String playerName = JOptionPane.showInputDialog(null,
                "🌋 IL SIGNORE DEGLI ANELLI - LA COMPAGNIA DELLA TESI 🌋\n\n" +
                "Qual è il tuo nome, Portatore della Tesi?",
                "Lord of the Thesis",
                JOptionPane.QUESTION_MESSAGE);
            
            if (playerName == null || playerName.trim().isEmpty()) {
                playerName = "Frodo";
            }
            
            // Launch game with cinematic intro
            FullScreenGUI gui = new FullScreenGUI();
            gui.startGame(playerName);
        });
    }
}
