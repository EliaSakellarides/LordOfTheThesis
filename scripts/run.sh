#!/bin/bash

# Script di esecuzione per Lord of the Thesis

echo "🎮 Avvio di Lord of the Thesis con Java 21..."
echo ""

# Verifica che i file siano compilati
if [ ! -d "bin" ]; then
    echo "⚠️  Il progetto non è compilato!"
    echo "Esegui prima: ./compile.sh"
    exit 1
fi

# Avvia il gioco con Java 21
/Library/Java/JavaVirtualMachines/temurin-21.jdk/Contents/Home/bin/java -cp bin com.lordofthethesis.Main

echo ""
echo "👋 Grazie per aver giocato!"
