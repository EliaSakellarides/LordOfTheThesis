# 🧪 Guida ai Test - Lord of the Thesis

## ✅ Test delle Funzionalità Implementate

### 📖 **Test dei 17 Capitoli**

Il gioco ora ha **17 capitoli completi** che seguono la storia del Signore degli Anelli:

1. **Prologo - Sauron** (intro): Forgiatura della Tesi Unica
2. **La Contea** (contea): Casa dello hobbit
3. **Bag End** (bagend): Festa di Bilbo (111° compleanno)
4. **Gli Spettri** (cavalieri neri): ⚔️ **PRIMA SCELTA!**
5. **Granpasso/Relatore** (incontro granpasso): Il relatore Aragorn
6. **Rivendell** (granburrone): Casa di Elrond
7. **Gran Concilio** (granconcilio): Formazione della Compagnia
8. **Porte di Durin** (porte di durin): ❓ Quiz "Mellon"!
9. **Argonath** (argonath): I Pilastri dei Re
10. **Moria - Balrog** (moria/balrog): Caduta di Gandalf 💔
11. **Amon Hen - Divisione** (divisione): ⚔️ **SECONDA SCELTA con Boromir!**
12. **Viaggio con Sam** (fragole): Tu e Sam verso Mordor
13. **Alle Porte di Mordor** (mordor): Le Aquile e Gandalf il Bianco
14. **Monte Fato - Scalata** (interno monte fato): ⚔️ **SCELTA FINALE!**
15. **La Distruzione** (tesi che brucia): Distruggi la Tesi!
16. **Le Aquile** (aquile): Salvataggio epico! 🦅
17. **La Laurea** (seduta): Epilogo e laurea! 🎓

---

## ⚔️ **Test Sistema di Scelte**

### Capitolo 4 - Gli Spettri
Dopo aver completato l'enigma, prova:
- `scegli A` - Correre (sicuro, 0 corruzione)
- `scegli B` - Usare l'Anello (+1 corruzione) ⚠️
- `scegli C` - Combattere (GAME OVER) ❌

### Capitolo 11 - Boromir vuole la Tesi
- `scegli A` - Dare la tesi (GAME OVER) ❌
- `scegli B` - Usare l'Anello (+2 corruzione) ⚠️
- `scegli C` - Convincere Boromir (sicuro, 0 corruzione)

### Capitolo 14 - Monte Fato - Momento Decisivo!
- `scegli A` - Tenere l'Anello (GAME OVER cattivo) ❌
- `scegli B` - Distruggere la Tesi (VITTORIA!) ✅
- `scegli C` - Esitare (+1 corruzione) ⚠️

---

## 💍 **Test Contatore di Corruzione**

### Comandi da Provare:
```
corruzione       - Mostra il livello di corruzione attuale
status           - Alias di corruzione
dove             - Mostra progresso + corruzione
stato            - Alias di dove
```

### Livelli di Corruzione:
- **0**: 🟢 Puro
- **1-2**: 🟡 Leggermente corrotto
- **3-4**: 🟠 Corrotto
- **5+**: 🔴 Molto corrotto

### Come Aumentare la Corruzione:
1. Cap 4: Scegli B (+1)
2. Cap 11: Scegli B (+2)
3. Cap 14: Scegli C (+1)
**Massimo teorico**: 4 punti corruzione

---

## 🎮 **Test Comandi Base**

### Comandi Narrativi:
```
avanti              - Inizia il prossimo capitolo
rispondi [risposta] - Risponde all'enigma del capitolo
scegli [A/B/C]      - Fa una scelta narrativa
aiuto               - Mostra tutti i comandi
dove / stato        - Progresso nel viaggio
corruzione          - Livello di corruzione
inventario          - Mostra zaino
esci                - Esci dal gioco
```

---

## 🎯 **Percorsi di Test Consigliati**

### 🟢 **Test Percorso Puro** (0 corruzione)
1. Completa tutti i capitoli
2. Cap 4: Scegli A (corri)
3. Cap 11: Scegli C (convinci Boromir)
4. Cap 14: Scegli B (distruggi)
5. **Risultato**: Finale perfetto, 0 corruzione

### 🔴 **Test Percorso Corrotto** (4 corruzione)
1. Completa tutti i capitoli
2. Cap 4: Scegli B (usa Anello) +1
3. Cap 11: Scegli B (usa Anello) +2
4. Cap 14: Scegli C (esita) +1, poi B (distruggi)
5. **Risultato**: Finale con corruzione

