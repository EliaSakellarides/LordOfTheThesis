package com.lordofthethesis;

import com.lordofthethesis.gui.FullScreenGUI;
import javax.swing.*;

/**
 * Versione full-screen del gioco
 * Interfaccia immersiva con grafica e testo integrati
 */
public class MainFullScreen {
    public static void main(String[] args) {
        // Imposta look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Usa il default
        }
        
        SwingUtilities.invokeLater(() -> {
            // Chiedi solo il nome del giocatore - l'intro cinematica verrà mostrata nel gioco
            String playerName = JOptionPane.showInputDialog(
                null,
                "🧙‍♂️ Benvenuto nella Terra di Mezzo! 🧙‍♂️\n\n" +
                "Come ti chiami, giovane Hobbit?\n\n" +
                "(Sei cugino di Bilbo e suo erede)",
                "🌋 Il Signore degli Anelli 🌋",
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (playerName == null || playerName.trim().isEmpty()) {
                playerName = "Frodo";
            }
            
            // Avvia GUI full-screen
            FullScreenGUI gui = new FullScreenGUI();
            gui.startGame(playerName.trim());
        });
    }
}
