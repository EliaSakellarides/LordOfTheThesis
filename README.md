# 🌋 Lord of the Thesis - La Compagnia della Tesi

**Un'avventura narrativa epica ispirata a Il Signore degli Anelli**

![Java](https://img.shields.io/badge/Java-21-orange.svg)
![License](https://img.shields.io/badge/License-Educational-blue.svg)
![Platform](https://img.shields.io/badge/Platform-Cross--platform-green.svg)

---

## 📖 Descrizione

**Lord of the Thesis** è un'avventura grafica testuale che combina la narrativa epica di J.R.R. Tolkien con l'ambiente accademico italiano. Porta la TESI (l'Anello Unico del Sapere) dalla Contea a Mordor per distruggerla nel Monte Fato e ottenere la laurea!

### ✨ Caratteristiche

- 🎬 **Intro cinematica** narrata da Galadriel (3 parti)
- 📖 **12 capitoli** che seguono la storia di LOTR
- 🎨 **Pixel art** personalizzata per ogni location
- 🎵 **Colonna sonora 8-bit** (7 tracce originali)
- 🧩 **Enigmi integrati** nella narrazione
- 🎮 **Interfaccia full-screen** immersiva
- 🎭 **Personaggi iconici**: Gandalf, Aragorn, Elrond, Galadriel, Sauron
- 🏆 **Sistema di punteggio** (+100 per risposta corretta, -10 per errore)

---

## 🚀 Avvio Rapido

### Prerequisiti

- **Java 21** (LTS) installato
- Sistema operativo: Windows, macOS o Linux

### Compilazione ed Esecuzione

```bash
# 1. Compila il progetto
./scripts/compile.sh

# 2. Avvia il gioco
./scripts/run.sh
```

**Su Windows:**
```cmd
scripts\compile.bat
scripts\run.bat
```

---

## 🎮 Come si Gioca

### Comandi Principali

- `avanti` - Procedi al prossimo capitolo
- `rispondi [risposta]` - Rispondi agli enigmi
- `guarda` / **F5** - Guarda intorno
- `inventario` - Vedi l'inventario
- `aiuto` / **F12** - Mostra l'aiuto

### Controlli Audio

- **F1** - Abbassa volume
- **F2** - Alza volume
- **F3** - Mute/Unmute

### 📚 I 12 Capitoli

1. 🏡 **La Contea** - Festa di Bilbo (111 anni)
2. 🌙 **Gli Spettri** - Fuga dai Nazgûl
3. 🏛️ **Gran Burrone** - Arrivo a Rivendell
4. 🗣️ **Il Concilio** - La Compagnia si forma
5. ⛰️ **Moria** - Le miniere oscure
6. 🔥 **Il Balrog** - "Tu non puoi passare!"
7. 🌳 **Lothlórien** - Incontro con Galadriel
8. 🐴 **Rohan** - I cavalieri
9. 🏰 **Gondor** - Minas Tirith
10. 💀 **Sentieri dei Morti** - L'esercito fantasma
11. 🌋 **Mordor** - Il Cancello Nero
12. 🔥 **Monte Fato** - Distruzione dell'Anello!

---

## 📁 Struttura del Progetto

```
LordOfTheThesis/
├── src/                          # Codice sorgente Java
│   └── com/lordofthethesis/
│       ├── Main.java             # Entry point principale
│       ├── MainFullScreen.java   # Versione full-screen
│       ├── audio/                # Sistema audio
│       ├── engine/               # Game engine
│       ├── graphics/             # Rendering grafico
│       ├── gui/                  # Interfaccia utente
│       └── model/                # Modelli dati
├── assets/                       # Risorse del gioco
│   ├── images/                   # Pixel art (800×480 PNG)
│   └── music/                    # Colonna sonora 8-bit (WAV)
├── bin/                          # File compilati (.class)
├── scripts/                      # Script di compilazione/esecuzione
│   ├── compile.sh
│   ├── run.sh
│   └── build-jar.sh
└── docs/                         # Documentazione
```

---

## 🎨 Asset

### Immagini
- Risoluzione: 800×480 pixel
- Formato: PNG
- Stile: Pixel art LOTR-inspired

### Musica
7 tracce 8-bit basate sulla colonna sonora di Howard Shore:
- `intro.wav` - One Ring Theme
- `contea.wav` - Concerning Hobbits
- `rivendell.wav` - LOTR Main Theme
- `granpasso.wav` - Epic Theme
- E altre...

---

## 🛠️ Tecnologie

- **Java 21 LTS** - Runtime
- **Swing** - GUI Framework
- **javax.sound.sampled** - Audio System
- **Custom Rendering** - Pixel art engine

---

## 📝 Note

Questo è un progetto educativo creato per scopi didattici. 

Tutti i personaggi, luoghi e riferimenti a *Il Signore degli Anelli* appartengono a J.R.R. Tolkien e sono usati solo come ispirazione.

Le musiche sono remix 8-bit della colonna sonora originale di Howard Shore.

---

## 👨‍💻 Autore

**Elia Sakellarides**  
Università degli Studi di Bari "Aldo Moro"

---

## 📄 Licenza

Progetto educativo - MIT License

---

<div align="center">

*"Even the smallest person can change the course of the future."* - Galadriel

**Buona avventura! 🗡️💍**

</div>
