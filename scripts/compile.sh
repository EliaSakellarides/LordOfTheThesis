#!/bin/bash

# Script di compilazione per Lord of the Thesis

echo "🔨 Compilazione di Lord of the Thesis con Java 21..."

# Crea la directory bin se non esiste
mkdir -p bin

# Compila tutti i file Java con Java 21
/Library/Java/JavaVirtualMachines/temurin-21.jdk/Contents/Home/bin/javac --release 21 -d bin -sourcepath src src/com/lordofthethesis/**/*.java

if [ $? -eq 0 ]; then
    echo "✅ Compilazione completata con successo!"
    echo "📂 I file compilati sono in: bin/"
    echo ""
    echo "Per avviare il gioco, usa: ./run.sh"
else
    echo "❌ Errore durante la compilazione!"
    exit 1
fi
