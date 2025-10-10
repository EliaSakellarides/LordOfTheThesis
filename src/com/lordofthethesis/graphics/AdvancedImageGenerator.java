package com.lordofthethesis.graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Generatore avanzato di immagini pixel art per avventura grafica
 * Stile: Fallout Shelter / Thimbleweed Park / avventure grafiche anni '90
 */
public class AdvancedImageGenerator {
    
    private static final int WIDTH = 610;
    private static final int HEIGHT = 343;
    
    // Palette colori stile pixel art
    private static final Color FLOOR_DARK = new Color(80, 60, 50);
    private static final Color FLOOR_LIGHT = new Color(100, 80, 65);
    private static final Color WALL = new Color(140, 160, 140);
    private static final Color WALL_DARK = new Color(100, 120, 100);
    private static final Color FURNITURE_WOOD = new Color(120, 80, 50);
    private static final Color FURNITURE_DARK = new Color(80, 60, 40);
    private static final Color ACCENT_GOLD = new Color(200, 180, 100);
    private static final Color SHADOW = new Color(30, 30, 30, 150);
    
    public static void main(String[] args) {
        System.out.println("🎨 Generazione immagini pixel art avanzate...");
        
        File dir = new File("assets/images");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        generateContea();
        generateBiblioteca();
        generateCorridoi();
        generateAulaMagna();
        generateLaboratorio();
        generateUfficio();
        generateMensa();
        generateGiardini();
        generateSegreteria();
        
        System.out.println("✅ Immagini pixel art generate in: assets/images/");
    }
    
    private static void generateContea() {
        BufferedImage img = createBaseRoom(new Color(139, 110, 80), "LA CONTEA");
        Graphics2D g = img.createGraphics();
        setupGraphics(g);
        
        // Porta rotonda Hobbit al centro
        drawRoundDoor(g, WIDTH/2 - 40, HEIGHT/2 - 60, 80, 100);
        
        // Finestre circolari
        drawCircularWindow(g, 150, 120, 30);
        drawCircularWindow(g, WIDTH - 180, 120, 30);
        
        // Camino con fuoco
        drawFireplace(g, 100, HEIGHT - 120, 60, 80);
        
        // Tavolino rotondo
        drawRoundTable(g, WIDTH - 150, HEIGHT - 100, 50);
        
        // Testo atmosferico
        g.setColor(new Color(100, 80, 60));
        g.setFont(new Font("Monospaced", Font.ITALIC, 10));
        g.drawString("\"Casa dolce casa...\"", WIDTH/2 - 60, HEIGHT - 20);
        
        g.dispose();
        saveImage(img, "contea.png");
    }
    
    private static void generateBiblioteca() {
        BufferedImage img = createBaseRoom(new Color(90, 70, 50), "BIBLIOTECA");
        Graphics2D g = img.createGraphics();
        setupGraphics(g);
        
        // Scaffali a sinistra
        drawBookshelf(g, 30, 80, 80, 150);
        drawBookshelf(g, 120, 80, 80, 150);
        
        // Scaffali a destra
        drawBookshelf(g, WIDTH - 110, 80, 80, 150);
        drawBookshelf(g, WIDTH - 200, 80, 80, 150);
        
        // Tavolo al centro con libri aperti
        drawTable(g, WIDTH/2 - 60, HEIGHT - 100, 120, 50);
        drawOpenBook(g, WIDTH/2 - 20, HEIGHT - 110, 40, 30);
        
        // Lampada da tavolo
        drawLamp(g, WIDTH/2 + 40, HEIGHT - 130, 20, 40);
        
        g.setColor(new Color(200, 180, 140));
        g.setFont(new Font("Serif", Font.ITALIC, 10));
        g.drawString("Sapientia est potentia", WIDTH/2 - 70, HEIGHT - 20);
        
        g.dispose();
        saveImage(img, "biblioteca.png");
    }
    
