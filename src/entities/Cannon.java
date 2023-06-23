package entities;

import main.Game;

public class Cannon extends Enemy {

    private int tileY;

    public Cannon(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height, enemyType);
        tileY = (int) y / Game.TILES_SIZE;
        initHitbox(40, 26);
        hitbox.x -= (int) (4 * Game.SCALE);
        hitbox.y += (int) (6 * Game.SCALE);
    }

    public void update() {
        if (doAnimation) {
            updateAnimationTick();
        }
    }

}
