# 🚀 Guida Rapida - Lord of the Thesis

## ⚡ Quick Start (3 passi)

```bash
# 1. Compila
./compile.sh

# 2. Esegui
./run.sh

# 3. Gioca!
```

---

## 📋 Comandi Disponibili

### 🔨 Compilazione

```bash
# macOS / Linux
./compile.sh

# Windows
compile.bat

# Output: bin/ con tutti i file .class
```

### 🎮 Esecuzione Diretta

```bash
# macOS / Linux
./run.sh

# Windows
run.bat

# Alternativa manuale
java -cp bin com.lordofthethesis.Main
```

### 📦 Creazione JAR Distribuibile

```bash
# macOS / Linux
./build-jar.sh

# Output: dist/LordOfTheThesis.jar (~28KB)
```

### 🚀 Esecuzione JAR

```bash
java -jar dist/LordOfTheThesis.jar

# Oppure doppio click sul file (se supportato dal sistema)
```

---

## 🎯 Comandi di Gioco

### Movimento
- `nord`, `sud`, `est`, `ovest`

### Azioni
- `prendi [oggetto]` - Raccogli un oggetto
- `usa [oggetto]` - Usa un oggetto dall'inventario
- `esamina [oggetto]` - Esamina in dettaglio (prova con "tesi"!)
- `parla [personaggio]` - Conversa con un NPC

### Informazioni
- `inventario` o `zaino` - Mostra l'inventario
- `guarda` o `osserva` - Esamina la stanza
- `aiuto` - Mostra i comandi disponibili

### Sistema
- `esci` o `quit` - Termina il gioco

---

## 🗺️ Mappa del Mondo

```
        Ufficio Gandalf
              |
        Laboratorio
              |
    Aula --- Biblioteca --- Corridoi --- SEGRETERIA ★
              |                              (META!)
            Contea --- Giardini
           (START)         |
                        Mensa
```

### Percorso Veloce per Vincere:
1. **Contea** → `prendi tesi`
2. `nord` → **Biblioteca**
3. `est` → **Corridoi**
4. `est` → **Segreteria**
5. `usa tesi` → 🎓 **VITTORIA!**

---

## 🎨 Easter Eggs

- 🔥 `esamina tesi` - Vedi la tesi scritta in lingua di Mordor!
- 💬 Parla con tutti i personaggi per frasi divertenti
- ☕ Usa il caffè per energia (+10 punti)
- 🎮 Guarda le scene isometriche cambiare mentre ti muovi!

---

## 🛠️ Gestione Progetto

### Pulizia
```bash
# Rimuovi file compilati
rm -rf bin/

# Rimuovi JAR
rm -rf dist/
```

### Ricompilazione Completa
```bash
rm -rf bin/ dist/
./compile.sh
./build-jar.sh
```

### Verifica Struttura
```bash
# Conta file Java
find src -name "*.java" | wc -l

# Conta file compilati
find bin -name "*.class" | wc -l

# Dimensione progetto
du -sh .
```

---

## 📊 Struttura File

```
LordOfTheThesis/
├── src/          # Codice sorgente
├── bin/          # File compilati
├── dist/         # JAR distribuibile
├── *.sh          # Script Unix
├── *.bat         # Script Windows
├── *.md          # Documentazione
├── .gitignore    # Git ignore
└── MANIFEST.MF   # Manifest JAR
```

---

## 🐛 Troubleshooting

### "javac: command not found"
```bash
# Installa Java JDK
# Mac: brew install openjdk
# Ubuntu: sudo apt install default-jdk
```

### "Could not find or load main class"
```bash
# Ricompila il progetto
./compile.sh
```

### "Permission denied"
```bash
# Rendi eseguibili gli script
chmod +x *.sh
```

### Grafica non visualizzata
- Verifica supporto GUI
- Prova su un altro sistema
- Usa Java 11+ per migliore compatibilità

---

## 📚 Documentazione Completa

- **README.md** - Informazioni sul gioco e gameplay
- **INSTALL.md** - Guida completa all'installazione
- **PROJECT_STRUCTURE.md** - Architettura del progetto
- **QUICKSTART.md** - Questa guida rapida

---

## 🎓 Obiettivo del Gioco

Sei uno studente universitario che ha completato la tesi.
Ora devi consegnarla al **Dipartimento di Informatica di Bari**!

Esplora l'università, parla con professori e studenti,
raccogli oggetti e completa la tua missione accademica!

---

## 🌟 Features

✨ Grafica isometrica in stile Final Fantasy Tactics
📜 Tesi scritta in "lingua di Mordor"
🎮 9 locazioni esplorabili
👥 5 personaggi con dialoghi
📦 Sistema di inventario
🏆 Sistema di punteggio
💬 Comandi testuali + bottoni rapidi
🎨 Rendering pixel art in tempo reale

---

## 💻 Requisiti di Sistema

**Minimo:**
- Java 8+
- 512 MB RAM
- 10 MB spazio disco
- Risoluzione 1024x768

**Consigliato:**
- Java 11+
- 1 GB RAM
- Risoluzione 1920x1080

---

## 📞 Supporto

Problemi? Controlla:
1. INSTALL.md per l'installazione
2. README.md per info sul gioco
3. PROJECT_STRUCTURE.md per sviluppatori

---

**Buon divertimento! 🎮✨**

*"Uno studente non è mai in ritardo, né in anticipo.  
Arriva precisamente quando deve consegnare!"*  
— Professor Gandalf
