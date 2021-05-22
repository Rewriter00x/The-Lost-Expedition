public class Effect {

    public Effect(int effect) {
        this.effect=effect;
    } // Card actions move or die

    public Effect(int effect, boolean mode) { // effect 1 2 3
        this.effect=effect;
        this.mode=mode;
    }

    public Effect(Token token, boolean mode) { // for tokens
        this.token=token;
        this.mode=mode;
    }

    public void doEffect() { //TODO doing effects
        if (token==null) {
            if (effect >= 1 && effect <= 3) {
                if (mode) {
                    // Add effect
                } else {
                    // Remove effect
                }
            } else {
                // Effects with cards, move or die
            }
        }
        else {
            if (mode) {
                // Give token
            }
            else {
                // Remove token
            }
        }
    }

    private int effect=-2;
    private boolean mode;
    private Token token=null;

    public static final int KILL = -1;

    public static final int MOVE = 0;

    public static final int HEALTH = 1;

    public static final int FOOD = 2;

    public static final int BULLET = 3;

    public static final int SKIP_CARD = 4;

    public static final int SWAP_CARD = 5;

    public static final int REMOVE_CARD = 6;

    public static final int ADD_CARD = 7;

    public static final boolean ADD = true;

    public static final boolean REMOVE = false;
}
