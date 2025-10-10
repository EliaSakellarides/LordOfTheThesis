# 🖼️ Guida alle Immagini

## Sistema di Caricamento Immagini

`IsometricRenderer` è stato **semplificato** per caricare immagini statiche da file invece di generarle proceduralmente. Questo permette massima flessibilità visiva mantenendo il focus sull'avventura testuale.

---

## 📂 Directory delle Immagini

Le immagini vanno posizionate in:
```
/assets/images/
```

Se un'immagine non viene trovata, il renderer genera automaticamente un **placeholder colorato** con il nome della stanza.

---

## 🎨 Immagini Richieste

| Stanza | Filename | Tema Suggerito |
|--------|----------|----------------|
| **Contea** | `contea.png` | Casa Hobbit, ingresso caldo e accogliente |
| **Biblioteca** | `biblioteca.png` | Scaffali di libri, atmosfera accademica |
| **Corridoi** | `corridoi.png` | Corridoi universitari bianchi/grigi |
| **Aula Magna** | `aulamagna.png` | Grande aula per conferenze |
| **Laboratorio** | `laboratorio.png` | Computer, server, tecnologia |
| **Ufficio** | `ufficio.png` | Ufficio del professore, scrivania, libri |
| **Mensa** | `mensa.png` | Tavoli, cibo, studenti che mangiano |
| **Giardini** | `giardini.png` | Verde, natura, spazio aperto |
| **Segreteria** | `segreteria.png` | Bancone, documenti, atmosfera burocratica |

---

## ⚙️ Specifiche Tecniche

### Dimensioni Consigliate
- **400×300 pixel** (verrà scalata automaticamente al pannello)
- Aspect ratio: **4:3** per evitare distorsioni

### Formati Supportati
- **PNG** (consigliato per trasparenze)
- **JPEG/JPG** (per foto realistiche)
- **GIF** (funziona ma senza animazione)

### Dimensioni Finali nel Gioco
Il pannello delle immagini è impostato a **300×225 pixel** nella GUI, 
quindi l'immagine originale viene scalata proporzionalmente.

---

## 🎨 Stile Consigliato

### Opzione 1: Pixel Art
- Stile retro 8-bit/16-bit
- Palette limitata (es. 16-32 colori)
- Ispirazioni: Final Fantasy Tactics, Octopath Traveler

### Opzione 2: Illustrazioni Fantasy
- Disegni in stile LOTR
- Colori caldi per ambientazioni accademiche
- Atmosfera da libro illustrato

### Opzione 3: Fotografie Modificate
- Foto di università reali
- Filtro seppia o vintage per atmosfera
- Eventualmente applicare posterizzazione

---

## 🛠️ Come Sostituire le Immagini

### Passo 1: Prepara le tue immagini
Crea o scarica le tue immagini (400×300 pixel, PNG o JPEG)

### Passo 2: Rinomina i file
Usa esattamente questi nomi:
```
contea.png
biblioteca.png
corridoi.png
aulamagna.png
laboratorio.png
ufficio.png
mensa.png
giardini.png
segreteria.png
```

### Passo 3: Copia nella directory
```bash
cp mie-immagini/*.png /tmp/LordOfTheThesis/assets/images/
```

### Passo 4: Riavvia il gioco
```bash
./scripts/run.sh
```

Le nuove immagini verranno caricate automaticamente!

---

## 📝 Note Importanti

1. **Le immagini sono decorative**: Il focus del gioco rimane sul testo e sulle scelte narrative
2. **Nomi file case-sensitive**: Su Linux/Mac, `contea.png` ≠ `Contea.png`
3. **Fallback automatico**: Se manca un'immagine, viene generato un placeholder colorato
4. **Dimensioni del pannello**: L'immagine viene scalata, non è necessario ridimensionare manualmente
5. **Performance**: Le immagini vengono caricate in memoria quando si cambia stanza

---

## 🔧 Codice Sorgente

Il rendering delle immagini è gestito in:
```
src/com/lordofthethesis/graphics/IsometricRenderer.java
```

Metodi principali:
- `loadRoomImage(String roomKey)` - Carica l'immagine da file
- `createPlaceholder(String roomKey)` - Crea placeholder colorato se immagine mancante
- `getRoomColor(String roomKey)` - Mappa colori per placeholder

---

## 🌟 Esempi di Risorse Online

### Dove trovare immagini gratuite:
- **OpenGameArt.org** - Asset gratuiti per giochi
- **Itch.io** - Asset pixel art (molti gratuiti)
- **Unsplash.com** - Foto ad alta qualità (CC0)
- **Pexels.com** - Stock photos gratuite

### Come creare pixel art:
- **Aseprite** - Editor pixel art professionale
- **Piskel** - Editor online gratuito
- **GIMP** - Effetto posterizzazione per convertire foto in pixel art

---

## ✅ Checklist Pre-Release

Prima di distribuire il gioco con immagini personalizzate:

- [ ] Tutte le 9 immagini sono presenti in `assets/images/`
- [ ] Le immagini hanno nomi corretti (minuscolo, `.png` o `.jpg`)
- [ ] Le immagini sono di dimensioni ragionevoli (< 500KB ciascuna)
- [ ] Hai testato il gioco e tutte le stanze caricano correttamente
- [ ] Le immagini hanno licenza appropriata (se distribuite pubblicamente)

---

## 🐛 Troubleshooting

### "Non vedo le mie immagini!"
1. Verifica che i file siano in `/assets/images/` (relativo alla root del progetto)
2. Controlla i nomi file (minuscolo, no spazi)
3. Verifica che siano PNG o JPEG validi

### "Le immagini sono distorte!"
- Usa aspect ratio 4:3 (es. 400×300, 800×600, 1200×900)

### "Il gioco è lento!"
- Riduci le dimensioni delle immagini (max 400×300)
- Comprimi le immagini con strumenti come TinyPNG

---

**Buona personalizzazione! 🎨**
