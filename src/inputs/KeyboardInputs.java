package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.Gamestate;
import main.GamePanel;
import static utilz.Constants.Directions.*;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;
    
    public KeyboardInputs(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // switch(e.getKeyCode()) {

        //     case KeyEvent.VK_W:
        //         gamePanel.getGame().getPlayer().setUp(false);
        //         break;          
        //     case KeyEvent.VK_A:
        //         gamePanel.getGame().getPlayer().setLeft(false);
        //         break;
        //     case KeyEvent.VK_S:
        //         gamePanel.getGame().getPlayer().setDown(false);
        //         break;
        //     case KeyEvent.VK_D:
        //         gamePanel.getGame().getPlayer().setRight(false);
        //         break;
        //     case KeyEvent.VK_P:
        //         gamePanel.getGame().getPlayer().setAttack(false);
        //         break;
        //     case KeyEvent.VK_SPACE:
        //         gamePanel.getGame().getPlayer().setJump(false);
        //         break;
        // }

        switch(Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyReleased(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyReleased(e);
                break;
            default:
                break;
            }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // switch(e.getKeyCode()) {

        //     case KeyEvent.VK_W:
        //         gamePanel.getGame().getPlayer().setUp(true);
        //         break;          
        //     case KeyEvent.VK_A:
        //         gamePanel.getGame().getPlayer().setLeft(true);
        //         break;
        //     case KeyEvent.VK_S:
        //         gamePanel.getGame().getPlayer().setDown(true);
        //         break;
        //     case KeyEvent.VK_D:
        //         gamePanel.getGame().getPlayer().setRight(true);
        //         break;
        //     case KeyEvent.VK_P:
        //         gamePanel.getGame().getPlayer().setAttack(true);
        //         break;
        //     case KeyEvent.VK_SPACE:
        //         gamePanel.getGame().getPlayer().setJump(true);
        //         break;
        // }

        switch(Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
            case PLAYING:
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            default:
                break;
        }
    }
}
