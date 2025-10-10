# 🎵 Musiche 8-bit per Lord of the Thesis

Questa cartella contiene le tracce musicali in stile chiptune/8-bit per ogni capitolo del gioco.

## 📋 Tracce necessarie:

1. **intro.wav** - Tema di Sauron
   - Stile: Epico, dark, orchestrale 8-bit
   - Mood: Minaccioso, potente
   - Riferimento: Final Boss theme, Castlevania
   - Durata: ~30-60 secondi (loop)

2. **contea.wav** - La Contea
   - Stile: Pastorale, allegro, rilassante
   - Mood: Tranquillo, verde, hobbit-like
   - Riferimento: Zelda village theme
   - Durata: ~30-60 secondi (loop)

3. **bagend.wav** - Festa di Bilbo
   - Stile: Festoso, gioioso, danza
   - Mood: Celebrazione, fuochi d'artificio
   - Riferimento: Super Mario party theme
   - Durata: ~30-60 secondi (loop)

4. **spettri.wav** - Nazgûl (Spettri)
   - Stile: Dark, horror, tensione
   - Mood: Paura, inseguimento, oscurità
   - Riferimento: Silent Hill, Resident Evil 8-bit
   - Durata: ~30-60 secondi (loop)

5. **granpasso.wav** - Incontro con il Relatore
   - Stile: Eroico, epico, speranza
   - Mood: Coraggio, protezione, guida
   - Riferimento: Final Fantasy hero theme
   - Durata: ~30-60 secondi (loop)

6. **rivendell.wav** - Rivendell (Gran Burrone)
   - Stile: Elfico, magico, etereo
   - Mood: Pace, bellezza, mistero
   - Riferimento: Secret of Mana, Chrono Trigger
   - Durata: ~30-60 secondi (loop)

7. **concilio.wav** - Concilio di Elrond
   - Stile: Solenne, epico, drammatico
   - Mood: Decisione importante, destino
   - Riferimento: FF7 Shinra theme, LOTR Council
   - Durata: ~30-60 secondi (loop)

## 🎹 Specifiche tecniche:

- **Formato**: WAV (16-bit PCM o 8-bit PCM)
- **Sample rate**: 22050 Hz o 44100 Hz
- **Canali**: Stereo o Mono
- **Bitrate**: 8-bit per autenticità chiptune
- **Loop**: Seamless (inizio e fine devono collegarsi perfettamente)

## 🔗 Risorse per trovare/creare musiche 8-bit:

### Siti gratuiti:
- **Freesound.org** - Ricerca "chiptune", "8-bit", "retro game"
- **OpenGameArt.org** - Sezione music, filtro chiptune
- **Incompetech.com** - Musiche royalty-free
- **ModArchive.org** - Musiche .mod/.xm convertibili

### Tool per generare 8-bit:
- **BeepBox** (beepbox.co) - Editor online gratuito
- **FamiTracker** - Per NES-style music
- **Bosca Ceoil** - Semplice editor chiptune
- **LMMS** - DAW gratuito con synth 8-bit

### AI Generators:
- **Suno AI** / **Udio** - Genera musica 8-bit da prompt
- Prompt esempio: "epic 8-bit dark fantasy boss battle chiptune loop 30 seconds"

## 📝 Note:

- I file devono avere esattamente questi nomi per funzionare con AudioManager
- Se un file non esiste, il gioco funzionerà comunque (solo senza musica per quella scena)
- Per testare: usa file .wav qualsiasi e rinominali temporaneamente
- Volume di default: 70% (modificabile con F1/F2 in-game)

## 🎮 Come aggiungere le musiche:

1. Scarica/genera le tracce
2. Converti in formato WAV (se necessario)
3. Rinomina con i nomi sopra
4. Copia in questa cartella `assets/music/`
5. Riavvia il gioco!

## 🧪 Test temporaneo:

Per testare subito il sistema audio, puoi usare file qualsiasi:
```bash
# Esempio con beep di sistema (macOS)
afplay /System/Library/Sounds/Glass.aiff > assets/music/intro.wav
```

O usa musiche da YouTube (8-bit LOTR covers) convertite in WAV.
