package utilz;

public class Constants {

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
    
}
