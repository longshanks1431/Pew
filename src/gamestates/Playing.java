package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import entities.EnemyManager;
import entities.Player;
import levels.LevelManager;
import main.Game;
import utilz.LoadSave;
import java.awt.image.BufferedImage;
import java.util.Random;

import static utilz.Constants.Environment.*;

public class Playing extends State implements Statemethods {

    private Player player;
    private LevelManager levelManager;
    private EnemyManager enemyManager;

    private int xLvlOffset;
    private int leftBorder = (int) (0.2 * Game.GAME_WIDTH);
    private int rightBorder = (int) (0.8 * Game.GAME_WIDTH);
    private int lvlTilesWide = LoadSave.GetLevelData()[0].length;
    private int maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
    private int maxLvlOffsetX = maxTilesOffset * Game.TILES_SIZE;

    private BufferedImage backgroundImg, bigCloud, smallCloud;
    private int[] smallCloudsPos;
    private Random rnd = new Random();

    // constructor for the Playing state where the actual gameplay happens
    public Playing(Game game) {
        super(game);
        initClasses();

        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PLAYING_BG_IMAGE);
        bigCloud = LoadSave.GetSpriteAtlas(LoadSave.BIG_CLOUDS);
        smallCloud = LoadSave.GetSpriteAtlas(LoadSave.SMALL_CLOUDS);
        smallCloudsPos = new int[8];
        for(int i = 0; i < smallCloudsPos.length; i++)
            smallCloudsPos[i] = (int)(90 * Game.SCALE) + rnd.nextInt((int)(100*Game.SCALE));

    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        enemyManager = new EnemyManager(this);
        player  = new Player(200,200, (int)(32 * Game.SCALE), (int)(32 * Game.SCALE));
        player.loadLvlData(levelManager.getCurrenLevel().GetLevelData());
    }

    @Override
    public void update() {
        levelManager.update();
        player.update();
        enemyManager.update();

        checkCloseToBorder();
    }

    private void checkCloseToBorder() {
        int playerX = (int) player.getHitbox().x;
        int diff = playerX - xLvlOffset;

        if(diff > rightBorder)
            xLvlOffset += diff - rightBorder;
        else if (diff < leftBorder)
            xLvlOffset += diff - leftBorder;

        if (xLvlOffset > maxLvlOffsetX)
            xLvlOffset = maxLvlOffsetX;
        else if (xLvlOffset < 0)
            xLvlOffset = 0;

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImg, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        drawClouds(g);

        levelManager.draw(g, xLvlOffset);
        player.render(g, xLvlOffset);
        enemyManager.draw(g, xLvlOffset);
    }

    private void drawClouds(Graphics g) {
        for (int i = 0; i < 3; i++)
            g.drawImage(bigCloud, i * BIG_CLOUD_WIDTH - (int) (xLvlOffset * 0.3), (int) (204 * Game.SCALE), BIG_CLOUD_WIDTH, BIG_CLOUD_HEIGHT, null);

        for (int i = 0; i<smallCloudsPos.length; i++)
            g.drawImage(smallCloud, SMALL_CLOUD_WIDTH * 4 * i - (int) (xLvlOffset * 0.7), smallCloudsPos[i], SMALL_CLOUD_WIDTH, SMALL_CLOUD_HEIGHT, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch(e.getKeyCode()) {

            case KeyEvent.VK_W:
                player.setUp(true);
                break;          
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_S:
                player.setDown(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_P:
                player.setAttack(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
            case KeyEvent.VK_BACK_SPACE: 
                Gamestate.state = Gamestate.MENU;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {

            case KeyEvent.VK_W:
                player.setUp(false);
                break;          
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_S:
                player.setDown(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_P:
                player.setAttack(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;
        }
    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }

    public Player getPlayer() {
        return player;
    }

    
}
