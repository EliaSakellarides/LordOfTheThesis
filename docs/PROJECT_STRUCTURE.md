# 📂 Struttura del Progetto Lord of the Thesis

## 🎯 Panoramica

Progetto Java per un gioco d'avventura testuale grafico in stile Final Fantasy Tactics, ambientato nel mondo accademico universitario.

---

## 📁 Albero delle Directory

```
LordOfTheThesis/
│
├── 📂 src/                          # Codice sorgente
│   └── com/
│       └── lordofthethesis/
│           │
│           ├── Main.java           # 🚀 Entry point dell'applicazione
│           │
│           ├── 📂 model/           # Modello dati del gioco
│           │   ├── Room.java              # Stanze/locazioni
│           │   ├── Item.java              # Oggetti raccoglibili
│           │   ├── GameCharacter.java     # Personaggi NPC
│           │   └── Player.java            # Giocatore con inventario
│           │
│           ├── 📂 engine/          # Logica di gioco
│           │   └── GameEngine.java        # Motore principale, comandi, mondo
│           │
│           ├── 📂 graphics/        # Sistema grafico
│           │   ├── IsometricRenderer.java # Renderer grafica isometrica FFT
│           │   └── PixelArtManager.java   # Gestione arte ASCII/pixel
│           │
│           └── 📂 gui/             # Interfaccia utente
│               └── GameGUI.java           # GUI Swing con grafica
│
├── 📂 bin/                          # 🔨 File compilati (.class)
│   └── com/lordofthethesis/...           # [Generato dalla compilazione]
│
├── 📂 dist/                         # 📦 File distribuibili
│   └── LordOfTheThesis.jar              # JAR eseguibile (28KB)
│
├── 📜 Script di Build e Esecuzione
│   ├── compile.sh                  # ⚙️ Compila il progetto (macOS/Linux)
│   ├── compile.bat                 # ⚙️ Compila il progetto (Windows)
│   ├── run.sh                      # 🎮 Esegue il gioco (macOS/Linux)
│   ├── run.bat                     # 🎮 Esegue il gioco (Windows)
│   └── build-jar.sh                # 📦 Crea JAR distribuibile (macOS/Linux)
│
├── 📄 Documentazione
│   ├── README.md                   # Documentazione principale del gioco
│   ├── INSTALL.md                  # Guida all'installazione
│   └── PROJECT_STRUCTURE.md        # Questo file
│
├── ⚙️ File di Configurazione
│   ├── .gitignore                  # File da ignorare in Git
│   └── MANIFEST.MF                 # Manifest per il JAR
│
└── 📊 Statistiche
    • Linee di codice: ~2000+
    • File Java: 9
    • Packages: 4 (model, engine, graphics, gui)
    • Classi: 9
    • Dimensione JAR: ~28KB
```

---

## 🎨 Architettura del Progetto

### 1. **Model (MVC Pattern)**
```
model/
├── Room.java           - Gestisce le stanze con uscite, oggetti, personaggi
├── Item.java           - Oggetti raccoglibili e usabili
├── GameCharacter.java  - NPC con dialoghi
└── Player.java         - Stato del giocatore, inventario, punteggio
```

**Responsabilità**: Rappresentazione dei dati del gioco

### 2. **Engine (Controller)**
```
engine/
└── GameEngine.java     - Logica centrale:
                          • Creazione del mondo (9 stanze)
                          • Processamento comandi
                          • Gestione stato gioco
                          • Win/Lose conditions
```

**Responsabilità**: Logica di business e coordinamento

### 3. **Graphics (View Support)**
```
graphics/
├── IsometricRenderer.java  - Rendering grafica isometrica 3D
│                             • Tile isometrici
│                             • Cubi 3D
│                             • Personaggi pixelati
│                             • 9 scene uniche
└── PixelArtManager.java    - ASCII art e icone
                              • Arte testuale
                              • Tesi in lingua Mordor
                              • Icone emoji
```

**Responsabilità**: Rendering visuale del gioco

### 4. **GUI (View)**
```
gui/
└── GameGUI.java        - Interfaccia Swing:
                          • Pannello grafica isometrica (450x400px)
                          • Area testo narrazione
                          • Pannello inventario
                          • Campo input comandi
                          • Bottoni rapidi
                          • Menu
```

**Responsabilità**: Interazione utente

---

## 🔧 Script e Comandi

### Compilazione
```bash
# macOS/Linux
./compile.sh

# Windows
compile.bat
```
**Output**: File `.class` in `bin/`

