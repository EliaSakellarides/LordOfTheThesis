package com.lordofthethesis.gui;

import com.lordofthethesis.engine.GameEngine;
import com.lordofthethesis.graphics.PixelArtManager;
import com.lordofthethesis.graphics.IsometricRenderer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Interfaccia grafica principale del gioco
 */
public class GameGUI extends JFrame {
    private GameEngine engine;
    private JTextArea gameTextArea;
    private JTextField commandField;
    private JButton submitButton;
    private JTextArea inventoryArea;
    private JLabel statusLabel;
    private IsometricRenderer isoRenderer;
    
    public GameGUI() {
        engine = new GameEngine();
        initializeGUI();
    }
    
    private void initializeGUI() {
        setTitle("Lord of the Thesis - Un'Avventura Accademica");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Colori tema "fantasy-accademico"
        Color bgColor = new Color(45, 45, 48);
        Color textColor = new Color(220, 220, 220);
        Color accentColor = new Color(0, 122, 204);
        
        // Panel superiore con titolo
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(30, 30, 30));
        JLabel titleLabel = new JLabel("⚔ LORD OF THE THESIS ⚔");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setForeground(new Color(255, 215, 0));
        titlePanel.add(titleLabel);
        add(titlePanel, BorderLayout.NORTH);
        
        // Panel centrale con area di gioco
        JPanel centerPanel = new JPanel(new BorderLayout(5, 5));
        centerPanel.setBackground(bgColor);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel sinistro - Grafica Isometrica FFT Style
        JPanel leftPanel = new JPanel(new BorderLayout(5, 5));
        leftPanel.setBackground(bgColor);
        leftPanel.setPreferredSize(new Dimension(450, 400));
        
        JLabel artLabel = new JLabel("🎨 SCENA ISOMETRICA");
        artLabel.setForeground(new Color(255, 215, 0));
        artLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        artLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        leftPanel.add(artLabel, BorderLayout.NORTH);
        
