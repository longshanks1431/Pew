package utilz;
import main.Game;

public class Constants {

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

    public static class PlayerConstants{
        public static final int IDLE = 5;
        public static final int RUNNING = 6;
        public static final int JUMP = 3;
        public static final int DUBJUMP = 0;
        public static final int FALL = 1;
        public static final int HIT = 2;
        public static final int WALLJUMP = 4;

        public static int GetSpriteAmount(int player_action) {

            switch(player_action) {
                case IDLE: return 11;
                case RUNNING: return 12;
                case JUMP: return 1;
                case DUBJUMP: return 6;
                case FALL: return 1;
                case HIT: return 7;
                case WALLJUMP: return 5;
                default: return 1;
            }
        }        
    }

    public static class EnemyConstants{
        public static final int CRABBY = 0;

        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int ATTACK = 2;
        public static final int HIT = 3;
        public static final int DEAD = 4;

        public static final int CRABBY_WIDTH_DEFAULT = 72;
        public static final int CRABBY_HEIGHT_DEFAULT = 32;

        public static final int CRABBY_WIDTH = (int) (CRABBY_WIDTH_DEFAULT * Game.SCALE);
        public static final int CRABBY_HEIGHT = (int) (CRABBY_HEIGHT_DEFAULT * Game.SCALE);

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
                    }
            }
            return 0;
        }

    }
    
}
