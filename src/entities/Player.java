package entities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import utilz.LoadSave;
import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;
import main.Game;
import objects.ObjectManager;
import objects.Projectile;
import objects.Projectile.*;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import gamestates.Playing;
import static utilz.Constants.Projectiles.*;

public class Player extends Entity {

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 14;
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down, jump, shoot;
    private float playerSpeed = 2.0f;
    private int[][] lvlData;
    private float xDrawOffset = 5 * Game.SCALE;
    private float yDrawOffset = 8 * Game.SCALE;

    // Jumping and Gravity
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;

    // Status Bar UI

    private BufferedImage statusBarImg;
    private int statusBarWidth = (int) (192 * Game.SCALE);
    private int statusBarHeight = (int) (58 * Game.SCALE);
    private int statusBarX = (int) (10 * Game.SCALE);
    private int statusBarY = (int) (10 * Game.SCALE);
    private int healthBarWidth = (int) (150 * Game.SCALE);
    private int healthBarHeight = (int) (4 * Game.SCALE);
    private int healthBarXStart = (int) (34 * Game.SCALE);
    private int healthBarYStart = (int) (14 * Game.SCALE);
    private int maxHealth = 100;
    private int currentHealth = maxHealth;
    private int healthWidth = healthBarWidth;

    // Attack Box
    private Rectangle2D.Float attackBox;
    private int flipX = 0;
    private int flipW = 1;

    private boolean attackChecked;
    private Playing playing;

    private BufferedImage ballImg = LoadSave.GetSpriteAtlas(LoadSave.BALL);
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private Projectile bullet = new Projectile(0, 0, -1, playing);

    public Player(float x, float y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;
        loadAnimations();
        initHitbox(x, y, (int) 23 * Game.SCALE, (int) 23 * Game.SCALE);
        initAttackBox();
    }

