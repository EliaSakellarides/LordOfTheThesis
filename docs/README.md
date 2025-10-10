# Lord of the Thesis

Un gioco d'avventura testuale grafico ispirato al Signore degli Anelli, ambientato nel mondo accademico universitario!

## 🎮 La Storia

Sei uno studente che ha finalmente completato la tesi di laurea. Ma il tuo viaggio non è finito! Come Frodo doveva distruggere l'Anello nel Monte Fato, tu devi consegnare la tua tesi al **Dipartimento di Informatica di Bari** per laurearti.

Attraversa l'università, incontra personaggi memorabili, raccogli oggetti e completa la tua missione accademica finale!

## 🎯 Obiettivo

Consegnare la tesi alla Segreteria del Dipartimento di Informatica.

## 🕹️ Come Giocare

### Avvio del Gioco

```bash
# Compilare il progetto
cd /tmp/LordOfTheThesis/src
javac com/lordofthethesis/**/*.java

# Eseguire il gioco
java com.lordofthethesis.Main
```

### Comandi Disponibili

- **Movimento**: `nord`, `sud`, `est`, `ovest` - Muoviti tra le stanze
- **prendi [oggetto]** - Raccogli un oggetto dalla stanza
- **usa [oggetto]** - Usa un oggetto dall'inventario
- **esamina [oggetto]** - Esamina attentamente un oggetto (NUOVO! Prova con "esamina tesi" per vedere la tesi in lingua di Mordor!)
- **parla [personaggio]** - Parla con un personaggio
- **inventario** - Mostra il contenuto del tuo zaino
- **guarda** - Osserva la stanza corrente
- **aiuto** - Mostra la lista dei comandi
- **esci** - Esci dal gioco

### Bottoni Rapidi

L'interfaccia grafica include bottoni per i comandi più comuni per un accesso rapido.

## 🗺️ Luoghi da Esplorare

- **La Contea (La tua stanza)** - Dove tutto è iniziato
- **Biblioteca Universitaria** - Regno della conoscenza e della disperazione
- **Corridoi del Dipartimento** - I misteriosi corridoi dell'informatica
- **Aula Magna** - Dove hai dormito durante Analisi
- **Laboratorio di Informatica** - Computer e schermi blu della morte
- **Ufficio del Professor Gandalf** - Il tuo relatore ti aspetta
- **Mensa Universitaria** - Cibo... tecnicamente commestibile
- **Giardini dell'Università** - Un'oasi di pace
- **Segreteria del Dipartimento** - La tua destinazione finale!

## 👥 Personaggi

- **Professor Gandalf** - Il tuo relatore saggio
- **Elrond** - Il bibliotecario custode della conoscenza
- **Frodo** - Uno studente come te
- **Gimli** - Il tecnico informatico sempre impegnato
- **Galadriel** - La segretaria del dipartimento

## 🎒 Oggetti

- **Tesi** - La tua preziosa tesi di laurea (oggetto chiave!)
- **Caffè** - Per recuperare energie
- **Librone** - Un pesante libro di algoritmi
- **Penna USB** - Backup della tesi

## 🎨 Caratteristiche

- ✨ Interfaccia grafica moderna con Swing
- �️ **GRAFICA PIXELLATA ASCII** - Ogni locazione ha la sua scena pixellata in stile retro!
- 📜 **Tesi in Lingua di Mordor** - La tesi è scritta con simboli runici mistici (usa "esamina tesi")
- 🎨 Arte ASCII 3D per tutte le 9 locazioni del gioco
- �📝 Sistema di comando testuale e bottoni rapidi
- 🎒 Sistema di inventario con icone
- 💬 Dialoghi con personaggi NPC
- 🏆 Sistema di punteggio
- 🗺️ Mondo esplorabile con 9 diverse locazioni
- 🎭 Umorismo accademico e riferimenti al Signore degli Anelli
- 🖥️ Effetto terminal retro verde per le scene pixellate

## 🛠️ Requisiti Tecnici

- Java 8 o superiore
- Supporto per Swing (incluso in JDK standard)

## 📁 Struttura del Progetto

```
LordOfTheThesis/
├── src/
│   └── com/
│       └── lordofthethesis/
│           ├── Main.java              # Punto di ingresso
│           ├── model/                 # Modello del gioco
│           │   ├── Room.java         # Stanze/locazioni
│           │   ├── Item.java         # Oggetti
│           │   ├── Character.java    # Personaggi NPC
│           │   └── Player.java       # Giocatore
│           ├── engine/               # Logica del gioco
│           │   └── GameEngine.java   # Motore principale
│           ├── graphics/             # Sistema grafico NEW!
│           │   └── PixelArtManager.java  # Gestione arte ASCII pixellata
│           └── gui/                  # Interfaccia grafica
│               └── GameGUI.java      # GUI con Swing
└── README.md
```

## 🎓 Sviluppo Futuro

Possibili estensioni:
- Sistema di combattimento con esami
- Più personaggi e side quest
- Sistema di salvataggio
- Mappa grafica interattiva
- Più ending alternativi
- Musica e effetti sonori

## 📝 Note per gli Sviluppatori

Il gioco è strutturato seguendo il pattern MVC:
- **Model**: Classi nel package `model` (Room, Item, Character, Player)
- **Controller**: `GameEngine` gestisce la logica di gioco
- **View**: `GameGUI` gestisce l'interfaccia utente

## 🎮 Buon Divertimento!

Ricorda: "Anche il più piccolo commit può cambiare il corso del progetto!"
