#!/bin/bash

# Script di esecuzione per Lord of the Thesis

echo "🎮 Avvio di Lord of the Thesis..."
echo ""

# Verifica che i file siano compilati
if [ ! -d "bin" ]; then
    echo "⚠️  Il progetto non è compilato!"
    echo "Esegui prima: ./compile.sh"
    exit 1
fi

# Avvia il gioco
java -cp bin com.lordofthethesis.Main

echo ""
echo "👋 Grazie per aver giocato!"