### ❌ **Test Game Over**
- Cap 4: Scegli C (combatti Spettri)
- Cap 11: Scegli A (dai tesi a Boromir)
- Cap 14: Scegli A (tieni l'Anello)

---

## 🎵 **Test Audio**

Verifica che la musica cambi ad ogni capitolo:
- Cap 1: `intro.wav`
- Cap 2-3: `contea.wav` / `bagend.wav`
- Cap 4: `spettri.wav`
- Cap 5: `granpasso.wav`
- Cap 6-7: `rivendell.wav` / `concilio.wav`
- Altri capitoli: riutilizzano le musiche esistenti

---

## 🖼️ **Test Immagini**

Verifica che le immagini corrispondano ai capitoli:
- ✅ Cap 1: `introsauron.png`
- ✅ Cap 2: `contea.png`
- ✅ Cap 3: `Cena a Bag End di Bilbo.png`
- ✅ Cap 4: `cavalieri neri.png`
- ✅ Cap 5: `incontro con granpasso relatore.png`
- ✅ Cap 6: `granburrone.png`
- ✅ Cap 7: `granconcilio.png`
- ✅ Cap 8: `porte di durin.png`
- ✅ Cap 9: `argonath.png`
- ✅ Cap 10: `moria.png` o `balrog.png`
- ✅ Cap 11: `divisione.png`
- ✅ Cap 12: `fragole.png` (Sam con fragole)
- ✅ Cap 13: `mordor.png`
- ✅ Cap 14: `interno monte fato.png`
- ✅ Cap 15: `tesi che brucia.png`
- ✅ Cap 16: `aquile.png`
- ✅ Cap 17: `seduta.png` (laurea)

---

## 🐛 **Cosa Verificare Durante il Test**

### ✅ Funzionalità Base:
- [ ] Il gioco si avvia in fullscreen
- [ ] La musica parte automaticamente
- [ ] I comandi funzionano correttamente
- [ ] Gli enigmi accettano le risposte corrette
- [ ] Il punteggio aumenta (+100 per capitolo completato)
- [ ] Il punteggio diminuisce (-10 per risposta errata)

### ✅ Sistema di Scelte:
- [ ] Il comando `scegli A/B/C` funziona
- [ ] Le scelte hanno conseguenze diverse
- [ ] Game Over funziona (C cap 4, A cap 11, A cap 14)
- [ ] La corruzione aumenta correttamente

### ✅ Contatore di Corruzione:
- [ ] Il comando `corruzione` mostra lo status
- [ ] Lo status cambia colore (🟢🟡🟠🔴)
- [ ] Il livello è visibile nel comando `dove`
- [ ] La corruzione persiste tra i capitoli

### ✅ Progressione:
- [ ] Tutti i 17 capitoli si susseguono correttamente
- [ ] Le immagini cambiano ad ogni capitolo
- [ ] La musica cambia appropriatamente
- [ ] Il finale appare dopo il cap 17

---

## 📊 **Log di Test**

```
Data Test: ___________
Tester: ___________

Capitoli completati: ___ / 17
Scelte testate: □ Cap 4  □ Cap 11  □ Cap 14
Corruzione finale: ___
Game Over testati: □ Spettri  □ Boromir  □ Monte Fato
Bug trovati: ___________________________
```

---

## 🎓 **Risultato Atteso**

Al completamento del Cap 17, dovresti vedere:
```
🎓👑 SEI TORNATO A GRAN BURRONE! 🎓👑

Tutti i tuoi amici ti aspettano:
- 🧙‍♂️ Gandalf il Bianco
- 👑 Aragorn, ora RE di Gondor
- 🧝 Legolas
- 🪓 Gimli
- 🌿 Sam

'TI PROCLAMO DOTTORE DELLA TERRA DI MEZZO!'

🎊🎉 HAI VINTO! SEI LAUREATO CON LODE! 🎉🎊
```

---

## 🚀 **Prossimi Passi**

Dopo questo test:
1. ⏳ Implementare minigiochi
2. ⏳ Aggiungere finali multipli basati su corruzione
3. ⏳ Test completo finale
4. ⏳ Commit su GitHub

---

**Buon test!** 🎮✨
