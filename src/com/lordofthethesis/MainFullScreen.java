package com.lordofthethesis;

import com.lordofthethesis.gui.FullScreenGUI;
import javax.swing.*;

/**
 * Launcher per versione full-screen
 * Avventura grafica con tutto integrato nell'immagine
 */
public class MainFullScreen {
    public static void main(String[] args) {
        // Set look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Usa default
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
