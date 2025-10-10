package com.lordofthethesis.graphics;

import java.awt.*;
import javax.swing.*;

/**
 * Gestore delle immagini pixellate ASCII per le scene del gioco
 */
public class PixelArtManager {
    
    // Colori per le scene
    public static final Color COLOR_GRASS = new Color(34, 139, 34);
    public static final Color COLOR_SKY = new Color(135, 206, 235);
    public static final Color COLOR_STONE = new Color(128, 128, 128);
    public static final Color COLOR_GOLD = new Color(255, 215, 0);
    public static final Color COLOR_DARK = new Color(40, 40, 40);
    public static final Color COLOR_FIRE = new Color(255, 69, 0);
    
    /**
     * Ritorna l'arte ASCII per una stanza specifica
     */
    public static String getRoomArt(String roomKey) {
        switch (roomKey.toLowerCase()) {
            case "contea":
                return getConteaArt();
            case "biblioteca":
                return getBibliotecaArt();
            case "corridoi":
                return getCorridoiArt();
            case "aulamagna":
                return getAulaMagnaArt();
            case "laboratorio":
                return getLaboratorioArt();
            case "ufficio":
                return getUfficioArt();
            case "mensa":
                return getMensaArt();
            case "giardini":
                return getGiardiniArt();
            case "segreteria":
                return getSegreteriaArt();
            default:
                return getDefaultArt();
        }
    }
    
    /**
     * La Contea - La tua stanza da studente
     */
    private static String getConteaArt() {
        return 
        "╔════════════════════════════════════════╗\n" +
        "║     🏠 LA CONTEA (Tua Stanza) 🏠      ║\n" +
        "╠════════════════════════════════════════╣\n" +
        "║  ┌─────────┐        ___________       ║\n" +
        "║  │ ▓▓▓▓▓▓▓ │       |  POSTER  |      ║\n" +
        "║  │ ▓▓███▓▓ │       | ALGORITMI |      ║\n" +
        "║  │ ▓▓▓▓▓▓▓ │       |___________|      ║\n" +
        "║  │  LETTO  │                          ║\n" +
        "║  └─────────┘         ┌──┐             ║\n" +
        "║                      │PC│ ☕          ║\n" +
        "║   📚📚📚            └──┘             ║\n" +
        "║   LIBRI              SCRIVANIA        ║\n" +
        "║                                        ║\n" +
        "║   📄 [LA TUA TESI È QUI!] 📄         ║\n" +
        "╚════════════════════════════════════════╝";
    }
    
    /**
     * Biblioteca Universitaria
     */
    private static String getBibliotecaArt() {
        return
        "╔════════════════════════════════════════╗\n" +
        "║      📚 BIBLIOTECA UNIVERSITARIA 📚    ║\n" +
        "╠════════════════════════════════════════╣\n" +
        "║  ║║║║      ___________      ║║║║      ║\n" +
        "║  ║║║║     |           |     ║║║║      ║\n" +
        "║  ████     | SILENZIO! |     ████      ║\n" +
        "║  ████     |___________|     ████      ║\n" +
        "║  ████         📖            ████      ║\n" +
        "║  ████      ┌─────┐          ████      ║\n" +
        "║  ████      │ 😴  │          ████      ║\n" +
        "║  SCAFF     │     │          SCAFF     ║\n" +
        "║  FALI      └─────┘          FALI      ║\n" +
        "║          STUDENTE CHE DORME           ║\n" +
        "║   ☕☕☕     💻💻💻    📚📚📚        ║\n" +
        "╚════════════════════════════════════════╝";
    }
    
    /**
     * Corridoi del Dipartimento
     */
    private static String getCorridoiArt() {
        return
        "╔════════════════════════════════════════╗\n" +
        "║    🚪 CORRIDOI DEL DIPARTIMENTO 🚪    ║\n" +
        "╠════════════════════════════════════════╣\n" +
        "║                                        ║\n" +
        "║  ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓   ║\n" +
        "║  ▓ 🚪 LAB1  🚪 LAB2  🚪 LAB3  🚪 ▓   ║\n" +
        "║  ▓                                ▓   ║\n" +
        "║  ▓         ═══════════════        ▓   ║\n" +
        "║  ▓         CORRIDOIO LUNGO        ▓   ║\n" +
        "║  ▓         ═══════════════        ▓   ║\n" +
        "║  ▓                                ▓   ║\n" +
        "║  ▓ 🚪 SALA  🚪 UFFICI 🚪 AULE 🚪 ▓   ║\n" +
        "║  ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓   ║\n" +
        "║        🖥️  Server Room  🖥️           ║\n" +
        "╚════════════════════════════════════════╝";
    }
    
