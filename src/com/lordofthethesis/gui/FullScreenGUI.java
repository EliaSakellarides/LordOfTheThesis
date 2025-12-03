package com.lordofthethesis.gui;

import com.lordofthethesis.engine.GameEngine;
import com.lordofthethesis.graphics.FullScreenRenderer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Interfaccia grafica a schermo intero
 * Integra immagini, dialoghi, inventario e comandi
 * 
 * @author Elia Sakellarides
 */
public class FullScreenGUI extends JFrame {
    private GameEngine engine;
    private FullScreenRenderer renderer;
    private JTextField commandField;
    private JButton submitButton;
    private JPanel commandPanel;
    private JPanel choicePanel; // Pannello per scelte rapide
    private JButton[] choiceButtons; // Bottoni A, B, C
    
    public FullScreenGUI() {
        engine = new GameEngine();
        initializeGUI();
    }
    
    private void initializeGUI() {
        setTitle("Lord of the Thesis");
        setSize(1000, 800); // Aumentato: 1000x750 per renderer + 50 per comandi
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
        
        // Pannello superiore per bottoni scelta rapida
        choicePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        choicePanel.setBackground(new Color(20, 20, 30));
        createChoiceButtons();
        commandPanel.add(choicePanel, BorderLayout.NORTH);
        
        // Pannello inferiore per input testo
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.setBackground(new Color(20, 20, 30));
        
        // Label prompt
        JLabel promptLabel = new JLabel(">");
        promptLabel.setForeground(new Color(200, 180, 100));
        promptLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
        inputPanel.add(promptLabel, BorderLayout.WEST);
        
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
        inputPanel.add(commandField, BorderLayout.CENTER);
        
        // Bottone submit (piccolo)
        submitButton = new JButton("↵");
        submitButton.setBackground(new Color(60, 60, 80));
        submitButton.setForeground(new Color(200, 180, 100));
        submitButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        submitButton.setFocusPainted(false);
        submitButton.setBorder(BorderFactory.createLineBorder(new Color(200, 180, 100), 2));
        submitButton.setPreferredSize(new Dimension(50, 30));
        inputPanel.add(submitButton, BorderLayout.EAST);
        
        commandPanel.add(inputPanel, BorderLayout.CENTER);
    }
    
    private void createChoiceButtons() {
        choiceButtons = new JButton[4]; // Avanti + A, B, C
        String[] labels = {"⏩ AVANTI", "A", "B", "C"};
        String[] commands = {"avanti", "A", "B", "C"};
        Color[] colors = {
            new Color(100, 150, 100), // Verde per Avanti
            new Color(80, 100, 150),  // Blu per A
            new Color(150, 100, 80),  // Arancio per B
            new Color(120, 80, 120)   // Viola per C
        };
        
        for (int i = 0; i < choiceButtons.length; i++) {
            final String cmd = commands[i];
            choiceButtons[i] = new JButton(labels[i]);
            choiceButtons[i].setBackground(colors[i]);
            choiceButtons[i].setForeground(Color.WHITE);
            choiceButtons[i].setFont(new Font("SansSerif", Font.BOLD, 14));
            choiceButtons[i].setFocusPainted(false);
            choiceButtons[i].setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 180, 100), 2),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));
            choiceButtons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            choiceButtons[i].addActionListener(e -> {
                processCommand(cmd);
                commandField.requestFocusInWindow();
            });
            choicePanel.add(choiceButtons[i]);
        }
    }
    
    private void setupListeners() {
        // Comando Enter
        commandField.addActionListener(e -> processCommand());
        submitButton.addActionListener(e -> processCommand());
        
        // Pulsanti rapidi con tastiera
        commandField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // Scelte rapide con numeri 1, 2, 3 (solo se campo vuoto)
                if (commandField.getText().isEmpty()) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_1:
                        case KeyEvent.VK_NUMPAD1:
                            processCommand("A");
                            e.consume();
                            return;
                        case KeyEvent.VK_2:
                        case KeyEvent.VK_NUMPAD2:
                            processCommand("B");
                            e.consume();
                            return;
                        case KeyEvent.VK_3:
                        case KeyEvent.VK_NUMPAD3:
                            processCommand("C");
                            e.consume();
                            return;
                    }
                }
                
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                    case KeyEvent.VK_SPACE:
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_PAGE_DOWN:
                        // Se ci sono più pagine, mostra la prossima
                        if (engine.hasMorePages()) {
                            engine.nextPage();
                            String nextPageText = engine.getCurrentPageText();
                            renderer.setNarrativeText(nextPageText);
                            renderer.repaint();
                            e.consume(); // Previeni l'azione normale
                            return;
                        }
                        break;
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_PAGE_UP:
                        // Torna alla pagina precedente
                        if (engine.previousPage()) {
                            String prevPageText = engine.getCurrentPageText();
                            renderer.setNarrativeText(prevPageText);
                            renderer.repaint();
                            e.consume();
                            return;
                        }
                        break;
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
                     "         🎯 SCELTE RAPIDE 🎯\n" +
                     "═══════════════════════════════════════════════\n\n" +
                     "🟢 Bottone AVANTI / ENTER vuoto - Avanza storia\n" +
                     "🔵 Bottone A / tasto 1 - Scelta A\n" +
                     "🟠 Bottone B / tasto 2 - Scelta B\n" +
                     "🟣 Bottone C / tasto 3 - Scelta C\n\n" +
                     "═══════════════════════════════════════════════\n" +
                     "         📖 COMANDI TESTO 📖\n" +
                     "═══════════════════════════════════════════════\n\n" +
                     "📖 avanti - Inizia prossimo capitolo\n" +
                     "✍️  [risposta] - Risposta diretta (senza 'rispondi')\n" +
                     "🎒 inventario / stato - Info giocatore\n" +
                     "🍞 prendi [nome] - Raccogli oggetto\n" +
                     "💍 usa [nome] - Usa oggetto\n" +
                     "📍 dove - Progresso storia\n" +
                     "❓ aiuto - Mostra aiuto completo\n" +
                     "🚪 esci - Uscire dal gioco\n\n" +
                     "═══════════════════════════════════════════════\n" +
                     "   🎵 Le musiche cambiano ad ogni capitolo! 🎵\n" +
                     "═══════════════════════════════════════════════";
        
        renderer.setNarrativeText(help);
    }
    
    public void startGame(String playerName) {
        engine.initializeGame(playerName);
        
        // In modalità narrativa, avvia direttamente il primo capitolo
        if (engine.isNarrativeMode()) {
            // Forza l'avvio del primo capitolo con il suo enigma
            String firstChapter = engine.forceStartFirstChapter();
            renderer.setNarrativeText(firstChapter);
            renderer.setRoom(engine.getCurrentRoomKey());
        } else {
            // Modalità classica: mostra solo messaggio di benvenuto
            String welcome = engine.getLastLog();
            renderer.setNarrativeText(welcome);
            renderer.setRoom(engine.getCurrentRoomKey());
        }
        
        // Mostra inventario iniziale (con l'Anello)
        java.util.List<String> items = new ArrayList<>();
        for (com.lordofthethesis.model.Item item : engine.getPlayer().getInventory()) {
            items.add(item.getName());
        }
        renderer.setInventory(items);
        
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
