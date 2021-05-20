public class Effect {
    public Effect(int effect, int mode) {
        this.effect=effect;
        this.mode=mode;
    }

    public void doEffect() {
        if (mode==0) {
            //Add effect
        }
        else if (mode==1){
            //Remove effect
        }
        else {
            //Effect with cards
        }
    }

    private int effect, mode;

    private static final int MOVE = 0;

    private static final int HEALTH = 1;

    private static final int FOOD = 2;

    private static final int AMMO = 3;

    private static final int LEAF = 4;

    private static final int TENT = 5;

    private static final int COMPASS = 6;
}