    /**
     * Aula Magna
     */
    private static String getAulaMagnaArt() {
        return
        "╔════════════════════════════════════════╗\n" +
        "║         🎓 AULA MAGNA 🎓              ║\n" +
        "╠════════════════════════════════════════╣\n" +
        "║    ┌─────────────────────────┐        ║\n" +
        "║    │  LAVAGNA INTERATTIVA    │        ║\n" +
        "║    │   f(x) = x² + 2x + 1    │        ║\n" +
        "║    └─────────────────────────┘        ║\n" +
        "║         👨‍🏫 PROFESSORE             ║\n" +
        "║                                        ║\n" +
        "║    💺💺💺💺💺💺💺💺💺💺              ║\n" +
        "║    💺💺💺💺💺💺💺💺💺💺              ║\n" +
        "║    💺💺💺💺💺💺💺💺💺💺              ║\n" +
        "║    👨‍🎓👨‍🎓👨‍🎓  STUDENTI           ║\n" +
        "║    Capacità: 200 posti                ║\n" +
        "╚════════════════════════════════════════╝";
    }
    
    /**
     * Laboratorio di Informatica
     */
    private static String getLaboratorioArt() {
        return
        "╔════════════════════════════════════════╗\n" +
        "║    💻 LABORATORIO INFORMATICA 💻      ║\n" +
        "╠════════════════════════════════════════╣\n" +
        "║  🖥️ 💻 🖥️ 💻 🖥️ 💻 🖥️ 💻          ║\n" +
        "║  ▓  ▓  ▓  ▓  ▓  ▓  ▓  ▓              ║\n" +
        "║                                        ║\n" +
        "║  ┌────┐  ┌────┐  ┌────┐  ┌────┐      ║\n" +
        "║  │💀  │  │ OK │  │💀  │  │ OK │      ║\n" +
        "║  │BSOD│  │    │  │BSOD│  │    │      ║\n" +
        "║  └────┘  └────┘  └────┘  └────┘      ║\n" +
        "║  BROKEN   WORK   BROKEN   WORK        ║\n" +
        "║                                        ║\n" +
        "║  🔧 ZONA RIPARAZIONE 🔧               ║\n" +
        "║     \"Have you tried reboot?\"          ║\n" +
        "╚════════════════════════════════════════╝";
    }
    
    /**
     * Ufficio del Professor Gandalf
     */
    private static String getUfficioArt() {
        return
        "╔════════════════════════════════════════╗\n" +
        "║   🧙 UFFICIO PROF. GANDALF 🧙         ║\n" +
        "╠════════════════════════════════════════╣\n" +
        "║  ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓    ║\n" +
        "║  ▓ 📚📚    ╔═══════╗    📚📚 ▓    ║\n" +
        "║  ▓ 📚📚    ║DIPLOMA║    📚📚 ▓    ║\n" +
        "║  ▓ 📚📚    ╚═══════╝    📚📚 ▓    ║\n" +
        "║  ▓                              ▓    ║\n" +
        "║  ▓      ┌──────────────┐       ▓    ║\n" +
        "║  ▓      │  SCRIVANIA   │       ▓    ║\n" +
        "║  ▓      │  📄📄☕🖊️  │       ▓    ║\n" +
        "║  ▓      └──────────────┘       ▓    ║\n" +
        "║  ▓         🧙‍♂️ GANDALF        ▓    ║\n" +
        "║  ▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓▓    ║\n" +
        "╚════════════════════════════════════════╝";
    }
    
    /**
     * Mensa Universitaria
     */
    private static String getMensaArt() {
        return
        "╔════════════════════════════════════════╗\n" +
        "║      🍽️ MENSA UNIVERSITARIA 🍽️       ║\n" +
        "╠════════════════════════════════════════╣\n" +
        "║  ╔══════════════════════════════╗     ║\n" +
        "║  ║  🍝 🍕 🥗 ☕ 🍰 🥤 🍎      ║     ║\n" +
        "║  ║      ZONA SERVIZIO           ║     ║\n" +
        "║  ╚══════════════════════════════╝     ║\n" +
        "║                                        ║\n" +
        "║   🪑🍽️🪑    🪑🍽️🪑    🪑🍽️🪑       ║\n" +
        "║   👨‍🎓👨‍🎓    👨‍🎓👨‍🎓    👨‍🎓👨‍🎓     ║\n" +
        "║   TAVOLO 1   TAVOLO 2   TAVOLO 3      ║\n" +
        "║                                        ║\n" +
        "║   💬 \"Il cibo è...commestibile\"       ║\n" +
        "║   💬 \"Almeno costa poco!\"              ║\n" +
        "╚════════════════════════════════════════╝";
    }
    
