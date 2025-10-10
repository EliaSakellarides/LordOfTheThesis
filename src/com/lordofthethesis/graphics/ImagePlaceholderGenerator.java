package com.lordofthethesis.graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Utility per generare immagini placeholder semplici
 * Sostituisci questi placeholder con tue immagini JPEG/PNG!
 */
public class ImagePlaceholderGenerator {
    
    public static void generatePlaceholders(String outputDir) {
        try {
            generateImage(outputDir + "/contea.png", "LA CONTEA", new Color(139, 90, 60));
            generateImage(outputDir + "/biblioteca.png", "BIBLIOTECA", new Color(200, 180, 140));
            generateImage(outputDir + "/corridoi.png", "CORRIDOI", new Color(180, 180, 180));
            generateImage(outputDir + "/aulamagna.png", "AULA MAGNA", new Color(100, 150, 200));
            generateImage(outputDir + "/laboratorio.png", "LABORATORIO", new Color(100, 200, 255));
            generateImage(outputDir + "/ufficio.png", "UFFICIO GANDALF", new Color(150, 100, 200));
            generateImage(outputDir + "/mensa.png", "MENSA", new Color(255, 150, 50));
            generateImage(outputDir + "/giardini.png", "GIARDINI", new Color(100, 255, 100));
            generateImage(outputDir + "/segreteria.png", "SEGRETERIA", new Color(255, 50, 50));
            System.out.println("✓ Placeholder generati in: " + outputDir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void generateImage(String path, String title, Color bgColor) throws Exception {
        int w = 400, h = 300;
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = img.createGraphics();
        
        // Sfondo
        g.setColor(bgColor);
        g.fillRect(0, 0, w, h);
        
        // Pattern semplice
        g.setColor(new Color(0, 0, 0, 30));
        for (int i = 0; i < 20; i++) {
            g.fillRect(i * 25, 0, 10, h);
        }
        
        // Bordo
        g.setColor(Color.BLACK);
        g.drawRect(5, 5, w - 10, h - 10);
        
        // Titolo
        g.setFont(new Font("Serif", Font.BOLD, 32));
        FontMetrics fm = g.getFontMetrics();
        int titleWidth = fm.stringWidth(title);
        g.setColor(Color.WHITE);
        g.drawString(title, (w - titleWidth) / 2, h / 2);
        g.setColor(Color.BLACK);
        g.drawString(title, (w - titleWidth) / 2 + 2, h / 2 + 2);
        
        // Testo suggerimento
        g.setFont(new Font("SansSerif", Font.PLAIN, 12));
        String hint = "[Sostituisci con tua immagine JPEG/PNG]";
        int hintWidth = g.getFontMetrics().stringWidth(hint);
        g.setColor(new Color(255, 255, 255, 180));
        g.drawString(hint, (w - hintWidth) / 2, h - 30);
        
        g.dispose();
        
        // Salva
        ImageIO.write(img, "png", new File(path));
    }
    
    public static void main(String[] args) {
        generatePlaceholders("assets/images");
    }
}