### Esecuzione
```bash
# macOS/Linux
./run.sh

# Windows
run.bat
```

### Creazione JAR
```bash
# macOS/Linux
./build-jar.sh
```
**Output**: `dist/LordOfTheThesis.jar`

### Esecuzione JAR
```bash
java -jar dist/LordOfTheThesis.jar
```
O doppio click sul file JAR

---

## 📊 Statistiche del Codice

| Componente | File | Linee | Descrizione |
|------------|------|-------|-------------|
| **Model** | 4 | ~300 | Classi dati |
| **Engine** | 1 | ~400 | Logica gioco |
| **Graphics** | 2 | ~800 | Rendering |
| **GUI** | 1 | ~350 | Interfaccia |
| **Main** | 1 | ~25 | Bootstrap |
| **TOTALE** | **9** | **~1875** | |

---

## 🎮 Flusso di Esecuzione

```
Main.java
    ↓
GameGUI costruisce interfaccia
    ↓
Utente: "Nuovo Gioco"
    ↓
GameEngine.initializeGame()
    ├→ Crea mondo (9 stanze)
    ├→ Popola oggetti
    ├→ Aggiunge personaggi
    └→ Posiziona player
    ↓
Loop di gioco:
    ├→ Utente inserisce comando
    ├→ GameEngine.processCommand()
    ├→ Aggiorna stato
    ├→ GUI aggiorna visualizzazione
    │   ├→ Testo narrazione
    │   ├→ Grafica isometrica
    │   └→ Inventario
    └→ Controlla vittoria/sconfitta
```

---

## 🏗️ Design Pattern Utilizzati

1. **MVC (Model-View-Controller)**
   - Model: `model/` package
   - View: `gui/` + `graphics/` packages
   - Controller: `engine/` package

2. **Singleton Pattern** (implicito)
   - Un solo `GameEngine` per partita
   - Un solo `Player` per partita

3. **Factory Pattern** (parziale)
   - `GameEngine.createWorld()` crea oggetti

4. **Observer Pattern** (implicito)
   - GUI osserva cambiamenti in GameEngine

---

## 🎯 Dipendenze

### Runtime
- **Java 8+** (consigliato Java 11+)
- **Swing** (incluso in JDK)
- **AWT Graphics2D** (incluso in JDK)

### Sviluppo
- **javac** (Java Compiler)
- **jar** (JAR tool)
- Editor di testo o IDE

**Nessuna dipendenza esterna!** Il progetto usa solo librerie standard Java.

---

## 📝 Note per Sviluppatori

### Aggiungere una Nuova Stanza
1. Crea oggetto `Room` in `GameEngine.createWorld()`
2. Aggiungi collegamenti (`addExit`)
3. Aggiungi oggetti e personaggi
4. Crea metodo render in `IsometricRenderer`
5. Aggiorna `getRoomKey` in `GameGUI`

### Aggiungere un Nuovo Comando
1. Aggiungi case in `GameEngine.processCommand()`
2. Implementa metodo handler
3. Aggiorna `getHelpText()`
4. (Opzionale) Aggiungi bottone rapido in GUI

### Aggiungere un Nuovo Oggetto
1. Crea `Item` in `createWorld()`
2. Aggiungi alla stanza con `addItem()`
3. (Opzionale) Aggiungi logica in `useItem()`
4. (Opzionale) Aggiungi icona in `PixelArtManager`

---

## 🚀 Distribuzione

### File Necessari per Distribuzione
```
📦 Pacchetto Minimo:
├── dist/LordOfTheThesis.jar    (28KB)
└── README.md                    (documentazione)

📦 Pacchetto Completo:
├── dist/LordOfTheThesis.jar
├── README.md
├── INSTALL.md
├── run.sh / run.bat            (comodità)
└── src/                        (codice sorgente opzionale)
```

### Requisiti Utente Finale
- Java Runtime Environment (JRE) 8+
- ~10 MB spazio disco
- Display con supporto GUI (minimo 1200x800)

---

## 📈 Possibili Estensioni Future

- [ ] Sistema di salvataggio/caricamento
- [ ] Più ending alternativi
- [ ] Animazioni nelle scene
- [ ] Effetti sonori e musica
- [ ] Più personaggi e dialoghi
- [ ] Sistema di combattimento (esami!)
- [ ] Multiplayer (collaborativo)
- [ ] Traduzione in altre lingue
- [ ] Mod support

---

## 📄 Licenza

Progetto educativo/dimostrativo.

---

**Creato con ❤️ per gli studenti universitari!** 🎓⚔️
