import java.util.*;

public class TheLostExpedition {
    public static void main(String[] args) {
        fillCards();
    }

    private static void fillCards() {
        deck = fillDeck();
    }

    private static ArrayList<Card> fillDeck() {
        ArrayList<Card> tempDeck = allCards, endDeck = new ArrayList<>();
        int n;
        for (int i = 0; i<allCards.size(); i++) {
            n = rand.nextInt(allCards.size()-i);
            endDeck.add(tempDeck.remove(n));
        }
        return endDeck;
    }

    private static ArrayList<Card> makeCards() {
        // Making cards here
        ArrayList<Card> endDeck = new ArrayList<>();
        ArrayList<Effect> yellowEffects;
        ArrayList<ArrayList<Effect>> redEffects,blueEffects;

        // Card 1
        yellowEffects=null;
        redEffects=null;
        blueEffects=null;
        endDeck.add(new Card(1,yellowEffects,redEffects,blueEffects));

        // Card 2
        yellowEffects=null;
        redEffects=null;
        blueEffects=null;
        endDeck.add(new Card(2,yellowEffects,redEffects,blueEffects));

        return endDeck;
    }

    private static ArrayList<Card> deck;

    private static final ArrayList<Card> allCards = makeCards();

    private static final Random rand = new Random();
}
