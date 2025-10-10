package com.lordofthethesis.audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Audio system manager for background music playback
 * Supports WAV files with continuous looping and volume control
 * 
 * @author Lord of the Thesis Development Team
 */
public class AudioManager {
    private Clip currentClip;
    private String currentMusicFile;
    private float volume = 0.7f; // Volume di default (0.0 a 1.0)
    private boolean muted = false;
    
    /**
     * Riproduce un file musicale in loop
     * @param musicFile Nome del file (es. "intro.wav") nella cartella assets/music/
     */
    public void playMusic(String musicFile) {
        // Se sta già suonando la stessa musica, non fare nulla
        if (currentMusicFile != null && currentMusicFile.equals(musicFile)) {
            return;
        }
        
        // Ferma la musica corrente
        stopMusic();
        
        try {
            // Percorso del file musicale
            String path = "assets/music/" + musicFile;
            File audioFile = new File(path);
            
            if (!audioFile.exists()) {
                System.err.println("⚠️ File musicale non trovato: " + path);
                return;
            }
            
            // Carica il file audio
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            currentClip = AudioSystem.getClip();
            currentClip.open(audioStream);
            
            // Imposta volume
            setVolume(volume);
            
            // Loop infinito
            currentClip.loop(Clip.LOOP_CONTINUOUSLY);
            
            currentMusicFile = musicFile;
            System.out.println("🎵 Musica in riproduzione: " + musicFile);
            
        } catch (UnsupportedAudioFileException e) {
            System.err.println("❌ Formato audio non supportato: " + musicFile);
        } catch (IOException e) {
            System.err.println("❌ Errore lettura file: " + musicFile);
        } catch (LineUnavailableException e) {
            System.err.println("❌ Linea audio non disponibile");
        }
    }
    
    /**
     * Ferma la musica corrente
     */
    public void stopMusic() {
        if (currentClip != null && currentClip.isRunning()) {
            currentClip.stop();
            currentClip.close();
            currentClip = null;
            currentMusicFile = null;
            System.out.println("⏹️ Musica fermata");
        }
    }
    
    /**
     * Imposta il volume (0.0 = silenzio, 1.0 = massimo)
     */
    public void setVolume(float newVolume) {
        volume = Math.max(0.0f, Math.min(1.0f, newVolume)); // Clamp 0-1
        
        if (currentClip != null && currentClip.isOpen()) {
            try {
                FloatControl volumeControl = (FloatControl) currentClip.getControl(FloatControl.Type.MASTER_GAIN);
                // Converti 0-1 in decibel (-80dB a 6dB circa)
                float dB = (float) (Math.log10(volume) * 20.0);
                volumeControl.setValue(dB);
            } catch (IllegalArgumentException e) {
                System.err.println("⚠️ Impossibile impostare volume");
            }
        }
    }
    
    /**
     * Aumenta il volume del 10%
     */
    public void volumeUp() {
        setVolume(volume + 0.1f);
        System.out.println("🔊 Volume: " + Math.round(volume * 100) + "%");
    }
    
    /**
     * Diminuisce il volume del 10%
     */
    public void volumeDown() {
        setVolume(volume - 0.1f);
        System.out.println("🔉 Volume: " + Math.round(volume * 100) + "%");
    }
    
    /**
     * Mute/unmute
     */
    public void toggleMute() {
        if (currentClip != null && currentClip.isOpen()) {
            if (muted) {
                setVolume(0.7f); // Ripristina volume
                muted = false;
                System.out.println("🔊 Audio riattivato");
            } else {
                setVolume(0.0f); // Silenzia
                muted = true;
                System.out.println("🔇 Audio silenziato");
            }
        }
    }
    
    /**
     * Verifica se una musica è in riproduzione
     */
    public boolean isPlaying() {
        return currentClip != null && currentClip.isRunning();
    }
    
    /**
     * Ottiene il nome del file musicale corrente
     */
    public String getCurrentMusicFile() {
        return currentMusicFile;
    }
}
