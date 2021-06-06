import java.util.*;

/**
 * Class Card
 * @author Burliai Danylo
 * This class Represents card with number and effects
 */
public class Card {

    public Card(int number, ArrayList<Effect> yellowEffects, ArrayList<ArrayList<Effect>> redEffects, ArrayList<ArrayList<Effect>> blueEffects) {
        this.number=number;
        this.yellowEffects=yellowEffects;
        this.redEffects=redEffects;
        this.blueEffects=blueEffects;
    }

    /**
     * Returns yellow effects
     * @return yellow effects
     */
    public ArrayList<Effect> getYellowEffects() {return yellowEffects;}

    /**
     * Returns red effects
     * @return red effects
     */
    public ArrayList<ArrayList<Effect>> getRedEffects() {return redEffects;}

    /**
     * Returns blue effects
     * @return blue effects
     */
    public ArrayList<ArrayList<Effect>> getBlueEffects() {return blueEffects;}

    /**
     * Returns card number
     * @return card number
     */
    public int getNumber() { return number; }

    public String toString() {
        return "Card "+number;
    }

    private final int number;
    private final ArrayList<Effect> yellowEffects;
    private final ArrayList<ArrayList<Effect>> redEffects,blueEffects;
}