    /**
     * Giardini dell'Università
     */
    private static String getGiardiniArt() {
        return
        "╔════════════════════════════════════════╗\n" +
        "║     🌳 GIARDINI UNIVERSITÀ 🌳         ║\n" +
        "╠════════════════════════════════════════╣\n" +
        "║        ☁️    ☁️       ☁️              ║\n" +
        "║     ☀️   CIELO SERENO  ☀️             ║\n" +
        "║                                        ║\n" +
        "║   🌳      🌳      🌳      🌳          ║\n" +
        "║      🌺🌺    🌺🌺    🌺🌺            ║\n" +
        "║   🌸🌸🌸   🌸🌸🌸   🌸🌸🌸         ║\n" +
        "║                                        ║\n" +
        "║      ⛲ FONTANA CENTRALE ⛲            ║\n" +
        "║      ~~~~  ~~~~  ~~~~  ~~~~            ║\n" +
        "║                                        ║\n" +
        "║   🧘 Studenti che si rilassano 📖     ║\n" +
        "╚════════════════════════════════════════╝";
    }
    
    /**
     * Segreteria - Monte Fato
     */
    private static String getSegreteriaArt() {
        return
        "╔════════════════════════════════════════╗\n" +
        "║  🏛️ SEGRETERIA DIPARTIMENTO 🏛️       ║\n" +
        "║      「DESTINAZIONE FINALE」            ║\n" +
        "╠════════════════════════════════════════╣\n" +
        "║                                        ║\n" +
        "║      ╔════════════════════╗            ║\n" +
        "║      ║  DEPOSITO TESI     ║            ║\n" +
        "║      ║   ▓▓▓▓▓▓▓▓▓▓▓▓   ║            ║\n" +
        "║      ║   ▓ 🔥🔥🔥 ▓   ║            ║\n" +
        "║      ║   ▓ 📄📄📄 ▓   ║            ║\n" +
        "║      ║   ▓▓▓▓▓▓▓▓▓▓▓▓   ║            ║\n" +
        "║      ╚════════════════════╝            ║\n" +
        "║                                        ║\n" +
        "║   \"Qui si conclude il tuo viaggio\"    ║\n" +
        "║   \"Consegna la tesi per laurearti!\"   ║\n" +
        "╚════════════════════════════════════════╝";
    }
    
    /**
     * Arte di default
     */
    public static String getDefaultArt() {
        return
        "╔════════════════════════════════════════╗\n" +
        "║      LORD OF THE THESIS                ║\n" +
        "║      Un'avventura accademica           ║\n" +
        "╚════════════════════════════════════════╝";
    }
    
    /**
     * La Tesi in "lingua nera di Mordor"
     */
    public static String getThesisInMordorLanguage() {
        return
        "╔══════════════════════════════════════════╗\n" +
        "║    ⚫ LA TESI DI LAUREA ⚫               ║\n" +
        "║                                          ║\n" +
        "║   ᚱᛟᚹᛖᚱᛖ  ᚦᛖᛋᛁᛋ  ᛏᛟ  ᚱᚢᛚᛖ  ᚦᛖᛗ  ᚨᛚᛚ     ║\n" +
        "║   「Una tesi per controllarli tutti」    ║\n" +
        "║                                          ║\n" +
        "║   ꙮꙮꙮ  ᛞᚨᚱᚲ  ᚲᚾᛟᚹᛚᛖᛞᚷᛖ  ꙮꙮꙮ           ║\n" +
        "║   「Conoscenza oscura e accademica」     ║\n" +
        "║                                          ║\n" +
        "║      ╔═══════════════════╗              ║\n" +
        "║      ║ CAPITOLO I: INTRO ║              ║\n" +
        "║      ║ CAPITOLO II: ...  ║              ║\n" +
        "║      ║ CAPITOLO III: ... ║              ║\n" +
        "║      ║ CONCLUSIONI       ║              ║\n" +
        "║      ╚═══════════════════╝              ║\n" +
        "║                                          ║\n" +
        "║   ⚠️ MANEGGIARE CON CURA ⚠️            ║\n" +
        "║   Mesi di lavoro in questo documento!   ║\n" +
        "╚══════════════════════════════════════════╝";
    }
    
