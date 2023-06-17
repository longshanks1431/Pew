package gamestates;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;

import UI.MenuButton;
import main.Game;

public class Menu extends State implements Statemethods {

    private MenuButton[] buttons = new MenuButton[3];

    public Menu(Game game) {
        super(game);
        loadButtons();
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (150*Game.SCALE), 0, Gamestate.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (220*Game.SCALE), 0, Gamestate.OPTIONS);
        buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (290*Game.SCALE), 0, Gamestate.QUIT);
    }

    @Override
    public void update() {
        for(MenuButton mb : buttons)
            mb.update();
    }

    @Override
    public void draw(Graphics g) {

        for(MenuButton mb : buttons)
            mb.draw(g);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
            Gamestate.state = Gamestate.PLAYING;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
}
