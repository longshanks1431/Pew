package entities;

import gamestates.Playing;
import utilz.LoadSave;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics;
import static utilz.Constants.EnemyConstants.*;
import java.awt.geom.Rectangle2D;

public class EnemyManager {

    private Playing playing;
    private BufferedImage[][] crabbyArr;
    private ArrayList<Crabby> crabbies = new ArrayList<>();

    public EnemyManager(Playing playing) {
        this.playing = playing;
        loadEnemyImgs();
        addEnemies();
    }

    private void addEnemies() {

        crabbies = LoadSave.GetCrabs();
        System.out.println("size of crabs: " + crabbies.size());

    }

    public void update(int[][] lvlData, Player player) {
        for (Crabby c : crabbies)
            if (c.isActive())
                c.update(lvlData, player);
    }

    public void draw(Graphics g, int xLvlOffset) {
        drawCrabs(g, xLvlOffset);
    }

    private void drawCrabs(Graphics g, int xLvlOffset) {
        for (Crabby c : crabbies)
            if (c.isActive()) {
                g.drawImage(crabbyArr[c.getEnemyState()][c.getAniIndex()],
                        (int) c.getHitbox().x - xLvlOffset - CRABBY_DRAWOFFSET_X + c.flipX(),
                        (int) c.getHitbox().y - CRABBY_DRAWOFFSET_Y - 40,
                        CRABBY_WIDTH * c.flipW(), CRABBY_HEIGHT, null);
                c.drawHitbox(g, xLvlOffset);
                c.drawAttackBox(g, xLvlOffset);
            }
    }

    public boolean checkEnemyHit(Rectangle2D.Float attackBox) {
        for (Crabby c : crabbies)
            if (c.isActive()) {
                if (attackBox.intersects(c.getHitbox())) {
                    c.hurt(10);
                    return true;
                }
            }
        return false;
    }

    private void loadEnemyImgs() {
        crabbyArr = new BufferedImage[5][9];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CRAB_ATLAS);
        for (int j = 0; j < crabbyArr.length; j++)
            for (int i = 0; i < crabbyArr[j].length; i++)
                crabbyArr[j][i] = temp.getSubimage(i * CRABBY_WIDTH_DEFAULT, j * CRABBY_HEIGHT_DEFAULT,
                        CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
    }

    public void resetAllEnemies() {
        for (Crabby c : crabbies)
            c.resetEnemy();
    }
}
