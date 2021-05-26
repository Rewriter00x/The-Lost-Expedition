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
        redEffects.get(2).add(new Effect(new Token(Token.COMPASS,Token.LEAF),Effect.ADD));
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
        redEffects.get(1).add(new Effect(new Token(Token.LEAF,Token.TENT),Effect.ADD));
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

        // Card 14
        yellowEffects=null;
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        redEffects.get(0).add(new Effect(new Token(Token.COMPASS),Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.FOOD,Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.FOOD,Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.MOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(Effect.BULLET,Effect.REMOVE));
        redEffects.get(2).add(new Effect(Effect.SKIP_CARD));
        redEffects.get(2).add(new Effect(Effect.SKIP_CARD));
        redEffects.add(new ArrayList<>());
        redEffects.get(3).add(new Effect(Effect.KILL));
        blueEffects=null;
        endDeck.add(new Card(14,yellowEffects,redEffects,blueEffects));

        // Card 15
        yellowEffects=null;
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.FOOD,Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.FOOD,Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.MOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.MOVE));
        redEffects.get(1).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        blueEffects=null;
        endDeck.add(new Card(15,yellowEffects,redEffects,blueEffects));

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

        // Card 17
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.HEALTH,Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.ADD_CARD));
        yellowEffects.add(new Effect(Effect.SKIP_CARD));
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(new Token(Token.LEAF),Effect.REMOVE));
        redEffects.get(1).add(new Effect(new Token(Token.LEAF),Effect.REMOVE));
        blueEffects=null;
        endDeck.add(new Card(17,yellowEffects,redEffects,blueEffects));

        // Card 18
        yellowEffects=null;
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(new Token(Token.COMPASS),Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.MOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(new Token(Token.COMPASS),Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.FOOD,Effect.ADD));
        redEffects.get(1).add(new Effect(Effect.FOOD,Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(new Token(Token.LEAF),Effect.REMOVE));
        redEffects.get(2).add(new Effect(Effect.FOOD,Effect.ADD));
        redEffects.get(2).add(new Effect(Effect.FOOD,Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(3).add(new Effect(Effect.HEALTH,Effect.ADD));
        blueEffects=null;
        endDeck.add(new Card(18,yellowEffects,redEffects,blueEffects));

        // Card 19
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.SKIP_CARD));
        redEffects.get(0).add(new Effect(Effect.MOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.FOOD,Effect.REMOVE));
        redEffects.get(1).add(new Effect(new Token(Token.COMPASS),Effect.ADD));
        blueEffects = null;
        endDeck.add(new Card(19,yellowEffects,redEffects,blueEffects));

        // Card 20
        yellowEffects=null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.BULLET,Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.FOOD,Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        blueEffects=null;
        endDeck.add(new Card(20,yellowEffects,redEffects,blueEffects));

        // Card 21
        yellowEffects=new ArrayList<>();
        yellowEffects.add(new Effect(new Token(Token.TENT),Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.ADD_CARD));
        yellowEffects.add(new Effect(Effect.SWAP_CARD));
        redEffects=null;
        blueEffects=null;
        endDeck.add(new Card(21,yellowEffects,redEffects,blueEffects));

        // Card 22
        yellowEffects=null;
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.FOOD,Effect.REMOVE));
        redEffects.get(0).add(new Effect(new Token(Token.TENT),Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(new Token(Token.COMPASS),Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.MOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(Effect.KILL));
        redEffects.get(2).add(new Effect(Effect.MOVE));
        blueEffects=null;
        endDeck.add(new Card(22,yellowEffects,redEffects,blueEffects));

        // Card 23
        yellowEffects=null;
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(new Token(Token.TENT),Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        blueEffects=null;
        endDeck.add(new Card(23,yellowEffects,redEffects,blueEffects));

        // Card 24
        yellowEffects=new ArrayList<>();
        yellowEffects.add(new Effect(Effect.SKIP_CARD));
        yellowEffects.add(new Effect(Effect.SKIP_CARD));
        redEffects=null;
        blueEffects=new ArrayList<>();
        blueEffects.add(new ArrayList<>());
        blueEffects.get(0).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        blueEffects.get(0).add(new Effect(Effect.FOOD,Effect.ADD));
        blueEffects.get(0).add(new Effect(Effect.FOOD,Effect.ADD));
        endDeck.add(new Card(24,yellowEffects,redEffects,blueEffects));

        // Card 25
        yellowEffects=null;
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.BULLET,Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.FOOD,Effect.ADD));
        redEffects.get(0).add(new Effect(Effect.FOOD,Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.ADD_CARD));
        redEffects.get(1).add(new Effect(Effect.ADD_CARD));
        blueEffects=null;
        endDeck.add(new Card(25,yellowEffects,redEffects,blueEffects));

        // Card 26
        yellowEffects=new ArrayList<>();
        yellowEffects.add(new Effect(new Token(Token.TENT),Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.FOOD,Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.SWAP_CARD));
        yellowEffects.add(new Effect(Effect.SKIP_CARD));
        redEffects=null;
        blueEffects=null;
        endDeck.add(new Card(26,yellowEffects,redEffects,blueEffects));

        // Card 27
        yellowEffects=new ArrayList<>();
        yellowEffects.add(new Effect(Effect.SWAP_CARD));
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(new Token(Token.TENT),Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.KILL));
        blueEffects=null;
        endDeck.add(new Card(27,yellowEffects,redEffects,blueEffects));

        // Card 28
        yellowEffects=new ArrayList<>();
        yellowEffects.add(new Effect(Effect.HEALTH,Effect.REMOVE));
        yellowEffects.add(new Effect(new Token(Token.TENT),Effect.ADD));
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.HEALTH,Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(new Token(Token.COMPASS),Effect.REMOVE));
        blueEffects=null;
        endDeck.add(new Card(28,yellowEffects,redEffects,blueEffects));

        // Card 29
        yellowEffects=null;
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.FOOD,Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.ADD_CARD));
        redEffects.get(0).add(new Effect(Effect.MOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.FOOD,Effect.REMOVE));
        redEffects.get(1).add(new Effect(new Token(Token.COMPASS),Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.MOVE));
        blueEffects=new ArrayList<>();
        blueEffects.add(new ArrayList<>());
        blueEffects.get(0).add(new Effect(new Token(Token.LEAF),Effect.REMOVE));
        blueEffects.get(0).add(new Effect(Effect.SWAP_CARD));
        blueEffects.get(0).add(new Effect(Effect.SWAP_CARD));
        endDeck.add(new Card(29,yellowEffects,redEffects,blueEffects));

        // Card 30
        yellowEffects=null;
        redEffects=new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.BULLET,Effect.REMOVE));
        redEffects.get(0).add(new Effect(new Token(Token.COMPASS),Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(new Token(Token.LEAF),Effect.REMOVE));
        blueEffects=null;
        endDeck.add(new Card(30,yellowEffects,redEffects,blueEffects));

        // Example
        // Card
        yellowEffects=null;
        redEffects=null;
        blueEffects=null;
        endDeck.add(new Card(0,yellowEffects,redEffects,blueEffects));

        return endDeck;
    }
    private static ArrayList<Hero> makeHeroes() {
        ArrayList<Hero> heroes =new ArrayList<>();

        Hero YNES = new Hero(Hero.LEAF,"YNES");
        heroes.add(YNES);
        Hero ROY = new Hero(Hero.LEAF,"ROY");
        heroes.add(ROY);
        Hero BESSIE = new Hero(Hero.TENT,"BESSIE");
        heroes.add(BESSIE);
        Hero TEDDY = new Hero(Hero.TENT,"TEDDY");
        heroes.add(TEDDY);
        Hero CANDIDO = new Hero (Hero.COMPASS,"CANDIDO");
        heroes.add(CANDIDO);
        Hero ISABELLE = new Hero(Hero.COMPASS,"ISABELLE");
        heroes.add(ISABELLE);

        return heroes;
    }
    private static ArrayList<Card> deck;

    private static final ArrayList<Card> allCards = makeCards();

    private static final Random rand = new Random();
}
