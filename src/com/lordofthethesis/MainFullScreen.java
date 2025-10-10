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
            // INTRO EPICA - LA FORGIATURA DELLE TESI
            String intro1 = 
                "═══════════════════════════════════════════════════════════\n" +
                "    🌋 IL SIGNORE DEGLI ANELLI 🌋\n" +
                "         LA COMPAGNIA DELLA TESI\n" +
                "═══════════════════════════════════════════════════════════\n\n" +
                "La storia è narrata da Galadriel...\n\n" +
                "\"Il mondo è cambiato. Lo sento nell'acqua.\n" +
                "Lo sento nella terra. Lo odoro nell'aria.\n" +
                "Molto di ciò che era è andato perduto,\n" +
                "poiché ora non vive più nessuno che lo ricordi.\"\n\n" +
                "\"Tutto ebbe inizio con la forgiatura delle Grandi Tesi.\n\n" +
                "Tre furono donate agli Elfi, immortali, saggi e giusti.\n" +
                "Sette ai Signori dei Nani, scavatori nelle montagne.\n" +
                "E nove, nove tesi furono donate agli Uomini,\n" +
                "che sopra ogni cosa desiderano il potere.\"\n\n";
            
            JOptionPane.showMessageDialog(null, intro1, 
                "Prologo - Parte I", JOptionPane.PLAIN_MESSAGE);
            
            // INTRO PARTE 2 - SAURON E LA TESI UNICA
            String intro2 = 
                "═══════════════════════════════════════════════════════════\n" +
                "         💍 LA TESI UNICA 💍\n" +
                "═══════════════════════════════════════════════════════════\n\n" +
                "\"Ma tutti furono ingannati, perché fu creata un'altra Tesi.\n\n" +
                "Nella terra di Mordor, nelle fiamme del Monte Fato,\n" +
                "il Signore Oscuro Sauron forgiò in segreto\n" +
                "una Tesi Suprema per controllarle tutte.\n\n" +
                "E in questa Tesi versò la sua crudeltà,\n" +
                "la sua malvagità e la sua volontà\n" +
                "di dominare ogni forma di vita.\n\n" +
                "💍 UNA TESI PER DOMINARLE TUTTE 💍\n" +
                "   UNA TESI PER TROVARLE\n" +
                "   UNA TESI PER GHERMIRLE\n" +
                "   E NEL BUIO INCATENARLE\n\n" +
                "Questa Tesi ha il potere di dare la laurea con lode...\n" +
                "Ma può anche corrompere chi la possiede.\n\n" +
                "Deve essere distrutta. A Mordor, dove fu forgiata.\"";
            
            JOptionPane.showMessageDialog(null, intro2,
                "Prologo - Parte II", JOptionPane.PLAIN_MESSAGE);
            
            // INTRO PARTE 3 - LA CONTEA E BILBO
            String intro3 = 
                "═══════════════════════════════════════════════════════════\n" +
                "         🏡 LA CONTEA 🏡\n" +
                "═══════════════════════════════════════════════════════════\n\n" +
                "60 anni dopo...\n\n" +
                "Nella tranquilla Contea, tra verdi colline e case hobbit,\n" +
                "vive Bilbo Baggins.\n\n" +
                "Oggi compie 111 anni - un'età straordinaria!\n\n" +
                "Alla sua festa di compleanno, farà un annuncio importante...\n" +
                "E ti lascerà un'eredità che cambierà la tua vita.\n\n" +
                "La TESI UNICA è nelle tue mani ora.\n\n" +
                "Il tuo viaggio attraverso la Terra di Mezzo sta per iniziare.";
            
            JOptionPane.showMessageDialog(null, intro3,
                "La Contea - Inizio dell'Avventura", JOptionPane.PLAIN_MESSAGE);
            
            // Chiedi nome
            String playerName = JOptionPane.showInputDialog(
                null,
                "Come ti chiami, giovane Hobbit?\n\n" +
                "(Sei cugino di Bilbo e suo erede)",
                "Il Signore degli Anelli",
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
