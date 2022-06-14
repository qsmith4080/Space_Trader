package edu.gatech.team85.game.frontend;

public class Main {
    public static void main(String[] args) {
        GameWindow gameWindow = new GameWindow();
        // Select the first screen of the game
        gameWindow.setWindowPanel(new SwingCharacterCreation(gameWindow));
    }
}
