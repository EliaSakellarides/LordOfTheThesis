# 🎵 Sistema Audio 8-bit - LORD OF THE THESIS 🎵

## ✅ SISTEMA IMPLEMENTATO!

Il gioco ora ha un **sistema audio completo** con musiche di sottofondo che cambiano ad ogni capitolo!

---

## 🎮 **COME FUNZIONA**

### **1. Cambio automatico musica**
Quando passi da un capitolo all'altro (scrivendo `avanti` e rispondendo agli enigmi), la musica cambia automaticamente:

| Capitolo | Immagine | Musica | Stile |
|----------|----------|--------|-------|
| **Cap 1** | Sauron | `intro.wav` | 🔥 Epico, dark, minaccioso |
| **Cap 2** | Contea | `contea.wav` | 🏡 Pastorale, rilassante |
| **Cap 3** | Bag End | `bagend.wav` | 🎂 Festoso, allegro |
| **Cap 4** | Nazgûl | `spettri.wav` | 🧛 Dark, horror, tensione |
| **Cap 5** | Granpasso | `granpasso.wav` | ⚔️ Eroico, speranza |
| **Cap 6** | Rivendell | `rivendell.wav` | 🏛️ Elfico, magico |
| **Cap 7** | Concilio | `concilio.wav` | 🗣️ Solenne, epico |

### **2. Controlli da tastiera**

Durante il gioco, usa questi tasti:

- **F1** = 🔉 Volume Giù (-10%)
- **F2** = 🔊 Volume Su (+10%)
- **F3** = 🔇 Mute/Unmute (silenzio on/off)
- **F5** = 👁️ Guarda (comando)
- **F12** = ❓ Aiuto (mostra comandi)

### **3. Loop continuo**

Ogni musica va in **loop infinito** finché non cambi capitolo. Il cambio è automatico e fluido!

---

## 📁 **FILE NECESSARI**

### **Struttura cartelle:**
```
/tmp/LordOfTheThesis/
├── assets/
│   ├── images/        ← Immagini pixel art (già presente)
│   └── music/         ← Musiche 8-bit (NUOVO!)
│       ├── intro.wav
│       ├── contea.wav
│       ├── bagend.wav
│       ├── spettri.wav
│       ├── granpasso.wav
│       ├── rivendell.wav
│       └── concilio.wav
```

### **Formato audio supportato:**
- **WAV** (raccomandato) - `.wav`
- **MIDI** (opzionale) - `.mid`
- **Sample rate**: 22050 Hz o 44100 Hz
- **Canali**: Stereo o Mono
- **Bitrate**: 8-bit o 16-bit

---

## 🎹 **DOVE TROVARE/CREARE MUSICHE 8-BIT**

### **Opzione 1: Scaricare musiche gratuite**

#### **Siti consigliati:**
1. **Freesound.org** 
   - https://freesound.org
   - Ricerca: "8-bit fantasy", "chiptune", "retro game"
   - Licenze: Creative Commons (controlla sempre)

2. **OpenGameArt.org**
   - https://opengameart.org/art-search-advanced?keys=&field_art_type_tid%5B%5D=12
   - Sezione Music → Filtro "chiptune"
   - Licenze: CC0, CC-BY

3. **Incompetech.com**
   - https://incompetech.com/music/royalty-free/music.html
   - Cerca categorie: "Fantasy", "Dramatic"
   - Royalty-free

4. **ModArchive.org**
   - https://modarchive.org
   - Scarica file `.mod` o `.xm`
   - Converti in WAV con Audacity

### **Opzione 2: Generare con AI**

