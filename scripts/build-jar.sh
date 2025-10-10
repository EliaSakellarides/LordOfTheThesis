#!/bin/bash

# Script per creare un JAR eseguibile di Lord of the Thesis

echo "📦 Creazione del JAR eseguibile..."

# Compila se necessario
if [ ! -d "bin" ] || [ -z "$(ls -A bin)" ]; then
    echo "Compilazione del progetto..."
    ./compile.sh
    if [ $? -ne 0 ]; then
        exit 1
    fi
fi

# Crea la directory dist se non esiste
mkdir -p dist

# Verifica che MANIFEST.MF esista
if [ ! -f "MANIFEST.MF" ]; then
    echo "Creazione MANIFEST.MF..."
    cat > MANIFEST.MF << EOF
Manifest-Version: 1.0
Main-Class: com.lordofthethesis.Main
Created-By: Lord of the Thesis Build System

EOF
fi

# Crea il JAR
echo "Creazione JAR..."
cd bin
jar cfm ../dist/LordOfTheThesis.jar ../MANIFEST.MF .
cd ..

if [ $? -eq 0 ]; then
    echo "✅ JAR creato con successo!"
    echo "📂 File: dist/LordOfTheThesis.jar"
    echo "📊 Dimensione: $(du -h dist/LordOfTheThesis.jar | cut -f1)"
    echo ""
    echo "Per eseguirlo: java -jar dist/LordOfTheThesis.jar"
    echo "Oppure fai doppio click sul file JAR (su alcuni sistemi)"
else
    echo "❌ Errore durante la creazione del JAR!"
    exit 1
fi
