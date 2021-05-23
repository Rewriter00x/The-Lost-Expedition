import java.awt.*;
import java.util.*;

public class TheLostExpedition {
    public static void main(String[] args) {
        fillCards();
        MainFrame frame = new MainFrame();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);
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
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(new Token(Token.LEAF,Token.TENT,Token.COMPASS),Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.BULLET,Effect.ADD));
        redEffects.get(1).add(new Effect(Effect.BULLET,Effect.ADD));
        blueEffects=null;
        endDeck.add(new Card(1,yellowEffects,redEffects,blueEffects));

        /* Card 2 (example, add last)
        yellowEffects=null;
        redEffects=null;
        blueEffects=null;
        endDeck.add(new Card(2,yellowEffects,redEffects,blueEffects));*/

        //card 2
        yellowEffects=null;
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.BULLET,Effect.REMOVE));
        redEffects.get(0).add(new Effect(new Token(Token.TENT),Effect.ADD));
        redEffects.get(0).add(new Effect(Effect.SWAP_CARD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.MOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(Effect.KILL));
        blueEffects=null;
        endDeck.add(new Card(2,yellowEffects,redEffects,blueEffects));

        //card 3
        yellowEffects=null;
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.FOOD,Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.SKIP_CARD));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(new Token(Token.COMPASS),Effect.ADD));
        redEffects.get(2).add(new Effect(new Token(Token.LEAF),Effect.ADD));
        blueEffects=null;
        endDeck.add(new Card(3,yellowEffects,redEffects,blueEffects));

        //card 4
        yellowEffects=null;
        redEffects=null;
        blueEffects=new ArrayList<>();
        blueEffects.add(new ArrayList<>());
        blueEffects.get(0).add(new Effect(new Token(Token.TENT),Effect.REMOVE));
        blueEffects.get(0).add(new Effect(Effect.FOOD,Effect.ADD));
        blueEffects.get(0).add(new Effect(Effect.FOOD,Effect.ADD));
        blueEffects.add(new ArrayList<>());
        blueEffects.get(1).add(new Effect(new Token(Token.TENT),Effect.REMOVE));
        blueEffects.get(1).add(new Effect(Effect.HEALTH,Effect.ADD));
        endDeck.add(new Card(4,yellowEffects,redEffects,blueEffects));

        //card 5
        yellowEffects=new ArrayList<>();
        yellowEffects.add(new Effect(Effect.ADD_CARD));
        redEffects=null;
        blueEffects=new ArrayList<>();
        blueEffects.add(new ArrayList<>());
        blueEffects.get(0).add(new Effect(new Token(Token.COMPASS),Effect.REMOVE));
        blueEffects.get(0).add(new Effect(Effect.MOVE));
        blueEffects.add(new ArrayList<>());
        blueEffects.get(1).add(new Effect(Effect.BULLET,Effect.REMOVE));
        blueEffects.get(1).add(new Effect(Effect.FOOD,Effect.ADD));
        endDeck.add(new Card(5,yellowEffects,redEffects,blueEffects));


        // Card 6
        yellowEffects=null;
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.SKIP_CARD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.BULLET,Effect.REMOVE));
        redEffects.get(1).add(new Effect(new Token(Token.LEAF),Effect.ADD));
        blueEffects=null;
        endDeck.add(new Card(6,yellowEffects,redEffects,blueEffects));

        // Card 7
        yellowEffects=new ArrayList<>();
        yellowEffects.add(new Effect(new Token(Token.COMPASS),Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.SWAP_CARD));
        yellowEffects.add(new Effect(Effect.SKIP_CARD));
        redEffects=null;
        blueEffects=null;
        endDeck.add(new Card(7,yellowEffects,redEffects,blueEffects));

        //card 8
        yellowEffects=null;
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.SKIP_CARD));
        redEffects.get(0).add(new Effect(Effect.MOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.FOOD,Effect.ADD));
        redEffects.get(1).add(new Effect(Effect.FOOD,Effect.ADD));
        redEffects.get(1).add(new Effect(Effect.FOOD,Effect.ADD));
        blueEffects=null;
        endDeck.add(new Card(8,yellowEffects,redEffects,blueEffects));

        //card 9
        yellowEffects=new ArrayList<>();
        yellowEffects.add(new Effect(Effect.FOOD,Effect.ADD));
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(new Token(Token.TENT),Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        blueEffects=null;
        endDeck.add(new Card(9,yellowEffects,redEffects,blueEffects));

        //card 10
        yellowEffects=new ArrayList<>();
        yellowEffects.add(new Effect(Effect.SKIP_CARD));
        redEffects=null;
        blueEffects=new ArrayList<>();
        blueEffects.add(new ArrayList<>());
        blueEffects.get(0).add(new Effect(new Token(Token.LEAF),Effect.REMOVE));
        blueEffects.get(0).add(new Effect(Effect.HEALTH,Effect.ADD));
        blueEffects.get(0).add(new Effect(Effect.HEALTH,Effect.ADD));
        blueEffects.get(0).add(new Effect(Effect.ADD_CARD));
        endDeck.add(new Card(10,yellowEffects,redEffects,blueEffects));

        //card 11
        yellowEffects=null;
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.MOVE));
        redEffects.get(0).add(new Effect(Effect.ADD_CARD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(new Token(Token.LEAF),Effect.ADD));
        redEffects.get(1).add(new Effect(new Token(Token.TENT),Effect.ADD));
        blueEffects=null;
        endDeck.add(new Card(11,yellowEffects,redEffects,blueEffects));

        //card 12
        yellowEffects=null;
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.BULLET,Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.FOOD,Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.BULLET,Effect.REMOVE));
        redEffects.get(1).add(new Effect(new Token(Token.COMPASS),Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        redEffects.get(2).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        blueEffects=null;
        endDeck.add(new Card(12,yellowEffects,redEffects,blueEffects));

        //card 13
        yellowEffects=null;
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.BULLET,Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.FOOD,Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.BULLET,Effect.REMOVE));
        redEffects.get(1).add(new Effect(new Token(Token.COMPASS),Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        redEffects.get(2).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        blueEffects=null;
        endDeck.add(new Card(13,yellowEffects,redEffects,blueEffects));

        // Card 16
        yellowEffects=new ArrayList<>();
        yellowEffects.add(new Effect(Effect.HEALTH,Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.SKIP_CARD));
        redEffects=null;
        blueEffects=new ArrayList<>();
        blueEffects.add(new ArrayList<>());
        blueEffects.get(0).add(new Effect(new Token(Token.COMPASS),Effect.REMOVE));
        blueEffects.get(0).add(new Effect(new Token(Token.LEAF),Effect.ADD));
        endDeck.add(new Card(16,yellowEffects,redEffects,blueEffects));

        // Card 23
        yellowEffects=null;
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(new Token(Token.TENT),Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        blueEffects=null;
        endDeck.add(new Card(23,yellowEffects,redEffects,blueEffects));

        // Card 26
        yellowEffects=new ArrayList<>();
        yellowEffects.add(new Effect(new Token(Token.TENT),Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.FOOD,Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.SWAP_CARD));
        yellowEffects.add(new Effect(Effect.SKIP_CARD));
        redEffects=null;
        blueEffects=null;
        endDeck.add(new Card(26,yellowEffects,redEffects,blueEffects));

        return endDeck;
    }
    private static ArrayList<Hero> makeHeroes() {
        ArrayList<Hero> heroes =new ArrayList<>();

        Hero YNES = new Hero(1,"YNES");
        heroes.add(YNES);
        Hero ROY = new Hero(1,"ROY");
        heroes.add(ROY);
        Hero BESSIE = new Hero(2,"BESSIE");
        heroes.add(BESSIE);
        Hero TEDDY = new Hero(2,"TEDDY");
        heroes.add(TEDDY);
        Hero CANDIDO = new Hero (3,"CANDIDO");
        heroes.add(CANDIDO);
        Hero ISABELLE = new Hero(3,"ISABELLE");
        heroes.add(ISABELLE);

        return heroes;
    }
    private static ArrayList<Card> deck;

    private static final ArrayList<Card> allCards = makeCards();

    private static final Random rand = new Random();
}
