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

import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

public class GamePanel extends JPanel {

    private float xDelta, yDelta = 100;
    private float xDir = 2f, yDir = 2f;
    private BufferedImage img, subImg;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 8;
    private int playerAction = IDLE;
    private int playerDir = -1; 
    private boolean moving = false;

    //private Color color = new Color(150, 20, 90);
    //private Random random;

    public GamePanel() {
        //random = new Random();
        importImg();
        loadAnimations();
        
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
    }

    private void loadAnimations() {
        animations = new BufferedImage[7][12];

        for (int j = 0; j < animations.length; j++)
            for(int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage(i*32, j*32, 32, 32);
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/res/heroAtlas.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280,800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void setDirection(int direction) {
        this.playerDir = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    // public void changeXDelta(int value) {
    //     this.xDelta += value;
    // }

    // public void changeYDelta(int value) {
    //     this.yDelta += value;
    // }

    private void setAnimation() {
        if(moving)
            playerAction = RUNNING;
        else 
            playerAction = IDLE;
    }

    private void updatePos() {
            if(moving) {
                switch(playerDir) {
                    case LEFT:
                        xDelta-=5;
                        break;
                    case UP:
                        yDelta-=5;
                        break;                        
                    case RIGHT:
                        xDelta+=5;
                        break;
                    case DOWN:
                        yDelta+=5;
                        break;
                }
            }
    }

    public void updateGame() {
        updateAnimationTick();
        setAnimation();
        updatePos();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        //subImg = img.getSubimage(0, 0, 32, 32);
        g.drawImage(animations[playerAction][aniIndex], (int)xDelta, (int)yDelta, 96, 96, null);

        // updateRectangle();
        // g.setColor(color);
        // g.fillRect((int)xDelta, (int)yDelta, 50, 70);

    }


    private void updateAnimationTick() {

        aniTick++;
        if(aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(playerAction))
                aniIndex = 0;
        }

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
