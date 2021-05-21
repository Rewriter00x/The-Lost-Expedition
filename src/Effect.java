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

    private static final int KILL = -1;

    private static final int MOVE = 0;

    private static final int HEALTH = 1;

    private static final int FOOD = 2;

    private static final int AMMO = 3;

    private static final int LEAF = 4;

    private static final int TENT = 5;

    private static final int COMPASS = 6;

    private static final int SKIP = 7;

    private static final int SWAP = 8;

    private static final int REMOVE = 9;

    private static final int ADD = 10;
}