    public boolean faceRight() {
        if (flipW == -1)
            return false;
        return true;
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x, y, (int) (20 * Game.SCALE), (int) (20 * Game.SCALE));
    }

    public void update() {

        updateHealthBar();

        if (currentHealth <= 0) {
            playing.setGameOver(true);
            return;
        }

        updateAttackBox();
        updatePos();
        if (attacking)
            checkAttack();
        updateAnimationTick();
        setAnimation();
        updateProjectiles();
        updateProjectiles1();
    }

    private void updateProjectiles() {
        for (Projectile p : projectiles)
            if (p.isActive()) {
                p.updatePos();
                // if (p.getHitbox().intersects(getHitbox())) {
                // changeHealth(-25);
                // p.setActive(false);
                // } else
                if (IsProjectileHittingLevel(p, lvlData))
                    p.setActive(false);
            }
    }

    private void updateProjectiles1() {
        if (bullet.isActive()) {
            bullet.updatePos();
            playing.checkEnemyHit(bullet.getHitbox());
            if (playing.checkEnemyHit(bullet.getHitbox()))
                bullet.setActive(false);

            if (IsProjectileHittingLevel(bullet, lvlData))
                bullet.setActive(false);
        }
    }

    private void checkAttack() {
        if (attackChecked || aniIndex != 1)
            return;
        attackChecked = true;
        playing.checkEnemyHit(attackBox);
    }

    private void updateAttackBox() {
        if (right) {
            attackBox.x = hitbox.x + hitbox.width + (int) (Game.SCALE * 10);
        } else if (left) {
            attackBox.x = hitbox.x - hitbox.width - (int) (Game.SCALE * 10);
        }
        attackBox.y = hitbox.y + (Game.SCALE * 10);
    }

    private void updateHealthBar() {
        healthWidth = (int) ((currentHealth / (float) maxHealth) * healthBarWidth);
    }

    public void render(Graphics g, int lvlOffset) {
        g.drawImage(animations[playerAction][aniIndex],
                (int) (hitbox.x - xDrawOffset) - lvlOffset + flipX,
                (int) (hitbox.y - yDrawOffset),
                width * flipW, height, null);
        // drawHitbox(g, lvlOffset);
        drawAttackBox(g, lvlOffset);
        drawProjectiles(g, lvlOffset);
        drawOneBullet(g, lvlOffset);
        drawUI(g);
    }

    private void drawProjectiles(Graphics g, int xLvlOffset) {
        for (Projectile p : projectiles)
            if (p.isActive())
                g.drawImage(ballImg, (int) (p.getHitbox().x - xLvlOffset), (int) (p.getHitbox().y),
                        BALL_WIDTH, BALL_HEIGHT, null);
    }

    private void drawOneBullet(Graphics g, int xLvlOffset) {
        if (bullet.isActive())
            g.drawImage(ballImg, (int) (bullet.getHitbox().x - xLvlOffset), (int) (bullet.getHitbox().y),
                    BALL_WIDTH, BALL_HEIGHT, null);
    }

    private void drawAttackBox(Graphics g, int lvlOffsetX) {
        g.setColor(Color.red);
        g.drawRect((int) attackBox.x - lvlOffsetX, (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
    }

    private void drawUI(Graphics g) {
        g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
        g.setColor(Color.red);
        g.fillRect(healthBarXStart + statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
                attackChecked = false;
            }
        }
    }

    protected void shoot() {
        int dir = 1;
        if (faceRight() == false)
            dir = -1;

        projectiles.add(new Projectile((int) getHitbox().x, (int) getHitbox().y,
                dir, playing));
    }

    protected void shoot1() {
        int dir = 1;
        if (faceRight() == false)
            dir = -1;

        bullet = new Projectile((int) getHitbox().x, (int) getHitbox().y,
                dir, playing);
    }

    private void setAnimation() {

        int startAni = playerAction;

        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;

        if (inAir) {
            if (airSpeed < 0)
                playerAction = JUMP;
            else
                playerAction = FALL;
        }

        if (shoot) {
            shoot1();
        }

        if (attacking) {
            playerAction = WALLJUMP;
            if (startAni != WALLJUMP) {
                aniIndex = 1;
                aniTick = 0;
                return;
            }
        }

        if (startAni != playerAction)
            resetAniTick();
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {
        moving = false;

        if (jump)
            jump();

        if (!inAir)
            if ((!left && !right) || (right && left))
                return;

        float xSpeed = 0;

        if (left) {
            xSpeed -= playerSpeed;
            flipX = width;
            flipW = -1;
        }
        if (right) {
            xSpeed += playerSpeed;
            flipX = 0;
            flipW = 1;
        }

        if (!inAir) {
            if (!IsEntityOnFloor(hitbox, lvlData))
                inAir = true;
        }

        if (inAir) {

            if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {

                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);

            } else {
                hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                if (airSpeed > 0)
                    resetInAir();
                else
                    airSpeed = fallSpeedAfterCollision;
                updateXPos(xSpeed);
            }

        } else
            updateXPos(xSpeed);
        moving = true;
    }

    private void jump() {
        if (inAir)
            return;
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
            hitbox.x += xSpeed;
        } else {
            hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
        }

    }

    public void changeHealth(int value) {
        currentHealth += value;

        if (currentHealth <= 0) {
            currentHealth = 0;
            // gameOver();
        } else if (currentHealth >= maxHealth)
            currentHealth = maxHealth;
    }

    private void loadAnimations() {

        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

        animations = new BufferedImage[7][12];
        for (int j = 0; j < animations.length; j++)
            for (int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage(i * 32, j * 32, 32, 32);

        statusBarImg = LoadSave.GetSpriteAtlas(LoadSave.STATUS_BAR);

    }

    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
        if (!IsEntityOnFloor(hitbox, lvlData))
            inAir = true;
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttack(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    public void resetAll() {
        resetDirBooleans();
        inAir = false;
        attacking = false;
        moving = false;
        playerAction = IDLE;
        currentHealth = maxHealth;

        hitbox.x = x;
        hitbox.y = y;

        if (!IsEntityOnFloor(hitbox, lvlData))
            inAir = true;
    }

}
