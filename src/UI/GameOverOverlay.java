package UI;

import gamestates.Playing;
import main.Game;
import java.awt.Color;
import java.awt.Graphics;
import gamestates.Gamestate;
import java.awt.event.KeyEvent;

// this class contains content for the Game Over screen
public class GameOverOverlay {

    // this variable holds an instance of the playing class, which we need to pass
    // in
    private Playing playing;

    // constructor
    public GameOverOverlay(Playing playing) {

        this.playing = playing;

    }

    // code for rendering the Game Over screen
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        g.setColor(Color.white);
        g.drawString("GAME OVER", Game.GAME_WIDTH / 2, 150);
        g.drawString("Press esc to enter Main Menu", Game.GAME_WIDTH / 2, 300);

    }

    // if we press Escape in this screen, it's going to reset the Playing class and
    // set the Gamestate to Menu
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            playing.resetAll();
            Gamestate.state = Gamestate.MENU;
        }

    }

}
