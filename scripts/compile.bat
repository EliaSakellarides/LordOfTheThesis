@echo off
REM Script di compilazione per Lord of the Thesis (Windows)

echo Compilazione di Lord of the Thesis...

REM Crea la directory bin se non esiste
if not exist bin mkdir bin

REM Compila tutti i file Java
javac -d bin -sourcepath src src\com\lordofthethesis\**\*.java

if %ERRORLEVEL% EQU 0 (
    echo Compilazione completata con successo!
    echo I file compilati sono in: bin\
    echo.
    echo Per avviare il gioco, usa: run.bat
) else (
    echo Errore durante la compilazione!
    exit /b 1
)

pause
