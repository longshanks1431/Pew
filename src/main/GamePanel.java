package main;

import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;

public class GamePanel extends JPanel {

    private int xDelta, yDelta = 0;

    public GamePanel() {
        
        addKeyListener(new KeyboardInputs(this));
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
        repaint();
    }

    public void changeYDelta(int value) {
        this.yDelta += value;
        repaint();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.fillRect(50 + xDelta, 300 + yDelta, 50, 70);

    }
    
}
