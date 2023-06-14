package main;

public class Game {

    private GameWindow gameWindow;
    private GamePanel gamePanel;

    public Game() {
        //constructor is the head method of the class - whenever we create object, we call this

        System.out.println("I am the game constructor. I am working thus far.");

        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        
    }
    
}
