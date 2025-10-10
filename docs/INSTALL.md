# 🎮 Guida all'Installazione - Lord of the Thesis

## 📋 Requisiti

- **Java 8 o superiore** (consigliato Java 11+)
- Sistema operativo: Windows, macOS, o Linux
- Circa 10 MB di spazio su disco

## 🔍 Verifica Java

Prima di iniziare, verifica di avere Java installato:

```bash
java -version
javac -version
```

Se Java non è installato:
- **Windows/Mac**: Scarica da [Oracle](https://www.oracle.com/java/technologies/downloads/) o [Adoptium](https://adoptium.net/)
- **Linux**: `sudo apt install default-jdk` (Ubuntu/Debian) o `sudo yum install java-devel` (Fedora/CentOS)

---

## 🚀 Installazione Rapida

### Metodo 1: Usa gli Script (Consigliato)

#### Su macOS/Linux:

```bash
# 1. Rendi eseguibili gli script
chmod +x compile.sh run.sh build-jar.sh

# 2. Compila il progetto
./compile.sh

# 3. Avvia il gioco
./run.sh
```

#### Su Windows:

```batch
# 1. Compila il progetto
compile.bat

# 2. Avvia il gioco
run.bat
```

### Metodo 2: Usa il JAR (Distribuibile)

```bash
# 1. Crea il JAR eseguibile
./build-jar.sh  # macOS/Linux
# oppure esegui manualmente su Windows i comandi nel file build-jar.sh

# 2. Esegui il JAR
java -jar dist/LordOfTheThesis.jar

# 3. Oppure fai doppio click su LordOfTheThesis.jar
```

### Metodo 3: Compilazione Manuale

```bash
# 1. Crea la directory per i file compilati
mkdir bin

# 2. Compila il progetto
javac -d bin -sourcepath src src/com/lordofthethesis/**/*.java

# 3. Esegui il gioco
java -cp bin com.lordofthethesis.Main
```

---

## 📁 Struttura del Progetto

```
LordOfTheThesis/
├── src/                    # Codice sorgente
│   └── com/
│       └── lordofthethesis/
│           ├── Main.java               # Entry point
│           ├── model/                  # Modello dati
│           ├── engine/                 # Logica di gioco
│           ├── graphics/               # Sistema grafico
│           └── gui/                    # Interfaccia grafica
├── bin/                    # File compilati (generato)
├── dist/                   # JAR distribuibile (generato)
├── compile.sh/bat          # Script di compilazione
├── run.sh/bat             # Script di esecuzione
├── build-jar.sh           # Script per creare JAR
├── README.md              # Documentazione principale
├── INSTALL.md             # Questa guida
└── .gitignore             # File da ignorare in Git
```

---

## 🎯 Avvio Rapido del Gioco

1. **Compila** (solo la prima volta o dopo modifiche)
2. **Esegui** `./run.sh` o `run.bat`
3. Inserisci il nome del tuo personaggio
4. **Gioca!** Usa i comandi o i bottoni

---

## 🐛 Risoluzione Problemi

### Errore: "javac: command not found"
- Java JDK non è installato o non è nel PATH
- **Soluzione**: Installa Java JDK e aggiungi al PATH

### Errore: "Could not find or load main class"
- Il progetto non è compilato correttamente
- **Soluzione**: Esegui `./compile.sh` prima di `./run.sh`

### Errore: "Permission denied" (macOS/Linux)
- Gli script non sono eseguibili
- **Soluzione**: `chmod +x *.sh`

### La finestra non si apre
- Problema con il display
- **Soluzione**: Verifica che il sistema supporti GUI Java Swing

### Grafica non visualizzata correttamente
- Problemi di compatibilità grafica
- **Soluzione**: Aggiorna i driver grafici o prova su un altro sistema

---

## 💡 Suggerimenti

- **Prima esecuzione**: Usa gli script per semplicità
- **Distribuzione**: Crea il JAR con `build-jar.sh`
- **Sviluppo**: Ricompila dopo ogni modifica al codice
- **Performance**: Usa Java 11+ per prestazioni migliori

---

## 📞 Supporto

Se riscontri problemi:
1. Controlla i requisiti Java
2. Leggi la sezione Risoluzione Problemi
3. Controlla il file README.md per informazioni sul gioco

---

**Buon divertimento con Lord of the Thesis!** 🎓⚔️
