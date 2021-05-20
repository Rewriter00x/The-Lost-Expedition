import java.util.ArrayList;

public class Card {

    public Card(int number, ArrayList<ArrayList<Effect>> yellowEffects, ArrayList<ArrayList<Effect>> redEffects, ArrayList<ArrayList<Effect>> blueEffects) {
        this.number=number;
        this.yellowEffects=yellowEffects;
        this.redEffects=redEffects;
        this.blueEffects=blueEffects;
    }

    private final int number;
    private ArrayList<ArrayList<Effect>> yellowEffects,redEffects,blueEffects;
}
