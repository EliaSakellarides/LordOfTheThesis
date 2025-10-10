package com.lordofthethesis.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

/**
 * Renderer per grafica isometrica in stile Pixel art
 */
public class IsometricRenderer extends JPanel {
    private static final int TILE_WIDTH = 32;
    private static final int TILE_HEIGHT = 16;
    private BufferedImage sceneImage;
    private String currentRoom;
    
    public IsometricRenderer() {
        setPreferredSize(new Dimension(450, 400));
        setBackground(new Color(20, 20, 30));
        currentRoom = "default";
        renderScene();
    }
    
    public void setRoom(String roomKey) {
        if (!roomKey.equals(currentRoom)) {
            currentRoom = roomKey;
            renderScene();
            repaint();
        }
    }
    
    private void renderScene() {
        sceneImage = new BufferedImage(450, 400, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = sceneImage.createGraphics();
        
        // Anti-aliasing per pixel art morbida
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        
        // Sfondo
        g2d.setColor(new Color(20, 20, 30));
        g2d.fillRect(0, 0, 450, 400);
        
        // Disegna la scena in base alla stanza
        switch (currentRoom.toLowerCase()) {
            case "contea":
                renderContea(g2d);
                break;
            case "biblioteca":
                renderBiblioteca(g2d);
                break;
            case "corridoi":
                renderCorridoi(g2d);
                break;
            case "aulamagna":
                renderAulaMagna(g2d);
                break;
            case "laboratorio":
                renderLaboratorio(g2d);
                break;
            case "ufficio":
                renderUfficio(g2d);
                break;
            case "mensa":
                renderMensa(g2d);
                break;
            case "giardini":
                renderGiardini(g2d);
                break;
            case "segreteria":
                renderSegreteria(g2d);
                break;
            default:
                renderDefault(g2d);
                break;
        }
        
        g2d.dispose();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (sceneImage != null) {
            g.drawImage(sceneImage, 0, 0, null);
        }
    }
    
    // Metodi helper per disegnare tile isometrici con stile pixel art 3D
    private void drawIsoTile(Graphics2D g2d, int x, int y, Color topColor, Color leftColor, Color rightColor) {
        int centerX = x;
        int centerY = y;
        
        // Top faccia (rombo) - toon shading con flat colors
        int[] topX = {centerX, centerX + TILE_WIDTH/2, centerX, centerX - TILE_WIDTH/2};
        int[] topY = {centerY, centerY + TILE_HEIGHT/2, centerY + TILE_HEIGHT, centerY + TILE_HEIGHT/2};
        g2d.setColor(topColor);
        g2d.fillPolygon(topX, topY, 4);
        
        // Pixel-perfect outline
        g2d.setColor(new Color(20, 20, 20));
        g2d.drawPolygon(topX, topY, 4);
    }
    
    private void drawIsoCube(Graphics2D g2d, int x, int y, int height, Color color) {
        // Toon-style lighting: top bright, left medium, right dark
        Color topColor = brighten(color, 1.3f);
        Color leftColor = darken(color, 0.7f);
        Color rightColor = darken(color, 0.5f);
        
        // Top face
        int[] topX = {x, x + TILE_WIDTH/2, x, x - TILE_WIDTH/2};
        int[] topY = {y - height, y - height + TILE_HEIGHT/2, y - height + TILE_HEIGHT, y - height + TILE_HEIGHT/2};
        g2d.setColor(topColor);
        g2d.fillPolygon(topX, topY, 4);
        
        // Left face
        int[] leftX = {x - TILE_WIDTH/2, x - TILE_WIDTH/2, x, x};
        int[] leftY = {y - height + TILE_HEIGHT/2, y + TILE_HEIGHT/2, y + TILE_HEIGHT, y - height + TILE_HEIGHT};
        g2d.setColor(leftColor);
        g2d.fillPolygon(leftX, leftY, 4);
        
        // Right face
        int[] rightX = {x, x, x + TILE_WIDTH/2, x + TILE_WIDTH/2};
        int[] rightY = {y - height + TILE_HEIGHT, y + TILE_HEIGHT, y + TILE_HEIGHT/2, y - height + TILE_HEIGHT/2};
        g2d.setColor(rightColor);
        g2d.fillPolygon(rightX, rightY, 4);
        
        // Pixel-perfect black outlines
        g2d.setColor(new Color(20, 20, 20));
        g2d.drawPolygon(topX, topY, 4);
        g2d.drawPolygon(leftX, leftY, 4);
        g2d.drawPolygon(rightX, rightY, 4);
        
        // Edge highlights on top edges (convex edges)
        g2d.setColor(new Color(255, 255, 255, 100));
        g2d.drawLine(topX[0], topY[0], topX[1], topY[1]);
    }
    
    private void drawCharacter(Graphics2D g2d, int x, int y, Color skinColor) {
        // Corpo pixelato con outline e toon shading
        // Testa
        g2d.setColor(brighten(skinColor, 1.1f));
        g2d.fillRect(x - 4, y - 12, 8, 6);
        // Corpo
        g2d.setColor(new Color(80, 100, 140));
        g2d.fillRect(x - 6, y - 6, 12, 8);
        // Gambe
        g2d.setColor(new Color(40, 60, 100));
        g2d.fillRect(x - 6, y + 2, 4, 8);
        g2d.fillRect(x + 2, y + 2, 4, 8);
        
        // Pixel-perfect outline
        g2d.setColor(new Color(20, 20, 20));
        g2d.drawRect(x - 4, y - 12, 8, 6);
        g2d.drawRect(x - 6, y - 6, 12, 8);
        g2d.drawRect(x - 6, y + 2, 4, 8);
        g2d.drawRect(x + 2, y + 2, 4, 8);
        
        // Highlight sulla testa
        g2d.setColor(new Color(255, 255, 255, 80));
        g2d.fillRect(x - 2, y - 11, 2, 2);
    }
    
    // Helper methods per toon lighting
    private Color brighten(Color c, float factor) {
        int r = Math.min(255, (int)(c.getRed() * factor));
        int g = Math.min(255, (int)(c.getGreen() * factor));
        int b = Math.min(255, (int)(c.getBlue() * factor));
        return new Color(r, g, b);
    }
    
    private Color darken(Color c, float factor) {
        int r = (int)(c.getRed() * factor);
        int g = (int)(c.getGreen() * factor);
        int b = (int)(c.getBlue() * factor);
        return new Color(r, g, b);
    }
    
    // God rays effect per scene atmosferiche
    private void drawGodRays(Graphics2D g2d, int centerX, int centerY) {
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.15f));
        for (int i = 0; i < 8; i++) {
            double angle = (i * Math.PI / 4) + (System.currentTimeMillis() / 2000.0);
            int length = 150;
            int x2 = centerX + (int)(Math.cos(angle) * length);
            int y2 = centerY + (int)(Math.sin(angle) * length);
            
            GradientPaint gp = new GradientPaint(
                centerX, centerY, new Color(255, 215, 0, 120),
                x2, y2, new Color(255, 215, 0, 0)
            );
            g2d.setPaint(gp);
            
            int[] xPts = {centerX, x2 + 5, x2, x2 - 5};
            int[] yPts = {centerY, y2, y2 + 10, y2};
            g2d.fillPolygon(xPts, yPts, 4);
        }
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
    
