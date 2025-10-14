package com.lordofthethesis.graphics;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Renderer full-screen con dialoghi e inventario integrati nell'immagine
 * Stile: Avventura grafica classica (Monkey Island, Day of the Tentacle)
 */
public class FullScreenRenderer extends JPanel {
    private BufferedImage roomImage;
    private String currentRoom;
    private String narrativeText = "";
    private List<String> inventory = new ArrayList<>();
    
    // Dimensioni
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 750;
    private static final int TEXT_BOX_HEIGHT = 250; // Box del testo ancora più grande
    private static final int INVENTORY_SIZE = 40;
    
    // Colori tema scuro elegante
    private static final Color TEXT_BG = new Color(20, 20, 30, 220);
    private static final Color TEXT_FG = new Color(240, 240, 200);
    private static final Color BORDER_COLOR = new Color(200, 180, 100);
    
    public FullScreenRenderer() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        currentRoom = "contea";
        loadRoomImage(currentRoom);
    }
    
    public void setRoom(String roomKey) {
        if (!roomKey.equals(currentRoom)) {
            currentRoom = roomKey;
            loadRoomImage(roomKey);
            repaint();
        }
    }
    
    public void setNarrativeText(String text) {
        this.narrativeText = text;
        repaint();
    }
    
    public void setInventory(List<String> items) {
        this.inventory = new ArrayList<>(items);
        repaint();
    }
    
    private void loadRoomImage(String roomKey) {
        try {
            String imagePath = "assets/images/" + roomKey + ".png";
            File imageFile = new File(imagePath);
            
            if (imageFile.exists()) {
                BufferedImage original = ImageIO.read(imageFile);
                // Scala l'immagine a tutto schermo
                roomImage = scaleImage(original, WIDTH, HEIGHT - TEXT_BOX_HEIGHT);
            } else {
                roomImage = createPlaceholder(roomKey);
            }
        } catch (Exception e) {
            roomImage = createPlaceholder(roomKey);
        }
    }
    
    private BufferedImage scaleImage(BufferedImage original, int targetWidth, int targetHeight) {
        BufferedImage scaled = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = scaled.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(original, 0, 0, targetWidth, targetHeight, null);
        g.dispose();
        return scaled;
    }
    
    private BufferedImage createPlaceholder(String roomKey) {
        BufferedImage img = new BufferedImage(WIDTH, HEIGHT - TEXT_BOX_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        
        Color bgColor = getRoomColor(roomKey);
        g.setColor(bgColor);
        g.fillRect(0, 0, WIDTH, HEIGHT - TEXT_BOX_HEIGHT);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 36));
        String title = roomKey.toUpperCase();
        FontMetrics fm = g.getFontMetrics();
        g.drawString(title, (WIDTH - fm.stringWidth(title)) / 2, (HEIGHT - TEXT_BOX_HEIGHT) / 2);
        
        g.dispose();
        return img;
    }
    
    private Color getRoomColor(String roomKey) {
        switch (roomKey.toLowerCase()) {
            case "contea": return new Color(139, 90, 60);
            case "biblioteca": return new Color(200, 180, 140);
            case "corridoi": return new Color(180, 180, 180);
            case "aulamagna": return new Color(100, 150, 200);
            case "laboratorio": return new Color(100, 200, 255);
            case "ufficio": return new Color(150, 100, 200);
            case "mensa": return new Color(255, 150, 50);
            case "giardini": return new Color(100, 255, 100);
            case "segreteria": return new Color(255, 50, 50);
            default: return new Color(60, 60, 80);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // 1. Disegna l'immagine della stanza (occupa parte superiore)
        if (roomImage != null) {
            g2d.drawImage(roomImage, 0, 0, null);
        }
        
        // 2. Disegna box narrativo in basso (stile LucasArts)
        drawNarrativeBox(g2d);
        
        // 3. Disegna mini inventario in alto a destra
        drawMiniInventory(g2d);
        
        // 4. Disegna nome stanza in alto a sinistra
        drawRoomTitle(g2d);
    }
    
    private void drawNarrativeBox(Graphics2D g2d) {
        int boxY = HEIGHT - TEXT_BOX_HEIGHT;
        
        // Sfondo semi-trasparente
        g2d.setColor(TEXT_BG);
        g2d.fillRect(0, boxY, WIDTH, TEXT_BOX_HEIGHT);
        
        // Bordo decorativo
        g2d.setColor(BORDER_COLOR);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(5, boxY + 5, WIDTH - 10, TEXT_BOX_HEIGHT - 10);
        
        // Disegna il testo narrativo con word wrap - USA TUTTO LO SPAZIO!
        g2d.setColor(TEXT_FG);
        g2d.setFont(new Font("Monospaced", Font.PLAIN, 13));
        drawWrappedText(g2d, narrativeText, 15, boxY + 20, WIDTH - 30, TEXT_BOX_HEIGHT - 30);
    }
    
    private void drawMiniInventory(Graphics2D g2d) {
        int startX = WIDTH - 200;
        int startY = 10;
        
        // Sfondo per inventario
        g2d.setColor(new Color(20, 20, 30, 200));
        g2d.fillRoundRect(startX - 10, startY - 5, 190, 60, 10, 10);
        g2d.setColor(BORDER_COLOR);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(startX - 10, startY - 5, 190, 60, 10, 10);
        
        // Titolo inventario
        g2d.setFont(new Font("SansSerif", Font.BOLD, 10));
        g2d.setColor(BORDER_COLOR);
        g2d.drawString("INVENTARIO", startX, startY + 10);
        
        // Disegna icone inventario (max 4 visibili)
        for (int i = 0; i < Math.min(4, inventory.size()); i++) {
            String item = inventory.get(i);
            int x = startX + i * 45;
            int y = startY + 20;
            
            // Box per item
            g2d.setColor(new Color(60, 60, 80));
            g2d.fillRect(x, y, INVENTORY_SIZE, INVENTORY_SIZE);
            g2d.setColor(BORDER_COLOR);
            g2d.drawRect(x, y, INVENTORY_SIZE, INVENTORY_SIZE);
            
            // Icona item (emoji o simbolo)
            String icon = getItemIcon(item);
            g2d.setFont(new Font("SansSerif", Font.PLAIN, 24));
            g2d.setColor(Color.WHITE);
            FontMetrics fm = g2d.getFontMetrics();
            int iconX = x + (INVENTORY_SIZE - fm.stringWidth(icon)) / 2;
            int iconY = y + (INVENTORY_SIZE + fm.getAscent()) / 2 - 2;
            g2d.drawString(icon, iconX, iconY);
        }
        
        // Indicatore "+" se ci sono più item
        if (inventory.size() > 4) {
            g2d.setFont(new Font("SansSerif", Font.BOLD, 12));
            g2d.setColor(BORDER_COLOR);
            g2d.drawString("+" + (inventory.size() - 4), startX + 165, startY + 50);
        }
    }
    
    private void drawRoomTitle(Graphics2D g2d) {
        // Badge con nome stanza in alto a sinistra
        g2d.setColor(new Color(20, 20, 30, 200));
        g2d.fillRoundRect(10, 10, 250, 40, 10, 10);
        g2d.setColor(BORDER_COLOR);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(10, 10, 250, 40, 10, 10);
        
        // Nome stanza
        g2d.setFont(new Font("Serif", Font.BOLD, 20));
        g2d.setColor(TEXT_FG);
        String roomName = getRoomDisplayName(currentRoom);
        g2d.drawString(roomName, 25, 37);
    }
    
    private String getRoomDisplayName(String roomKey) {
        switch (roomKey.toLowerCase()) {
            case "intro": return ""; // Nessun titolo durante l'intro cinematica
            case "introsauron": return ""; // Nessun titolo durante l'intro cinematica
            case "contea": return "🏡 LA CONTEA";
            case "bagend": return "🏡 BAG END";
            case "spettri": return "👻 CAVALIERI NERI";
            case "incontro con granpasso relatore": return "⚔️ GRANPASSO";
            case "granburrone": return "🏰 GRANBURRONE";
            case "rivendell": return "🏰 RIVENDELL";
            case "concilio": return "🎭 GRAN CONCILIO";
            case "granconcilio": return "🎭 GRAN CONCILIO";
            case "elrond concilio": return "🎭 CONCILIO DI ELROND";
            case "moria": return "⛰️ MORIA";
            case "porte di durin": return "🚪 PORTE DI DURIN";
            case "argonath": return "🗿 ARGONATH";
            case "balrog": return "🔥 BALROG";
            case "divisione": return "⚔️ DIVISIONE";
            case "mordor": return "🌋 MORDOR";
            case "interno monte fato": return "🔥 MONTE FATO";
            case "aquile": return "🦅 LE AQUILE";
            case "seduta": return "🎓 SEDUTA DI LAUREA";
            case "biblioteca": return "📚 BIBLIOTECA";
            case "corridoi": return "🚪 CORRIDOI";
            case "aulamagna": return "🎓 AULA MAGNA";
            case "laboratorio": return "💻 LABORATORIO";
            case "ufficio": return "🎩 UFFICIO";
            case "mensa": return "🍽️ MENSA";
            case "giardini": return "🌳 GIARDINI";
            case "segreteria": return "📋 SEGRETERIA";
            default: return roomKey.toUpperCase();
        }
    }
    
    private String getItemIcon(String itemName) {
        String lower = itemName.toLowerCase();
        if (lower.contains("anello") || lower.contains("ring")) return "💍";
        if (lower.contains("tesi") || lower.contains("thesis")) return "📜";
        if (lower.contains("libro") || lower.contains("book")) return "📖";
        if (lower.contains("chiave") || lower.contains("key")) return "🔑";
        if (lower.contains("spada") || lower.contains("sword")) return "⚔️";
        if (lower.contains("pozione") || lower.contains("potion")) return "🧪";
        if (lower.contains("mappa") || lower.contains("map")) return "🗺️";
        if (lower.contains("torcia") || lower.contains("torch")) return "🔦";
        if (lower.contains("cibo") || lower.contains("food")) return "🍞";
        return "📦"; // Default
    }
    
    private void drawWrappedText(Graphics2D g2d, String text, int x, int y, int maxWidth, int maxHeight) {
        if (text == null || text.isEmpty()) {
            return;
        }
        
        FontMetrics fm = g2d.getFontMetrics();
        int lineHeight = fm.getHeight();
        int currentY = y + fm.getAscent(); // Inizia dalla baseline
        int maxY = y + maxHeight;
        
        // Prima separa per \n (ritorni a capo espliciti)
        String[] paragraphs = text.split("\n", -1); // -1 per mantenere i paragrafi vuoti
        
        for (int p = 0; p < paragraphs.length; p++) {
            String paragraph = paragraphs[p];
            
            // Se il paragrafo è vuoto (riga vuota), salta una riga
            if (paragraph.trim().isEmpty()) {
                if (p > 0) { // Non aggiungere spazio all'inizio
                    currentY += lineHeight;
                    if (currentY > maxY) return;
                }
                continue;
            }
            
            // Poi gestisci il word wrap per ogni paragrafo
            String[] words = paragraph.trim().split("\\s+"); // Split per spazi multipli
            StringBuilder line = new StringBuilder();
            
            for (String word : words) {
                if (word.isEmpty()) continue;
                
                // Testa se la parola + spazio entra nella riga corrente
                String testLine = line.length() > 0 ? line + " " + word : word;
                int testWidth = fm.stringWidth(testLine);
                
                if (testWidth > maxWidth && line.length() > 0) {
                    // La riga è piena, disegnala
                    g2d.drawString(line.toString(), x, currentY);
                    currentY += lineHeight;
                    
                    // Controlla se c'è ancora spazio
                    if (currentY > maxY) {
                        return; // Tronca silenziosamente
                    }
                    
                    // Inizia nuova riga con la parola corrente
                    line = new StringBuilder(word);
                } else if (testWidth > maxWidth && line.length() == 0) {
                    // La parola singola è troppo lunga, disegnala comunque troncata
                    g2d.drawString(word, x, currentY);
                    currentY += lineHeight;
                    
                    if (currentY > maxY) {
                        return;
                    }
                } else {
                    // La parola entra nella riga
                    line = new StringBuilder(testLine);
                }
            }
            
            // Disegna l'ultima linea del paragrafo
            if (line.length() > 0) {
                if (currentY <= maxY) {
                    g2d.drawString(line.toString(), x, currentY);
                    currentY += lineHeight;
                }
            }
        }
    }
}
