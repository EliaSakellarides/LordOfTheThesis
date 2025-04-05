package it.lordofthethesis;

public class Main {
    public static void main(String[] args) {
        try {
            // Avvia il gioco, qui potrai in seguito aggiungere il lancio della musica, etc.
            Game game = new Game("Giocatore");
            game.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
