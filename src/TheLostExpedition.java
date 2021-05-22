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
        ArrayList<Integer> tokens;

        // Card 1
        yellowEffects=null;
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        tokens = new ArrayList<>();
        tokens.add(Token.LEAF);
        tokens.add(Token.TENT);
        tokens.add(Token.COMPASS);
        redEffects.get(0).add(new Effect(new Token(tokens),Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.BULLET,Effect.ADD));
        redEffects.get(1).add(new Effect(Effect.BULLET,Effect.ADD));
        blueEffects=null;
        endDeck.add(new Card(1,yellowEffects,redEffects,blueEffects));

        // Card 2 (example, add last)
        yellowEffects=null;
        redEffects=null;
        blueEffects=null;
        endDeck.add(new Card(2,yellowEffects,redEffects,blueEffects));

        // Card 6
        yellowEffects=null;
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.SKIP_CARD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.BULLET,Effect.REMOVE));
        tokens = new ArrayList<>();
        tokens.add(Token.LEAF);
        redEffects.get(1).add(new Effect(new Token(tokens),Effect.ADD));
        blueEffects=null;
        endDeck.add(new Card(6,yellowEffects,redEffects,blueEffects));

        // Card 16
        yellowEffects=new ArrayList<>();
        yellowEffects.add(new Effect(Effect.HEALTH,Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.SKIP_CARD));
        redEffects=null;
        blueEffects=new ArrayList<>();
        blueEffects.add(new ArrayList<>());
        tokens = new ArrayList<>();
        tokens.add(Token.COMPASS);
        blueEffects.get(0).add(new Effect(new Token(tokens),Effect.REMOVE));
        tokens = new ArrayList<>();
        tokens.add(Token.LEAF);
        blueEffects.get(0).add(new Effect(new Token(tokens),Effect.ADD));
        endDeck.add(new Card(16,yellowEffects,redEffects,blueEffects));

        // Card 23
        yellowEffects=null;
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        tokens = new ArrayList<>();
        tokens.add(Token.TENT);
        redEffects.get(0).add(new Effect(new Token(tokens),Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        blueEffects=null;
        endDeck.add(new Card(23,yellowEffects,redEffects,blueEffects));

        // Card 26
        yellowEffects=new ArrayList<>();
        tokens = new ArrayList<>();
        tokens.add(Token.TENT);
        yellowEffects.add(new Effect(new Token(tokens),Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.FOOD,Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.SWAP_CARD));
        yellowEffects.add(new Effect(Effect.SKIP_CARD));
        redEffects=null;
        blueEffects=null;
        endDeck.add(new Card(26,yellowEffects,redEffects,blueEffects));

        return endDeck;
    }

    private static ArrayList<Card> deck;

    private static final ArrayList<Card> allCards = makeCards();

    private static final Random rand = new Random();
}
