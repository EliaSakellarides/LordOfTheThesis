package it.lordofthethesis;

import java.util.StringTokenizer;

public class Parser {
    public Command getCommand(String inputLine) {
        StringTokenizer tokenizer = new StringTokenizer(inputLine);
        if (!tokenizer.hasMoreTokens()) {
            return null;
        }
        String commandWord = tokenizer.nextToken();
        String secondWord = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : null;
        return new Command(commandWord, secondWord);
    }
}
