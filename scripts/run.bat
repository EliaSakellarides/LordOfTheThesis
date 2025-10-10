@echo off
REM Script di esecuzione per Lord of the Thesis (Windows)

echo Avvio di Lord of the Thesis...
echo.

REM Verifica che i file siano compilati
if not exist bin (
    echo Il progetto non e' compilato!
    echo Esegui prima: compile.bat
    pause
    exit /b 1
)

REM Avvia il gioco
java -cp bin com.lordofthethesis.Main

echo.
echo Grazie per aver giocato!
pause