    /**
     * Icone per oggetti
     */
    public static String getItemIcon(String itemName) {
        switch (itemName.toLowerCase()) {
            case "tesi":
                return "📜";
            case "caffè":
            case "caffe":
                return "☕";
            case "librone":
                return "📚";
            case "pennausb":
                return "💾";
            default:
                return "📦";
        }
    }
    
    /**
     * Icone per personaggi
     */
    public static String getCharacterIcon(String characterName) {
        switch (characterName.toLowerCase()) {
            case "gandalf":
                return "🧙‍♂️";
            case "elrond":
                return "👨‍🏫";
            case "frodo":
                return "👨‍🎓";
            case "gimli":
                return "🔧";
            case "galadriel":
                return "👩‍💼";
            default:
                return "👤";
        }
    }
    
    /**
     * Banner di vittoria
     */
    public static String getVictoryBanner() {
        return
        "╔══════════════════════════════════════════╗\n" +
        "║                                          ║\n" +
        "║        🎓 HAI VINTO! 🎓                 ║\n" +
        "║                                          ║\n" +
        "║      ⭐⭐⭐⭐⭐⭐⭐⭐⭐⭐            ║\n" +
        "║                                          ║\n" +
        "║   LA TESI È STATA CONSEGNATA!           ║\n" +
        "║                                          ║\n" +
        "║        🎉 CONGRATULAZIONI! 🎉           ║\n" +
        "║                                          ║\n" +
        "║   Sei ufficialmente un LAUREANDO!       ║\n" +
        "║                                          ║\n" +
        "║      🏆 MISSIONE COMPLETATA 🏆          ║\n" +
        "║                                          ║\n" +
        "╚══════════════════════════════════════════╝";
    }
    
    /**
     * Crea un pannello JTextArea preformattato per mostrare l'arte ASCII
     */
    public static JTextArea createArtPanel(String art) {
        JTextArea panel = new JTextArea(art);
        panel.setEditable(false);
        panel.setFont(new Font("Monospaced", Font.PLAIN, 11));
        panel.setBackground(new Color(20, 20, 20));
        panel.setForeground(new Color(0, 255, 0)); // Verde stile terminal
        panel.setMargin(new Insets(10, 10, 10, 10));
        return panel;
    }

    /**
     * Ritorna una semplice icona pixel art che richiama il segno degli anelli.
     * Il risultato                       
     */
    public static javax.swing.ImageIcon getRingSigilIcon() {
        int size = 128;
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(size, size, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D g = img.createGraphics();
        g.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_OFF);
        // Background
        g.setColor(new Color(30, 20, 10, 0));
        g.fillRect(0, 0, size, size);

        // Draw multiple rings with pixel-like stroke
        int center = size/2;
        for (int r = 48; r <= 58; r += 4) {
            for (int a = 0; a < 360; a += 8) {
                double rad = Math.toRadians(a);
                int x = center + (int)(r * Math.cos(rad));
                int y = center + (int)(r * Math.sin(rad));
                g.setColor((a % 16 == 0) ? COLOR_GOLD.brighter() : COLOR_GOLD);
                g.fillRect(x-2, y-2, 4, 4);
            }
        }

        // Inner dark disc
        g.setColor(COLOR_DARK);
        g.fillOval(center-34, center-34, 68, 68);

        // Decorative runes (stylized pixels)
        g.setColor(COLOR_FIRE);
        g.fillRect(center-2, 12, 6, 6);
        g.fillRect(12, center-2, 6, 6);
        g.fillRect(size-20, center-2, 6, 6);

        g.dispose();
        return new javax.swing.ImageIcon(img);
    }

    /**
     * Ritorna una piccola icona che rappresenta la tesi (per inventario)
     */
    public static javax.swing.ImageIcon getThesisIcon() {
        int w = 48, h = 36;
        java.awt.image.BufferedImage img = new java.awt.image.BufferedImage(w, h, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics2D g = img.createGraphics();
        g.setColor(new Color(240,240,220));
        g.fillRect(2,2,w-4,h-4);
        g.setColor(Color.BLACK);
        g.drawRect(2,2,w-4,h-4);
        g.setColor(new Color(200,50,50));
        g.fillRect(6,6, w-12, 6);
        g.dispose();
        return new javax.swing.ImageIcon(img);
    }

    /**
     * Ritorna ascii art del sigillo per la modalità testo
     */
    public static String getRingSigilArt() {
        return "   _____\n  /     \\\n /  /\\\\  \\\n|  |  ||  |\n \\  \\//  /\n  \\_____/";
    }
}
