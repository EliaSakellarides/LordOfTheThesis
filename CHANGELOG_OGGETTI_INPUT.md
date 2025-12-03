# 🎮 Aggiornamento: Sistema Oggetti e Input Rapido

## Data: 3 Dicembre 2025

---

## 🎯 Obiettivi Implementati

### ✅ Sistema Oggetti Interattivi LOTR

Aggiunto un sistema completo di oggetti ispirati a **Il Signore degli Anelli** con effetti reali sul gameplay:

#### 💍 **Anello Unico**
- **Effetto**: Invisibilità + 2 corruzione
- **Descrizione**: L'Anello del Potere. Ti rende invisibile ma corrompe la tua anima
- **Usi**: Infiniti
- **Posizione**: Il giocatore inizia con l'Anello

#### 🍞 **Lembas** (Pane Elfico)
- **Effetto**: +50 energia
- **Descrizione**: Pane degli Elfi di Lothlórien. Un morso ti sazia per giorni!
- **Usi**: 3 per ogni pezzo
- **Posizione**: Lothlorien (2 pezzi disponibili)

#### 🧥 **Mantello Elfico**
- **Effetto**: Invisibilità SENZA corruzione
- **Descrizione**: Mantello di Lothlórien. Invisibilità pulita!
- **Usi**: Infiniti
- **Posizione**: Lothlorien
- **Vantaggio**: Alternativa all'Anello per nascondersi senza corrompersi

#### 💡 **Fiala di Galadriel**
- **Effetto**: Illuminazione nelle tenebre
- **Descrizione**: Fiala con la luce di Eärendil. Scaccia le tenebre!
- **Usi**: Infiniti
- **Posizione**: Lothlorien
- **Utilità**: Perfetta per Moria o altri luoghi bui

#### ⚔️ **Pungolo** (Spada di Bilbo)
- **Effetto**: +20 difesa
- **Descrizione**: Spada elfica. Brilla di blu quando i nemici sono vicini!
- **Usi**: Infiniti
- **Posizione**: Gran Burrone

---

## 🎮 Sistema di Input Rapido (Mix)

### **Navigazione Veloce**
- **ENTER** (campo vuoto) → Avanza al prossimo capitolo
- **SPAZIO** → Avanza (stesso effetto di ENTER)
- Risposta diretta senza "rispondi" → Basta scrivere la risposta

### **Scelte Multiple Choice**
- **Tasto 1** → Scelta A
- **Tasto 2** → Scelta B
- **Tasto 3** → Scelta C
- **Lettera A/B/C** → Scelta diretta
- **Bottoni grafici** → Clic sui bottoni colorati

### **Bottoni GUI**
- 🟢 **AVANTI** → Verde, avanza la storia
- 🔵 **A** → Blu, prima scelta
- 🟠 **B** → Arancione, seconda scelta
- 🟣 **C** → Viola, terza scelta

---

## 📋 Nuovi Comandi

### **Gestione Oggetti**
```
prendi [nome]     → Raccoglie un oggetto dalla stanza
usa [nome]        → Utilizza un oggetto dall'inventario
inventario        → Mostra gli oggetti nello zaino
stato             → Mostra energia, difesa, corruzione, punteggio
```

### **Esempi di Uso**
```
prendi Lembas     → Raccogli il pane elfico
usa Lembas        → Mangi il Lembas (+50 energia)
usa Mantello      → Indossi il mantello (invisibilità pulita)
usa Anello        → Usi l'Anello (+2 corruzione, invisibilità)
usa Fiala         → Alzi la Fiala (illumina le tenebre)
usa Pungolo       → Impugni Pungolo (+20 difesa)
```

---

## 🎨 Modifiche alle Classi

### **Item.java**
- Aggiunto `ItemType` enum (ANELLO, CIBO, VESTITO, LUCE, ARMA, ALTRO)
- Nuovi attributi: `energyBoost`, `corruptionCost`, `grantsInvisibility`, `grantsLight`, `defenseBoost`
- Sistema di usi limitati/infiniti (`usesRemaining`)
- Metodi `use()`, `getUsageMessage()`, `getIcon()`
- Icone emoji per ogni tipo di oggetto

