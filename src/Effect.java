public class Effect {
    public Effect(int thing, boolean mode) {
        this.thing=thing;
        this.mode=mode;
    }

    public void doEffect() {
        if (mode) {
            //Add effect
        }
        else {
            //Remove effect
        }
    }

    private int thing;
    private boolean mode;
}