    private static void generateCorridoi() {
        BufferedImage img = createBaseRoom(new Color(180, 180, 180), "CORRIDOI");
        Graphics2D g = img.createGraphics();
        setupGraphics(g);
        
        // Effetto prospettico del corridoio
        int[] xPoints = {WIDTH/2 - 100, WIDTH/2 + 100, WIDTH - 50, 50};
        int[] yPoints = {50, 50, HEIGHT - 80, HEIGHT - 80};
        g.setColor(new Color(150, 150, 150));
        g.fillPolygon(xPoints, yPoints, 4);
        
        // Porte lungo il corridoio
        drawDoor(g, 100, HEIGHT - 120, 50, 80);
        drawDoor(g, WIDTH/2 - 25, HEIGHT/2 - 40, 50, 80);
        drawDoor(g, WIDTH - 150, HEIGHT - 120, 50, 80);
        
        // Finestre
        for (int i = 0; i < 3; i++) {
            drawWindow(g, 200 + i * 120, 100, 60, 40);
        }
        
        // Luci fluorescenti
        for (int i = 0; i < 4; i++) {
            drawCeilingLight(g, 150 + i * 100, 40);
        }
        
        g.dispose();
        saveImage(img, "corridoi.png");
    }
    
    private static void generateAulaMagna() {
        BufferedImage img = createBaseRoom(new Color(100, 120, 160), "AULA MAGNA");
        Graphics2D g = img.createGraphics();
        setupGraphics(g);
        
        // Palco/pedana
        g.setColor(new Color(120, 100, 80));
        g.fillRect(WIDTH/2 - 150, 60, 300, 100);
        g.setColor(FURNITURE_DARK);
        g.drawRect(WIDTH/2 - 150, 60, 300, 100);
        
        // Podio
        drawPodium(g, WIDTH/2 - 30, 100, 60, 50);
        
        // File di sedie
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 6; col++) {
                drawChair(g, 80 + col * 80, HEIGHT - 150 + row * 40, 30, 30);
            }
        }
        
        // Lavagna dietro
        g.setColor(new Color(40, 60, 40));
        g.fillRect(WIDTH/2 - 100, 70, 200, 80);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.PLAIN, 12));
        g.drawString("DISCUSSIONE TESI", WIDTH/2 - 70, 110);
        
        g.dispose();
        saveImage(img, "aulamagna.png");
    }
    
    private static void generateLaboratorio() {
        BufferedImage img = createBaseRoom(new Color(80, 100, 120), "LABORATORIO");
        Graphics2D g = img.createGraphics();
        setupGraphics(g);
        
        // Banchi con computer
        for (int i = 0; i < 3; i++) {
            int x = 80 + i * 180;
            drawDesk(g, x, HEIGHT - 120, 120, 60);
            drawComputer(g, x + 30, HEIGHT - 140, 60, 50);
        }
        
        // Server rack
        drawServerRack(g, WIDTH - 120, HEIGHT/2, 80, 120);
        
        // Cavi e luci
        g.setColor(new Color(100, 200, 100, 180));
        for (int i = 0; i < 4; i++) {
            g.drawLine(WIDTH - 80, HEIGHT/2 + i * 30, WIDTH - 40, HEIGHT/2 + 10 + i * 30);
        }
        
        // Lavagna con codice
        g.setColor(new Color(40, 50, 40));
        g.fillRect(50, 60, 150, 100);
        g.setColor(new Color(0, 255, 0));
        g.setFont(new Font("Monospaced", Font.PLAIN, 8));
        g.drawString("while(thesis)", 60, 80);
        g.drawString("  compile();", 60, 95);
        g.drawString("  debug();", 60, 110);
        g.drawString("  sleep();", 60, 125);
        
        g.dispose();
        saveImage(img, "laboratorio.png");
    }
    
    private static void generateUfficio() {
        BufferedImage img = createBaseRoom(new Color(120, 90, 110), "UFFICIO DEL PROF");
        Graphics2D g = img.createGraphics();
        setupGraphics(g);
        
        // Scrivania grande
        drawDesk(g, WIDTH/2 - 80, HEIGHT - 130, 160, 80);
        
        // Pila di libri sulla scrivania
        for (int i = 0; i < 5; i++) {
            g.setColor(new Color(150 + i * 10, 100, 80));
            g.fillRect(WIDTH/2 - 50 + i * 3, HEIGHT - 140 - i * 8, 40, 8);
        }
        
        // Lampada da scrivania
        drawLamp(g, WIDTH/2 + 50, HEIGHT - 150, 25, 50);
        
        // Sedia girevole
        drawOfficeChair(g, WIDTH/2 - 20, HEIGHT - 90, 40, 50);
        
        // Libreria dietro
        drawBookshelf(g, 50, 80, 100, 140);
        
        // Finestra
        drawWindow(g, WIDTH - 150, 90, 100, 80);
        
        // Diploma sulla parete
        g.setColor(ACCENT_GOLD);
        g.fillRect(WIDTH/2 - 40, 80, 80, 60);
        g.setColor(FURNITURE_DARK);
        g.drawRect(WIDTH/2 - 40, 80, 80, 60);
        g.setFont(new Font("Serif", Font.ITALIC, 8));
        g.drawString("PhD", WIDTH/2 - 10, 115);
        
        g.dispose();
        saveImage(img, "ufficio.png");
    }
    
    private static void generateMensa() {
        BufferedImage img = createBaseRoom(new Color(200, 150, 100), "MENSA");
        Graphics2D g = img.createGraphics();
        setupGraphics(g);
        
        // Tavoli con sedie
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 3; col++) {
                int x = 100 + col * 150;
                int y = HEIGHT - 180 + row * 100;
                drawTable(g, x, y, 100, 60);
                // Vassoi su alcuni tavoli
                if ((row + col) % 2 == 0) {
                    drawTray(g, x + 30, y - 15, 40, 30);
                }
            }
        }
        
        // Bancone della mensa
        g.setColor(new Color(180, 140, 100));
        g.fillRect(30, 70, WIDTH - 60, 60);
        g.setColor(FURNITURE_DARK);
        g.drawRect(30, 70, WIDTH - 60, 60);
        
        // Piatti e cibo stilizzati
        for (int i = 0; i < 6; i++) {
            g.setColor(new Color(220, 220, 220));
            g.fillOval(100 + i * 80, 85, 30, 30);
            g.setColor(new Color(200, 100, 50));
            g.fillOval(108 + i * 80, 93, 14, 14);
        }
        
        g.dispose();
        saveImage(img, "mensa.png");
    }
    
    private static void generateGiardini() {
        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        setupGraphics(g);
        
        // Cielo con gradiente
        GradientPaint sky = new GradientPaint(0, 0, new Color(135, 206, 250), 
                                               0, HEIGHT/2, new Color(200, 230, 255));
        g.setPaint(sky);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        // Prato
        g.setColor(new Color(100, 180, 100));
        g.fillRect(0, HEIGHT/2, WIDTH, HEIGHT/2);
        
        // Alberi
        for (int i = 0; i < 5; i++) {
            drawTree(g, 80 + i * 120, HEIGHT - 150);
        }
        
        // Panchine
        drawBench(g, 200, HEIGHT - 80, 80, 40);
        drawBench(g, WIDTH - 280, HEIGHT - 80, 80, 40);
        
        // Fontana al centro
        drawFountain(g, WIDTH/2 - 40, HEIGHT - 140, 80, 60);
        
        // Sole
        g.setColor(new Color(255, 220, 100));
        g.fillOval(WIDTH - 100, 40, 60, 60);
        
        // Titolo
        g.setColor(new Color(60, 100, 60));
        g.setFont(new Font("Serif", Font.BOLD, 24));
        drawOutlinedText(g, "GIARDINI", WIDTH/2 - 60, 40);
        
        g.dispose();
        saveImage(img, "giardini.png");
    }
    
    private static void generateSegreteria() {
        BufferedImage img = createBaseRoom(new Color(200, 100, 100), "SEGRETERIA");
        Graphics2D g = img.createGraphics();
        setupGraphics(g);
        
        // Bancone divisorio
        g.setColor(new Color(160, 80, 80));
        g.fillRect(0, HEIGHT/2 - 20, WIDTH, 60);
        g.setColor(FURNITURE_DARK);
        g.drawRect(0, HEIGHT/2 - 20, WIDTH, 60);
        
        // Sportelli
        for (int i = 0; i < 3; i++) {
            int x = 100 + i * 180;
            g.setColor(new Color(100, 100, 100));
            g.fillRect(x, HEIGHT/2 - 15, 120, 50);
            g.setColor(Color.BLACK);
            g.drawRect(x, HEIGHT/2 - 15, 120, 50);
            g.setFont(new Font("Sans", Font.BOLD, 10));
            g.drawString("SPORTELLO " + (i + 1), x + 15, HEIGHT/2 + 12);
        }
        
        // Sedie per l'attesa
        for (int i = 0; i < 6; i++) {
            drawChair(g, 80 + i * 80, HEIGHT - 100, 30, 30);
        }
        
        // Schedario
        drawFileCabinet(g, WIDTH - 120, HEIGHT - 140, 80, 100);
        
        // Cartello
        g.setColor(Color.WHITE);
        g.fillRect(WIDTH/2 - 80, 60, 160, 50);
        g.setColor(Color.RED);
        g.setFont(new Font("Sans", Font.BOLD, 12));
        g.drawString("PRENDERE IL", WIDTH/2 - 50, 85);
        g.drawString("NUMERO", WIDTH/2 - 30, 100);
        
        g.dispose();
        saveImage(img, "segreteria.png");
    }
    
    // ===== HELPER METHODS =====
    
    private static BufferedImage createBaseRoom(Color bgColor, String title) {
        BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        setupGraphics(g);
        
        // Sfondo base
        g.setColor(bgColor);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        // Pavimento con texture
        g.setColor(FLOOR_DARK);
        for (int y = HEIGHT - 150; y < HEIGHT; y += 20) {
            for (int x = 0; x < WIDTH; x += 40) {
                if ((x + y) % 80 == 0) {
                    g.fillRect(x, y, 40, 20);
                }
            }
        }
        
        // Titolo in alto
        g.setColor(new Color(255, 255, 255, 200));
        g.setFont(new Font("Serif", Font.BOLD, 24));
        drawOutlinedText(g, title, WIDTH/2 - title.length() * 7, 35);
        
        g.dispose();
        return img;
    }
    
    private static void setupGraphics(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }
    
    private static void drawRoundDoor(Graphics2D g, int x, int y, int w, int h) {
        g.setColor(FURNITURE_WOOD);
        g.fillRoundRect(x, y, w, h, w, w);
        g.setColor(FURNITURE_DARK);
        g.drawRoundRect(x, y, w, h, w, w);
        // Maniglia
        g.setColor(ACCENT_GOLD);
        g.fillOval(x + w - 15, y + h/2 - 5, 10, 10);
    }
    
    private static void drawCircularWindow(Graphics2D g, int x, int y, int radius) {
        g.setColor(new Color(135, 206, 250));
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        g.setColor(FURNITURE_DARK);
        g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
    }
    
    private static void drawFireplace(Graphics2D g, int x, int y, int w, int h) {
        g.setColor(new Color(60, 40, 30));
        g.fillRect(x, y, w, h);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, w, h);
        // Fuoco
        g.setColor(new Color(255, 150, 0));
        for (int i = 0; i < 5; i++) {
            g.fillOval(x + 10 + i * 8, y + h - 30 - (i % 3) * 5, 8, 15);
        }
    }
    
    private static void drawRoundTable(Graphics2D g, int x, int y, int diameter) {
        g.setColor(FURNITURE_WOOD);
        g.fillOval(x - diameter/2, y - diameter/4, diameter, diameter/2);
        g.setColor(FURNITURE_DARK);
        g.drawOval(x - diameter/2, y - diameter/4, diameter, diameter/2);
    }
    
    private static void drawBookshelf(Graphics2D g, int x, int y, int w, int h) {
        g.setColor(FURNITURE_WOOD);
        g.fillRect(x, y, w, h);
        g.setColor(FURNITURE_DARK);
        g.drawRect(x, y, w, h);
        // Libri
        Color[] bookColors = {
            new Color(150, 50, 50), new Color(50, 100, 150),
            new Color(100, 150, 50), new Color(150, 100, 50)
        };
        for (int shelf = 0; shelf < 4; shelf++) {
            int shelfY = y + 15 + shelf * (h/4);
            for (int book = 0; book < 6; book++) {
                g.setColor(bookColors[(shelf + book) % bookColors.length]);
                g.fillRect(x + 10 + book * 10, shelfY, 8, 25);
            }
        }
    }
    
    private static void drawTable(Graphics2D g, int x, int y, int w, int h) {
        g.setColor(FURNITURE_WOOD);
        g.fillRect(x, y, w, h/3);
        g.fillRect(x + 10, y + h/3, 10, h * 2/3);
        g.fillRect(x + w - 20, y + h/3, 10, h * 2/3);
        g.setColor(FURNITURE_DARK);
        g.drawRect(x, y, w, h/3);
    }
    
    private static void drawOpenBook(Graphics2D g, int x, int y, int w, int h) {
        g.setColor(new Color(240, 230, 200));
        g.fillRect(x, y, w, h);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, w, h);
        g.drawLine(x + w/2, y, x + w/2, y + h);
    }
    
    private static void drawLamp(Graphics2D g, int x, int y, int w, int h) {
        g.setColor(ACCENT_GOLD);
        g.fillRect(x + w/4, y + h/2, w/2, h/2);
        g.fillPolygon(new int[]{x, x + w, x + w/2}, new int[]{y + h/2, y + h/2, y}, 3);
        // Luce
        g.setColor(new Color(255, 255, 200, 100));
        g.fillOval(x - w, y - 10, w * 3, 30);
    }
    
    private static void drawDoor(Graphics2D g, int x, int y, int w, int h) {
        g.setColor(FURNITURE_WOOD);
        g.fillRect(x, y, w, h);
        g.setColor(FURNITURE_DARK);
        g.drawRect(x, y, w, h);
        g.fillOval(x + w - 12, y + h/2 - 3, 6, 6);
    }
    
    private static void drawWindow(Graphics2D g, int x, int y, int w, int h) {
        g.setColor(new Color(180, 220, 255));
        g.fillRect(x, y, w, h);
        g.setColor(FURNITURE_DARK);
        g.drawRect(x, y, w, h);
        g.drawLine(x + w/2, y, x + w/2, y + h);
        g.drawLine(x, y + h/2, x + w, y + h/2);
    }
    
    private static void drawCeilingLight(Graphics2D g, int x, int y) {
        g.setColor(new Color(255, 255, 200));
        g.fillOval(x - 15, y - 10, 30, 20);
        g.setColor(new Color(255, 255, 200, 50));
        g.fillOval(x - 40, y + 10, 80, 100);
    }
    
    private static void drawPodium(Graphics2D g, int x, int y, int w, int h) {
        g.setColor(FURNITURE_WOOD);
        g.fillPolygon(new int[]{x, x + w, x + w - 10, x + 10}, 
                      new int[]{y + h, y + h, y, y}, 4);
        g.setColor(FURNITURE_DARK);
        g.drawPolygon(new int[]{x, x + w, x + w - 10, x + 10}, 
                      new int[]{y + h, y + h, y, y}, 4);
    }
    
    private static void drawChair(Graphics2D g, int x, int y, int w, int h) {
        g.setColor(FURNITURE_WOOD);
        g.fillRect(x, y + h/2, w, h/4);
        g.fillRect(x + w/4, y + h * 3/4, w/8, h/4);
        g.fillRect(x + w * 5/8, y + h * 3/4, w/8, h/4);
        g.fillRect(x + w/4, y, w/2, h/2);
        g.setColor(FURNITURE_DARK);
        g.drawRect(x, y + h/2, w, h/4);
    }
    
    private static void drawDesk(Graphics2D g, int x, int y, int w, int h) {
        g.setColor(FURNITURE_WOOD);
        g.fillRect(x, y, w, h/3);
        g.fillRect(x, y + h/3, 15, h * 2/3);
        g.fillRect(x + w - 15, y + h/3, 15, h * 2/3);
        g.setColor(FURNITURE_DARK);
        g.drawRect(x, y, w, h/3);
    }
    
    private static void drawComputer(Graphics2D g, int x, int y, int w, int h) {
        // Monitor
        g.setColor(new Color(60, 60, 60));
        g.fillRect(x, y, w, h * 3/4);
        g.setColor(new Color(0, 200, 0));
        g.fillRect(x + 5, y + 5, w - 10, h * 3/4 - 10);
        // Base
        g.setColor(new Color(80, 80, 80));
        g.fillRect(x + w/4, y + h * 3/4, w/2, h/4);
    }
    
    private static void drawServerRack(Graphics2D g, int x, int y, int w, int h) {
        g.setColor(new Color(40, 40, 40));
        g.fillRect(x, y, w, h);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, w, h);
        // LED
        for (int i = 0; i < 6; i++) {
            g.setColor((i % 2 == 0) ? Color.GREEN : Color.RED);
            g.fillOval(x + 10, y + 15 + i * 18, 5, 5);
        }
    }
    
    private static void drawOfficeChair(Graphics2D g, int x, int y, int w, int h) {
        g.setColor(new Color(60, 60, 80));
        g.fillOval(x, y + h/2, w, h/3);
        g.fillRect(x + w/3, y, w/3, h/2);
        g.fillRect(x + w/4, y + h * 4/5, w/2, h/5);
    }
    
    private static void drawTray(Graphics2D g, int x, int y, int w, int h) {
        g.setColor(new Color(200, 200, 200));
        g.fillRect(x, y, w, h);
        g.setColor(new Color(200, 100, 50));
        g.fillOval(x + 5, y + 5, w/3, h - 10);
        g.setColor(new Color(100, 200, 100));
        g.fillOval(x + w/2, y + 5, w/3, h - 10);
    }
    
    private static void drawTree(Graphics2D g, int x, int y) {
        // Tronco
        g.setColor(FURNITURE_WOOD);
        g.fillRect(x - 10, y, 20, 60);
        // Chioma
        g.setColor(new Color(80, 150, 80));
        g.fillOval(x - 30, y - 40, 60, 60);
        g.fillOval(x - 25, y - 50, 50, 50);
    }
    
    private static void drawBench(Graphics2D g, int x, int y, int w, int h) {
        g.setColor(FURNITURE_WOOD);
        g.fillRect(x, y, w, h/3);
        g.fillRect(x, y + h/3, 10, h * 2/3);
        g.fillRect(x + w - 10, y + h/3, 10, h * 2/3);
        g.fillRect(x, y - h/4, 5, h/4);
        g.fillRect(x + w - 5, y - h/4, 5, h/4);
    }
    
    private static void drawFountain(Graphics2D g, int x, int y, int w, int h) {
        g.setColor(new Color(180, 180, 180));
        g.fillOval(x, y + h/2, w, h/2);
        g.setColor(new Color(100, 150, 200));
        g.fillOval(x + 5, y + h/2 + 5, w - 10, h/2 - 10);
        // Getto d'acqua
        g.setColor(new Color(150, 200, 255));
        for (int i = 0; i < 5; i++) {
            g.fillOval(x + w/2 - 2 + (i % 2) * 4, y + h/4 - i * 8, 4, 8);
        }
    }
    
    private static void drawFileCabinet(Graphics2D g, int x, int y, int w, int h) {
        g.setColor(new Color(100, 100, 100));
        g.fillRect(x, y, w, h);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, w, h);
        for (int i = 0; i < 4; i++) {
            g.drawLine(x, y + i * h/4, x + w, y + i * h/4);
            g.fillRect(x + w/2 - 5, y + 10 + i * h/4, 10, 5);
        }
    }
    
    private static void drawOutlinedText(Graphics2D g, String text, int x, int y) {
        Color original = g.getColor();
        g.setColor(Color.BLACK);
        g.drawString(text, x - 1, y - 1);
        g.drawString(text, x + 1, y - 1);
        g.drawString(text, x - 1, y + 1);
        g.drawString(text, x + 1, y + 1);
        g.setColor(original);
        g.drawString(text, x, y);
    }
    
    private static void saveImage(BufferedImage img, String filename) {
        try {
            File output = new File("assets/images/" + filename);
            ImageIO.write(img, "PNG", output);
            System.out.println("  ✓ " + filename);
        } catch (IOException e) {
            System.err.println("  ✗ Errore salvando " + filename + ": " + e.getMessage());
        }
    }
}
