package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

import inputs.KeyboardInputs;

public class GamePanel extends JPanel {

    private float xDelta, yDelta = 100;
    private float xDir = 2f, yDir = 2f;
    private Color color = new Color(150, 20, 90);
    private Random random;

    public GamePanel() {
        random = new Random();
        addKeyListener(new KeyboardInputs(this));
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
    }

    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        updateRectangle();
        g.setColor(color);
        g.fillRect((int)xDelta, (int)yDelta, 50, 70);

    }

    private void updateRectangle() {
        xDelta+=xDir;
        if (xDelta > 1280 || xDelta < 0) {
            xDir *= -1;
            color = getRndColor();

        }    
        yDelta+=yDir;
        if (yDelta > 1280 || yDelta < 0) {
            yDir *= -1;
            color = getRndColor();
        }
    }

    private Color getRndColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return new Color(r,g,b);
    }
    
}
