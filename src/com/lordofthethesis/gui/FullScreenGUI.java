package com.lordofthethesis.gui;

import com.lordofthethesis.engine.GameEngine;
import com.lordofthethesis.graphics.FullScreenRenderer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Full-screen graphical interface for the adventure game
 * Integrates images, dialogue, inventory and commands in a classic adventure style
 * 
 * @author Lord of the Thesis Development Team
 */
public class FullScreenGUI extends JFrame {
    private GameEngine engine;
    private FullScreenRenderer renderer;
    private JTextField commandField;
    private JButton submitButton;
    private JPanel commandPanel;
    
    public FullScreenGUI() {
        engine = new GameEngine();
        initializeGUI();
    }
    
    private void initializeGUI() {
        setTitle("Lord of the Thesis");
        setSize(800, 650); // 600 per renderer + 50 per comandi
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));
        setResizable(false);
        
        // Renderer full-screen (occupa quasi tutto)
        renderer = new FullScreenRenderer();
        add(renderer, BorderLayout.CENTER);
        
        // Panel comandi (minimalista in basso)
        createCommandPanel();
        add(commandPanel, BorderLayout.SOUTH);
        
        // Setup listeners
        setupListeners();
        
        // Centra finestra
        setLocationRelativeTo(null);
    }
    
    private void createCommandPanel() {
        commandPanel = new JPanel(new BorderLayout(5, 5));
        commandPanel.setBackground(new Color(20, 20, 30));
        commandPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        
        // Label prompt
        JLabel promptLabel = new JLabel(">");
        promptLabel.setForeground(new Color(200, 180, 100));
        promptLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        commandPanel.add(promptLabel, BorderLayout.WEST);
        
        // Campo comando
        commandField = new JTextField();
        commandField.setBackground(new Color(40, 40, 50));
        commandField.setForeground(new Color(240, 240, 200));
        commandField.setCaretColor(new Color(200, 180, 100));
        commandField.setFont(new Font("Monospaced", Font.PLAIN, 14));
        commandField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 180, 100), 2),
            BorderFactory.createEmptyBorder(3, 8, 3, 8)
        ));
        commandPanel.add(commandField, BorderLayout.CENTER);
        
        // Bottone submit (piccolo)
        submitButton = new JButton("↵");
        submitButton.setBackground(new Color(60, 60, 80));
        submitButton.setForeground(new Color(200, 180, 100));
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        submitButton.setFocusPainted(false);
        submitButton.setBorder(BorderFactory.createLineBorder(new Color(200, 180, 100), 2));
        submitButton.setPreferredSize(new Dimension(50, 30));
        commandPanel.add(submitButton, BorderLayout.EAST);
    }
    
    private void setupListeners() {
        // Comando Enter
        commandField.addActionListener(e -> processCommand());
        submitButton.addActionListener(e -> processCommand());
        
        // Pulsanti rapidi con tastiera
        commandField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_F1:
                        engine.volumeDown(); // F1 = Volume giù
                        break;
                    case KeyEvent.VK_F2:
                        engine.volumeUp(); // F2 = Volume su
                        break;
                    case KeyEvent.VK_F3:
                        engine.toggleMute(); // F3 = Mute/Unmute
                        break;
                    case KeyEvent.VK_F5:
                        processCommand("guarda");
                        break;
                    case KeyEvent.VK_F12:
                        showHelp();
                        break;
                }
            }
        });
    }
    
    private void processCommand() {
        String command = commandField.getText().trim();
        if (!command.isEmpty()) {
            processCommand(command);
            commandField.setText("");
        }
    }
    
    private void processCommand(String command) {
        String result = engine.processCommand(command);
        
        // Aggiorna il testo narrativo nel renderer
        renderer.setNarrativeText(result);
        
        // Aggiorna stanza corrente
        String currentRoom = engine.getCurrentRoomKey();
        renderer.setRoom(currentRoom);
        
        // Aggiorna inventario
        java.util.List<String> items = new ArrayList<>();
        for (com.lordofthethesis.model.Item item : engine.getPlayer().getInventory()) {
            items.add(item.getName());
        }
        renderer.setInventory(items);
        
        // Focus su campo comando
        commandField.requestFocusInWindow();
    }
    
    private void showHelp() {
        String help = "═══════════════════════════════════════════════\n" +
                     "           🎮 COMANDI RAPIDI 🎮\n" +
                     "═══════════════════════════════════════════════\n\n" +
                     "🔉 F1 - Volume Giù\n" +
                     "🔊 F2 - Volume Su\n" +
                     "🔇 F3 - Mute/Unmute\n" +
                     "👁️  F5 - Guarda\n" +
                     "❓ F12 - Aiuto\n\n" +
                     "═══════════════════════════════════════════════\n" +
                     "         📖 COMANDI NARRATIVA LOTR 📖\n" +
                     "═══════════════════════════════════════════════\n\n" +
                     "📖 avanti - Inizia prossimo capitolo\n" +
                     "✍️  rispondi [risposta] - Rispondere enigma\n" +
                     "🎒 inventario - Vedere inventario\n" +
                     "📍 dove/stato - Progresso storia\n" +
                     "❓ aiuto - Mostra aiuto\n" +
                     "🚪 esci - Uscire dal gioco\n\n" +
                     "═══════════════════════════════════════════════\n" +
                     "   🎵 Le musiche cambiano ad ogni capitolo! 🎵\n" +
                     "═══════════════════════════════════════════════";
        
        renderer.setNarrativeText(help);
    }
    
    public void startGame(String playerName) {
        engine.initializeGame(playerName);
        
        // In modalità narrativa, avvia l'INTRO CINEMATICA con immagine di Sauron
        if (engine.isNarrativeMode()) {
            // Attiva intro cinematica e imposta immagine Sauron
            engine.startCinematicIntro();
            
            // Mostra il primo testo dell'intro
            String result = engine.processCommand("avanti");
            renderer.setNarrativeText(result);
            renderer.setRoom(engine.getCurrentRoomKey());
        } else {
            // Modalità classica: mostra solo messaggio di benvenuto
            String welcome = engine.getLastLog();
            renderer.setNarrativeText(welcome);
            renderer.setRoom(engine.getCurrentRoomKey());
        }
        
        // Inventario vuoto iniziale
        renderer.setInventory(new ArrayList<>());
        
        // Focus su comando
        commandField.requestFocusInWindow();
        
        // Mostra finestra
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Chiedi nome giocatore
            String playerName = JOptionPane.showInputDialog(
                null,
                "Inserisci il tuo nome, giovane avventuriero:",
                "Lord of the Thesis",
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (playerName == null || playerName.trim().isEmpty()) {
                playerName = "Frodo";
            }
            
            FullScreenGUI gui = new FullScreenGUI();
            gui.startGame(playerName.trim());
        });
    }
}