    // Scene specifiche
    private void renderContea(Graphics2D g2d) {
        // Titolo
        g2d.setColor(new Color(255, 215, 0));
        g2d.setFont(new Font("Monospaced", Font.BOLD, 14));
        g2d.drawString("LA CONTEA - Tua Stanza", 100, 20);
        
        // Pavimento
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                int isoX = 225 + (col - row) * TILE_WIDTH/2;
                int isoY = 150 + (col + row) * TILE_HEIGHT/2;
                drawIsoTile(g2d, isoX, isoY, new Color(139, 90, 60), new Color(120, 80, 50), new Color(110, 75, 45));
            }
        }
        
        // Letto
        drawIsoCube(g2d, 180, 200, 20, new Color(150, 100, 100));
        
        // Scrivania
        drawIsoCube(g2d, 270, 220, 25, new Color(101, 67, 33));
        
        // Computer sulla scrivania
        g2d.setColor(new Color(50, 50, 50));
        g2d.fillRect(268, 195, 12, 10);
        g2d.setColor(new Color(100, 150, 255));
        g2d.fillRect(270, 197, 8, 6);
        
        // Libri
        for (int i = 0; i < 3; i++) {
            drawIsoCube(g2d, 150 + i * 15, 280, 8, new Color(180, 50 + i * 30, 50));
        }
        
        // LA TESI - oggetto speciale luminoso
        g2d.setColor(new Color(255, 215, 0));
        g2d.fillRect(265, 210, 10, 3);
        g2d.setColor(new Color(255, 255, 0, 150));
        g2d.fillOval(260, 205, 20, 20); // Alone luminoso
        
        g2d.setColor(new Color(255, 215, 0));
        g2d.setFont(new Font("Monospaced", Font.BOLD, 10));
        g2d.drawString("TESI", 258, 228);
    }
    
    private void renderBiblioteca(Graphics2D g2d) {
        g2d.setColor(new Color(200, 180, 140));
        g2d.setFont(new Font("Monospaced", Font.BOLD, 14));
        g2d.drawString("BIBLIOTECA UNIVERSITARIA", 80, 20);
        
        // Pavimento marmo
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                int isoX = 225 + (col - row) * TILE_WIDTH/2;
                int isoY = 150 + (col + row) * TILE_HEIGHT/2;
                Color tileColor = (row + col) % 2 == 0 ? new Color(230, 230, 230) : new Color(200, 200, 200);
                drawIsoTile(g2d, isoX, isoY, tileColor, tileColor.darker(), tileColor.darker().darker());
            }
        }
        
        // Scaffali
        for (int i = 0; i < 3; i++) {
            drawIsoCube(g2d, 160, 180 + i * 40, 60, new Color(101, 67, 33));
            drawIsoCube(g2d, 290, 180 + i * 40, 60, new Color(101, 67, 33));
            
            // Libri sugli scaffali
            for (int j = 0; j < 5; j++) {
                g2d.setColor(new Color(150 + j * 10, 50 + j * 15, 50 + j * 10));
                g2d.fillRect(158 + j * 3, 158 - j * 2 + i * 40, 3, 15);
                g2d.fillRect(288 + j * 3, 158 - j * 2 + i * 40, 3, 15);
            }
        }
        
        // Tavolo con studente che dorme
        drawIsoCube(g2d, 225, 250, 20, new Color(150, 120, 80));
        drawCharacter(g2d, 225, 240, new Color(255, 220, 177));
        
        // Zzz per indicare il sonno
        g2d.setColor(new Color(255, 255, 255, 180));
        g2d.setFont(new Font("Serif", Font.ITALIC, 16));
        g2d.drawString("Z", 235, 220);
        g2d.drawString("Z", 243, 210);
        g2d.drawString("Z", 250, 200);
    }
    
    private void renderCorridoi(Graphics2D g2d) {
        g2d.setColor(new Color(180, 180, 180));
        g2d.setFont(new Font("Monospaced", Font.BOLD, 14));
        g2d.drawString("CORRIDOI DEL DIPARTIMENTO", 70, 20);
        
        // Pavimento corridoio lungo
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 4; col++) {
                int isoX = 225 + (col - row) * TILE_WIDTH/2;
                int isoY = 100 + (col + row) * TILE_HEIGHT/2;
                drawIsoTile(g2d, isoX, isoY, new Color(200, 200, 210), new Color(180, 180, 190), new Color(160, 160, 170));
            }
        }
        
        // Porte dei laboratori
        for (int i = 0; i < 4; i++) {
            drawIsoCube(g2d, 160, 120 + i * 50, 50, new Color(139, 69, 19));
            g2d.setColor(new Color(180, 140, 100));
            g2d.fillRect(158, 100 + i * 50, 8, 30);
            
            drawIsoCube(g2d, 290, 120 + i * 50, 50, new Color(139, 69, 19));
            g2d.setColor(new Color(180, 140, 100));
            g2d.fillRect(288, 100 + i * 50, 8, 30);
        }
        
        // Luci al neon sul soffitto
        g2d.setColor(new Color(200, 255, 255, 150));
        for (int i = 0; i < 6; i++) {
            g2d.fillRect(210 + i * 15, 50, 10, 3);
        }
    }
    
    private void renderAulaMagna(Graphics2D g2d) {
        g2d.setColor(new Color(100, 150, 200));
        g2d.setFont(new Font("Monospaced", Font.BOLD, 14));
        g2d.drawString("AULA MAGNA", 140, 20);
        
        // Pavimento
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                int isoX = 225 + (col - row) * TILE_WIDTH/2;
                int isoY = 120 + (col + row) * TILE_HEIGHT/2;
                drawIsoTile(g2d, isoX, isoY, new Color(160, 140, 120), new Color(140, 120, 100), new Color(120, 100, 80));
            }
        }
        
        // Lavagna
        drawIsoCube(g2d, 225, 140, 60, new Color(40, 40, 40));
        g2d.setColor(new Color(255, 255, 255));
        g2d.setFont(new Font("Monospaced", Font.PLAIN, 8));
        g2d.drawString("f(x)=x²+2x+1", 205, 120);
        
        // File di sedie
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 5; col++) {
                int seatX = 180 + col * 20;
                int seatY = 200 + row * 25;
                drawIsoCube(g2d, seatX, seatY, 15, new Color(100, 100, 150));
            }
        }
    }
    
    private void renderLaboratorio(Graphics2D g2d) {
        g2d.setColor(new Color(100, 200, 255));
        g2d.setFont(new Font("Monospaced", Font.BOLD, 14));
        g2d.drawString("LABORATORIO INFORMATICA", 80, 20);
        
        // Pavimento
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                int isoX = 225 + (col - row) * TILE_WIDTH/2;
                int isoY = 150 + (col + row) * TILE_HEIGHT/2;
                drawIsoTile(g2d, isoX, isoY, new Color(220, 220, 230), new Color(200, 200, 210), new Color(180, 180, 190));
            }
        }
        
        // Computer in griglia
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 4; col++) {
                int pcX = 170 + col * 30;
                int pcY = 180 + row * 40;
                
                // Desk
                drawIsoCube(g2d, pcX, pcY, 20, new Color(150, 150, 150));
                
                // Monitor
                g2d.setColor(new Color(50, 50, 50));
                g2d.fillRect(pcX - 6, pcY - 25, 12, 10);
                
                // Alcuni con BSOD (schermo blu della morte)
                if ((row + col) % 3 == 0) {
                    g2d.setColor(new Color(0, 120, 215));
                    g2d.fillRect(pcX - 5, pcY - 24, 10, 8);
                    g2d.setColor(Color.WHITE);
                    g2d.setFont(new Font("Monospaced", Font.BOLD, 6));
                    g2d.drawString(":(", pcX - 3, pcY - 18);
                } else {
                    g2d.setColor(new Color(100, 255, 100));
                    g2d.fillRect(pcX - 5, pcY - 24, 10, 8);
                }
            }
        }
    }
    
    private void renderUfficio(Graphics2D g2d) {
        g2d.setColor(new Color(150, 100, 200));
        g2d.setFont(new Font("Serif", Font.BOLD, 14));
        g2d.drawString("UFFICIO PROF. GANDALF", 90, 20);
        
        // Pavimento elegante
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                int isoX = 225 + (col - row) * TILE_WIDTH/2;
                int isoY = 150 + (col + row) * TILE_HEIGHT/2;
                drawIsoTile(g2d, isoX, isoY, new Color(120, 80, 60), new Color(100, 65, 50), new Color(80, 50, 40));
            }
        }
        
        // Librerie alte
        drawIsoCube(g2d, 160, 200, 80, new Color(101, 67, 33));
        drawIsoCube(g2d, 290, 200, 80, new Color(101, 67, 33));
        
        // Libri sulle librerie
        for (int i = 0; i < 8; i++) {
            g2d.setColor(new Color(150 + i * 10, 50, 50 + i * 15));
            g2d.fillRect(158, 160 - i * 8, 8, 6);
            g2d.fillRect(288, 160 - i * 8, 8, 6);
        }
        
        // Scrivania grande
        drawIsoCube(g2d, 225, 250, 30, new Color(70, 40, 20));
        
        // Oggetti sulla scrivania
        g2d.setColor(new Color(255, 215, 0));
        g2d.fillRect(220, 228, 4, 6); // Trofeo
        g2d.setColor(new Color(200, 200, 200));
        g2d.fillOval(230, 228, 6, 6); // Caffè
        
        // Professor Gandalf
        drawCharacter(g2d, 225, 240, new Color(255, 220, 177));
        // Barba
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(223, 234, 4, 6);
    }
    
    private void renderMensa(Graphics2D g2d) {
        g2d.setColor(new Color(255, 150, 50));
        g2d.setFont(new Font("Monospaced", Font.BOLD, 14));
        g2d.drawString("MENSA UNIVERSITARIA", 100, 20);
        
        // Pavimento
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                int isoX = 225 + (col - row) * TILE_WIDTH/2;
                int isoY = 150 + (col + row) * TILE_HEIGHT/2;
                drawIsoTile(g2d, isoX, isoY, new Color(240, 240, 240), new Color(220, 220, 220), new Color(200, 200, 200));
            }
        }
        
        // Bancone del cibo
        drawIsoCube(g2d, 225, 160, 25, new Color(200, 50, 50));
        
        // Cibo sul bancone
        g2d.setColor(new Color(255, 200, 100));
        g2d.fillOval(220, 140, 8, 6); // Pizza
        g2d.setColor(new Color(100, 200, 100));
        g2d.fillOval(230, 140, 8, 6); // Insalata
        
        // Tavoli
        for (int i = 0; i < 3; i++) {
            int tableX = 180 + i * 50;
            drawIsoCube(g2d, tableX, 240, 20, new Color(150, 100, 50));
            
            // Studenti ai tavoli
            if (i % 2 == 0) {
                drawCharacter(g2d, tableX, 230, new Color(255, 220, 177));
            }
        }
    }
    
    private void renderGiardini(Graphics2D g2d) {
        g2d.setColor(new Color(100, 255, 100));
        g2d.setFont(new Font("Monospaced", Font.BOLD, 14));
        g2d.drawString("GIARDINI UNIVERSITÀ", 100, 20);
        
        // Prato
        for (int row = 0; row < 7; row++) {
            for (int col = 0; col < 7; col++) {
                int isoX = 225 + (col - row) * TILE_WIDTH/2;
                int isoY = 130 + (col + row) * TILE_HEIGHT/2;
                Color grassColor = new Color(50 + (row * 5), 180 + (col * 3), 50 + (row * 3));
                drawIsoTile(g2d, isoX, isoY, grassColor, grassColor.darker(), grassColor.darker().darker());
            }
        }
        
        // Alberi
        for (int i = 0; i < 4; i++) {
            int treeX = 160 + i * 60;
            int treeY = 180 + (i % 2) * 60;
            
            // Tronco
            drawIsoCube(g2d, treeX, treeY, 40, new Color(101, 67, 33));
            
            // Fogliame
            g2d.setColor(new Color(34, 139, 34));
            g2d.fillOval(treeX - 20, treeY - 60, 40, 40);
            g2d.setColor(new Color(50, 180, 50));
            g2d.fillOval(treeX - 15, treeY - 55, 30, 30);
        }
        
        // Fontana centrale
        drawIsoCube(g2d, 225, 250, 30, new Color(169, 169, 169));
        g2d.setColor(new Color(100, 180, 255, 180));
        // Acqua
        for (int i = 0; i < 5; i++) {
            g2d.fillOval(220 + i * 2, 220 - i * 3, 4, 4);
        }
    }
    
    private void renderSegreteria(Graphics2D g2d) {
        g2d.setColor(new Color(255, 50, 50));
        g2d.setFont(new Font("Serif", Font.BOLD, 16));
        g2d.drawString("★ SEGRETERIA - DESTINAZIONE FINALE ★", 30, 20);
        
        // Pavimento elegante
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                int isoX = 225 + (col - row) * TILE_WIDTH/2;
                int isoY = 150 + (col + row) * TILE_HEIGHT/2;
                Color tileColor = (row + col) % 2 == 0 ? new Color(180, 180, 200) : new Color(150, 150, 180);
                drawIsoTile(g2d, isoX, isoY, tileColor, tileColor.darker(), tileColor.darker().darker());
            }
        }
        
        // Bancone segreteria
        drawIsoCube(g2d, 225, 200, 40, new Color(101, 67, 33));
        
        // Cassetta per le tesi - IL MONTE FATO!
        drawIsoCube(g2d, 225, 180, 50, new Color(150, 50, 50));
        
        // Effetto fuoco/lava (simbolico del Monte Fato)
        g2d.setColor(new Color(255, 100, 0, 180));
        g2d.fillOval(220, 155, 10, 8);
        g2d.setColor(new Color(255, 200, 0, 180));
        g2d.fillOval(222, 157, 6, 5);
        
        // Simbolo della tesi
        g2d.setColor(new Color(255, 215, 0));
        g2d.setFont(new Font("Monospaced", Font.BOLD, 12));
        g2d.drawString("DEPOSITO", 200, 170);
        g2d.drawString("TESI", 215, 182);
        
        // Alone mistico
        g2d.setColor(new Color(255, 215, 0, 50));
        g2d.fillOval(205, 140, 40, 40);
        
        // Segretaria
        drawCharacter(g2d, 240, 190, new Color(255, 220, 177));

        // Disegna il sigillo degli anelli sul fondo come pixel art
        try {
            javax.swing.ImageIcon icon = com.lordofthethesis.graphics.PixelArtManager.getRingSigilIcon();
            java.awt.Image img = icon.getImage();
            g2d.drawImage(img, 30, 40, 64, 64, null);
        } catch (Exception e) {
            // ignore se non disponibile
        }
    }
    
    private void renderDefault(Graphics2D g2d) {
        g2d.setColor(new Color(255, 215, 0));
        g2d.setFont(new Font("Serif", Font.BOLD, 20));
        g2d.drawString("LORD OF THE THESIS", 100, 180);
        g2d.setFont(new Font("Serif", Font.PLAIN, 14));
        g2d.drawString("Un'avventura accademica", 120, 210);
        
        // Logo tesi luminosa
        g2d.setColor(new Color(255, 215, 0));
        g2d.fillRect(210, 240, 30, 40);
        g2d.setColor(new Color(255, 255, 0, 100));
        g2d.fillOval(195, 225, 60, 70);
    }
}
