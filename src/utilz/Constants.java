package utilz;

import main.Game;

public class Constants {

    public static final float GRAVITY = 0.04f * Game.SCALE;
    public static final int ANI_SPEED = 25;

    public static class ObjectConstants {

        public static final int RED_POTION = 0;
        public static final int BLUE_POTION = 1;
        public static final int BARREL = 2;
        public static final int BOX = 3;
        public static final int SPIKE = 4;
        public static final int CANNON_LEFT = 5;
        public static final int CANNON_RIGHT = 6;

        public static final int RED_POTION_VALUE = 15;
        public static final int BLUE_POTION_VALUE = 10;

        public static final int CONTAINER_WIDTH_DEFAULT = 40;
        public static final int CONTAINER_HEIGHT_DEFAULT = 30;
        public static final int CONTAINER_WIDTH = (int) (Game.SCALE * CONTAINER_WIDTH_DEFAULT);
        public static final int CONTAINER_HEIGHT = (int) (Game.SCALE * CONTAINER_HEIGHT_DEFAULT);

        public static final int POTION_WIDTH_DEFAULT = 12;
        public static final int POTION_HEIGHT_DEFAULT = 16;
        public static final int POTION_WIDTH = (int) (Game.SCALE * POTION_WIDTH_DEFAULT);
        public static final int POTION_HEIGHT = (int) (Game.SCALE * POTION_HEIGHT_DEFAULT);

        public static final int SPIKE_WIDTH_DEFAULT = 32;
        public static final int SPIKE_HEIGHT_DEFAULT = 32;
        public static final int SPIKE_WIDTH = (int) (Game.SCALE * SPIKE_WIDTH_DEFAULT);
        public static final int SPIKE_HEIGHT = (int) (Game.SCALE * SPIKE_HEIGHT_DEFAULT);

        public static final int CANNON_WIDTH_DEFAULT = 40;
        public static final int CANNON_HEIGHT_DEFAULT = 26;
        public static final int CANNON_WIDTH = (int) (CANNON_WIDTH_DEFAULT * Game.SCALE);
        public static final int CANNON_HEIGHT = (int) (CANNON_HEIGHT_DEFAULT * Game.SCALE);

        public static int GetSpriteAmount(int object_type) {
            switch (object_type) {
                case RED_POTION, BLUE_POTION:
                    return 7;
                case BARREL, BOX:
                    return 8;
                case CANNON_LEFT, CANNON_RIGHT:
                    return 7;
            }
            return 1;
        }

    }

    public static class Projectiles {

        public static final int BALL_WIDTH_DEFAULT = 6;
        public static final int BALL_HEIGHT_DEFAULT = 6;
        public static final int BALL_WIDTH = (int) (BALL_WIDTH_DEFAULT * Game.SCALE);
        public static final int BALL_HEIGHT = (int) (BALL_HEIGHT_DEFAULT * Game.SCALE);
        public static final float SPEED = 3.0f * Game.SCALE;
    }

    public static class Environment {
        public static final int BIG_CLOUD_WIDTH_DEFAULT = 448;
        public static final int BIG_CLOUD_HEIGHT_DEFAULT = 101;
        public static final int BIG_CLOUD_WIDTH = (int) (BIG_CLOUD_WIDTH_DEFAULT * Game.SCALE);
        public static final int BIG_CLOUD_HEIGHT = (int) (BIG_CLOUD_HEIGHT_DEFAULT * Game.SCALE);

        public static final int SMALL_CLOUD_WIDTH_DEFAULT = 74;
        public static final int SMALL_CLOUD_HEIGHT_DEFAULT = 24;
        public static final int SMALL_CLOUD_WIDTH = (int) (SMALL_CLOUD_WIDTH_DEFAULT * Game.SCALE);
        public static final int SMALL_CLOUD_HEIGHT = (int) (SMALL_CLOUD_HEIGHT_DEFAULT * Game.SCALE);
    }

    public static class UI {
        public static class Buttons {
            public static final int B_WIDTH_DEFAULT = 140;
            public static final int B_HEIGHT_DEFAULT = 56;
            public static final int B_WIDTH = (int) (B_WIDTH_DEFAULT * Game.SCALE);
            public static final int B_HEIGHT = (int) (B_HEIGHT_DEFAULT * Game.SCALE);
        }
    }

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants {
        public static final int IDLE = 5;
        public static final int RUNNING = 6;
        public static final int JUMP = 3;
        public static final int DUBJUMP = 0;
        public static final int FALL = 1;
        public static final int HIT = 2;
        public static final int WALLJUMP = 4;

        public static int GetSpriteAmount(int player_action) {

            switch (player_action) {
                case IDLE:
                    return 11;
                case RUNNING:
                    return 12;
                case JUMP:
                    return 1;
                case DUBJUMP:
                    return 6;
                case FALL:
                    return 1;
                case HIT:
                    return 7;
                case WALLJUMP:
                    return 5;
                default:
                    return 1;
            }
        }
    }

    public static class EnemyConstants {
        public static final int CRABBY = 0;

        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int ATTACK = 2;
        public static final int HIT = 3;
        public static final int DEAD = 4;

        // Cannons
        public static final int CANNON_LEFT = 5;
        public static final int CANNON_RIGHT = 6;
        public static final int CANNON_WIDTH_DEFAULT = 40;
        public static final int CANNON_HEIGHT_DEFAULT = 26;
        public static final int CANNON_WIDTH = (int) (CANNON_WIDTH_DEFAULT * Game.SCALE);
        public static final int CANNON_HEIGHT = (int) (CANNON_HEIGHT_DEFAULT * Game.SCALE);

        public static final int CRABBY_WIDTH_DEFAULT = 72;
        public static final int CRABBY_HEIGHT_DEFAULT = 32;

        public static final int CRABBY_WIDTH = (int) (2 * CRABBY_WIDTH_DEFAULT * Game.SCALE);
        public static final int CRABBY_HEIGHT = (int) (2 * CRABBY_HEIGHT_DEFAULT * Game.SCALE);

        public static final int CRABBY_DRAWOFFSET_X = (int) (26 * Game.SCALE);
        public static final int CRABBY_DRAWOFFSET_Y = (int) (9 * Game.SCALE);

        public static int GetSpriteAmount(int enemy_type, int enemy_state) {

            switch (enemy_type) {
                case CRABBY:
                    switch (enemy_state) {
                        case IDLE:
                            return 9;
                        case RUNNING:
                            return 6;
                        case ATTACK:
                            return 7;
                        case HIT:
                            return 4;
                        case DEAD:
                            return 5;
                        case CANNON_LEFT, CANNON_RIGHT:
                            return 7;
                    }
            }
            return 0;
        }

        // Get the amount of health that an enemy starts with
        public static int GetMaxHealth(int enemy_type) {
            switch (enemy_type) {
                case CRABBY:
                    return 150;
                default:
                    return 1;
            }
        }

        // If enemy hit box intersects with player's, this is how much damage the enemy
        // will do
        public static int GetEnemyDmg(int enemy_type) {
            switch (enemy_type) {
                case CRABBY:
                    return 15;
                default:
                    return 0;
            }
        }

    }

}
