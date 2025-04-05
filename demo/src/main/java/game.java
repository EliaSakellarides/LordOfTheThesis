package it.lordofthethesis;

import java.util.Scanner;

public class Game {
    private boolean isRunning;
    private Parser parser;
    private Room currentRoom;
    private Inventory inventory;
    private String playerName;

    public Game(String playerName) {
        this.isRunning = true;
        this.playerName = playerName;
        this.parser = new Parser();  // Inizialmente il parser senza stopwords; poi lo estenderemo.
        this.inventory = new Inventory();
        createRooms();
    }

    // Metodo per creare le stanze di gioco
    private void createRooms() {
        // Per ora creiamo una stanza semplice: "Biblioteca"
        Room library = new Room("Biblioteca", "Sei nella biblioteca dell'università, dove Bilbo ha lasciato la tesi.");
        // Puoi aggiungere altre stanze e collegarle in seguito
        this.currentRoom = library;
    }

    public void start() {
        System.out.println("Benvenuto in Lord of the Thesis!");
        System.out.println("La tua missione: consegnare la tesi al Dipartimento di Informatica (Mordor).");
        System.out.println(currentRoom.getLongDescription());

        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            System.out.print("> ");
            String input = scanner.nextLine();
            processCommand(input);
        }
        scanner.close();
    }

    // Gestione dei comandi
    private void processCommand(String input) {
        Command command = parser.getCommand(input);
        if (command == null) {
            System.out.println("Non ho capito il comando.");
            return;
        }
        String cmdWord = command.getCommandWord();
        switch (cmdWord) {
            case "vai":
                goRoom(command.getSecondWord());
                break;
            case "prendi":
                pickUpItem(command.getSecondWord());
                break;
            case "esci":
                System.out.println("Grazie per aver giocato!");
                isRunning = false;
                break;
            default:
                System.out.println("Comando sconosciuto.");
        }
    }

    private void goRoom(String direction) {
        // Per ora, visualizziamo solo un messaggio
        System.out.println("Ti muovi verso " + direction + " (funzionalità in sviluppo).");
    }

    private void pickUpItem(String itemName) {
        System.out.println("Hai raccolto: " + itemName);
        inventory.addItem(new Item(itemName, "Un elemento cruciale per la tesi."));
    }
}
