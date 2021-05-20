public class Effect {
    public Effect(int thing, int mode) {
        this.thing=thing;
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

    private int thing, mode;
}
