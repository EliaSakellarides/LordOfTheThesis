package com.lordofthethesis;

import com.lordofthethesis.gui.FullScreenGUI;
import javax.swing.*;

/**
 * Lord of the Thesis - Avventura Grafica LOTR
 * 
 * Gioco testuale ispirato al Signore degli Anelli
 * ambientato nel mondo universitario.
 * 
 * @author Elia Sakellarides
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        // Imposta look and feel del sistema
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Usa quello di default
        }
        
        SwingUtilities.invokeLater(() -> {
            // Chiedi il nome al giocatore
            String playerName = JOptionPane.showInputDialog(null,
                "🌋 IL SIGNORE DEGLI ANELLI - LA COMPAGNIA DELLA TESI 🌋\n\n" +
                "Qual è il tuo nome, Portatore della Tesi?",
                "Lord of the Thesis",
                JOptionPane.QUESTION_MESSAGE);
            
            if (playerName == null || playerName.trim().isEmpty()) {
                playerName = "Frodo";
            }
            
            // Avvia il gioco
            FullScreenGUI gui = new FullScreenGUI();
            gui.startGame(playerName);
        });
    }
}