#### **AI Music Generators:**
1. **Suno AI** (https://suno.ai)
   - Prompt esempio: 
     - "epic 8-bit dark fantasy boss battle chiptune 30 seconds loop"
     - "pastoral 8-bit village theme relaxing chiptune loop"
   
2. **Udio** (https://udio.com)
   - Genera musica da testo
   - Specifica "8-bit", "chiptune", "NES-style"

3. **Soundraw** (https://soundraw.io)
   - Editor con preset 8-bit
   - Personalizzabile

### **Opzione 3: Creare musica da zero**

#### **Tool gratuiti:**
1. **BeepBox** (https://beepbox.co)
   - Editor online gratuito
   - Interface facile
   - Esporta WAV direttamente
   - ⭐ **PIÙ FACILE PER PRINCIPIANTI**

2. **FamiTracker** (Windows)
   - Per musica NES autentica
   - Più complesso ma potente

3. **Bosca Ceoil** (Desktop)
   - https://boscaceoil.net
   - Semplice e divertente
   - Esporta WAV

4. **LMMS** (Desktop - gratuito)
   - https://lmms.io
   - DAW completo
   - Plugin synth 8-bit inclusi

### **Opzione 4: Convertire musiche esistenti**

#### **Da YouTube:**
1. Cerca: "LOTR 8-bit cover", "Skyrim chiptune"
2. Scarica con youtube-dl o siti online
3. Converti in WAV con **Audacity**

#### **Tool di conversione:**
- **Audacity** (gratuito) - https://audacityteam.org
  - Apri file MP3/FLAC/OGG
  - Esporta come WAV
  - Ritaglia a 30-60 secondi
  - Crea loop seamless (fade in/out)

---

## 🧪 **TESTARE SUBITO (senza musiche)**

Il gioco funziona anche **SENZA** file audio! 

Se i file `.wav` non esistono, vedrai solo questo nella console:
```
⚠️ File musicale non trovato: assets/music/intro.wav
```

Ma il gioco continuerà normalmente!

### **Test rapido con suoni di sistema (macOS):**

```bash
cd /tmp/LordOfTheThesis/assets/music

# Copia suoni di sistema come test temporaneo
cp /System/Library/Sounds/Glass.aiff intro.wav
cp /System/Library/Sounds/Submarine.aiff spettri.wav
cp /System/Library/Sounds/Purr.aiff contea.wav
```

Poi riavvia il gioco e sentirai i suoni!

---

## 🔧 **CODICE IMPLEMENTATO**

### **Nuovi file creati:**

1. **`src/com/lordofthethesis/audio/AudioManager.java`**
   - Gestisce caricamento, riproduzione, loop musiche
   - Controllo volume (0-100%)
   - Mute/unmute
   - Supporto WAV/MIDI

2. **Modifiche a `GameEngine.java`:**
   - Import `AudioManager`
   - Campo `audioManager`
   - Metodo `updateRoomByChapter()` aggiornato per cambiare musica
   - Metodi pubblici: `volumeUp()`, `volumeDown()`, `toggleMute()`

3. **Modifiche a `FullScreenGUI.java`:**
   - Tasti F1/F2/F3 per controllo volume
   - Messaggio aiuto aggiornato

---

## 🎼 **ESEMPI DI PROMPT PER AI**

Se usi **Suno AI** o **Udio**, copia questi prompt:

### **1. intro.wav (Sauron)**
```
Epic 8-bit dark fantasy boss battle music, ominous orchestral chiptune, 
NES style, dramatic drums, minor key, 30 seconds seamless loop, retro game soundtrack
```

### **2. contea.wav (Shire)**
```
Peaceful 8-bit pastoral village theme, relaxing chiptune, happy melody, 
green fields, hobbit vibe, Zelda-style, 30 seconds loop, retro RPG music
```

### **3. bagend.wav (Party)**
```
Joyful 8-bit celebration party music, festive chiptune, upbeat dance, 
Super Mario party theme style, 30 seconds loop, retro game
```

### **4. spettri.wav (Nazgûl)**
```
Dark 8-bit horror chase music, tense chiptune, spooky atmosphere, 
minor key, fast tempo, Silent Hill style, 30 seconds loop, retro game
```

### **5. granpasso.wav (Hero)**
```
Heroic 8-bit adventure theme, epic chiptune, hope and courage, 
Final Fantasy hero music style, triumphant melody, 30 seconds loop
```

### **6. rivendell.wav (Elven)**
```
Ethereal 8-bit elven magic music, mystical chiptune, peaceful fantasy, 
Secret of Mana style, harp-like sounds, 30 seconds loop, retro RPG
```

### **7. concilio.wav (Council)**
```
Solemn 8-bit dramatic council theme, epic chiptune, serious atmosphere, 
destiny and decision music, orchestral 8-bit, 30 seconds loop, retro game
```

---

## 📊 **STATUS IMPLEMENTAZIONE**

### ✅ **COMPLETATO:**
- [x] Classe `AudioManager` creata
- [x] Integrazione con `GameEngine`
- [x] Cambio automatico musica per capitolo
- [x] Controlli volume F1/F2/F3
- [x] Mute/unmute
- [x] Loop infinito
- [x] Cartella `assets/music/` creata
- [x] Sistema compila e funziona

### ⏳ **DA FARE (opzionale):**
- [ ] Scaricare/generare 7 tracce musicali
- [ ] Testare audio con file reali
- [ ] Ottimizzare transizioni tra tracce
- [ ] Aggiungere effetti sonori (SFX) per azioni

---

## 🚀 **PROSSIMI PASSI**

### **1. Testare il sistema (ADESSO):**
```bash
cd /tmp/LordOfTheThesis
./scripts/run.sh
```

Premi **F1/F2/F3** per testare i controlli volume (anche senza musica funzionano).

### **2. Aggiungere musiche:**

Scegli una delle opzioni sopra:
- Scarica da Freesound/OpenGameArt
- Genera con AI (Suno/Udio)
- Crea con BeepBox
- Converti da YouTube

### **3. Rinomina e copia:**
```bash
# Esempio:
mv ~/Downloads/epic_8bit_1.wav /tmp/LordOfTheThesis/assets/music/intro.wav
mv ~/Downloads/peaceful_village.wav /tmp/LordOfTheThesis/assets/music/contea.wav
# ... etc
```

### **4. Riavvia e goditi il gioco con musica! 🎵**

---

## 🎮 **COMANDI COMPLETI**

Una volta avviato il gioco:

### **Comandi di gioco:**
- `avanti` = Inizia prossimo capitolo
- `rispondi [risposta]` = Rispondere enigma
- `inventario` = Vedere zaino
- `aiuto` = Mostra aiuto
- `dove` / `stato` = Progresso storia
- `esci` = Uscire

### **Controlli audio (tasti):**
- **F1** = Volume -10%
- **F2** = Volume +10%
- **F3** = Mute/Unmute

### **Altro:**
- **F5** = Comando "guarda"
- **F12** = Aiuto rapido

---

## 💡 **TIPS**

1. **Loop seamless**: Usa Audacity per creare fade-in/fade-out alle estremità del file WAV così il loop è più fluido.

2. **Dimensioni file**: I file WAV 8-bit sono piccoli (< 1MB per 30 sec), perfetti per il gioco!

3. **Volume di default**: Il volume iniziale è 70%. Puoi modificarlo in `AudioManager.java` alla riga 10: `private float volume = 0.7f;`

4. **Musica mancante**: Se un file non esiste, il gioco continua senza problemi!

5. **Test veloce**: Per testare subito, rinomina qualsiasi file WAV che hai e mettilo in `assets/music/intro.wav`

---

## 🎉 **CONCLUSIONE**

Il sistema audio è **pronto e funzionante**! 

Ora devi solo:
1. ✅ Avviare il gioco (`./scripts/run.sh`)
2. ✅ Testare i controlli (F1/F2/F3)
3. 🎵 Aggiungere le 7 tracce musicali
4. 🎮 Goderti l'avventura con sottofondo epico!

**Buon divertimento nella Terra di Mezzo! 🧙‍♂️🌋💍**
