# 🧙‍♂️ Lord of the Thesis

> *"Un gioco, una tesi per governarli tutti!"*

[![Java](https://img.shields.io/badge/Java-8%2B-orange.svg)](https://www.java.com)
[![License](https://img.shields.io/badge/License-Educational-blue.svg)](LICENSE)
[![Platform](https://img.shields.io/badge/Platform-Cross--platform-green.svg)](README.md)

**Avventura grafica testuale** ispirata a LOTR, ambientata nel mondo universitario italiano. 
Risolvi **domande semplicissime** per completare i livelli e consegnare la tua tesi!

---

## 🎮 Modalità di Gioco

### 1. **Esplorazione Libera**
- Cammina tra le 9 stanze del dipartimento (`nord`, `sud`, `est`, `ovest`)
- Raccogli oggetti (`prendi anello`)
- Parla con personaggi (`parla gandalf`)
- Consulta inventario (`inventario`)

### 2. **Modalità Livelli (Consigliata!)**
- Premi **"Inizia Livelli"** o digita `inizia livelli`
- Rispondi a **3 domande facilissime**:
  - "Quanto fa 1+1?" → Rispondi: `rispondi 2`
  - "Di che colore è il cielo?" → Rispondi: `rispondi azzurro`
  - "Qual è la prima lettera di 'tesi'?" → Rispondi: `rispondi t`
- Punteggio: +50 per risposta corretta, -5 per errore

---

## 🖼️ Personalizzare le Immagini

Il gioco carica immagini PNG/JPEG dalla cartella `assets/images/`. 
Sono inclusi dei **placeholder colorati** che puoi sostituire con le tue immagini!

### Come sostituire le immagini:
1. Vai in `assets/images/`
2. Sostituisci i file PNG con le tue immagini (400×300 pixel consigliato)
3. Mantieni i nomi dei file:
   - `contea.png` - Ingresso/Casa Hobbit
   - `biblioteca.png` - Biblioteca accademica
   - `corridoi.png` - Corridoi universitari
   - `aulamagna.png` - Aula Magna
   - `laboratorio.png` - Laboratorio informatico
   - `ufficio.png` - Ufficio del professore
   - `mensa.png` - Mensa universitaria
   - `giardini.png` - Giardini del campus
   - `segreteria.png` - Segreteria studenti

**Nota**: Le immagini sono decorative. Il focus del gioco è sul testo!

---

## 🚀 Avvio Rapido

### Versione Full-Screen ⭐ (CONSIGLIATA!)
```bash
# Unix/Mac/Linux
./scripts/run-fullscreen.sh

# Direttamente con Java
java -cp bin com.lordofthethesis.MainFullScreen
```

**Caratteristiche**:
- 🖼️ Immagine a schermo intero (800×600)
- 💬 Dialoghi integrati nell'immagine
- 🎒 Mini inventario visivo con icone
- 🎮 Stile avventura grafica classica (LucasArts/Sierra)

### Versione Classica (con pannelli)
```bash
# Unix/Mac/Linux
./scripts/run.sh

# Windows
scripts\run.bat

# Oppure direttamente il JAR
java -jar dist/LordOfTheThesis.jar
```

### Compilazione
```bash
# Unix/Mac/Linux
./scripts/compile.sh

# Windows
scripts\compile.bat
```

### Crea JAR
```bash
./scripts/build-jar.sh
```

---

## 📁 Struttura Progetto

```
LordOfTheThesis/
├── 📂 src/                          # Codice sorgente Java
│   └── com/lordofthethesis/
│       ├── Main.java
│       ├── model/                   # Modelli dati
│       ├── engine/                  # Logica di gioco
│       ├── graphics/                # Rendering isometrico
│       └── gui/                     # Interfaccia Swing
├── 📂 bin/                          # File compilati (.class)
├── 📂 dist/                         # JAR distribuibile
│   └── LordOfTheThesis.jar         # 30KB eseguibile
├── 📂 scripts/                      # Script di build/run
│   ├── compile.sh / compile.bat
│   ├── run.sh / run.bat
│   └── build-jar.sh
├── 📂 docs/                         # Documentazione
│   ├── README.md                    # Guida completa
│   ├── QUICKSTART.md               # Guida rapida
│   ├── INSTALL.md                  # Installazione
│   ├── PROJECT_STRUCTURE.md        # Architettura
│   └── ABOUT.md                    # Info e crediti
├── .gitignore
└── MANIFEST.MF                     # Configurazione JAR
```

---

## 🎮 Caratteristiche

- ✨ **Grafica Isometrica** - Stile Final Fantasy Tactics
- 🗺️ **9 Locazioni** - Da esplorare
- 👥 **5 Personaggi NPC** - Con dialoghi
- 🎒 **Sistema Inventario** - Raccogli oggetti
- 📜 **Trama Completa** - Ambientazione accademica italiana
- 💻 **Cross-platform** - Windows, Mac, Linux

---

## 📚 Documentazione

Tutta la documentazione è disponibile nella cartella `docs/`:

- **[README](docs/README.md)** - Guida completa al gioco
- **[QUICKSTART](docs/QUICKSTART.md)** - Guida rapida e comandi
- **[INSTALL](docs/INSTALL.md)** - Istruzioni di installazione
- **[PROJECT_STRUCTURE](docs/PROJECT_STRUCTURE.md)** - Architettura tecnica
- **[ABOUT](docs/ABOUT.md)** - Versione e crediti

---

## 🎯 Obiettivo del Gioco

Sei uno studente che ha appena finito la tesi di laurea. 
Devi consegnarla alla **Segreteria del Dipartimento di Informatica** attraversando l'università.

Esplora le locazioni, parla con i personaggi, raccogli oggetti e raggiungi la meta!

---

## 🕹️ Comandi Base

- `nord`, `sud`, `est`, `ovest` - Movimento
- `guarda` - Osserva la stanza
- `prendi [oggetto]` - Raccogli un oggetto
- `inventario` - Mostra inventario
- `parla [personaggio]` - Dialoga con NPC
- `usa [oggetto]` - Usa un oggetto
- `aiuto` - Mostra tutti i comandi

---

## 🛠️ Requisiti

- **Java 8+** (JRE o JDK)
- **Sistema Operativo**: Windows, macOS, Linux
- **Memoria**: Minimo 64MB RAM
- **Spazio**: ~1MB

---

## 🏃 Esecuzione

### Metodo 1: Script (consigliato)
```bash
./scripts/run.sh
```

### Metodo 2: JAR diretto
```bash
java -jar dist/LordOfTheThesis.jar
```

### Metodo 3: Da sorgente
```bash
./scripts/compile.sh
./scripts/run.sh
```

---

## 🔧 Sviluppo

### Compilare da sorgente
```bash
# Compilazione
./scripts/compile.sh

# Esecuzione
./scripts/run.sh

# Creare JAR
./scripts/build-jar.sh
```

### Struttura codice
- `model/` - Classi dati (Room, Item, Player, GameCharacter)
- `engine/` - Logica di gioco (GameEngine)
- `graphics/` - Rendering (IsometricRenderer, PixelArtManager)
- `gui/` - Interfaccia (GameGUI)

---

## 🎨 Screenshots

Il gioco presenta:
- **Grafica isometrica pixelata** stile Final Fantasy Tactics
- **Interfaccia Swing** con pannelli per narrazione e inventario
- **Rendering in tempo reale** di scene 3D isometriche

---

## 🐛 Troubleshooting

**Problema**: Java non trovato
```bash
# Verifica installazione Java
java -version
```

**Problema**: Script non eseguibile
```bash
chmod +x scripts/*.sh
```

**Problema**: Il JAR non si avvia
```bash
# Ricompila
./scripts/compile.sh
./scripts/build-jar.sh
```

Consulta [docs/INSTALL.md](docs/INSTALL.md) per maggiori dettagli.

---

## 📦 Distribuzione

Per distribuire il gioco:

1. **Solo JAR**: Condividi `dist/LordOfTheThesis.jar` + Java 8+
2. **Progetto completo**: Clona/scarica l'intero repository
3. **Codice sorgente**: Cartella `src/` + documentazione

---

## 🤝 Contributi

Questo è un progetto educativo. Idee per miglioramenti:

- [ ] Sistema di salvataggio
- [ ] Più locazioni ed NPC
- [ ] Animazioni
- [ ] Effetti sonori
- [ ] Traduzione inglese
- [ ] Modalità multiplayer (?)

---

## 📜 Licenza

Progetto educativo open source.
Libero uso per scopi educativi e non commerciali.

---

## 🙏 Crediti

- **Ispirazione**: J.R.R. Tolkien - "Il Signore degli Anelli"
- **Stile grafico**: Square Enix - "Final Fantasy Tactics"
- **Tema**: Vita universitaria italiana 🎓

---

## 📧 Supporto

Per domande o problemi:
1. Consulta la [documentazione](docs/)
2. Controlla la [guida rapida](docs/QUICKSTART.md)
3. Leggi il [troubleshooting](docs/INSTALL.md)

---

**Made with ☕ and 📚 by students, for students**

*Che il debug sia con te!* 🐛🔍

---

⚔️ [Inizia l'Avventura](dist/LordOfTheThesis.jar) | 📖 [Documentazione](docs/) | 🛠️ [Codice Sorgente](src/)
