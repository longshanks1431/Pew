package main;

import java.awt.Graphics;
import java.awt.Dimension;
import javax.swing.JPanel;

import inputs.KeyboardInputs;

public class GamePanel extends JPanel {

    private Game game;

    public GamePanel(Game game) {

        this.game = game;
        
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280,800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void updateGame() {
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        game.render(g);

    }

    public Game getGame() {
        return game;
    }
    
}
