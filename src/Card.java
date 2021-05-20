import java.util.ArrayList;

public class Card {

    public Card(int number, ArrayList<Effect> yellowEffects, ArrayList<ArrayList<Effect>> redEffects, ArrayList<ArrayList<Effect>> blueEffects) {
        this.number=number;
        this.yellowEffects=yellowEffects;
        this.redEffects=redEffects;
        this.blueEffects=blueEffects;
    }

    public void playCard() {
        if (yellowEffects.size()>0) for (Effect e : yellowEffects) e.doEffect();
        int n=0;// get number of red line
        if (redEffects.size()>0) for (Effect e: redEffects.get(0)) e.doEffect();
        n=0; // get number of blue line (n==-1 is no blue effect)
        if (n!=-1 && blueEffects.size()>0) for (Effect e: blueEffects.get(0)) e.doEffect();
    }

    private final int number;
    private final ArrayList<Effect> yellowEffects;
    private final ArrayList<ArrayList<Effect>> redEffects,blueEffects;
}
