public class Effect {
    public Effect(int effect, boolean mode) {
        this.effect=effect;
        this.mode=mode;
    }

    public void doEffect() {
        if (effect>0 && effect<7) {
            if (mode) {
                // Add effect
            }
            else {
                // Remove effect
            }
        }
        else {
            // Effects with cards, move or die
        }
    }

    private int effect;
    private boolean mode;

    public static final int KILL = -1;

    public static final int MOVE = 0;

    public static final int HEALTH = 1;

    public static final int FOOD = 2;

    public static final int AMMO = 3;

    public static final int LEAF = 4;

    public static final int TENT = 5;

    public static final int COMPASS = 6;

    public static final int SKIP = 7;

    public static final int SWAP = 8;

    public static final int REMOVE_CARD = 9;

    public static final int ADD_CARD = 10;

    public static final boolean ADD = true;

    public static final boolean REMOVE = false;
}
