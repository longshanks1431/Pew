package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import inputs.KeyboardInputs;

public class GamePanel extends JPanel {

    private float xDelta, yDelta = 100;
    private float xDir = 2f, yDir = 2f;
    private BufferedImage img;
    //private Color color = new Color(150, 20, 90);
    //private Random random;

    public GamePanel() {
        //random = new Random();
        importImg();
        
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/run.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280,800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void changeXDelta(int value) {
        this.xDelta += value;
    }

    public void changeYDelta(int value) {
        this.yDelta += value;
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.drawImage(img.getSubimage(0, 0, 32, 32), (int)xDelta, (int)yDelta, 128, 128, null);

        // updateRectangle();
        // g.setColor(color);
        // g.fillRect((int)xDelta, (int)yDelta, 50, 70);

    }

    // private void updateRectangle() {
    //     xDelta+=xDir;
    //     if (xDelta > 1280 || xDelta < 0) {
    //         xDir *= -1;
    //         color = getRndColor();

    //     }    
    //     yDelta+=yDir;
    //     if (yDelta > 1280 || yDelta < 0) {
    //         yDir *= -1;
    //         color = getRndColor();
    //     }
    // }

    // private Color getRndColor() {
    //     int r = random.nextInt(255);
    //     int g = random.nextInt(255);
    //     int b = random.nextInt(255);
    //     return new Color(r,g,b);
    // }
    
}