        // Pannello per immagine piccola di sfondo (300x225)
        isoRenderer = new IsometricRenderer();
        isoRenderer.setPreferredSize(new Dimension(300, 225));
        isoRenderer.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0), 2));
        leftPanel.add(isoRenderer, BorderLayout.CENTER);
        
        // Ridotto da 450 a 320 per dare più spazio al testo
        leftPanel.setPreferredSize(new Dimension(320, 400));
        centerPanel.add(leftPanel, BorderLayout.WEST);
        
        // Area di testo principale (centro)
        JPanel textPanel = new JPanel(new BorderLayout(5, 5));
        textPanel.setBackground(bgColor);
        
        gameTextArea = new JTextArea();
        gameTextArea.setEditable(false);
        gameTextArea.setLineWrap(true);
        gameTextArea.setWrapStyleWord(true);
        gameTextArea.setBackground(new Color(30, 30, 30));
        gameTextArea.setForeground(textColor);
        gameTextArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        gameTextArea.setMargin(new Insets(10, 10, 10, 10));
        
        JScrollPane scrollPane = new JScrollPane(gameTextArea);
        textPanel.add(scrollPane, BorderLayout.CENTER);
        
        centerPanel.add(textPanel, BorderLayout.CENTER);
        
        // Panel per inventario e info
        JPanel rightPanel = new JPanel(new BorderLayout(5, 5));
        rightPanel.setBackground(bgColor);
        rightPanel.setPreferredSize(new Dimension(250, 400));
        
        JLabel invLabel = new JLabel("📦 INVENTARIO");
        invLabel.setForeground(new Color(255, 215, 0));
        invLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        invLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        rightPanel.add(invLabel, BorderLayout.NORTH);
        
        inventoryArea = new JTextArea();
        inventoryArea.setEditable(false);
        inventoryArea.setBackground(new Color(30, 30, 30));
        inventoryArea.setForeground(textColor);
        inventoryArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
        inventoryArea.setMargin(new Insets(5, 5, 5, 5));
        JScrollPane invScroll = new JScrollPane(inventoryArea);
        rightPanel.add(invScroll, BorderLayout.CENTER);
        
        centerPanel.add(rightPanel, BorderLayout.EAST);
        
        add(centerPanel, BorderLayout.CENTER);
        
        // Panel inferiore con comandi
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        bottomPanel.setBackground(bgColor);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        
        // Status bar
        statusLabel = new JLabel("Premi 'Inizia Gioco' per cominciare l'avventura!");
        statusLabel.setForeground(accentColor);
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        bottomPanel.add(statusLabel, BorderLayout.NORTH);
        
        // Panel input
        JPanel inputPanel = new JPanel(new BorderLayout(5, 0));
        inputPanel.setBackground(bgColor);
        
        JLabel promptLabel = new JLabel(">");
        promptLabel.setForeground(textColor);
        promptLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        inputPanel.add(promptLabel, BorderLayout.WEST);
        
        commandField = new JTextField();
        commandField.setBackground(new Color(60, 60, 60));
        commandField.setForeground(textColor);
        commandField.setCaretColor(textColor);
        commandField.setFont(new Font("Monospaced", Font.PLAIN, 13));
        commandField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(accentColor, 1),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        commandField.setEnabled(false);
        inputPanel.add(commandField, BorderLayout.CENTER);
        
        submitButton = new JButton("Invia");
        submitButton.setBackground(accentColor);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setEnabled(false);
        inputPanel.add(submitButton, BorderLayout.EAST);
        
        bottomPanel.add(inputPanel, BorderLayout.CENTER);
        
        // Bottoni di azione rapida
        JPanel quickActionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        quickActionsPanel.setBackground(bgColor);
        
        String[] quickCommands = {"nord", "sud", "est", "ovest", "guarda", "inventario", "esamina tesi", "aiuto"};
        for (String cmd : quickCommands) {
            JButton btn = createQuickButton(cmd);
            quickActionsPanel.add(btn);
        }
        // Bottone speciale per modalita' a livelli
        JButton levelsBtn = new JButton("Inizia Livelli");
        levelsBtn.setFont(new Font("SansSerif", Font.PLAIN, 10));
        levelsBtn.setFocusPainted(false);
        levelsBtn.setBackground(new Color(80, 50, 100));
        levelsBtn.setForeground(new Color(220, 220, 220));
        levelsBtn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 100, 100), 1),
            BorderFactory.createEmptyBorder(3, 8, 3, 8)
        ));
        levelsBtn.setEnabled(false);
        levelsBtn.addActionListener(e -> {
            // Invia il comando per iniziare livelli
            commandField.setText("inizia livelli");
            submitCommand();
        });
        quickActionsPanel.add(levelsBtn);
        
        bottomPanel.add(quickActionsPanel, BorderLayout.SOUTH);
        
        add(bottomPanel, BorderLayout.SOUTH);
        
        // Menu bar
        createMenuBar();
        
        // Event listeners
        commandField.addActionListener(e -> submitCommand());
        submitButton.addActionListener(e -> submitCommand());
        
        setLocationRelativeTo(null);
        
        // Mostra automaticamente il dialogo per iniziare il gioco
        SwingUtilities.invokeLater(() -> startNewGame());
    }
    
    private JButton createQuickButton(String command) {
        JButton btn = new JButton(command);
        btn.setFont(new Font("SansSerif", Font.PLAIN, 10));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(70, 70, 70));
        btn.setForeground(new Color(220, 220, 220));
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 100, 100), 1),
            BorderFactory.createEmptyBorder(3, 8, 3, 8)
        ));
        btn.setEnabled(false);
        btn.addActionListener(e -> {
            commandField.setText(command);
            submitCommand();
        });
        return btn;
    }
    
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(50, 50, 50));
        
        JMenu gameMenu = new JMenu("Gioco");
        gameMenu.setForeground(new Color(220, 220, 220));
        
        JMenuItem newGameItem = new JMenuItem("Nuovo Gioco");
        newGameItem.addActionListener(e -> startNewGame());
        
        JMenuItem exitItem = new JMenuItem("Esci");
        exitItem.addActionListener(e -> System.exit(0));
        
        gameMenu.add(newGameItem);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);
        
        JMenu helpMenu = new JMenu("Aiuto");
        helpMenu.setForeground(new Color(220, 220, 220));
        
        JMenuItem aboutItem = new JMenuItem("Informazioni");
        aboutItem.addActionListener(e -> showAbout());
        
        helpMenu.add(aboutItem);
        
        menuBar.add(gameMenu);
        menuBar.add(helpMenu);
        
        setJMenuBar(menuBar);
    }
    
    private void startNewGame() {
        String playerName = JOptionPane.showInputDialog(
            this,
            "Inserisci il nome del tuo personaggio:",
            "Lord of the Thesis",
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Studente Coraggioso";
        }
        
        engine.initializeGame(playerName);
        commandField.setEnabled(true);
        submitButton.setEnabled(true);
        
        // Abilita i bottoni rapidi
        for (Component comp : ((JPanel)((JPanel)getContentPane().getComponent(2)).getComponent(2)).getComponents()) {
            if (comp instanceof JButton) {
                comp.setEnabled(true);
            }
        }
        
        updateDisplay();
        statusLabel.setText("Punteggio: 0 | Giocatore: " + playerName);
        commandField.requestFocus();
    }
    
    private void submitCommand() {
        String command = commandField.getText().trim();
        if (command.isEmpty()) return;
        
        gameTextArea.append("\n> " + command + "\n");
        commandField.setText("");
        
        String result = engine.processCommand(command);
        
        if (!engine.isGameRunning()) {
            if (engine.isGameWon()) {
                gameTextArea.append("\n" + result + "\n");
                showGameOver(true);
            } else {
                gameTextArea.append("\n" + result + "\n");
                showGameOver(false);
            }
            commandField.setEnabled(false);
            submitButton.setEnabled(false);
        } else {
            updateDisplay();
            // se siamo entrati in modalità livelli, mostriamo la domanda corrente in evidenza
            if (engine.isLevelMode()) {
                com.lordofthethesis.engine.Level lvl = engine.getCurrentLevel();
                if (lvl != null) {
                    gameTextArea.append("\n=== MODALITA' A LIVELLI ===\n");
                    gameTextArea.append(lvl.getTitle() + "\n");
                    gameTextArea.append(lvl.getPrompt() + "\n");
                    gameTextArea.append("Usa: rispondi [tua risposta]\n\n");
                }
            }
        }
    }
    
    private void updateDisplay() {
        // Aggiorna l'area di testo principale
        StringBuilder sb = new StringBuilder();
        for (String log : engine.getGameLog()) {
            sb.append(log).append("\n\n");
        }
        gameTextArea.setText(sb.toString());
        gameTextArea.setCaretPosition(gameTextArea.getDocument().getLength());
        
        // Aggiorna inventario con icone
        if (engine.getPlayer() != null) {
            StringBuilder invSb = new StringBuilder();
            if (engine.getPlayer().getInventory().isEmpty()) {
                invSb.append("Il tuo zaino è vuoto.");
            } else {
                invSb.append("Nel tuo zaino hai:\n\n");
                for (com.lordofthethesis.model.Item item : engine.getPlayer().getInventory()) {
                    String icon = PixelArtManager.getItemIcon(item.getName());
                    invSb.append(icon).append(" ").append(item.getName()).append("\n");
                    invSb.append("   ").append(item.getDescription()).append("\n\n");
                }
            }
            inventoryArea.setText(invSb.toString());
            
            statusLabel.setText("Punteggio: " + engine.getPlayer().getScore() + 
                              " | Stanza: " + engine.getPlayer().getCurrentRoom().getName());
            
            // Aggiorna l'arte ASCII della stanza corrente
            updateRoomArt();
        }
    }
    
    private void updateRoomArt() {
        if (engine.getPlayer() != null && engine.getPlayer().getCurrentRoom() != null) {
            String roomName = engine.getPlayer().getCurrentRoom().getName();
            String roomKey = getRoomKey(roomName);
            isoRenderer.setRoom(roomKey);
        }
    }
    
    private String getRoomKey(String roomName) {
        if (roomName.contains("Contea") || roomName.contains("stanza")) return "contea";
        if (roomName.contains("Biblioteca")) return "biblioteca";
        if (roomName.contains("Corridoi")) return "corridoi";
        if (roomName.contains("Aula Magna")) return "aulamagna";
        if (roomName.contains("Laboratorio")) return "laboratorio";
        if (roomName.contains("Ufficio") || roomName.contains("Gandalf")) return "ufficio";
        if (roomName.contains("Mensa")) return "mensa";
        if (roomName.contains("Giardini")) return "giardini";
        if (roomName.contains("Segreteria") || roomName.contains("Dipartimento")) return "segreteria";
        return "default";
    }
    
    private void showGameOver(boolean won) {
        if (won) {
            // Mostra la scena della segreteria per la vittoria
            isoRenderer.setRoom("segreteria");
        }
        
        String title = won ? "🎓 VITTORIA!" : "Game Over";
        String message = won ? 
            "Congratulazioni! Hai completato Lord of the Thesis!\n\n" +
            "Punteggio finale: " + engine.getPlayer().getScore() + "\n\n" +
            "Vuoi giocare di nuovo?" :
            "Hai abbandonato la missione.\n\nVuoi provare di nuovo?";
        
        int choice = JOptionPane.showConfirmDialog(
            this,
            message,
            title,
            JOptionPane.YES_NO_OPTION
        );
        
        if (choice == JOptionPane.YES_OPTION) {
            startNewGame();
        }
    }
    
    private void showAbout() {
        JOptionPane.showMessageDialog(
            this,
            "LORD OF THE THESIS\n\n" +
            "Un'avventura testuale ispirata al Signore degli Anelli\n" +
            "ambientata nel mondo accademico.\n\n" +
            "La tua missione: consegnare la tesi al Dipartimento\n" +
            "di Informatica di Bari!\n\n" +
            "Sviluppato con Java e Swing\n" +
            "© 2025",
            "Informazioni",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