### **Player.java**
- Aggiunto sistema energia: `energy`, `maxEnergy` (100/100)
- Aggiunto sistema difesa: `defense`
- Metodo `useItem(String)` → Usa oggetto e applica effetti
- Metodo `getStatus()` → Mostra stato completo del giocatore
- Aggiornato `getInventoryString()` per mostrare oggetti con icone

### **GameEngine.java**
- Comandi aggiunti: `prendi`, `raccogli`, `usa`, `utilizza`
- Input rapido: supporto per risposte dirette (senza "rispondi")
- Input rapido: numeri 1/2/3 e lettere A/B/C per scelte
- Metodo `takeItemFromRoom(String)` per raccogliere oggetti
- Oggetti LOTR distribuiti nelle stanze appropriate
- Help text aggiornato con tutti i nuovi comandi

### **Room.java**
- Aggiunto metodo `getItemsString()` per elencare oggetti nella stanza

### **FullScreenGUI.java**
- Pannello bottoni scelta rapida (AVANTI, A, B, C)
- Supporto tastiera: tasti 1/2/3 per scelte rapide
- Colori bottoni distintivi (verde, blu, arancione, viola)
- Help aggiornato con shortcuts tastiera

---

## 🎯 Strategie di Gioco

### **Gestione Corruzione**
- **Anello**: Forte ma corrompe (+2 ogni uso)
- **Mantello**: Alternativa pulita (0 corruzione)
- **Attenzione**: Alta corruzione può influenzare il finale!

### **Gestione Energia**
- Il **Lembas** ripristina 50 energia
- Raccogli più pezzi quando sei a Lothlorien
- Usa saggiamente: solo 3 usi per pezzo

### **Gestione Combattimenti**
- **Pungolo** aumenta la difesa (+20)
- Utile per affrontare nemici potenti
- Raccogli a Gran Burrone

### **Esplorazione Luoghi Bui**
- **Fiala** illumina le tenebre
- Essenziale per Moria
- Nessun costo di utilizzo

---

## 🚀 Come Giocare

### **Avvio Rapido**
1. Compila: `bash scripts/compile.sh`
2. Esegui: `bash scripts/run.sh`
3. Inserisci il tuo nome
4. Inizia l'avventura!

### **Primi Passi**
1. Rispondi agli enigmi per avanzare
2. Quando arrivi a **Lothlorien**, raccogli gli oggetti:
   ```
   prendi Lembas
   prendi Mantello
   prendi Fiala
   ```
3. Controlla il tuo stato:
   ```
   stato
   inventario
   ```
4. Usa gli oggetti quando necessario:
   ```
   usa Lembas    (se l'energia è bassa)
   usa Mantello  (per nasconderti dai Nazgûl)
   usa Fiala     (in luoghi bui come Moria)
   ```

### **Scelte Rapide**
- Invece di scrivere `scegli A`, premi semplicemente **1** o **A**
- Invece di `rispondi 2`, scrivi solo **2**
- Invece di `avanti`, premi **ENTER** con campo vuoto
- Oppure clicca sui bottoni colorati!

---

## 📊 Statistiche di Gioco

Il comando `stato` mostra:
- 👤 Nome giocatore
- ⚡ Energia attuale/massima (100/100)
- 🛡️ Livello di difesa
- 💀 Livello di corruzione (con indicatore colorato)
- 🏆 Punteggio
- 🎒 Numero oggetti nell'inventario

---

## 🎮 Tasti Rapidi F-Keys

- **F1** → Volume giù
- **F2** → Volume su
- **F3** → Mute/Unmute
- **F5** → Guarda (descrizione stanza)
- **F12** → Aiuto completo

---

## ✨ Caratteristiche Tecniche

- **Linguaggio**: Java 21 LTS
- **GUI**: Swing con rendering personalizzato
- **Pattern**: MVC (Model-View-Controller)
- **Compatibilità**: macOS, Linux, Windows
- **Requisiti**: Java 21+

---

## 🎉 Conclusione

Il gioco ora è molto più fluido e interattivo! Puoi:
- ✅ Giocare velocemente con tasti numerici e bottoni
- ✅ Raccogliere e usare oggetti LOTR autentici
- ✅ Gestire energia, difesa e corruzione
- ✅ Fare scelte strategiche (Anello vs Mantello)
- ✅ Premere ENTER per avanzare rapidamente

**Buon viaggio verso Mordor!** 🌋🔥💍
