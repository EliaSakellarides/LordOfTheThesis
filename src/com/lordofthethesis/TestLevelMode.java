package com.lordofthethesis;

import com.lordofthethesis.engine.GameEngine;

public class TestLevelMode {
    public static void main(String[] args) {
        GameEngine engine = new GameEngine();
        engine.initializeGame("Tester");
        System.out.println("Init: " + engine.getLastLog());

        // Avvia livelli
        String res1 = engine.processCommand("inizia livelli");
        System.out.println("Inizia: " + res1);

        // Rispondi correttamente al primo livello (1+1 = 2)
        String res2 = engine.processCommand("rispondi 2");
        System.out.println("Rispondi 1: " + res2);

        // Rispondi correttamente al secondo livello (colore cielo = azzurro)
        String res3 = engine.processCommand("rispondi azzurro");
        System.out.println("Rispondi 2: " + res3);

        // Rispondi correttamente al terzo livello (prima lettera di 'tesi' = t)
        String res4 = engine.processCommand("rispondi t");
        System.out.println("Rispondi 3: " + res4);
    }
}
