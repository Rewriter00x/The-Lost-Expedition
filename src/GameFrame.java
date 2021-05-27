import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class GameFrame extends JFrame {
    public GameFrame(int mode, int difficulty) {
        super();
        fillCards();
        if (difficulty==3) Hero.setMaxHP(3);
        team = new Team(new Hero(Hero.LEAF,"ynes"),new Hero(Hero.TENT,"teddy"),new Hero(Hero.COMPASS,"isabelle"));team = new Team(new Hero(Hero.LEAF,"ynes"),new Hero(Hero.TENT,"teddy"),new Hero(Hero.COMPASS,"isabelle"));
        setSize(width,height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel.setSize(width,height);
        panel.setLayout(null);
        add(panel);

        int heroCardWidth = 150;
        int heroCardHeight = 225;

        heroLeafImage=null;
        try {
            heroLeafImage = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/Heroes/"+team.getHero(0).getNAME()+".png")).getScaledInstance(heroCardWidth,heroCardHeight, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        heroLeafImage.setBounds(0,0,heroCardWidth,heroCardHeight);
        panel.add(heroLeafImage);

        heroLeafHP = new JLabel(team.getHero(0).getHP()+"/"+((difficulty==3)?3:4));
        heroLeafHP.setFont(new Font("Arial",Font.PLAIN,30));
        heroLeafHP.setBounds(0,heroLeafImage.getHeight(),45,30);
        heroLeafHP.setOpaque(true);
        heroLeafHP.setBackground(new Color(3, 87, 30));
        heroLeafHP.setForeground(new Color(245, 205, 76));
        panel.add(heroLeafHP);

        heroTentImage=null;
        try {
            heroTentImage = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/Heroes/"+team.getHero(1).getNAME()+".png")).getScaledInstance(heroCardWidth,heroCardHeight, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        heroTentImage.setBounds(heroLeafImage.getWidth()+20,0,heroCardWidth,heroCardHeight);
        panel.add(heroTentImage);

        heroTentHP = new JLabel(team.getHero(1).getHP()+"/"+((difficulty==3)?3:4));
        heroTentHP.setFont(new Font("Arial",Font.PLAIN,30));
        heroTentHP.setBounds(heroLeafImage.getWidth()+20,heroLeafImage.getHeight(),45,30);
        heroTentHP.setOpaque(true);
        heroTentHP.setBackground(new Color(3, 87, 30));
        heroTentHP.setForeground(new Color(245, 205, 76));
        panel.add(heroTentHP);

        heroCompassImage=null;
        try {
            heroCompassImage = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/Heroes/"+team.getHero(2).getNAME()+".png")).getScaledInstance(heroCardWidth,heroCardHeight, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        heroCompassImage.setBounds(0,heroLeafImage.getHeight()+50,heroCardWidth,heroCardHeight);
        panel.add(heroCompassImage);

        heroCompassHP = new JLabel(team.getHero(2).getHP()+"/"+((difficulty==3)?3:4));
        heroCompassHP.setFont(new Font("Arial",Font.PLAIN,30));
        heroCompassHP.setBounds(0,heroLeafImage.getHeight()*2+50,45,30);
        heroCompassHP.setOpaque(true);
        heroCompassHP.setBackground(new Color(3, 87, 30));
        heroCompassHP.setForeground(new Color(245, 205, 76));
        panel.add(heroCompassHP);
    }

    private void fillCards() {
        deck = fillDeck();
    }

    private ArrayList<Card> fillDeck() {
        ArrayList<Card> tempDeck = (ArrayList<Card>) allCards.clone(), endDeck = new ArrayList<>();
        int n;
        for (int i = 0; i<allCards.size(); i++) {
            n = rand.nextInt(allCards.size()-i);
            endDeck.add(tempDeck.remove(n));
        }
        return endDeck;
    }

    private ArrayList<Card> makeCards() {
        // Making cards here
        ArrayList<Card> endDeck = new ArrayList<>();
        ArrayList<Effect> yellowEffects;
        ArrayList<ArrayList<Effect>> redEffects,blueEffects;

        // Card 1
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(new Token(Token.LEAF, Token.TENT, Token.COMPASS), Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.BULLET, Effect.ADD));
        redEffects.get(1).add(new Effect(Effect.BULLET, Effect.ADD));
        blueEffects = null;
        endDeck.add(new Card(1, yellowEffects, redEffects, blueEffects));

        //card 2
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.BULLET, Effect.REMOVE));
        redEffects.get(0).add(new Effect(new Token(Token.TENT), Effect.ADD));
        redEffects.get(0).add(new Effect(Effect.SWAP_CARD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.MOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(Effect.KILL));
        blueEffects = null;
        endDeck.add(new Card(2, yellowEffects, redEffects, blueEffects));

        //card 3
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.FOOD, Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.SKIP_CARD));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(new Token(Token.COMPASS, Token.LEAF), Effect.ADD));
        blueEffects = null;
        endDeck.add(new Card(3, yellowEffects, redEffects, blueEffects));

        //card 4
        yellowEffects = null;
        redEffects = null;
        blueEffects = new ArrayList<>();
        blueEffects.add(new ArrayList<>());
        blueEffects.get(0).add(new Effect(new Token(Token.TENT), Effect.REMOVE));
        blueEffects.get(0).add(new Effect(Effect.FOOD, Effect.ADD));
        blueEffects.get(0).add(new Effect(Effect.FOOD, Effect.ADD));
        blueEffects.add(new ArrayList<>());
        blueEffects.get(1).add(new Effect(new Token(Token.TENT), Effect.REMOVE));
        blueEffects.get(1).add(new Effect(Effect.HEALTH, Effect.ADD));
        endDeck.add(new Card(4, yellowEffects, redEffects, blueEffects));

        //card 5
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.ADD_CARD));
        redEffects = null;
        blueEffects = new ArrayList<>();
        blueEffects.add(new ArrayList<>());
        blueEffects.get(0).add(new Effect(new Token(Token.COMPASS), Effect.REMOVE));
        blueEffects.get(0).add(new Effect(Effect.MOVE));
        blueEffects.add(new ArrayList<>());
        blueEffects.get(1).add(new Effect(Effect.BULLET, Effect.REMOVE));
        blueEffects.get(1).add(new Effect(Effect.FOOD, Effect.ADD));
        endDeck.add(new Card(5, yellowEffects, redEffects, blueEffects));

        // Card 6
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.SKIP_CARD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.BULLET, Effect.REMOVE));
        redEffects.get(1).add(new Effect(new Token(Token.LEAF), Effect.ADD));
        blueEffects = null;
        endDeck.add(new Card(6, yellowEffects, redEffects, blueEffects));

        // Card 7
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(new Token(Token.COMPASS), Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.SWAP_CARD));
        yellowEffects.add(new Effect(Effect.SKIP_CARD));
        redEffects = null;
        blueEffects = null;
        endDeck.add(new Card(7, yellowEffects, redEffects, blueEffects));

        //card 8
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.SKIP_CARD));
        redEffects.get(0).add(new Effect(Effect.MOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.FOOD, Effect.ADD));
        redEffects.get(1).add(new Effect(Effect.FOOD, Effect.ADD));
        redEffects.get(1).add(new Effect(Effect.FOOD, Effect.ADD));
        blueEffects = null;
        endDeck.add(new Card(8, yellowEffects, redEffects, blueEffects));

        //card 9
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.FOOD, Effect.ADD));
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(new Token(Token.TENT), Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        blueEffects = null;
        endDeck.add(new Card(9, yellowEffects, redEffects, blueEffects));

        //card 10
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.SKIP_CARD));
        redEffects = null;
        blueEffects = new ArrayList<>();
        blueEffects.add(new ArrayList<>());
        blueEffects.get(0).add(new Effect(new Token(Token.LEAF), Effect.REMOVE));
        blueEffects.get(0).add(new Effect(Effect.HEALTH, Effect.ADD));
        blueEffects.get(0).add(new Effect(Effect.HEALTH, Effect.ADD));
        blueEffects.get(0).add(new Effect(Effect.ADD_CARD));
        endDeck.add(new Card(10, yellowEffects, redEffects, blueEffects));

        //card 11
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.MOVE));
        redEffects.get(0).add(new Effect(Effect.ADD_CARD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(new Token(Token.LEAF, Token.TENT), Effect.ADD));
        blueEffects = null;
        endDeck.add(new Card(11, yellowEffects, redEffects, blueEffects));

        //card 12
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.BULLET, Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.FOOD, Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.BULLET, Effect.REMOVE));
        redEffects.get(1).add(new Effect(new Token(Token.COMPASS), Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.get(2).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        blueEffects = null;
        endDeck.add(new Card(12, yellowEffects, redEffects, blueEffects));

        //card 13
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.BULLET, Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.FOOD, Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.BULLET, Effect.REMOVE));
        redEffects.get(1).add(new Effect(new Token(Token.COMPASS), Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.get(2).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        blueEffects = null;
        endDeck.add(new Card(13, yellowEffects, redEffects, blueEffects));

        // Card 14
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.get(0).add(new Effect(new Token(Token.COMPASS), Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.FOOD, Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.FOOD, Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.MOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(Effect.BULLET, Effect.REMOVE));
        redEffects.get(2).add(new Effect(Effect.SKIP_CARD));
        redEffects.get(2).add(new Effect(Effect.SKIP_CARD));
        redEffects.add(new ArrayList<>());
        redEffects.get(3).add(new Effect(Effect.KILL));
        blueEffects = null;
        endDeck.add(new Card(14, yellowEffects, redEffects, blueEffects));

        // Card 15
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.FOOD, Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.FOOD, Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.MOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.MOVE));
        redEffects.get(1).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        blueEffects = null;
        endDeck.add(new Card(15, yellowEffects, redEffects, blueEffects));

        // Card 16
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.HEALTH, Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.SKIP_CARD));
        redEffects = null;
        blueEffects = new ArrayList<>();
        blueEffects.add(new ArrayList<>());
        blueEffects.get(0).add(new Effect(new Token(Token.COMPASS), Effect.REMOVE));
        blueEffects.get(0).add(new Effect(new Token(Token.LEAF), Effect.ADD));
        endDeck.add(new Card(16, yellowEffects, redEffects, blueEffects));

        // Card 17
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.HEALTH, Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.ADD_CARD));
        yellowEffects.add(new Effect(Effect.SKIP_CARD));
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(new Token(Token.LEAF), Effect.REMOVE));
        redEffects.get(1).add(new Effect(new Token(Token.LEAF), Effect.REMOVE));
        blueEffects = null;
        endDeck.add(new Card(17, yellowEffects, redEffects, blueEffects));

        // Card 18
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(new Token(Token.COMPASS), Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.MOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(new Token(Token.COMPASS), Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.FOOD, Effect.ADD));
        redEffects.get(1).add(new Effect(Effect.FOOD, Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(new Token(Token.LEAF), Effect.REMOVE));
        redEffects.get(2).add(new Effect(Effect.FOOD, Effect.ADD));
        redEffects.get(2).add(new Effect(Effect.FOOD, Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(3).add(new Effect(Effect.HEALTH, Effect.ADD));
        blueEffects = null;
        endDeck.add(new Card(18, yellowEffects, redEffects, blueEffects));

        // Card 19
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.SKIP_CARD));
        redEffects.get(0).add(new Effect(Effect.MOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.FOOD, Effect.REMOVE));
        redEffects.get(1).add(new Effect(new Token(Token.COMPASS), Effect.ADD));
        blueEffects = null;
        endDeck.add(new Card(19, yellowEffects, redEffects, blueEffects));

        // Card 20
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.BULLET, Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.FOOD, Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        blueEffects = null;
        endDeck.add(new Card(20, yellowEffects, redEffects, blueEffects));

        // Card 21
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(new Token(Token.TENT), Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.ADD_CARD));
        yellowEffects.add(new Effect(Effect.SWAP_CARD));
        redEffects = null;
        blueEffects = null;
        endDeck.add(new Card(21, yellowEffects, redEffects, blueEffects));

        // Card 22
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.FOOD, Effect.REMOVE));
        redEffects.get(0).add(new Effect(new Token(Token.TENT), Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(new Token(Token.COMPASS), Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.MOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(Effect.KILL));
        redEffects.get(2).add(new Effect(Effect.MOVE));
        blueEffects = null;
        endDeck.add(new Card(22, yellowEffects, redEffects, blueEffects));

        // Card 23
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(new Token(Token.TENT), Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        blueEffects = null;
        endDeck.add(new Card(23, yellowEffects, redEffects, blueEffects));

        // Card 24
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.SKIP_CARD));
        yellowEffects.add(new Effect(Effect.SKIP_CARD));
        redEffects = null;
        blueEffects = new ArrayList<>();
        blueEffects.add(new ArrayList<>());
        blueEffects.get(0).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        blueEffects.get(0).add(new Effect(Effect.FOOD, Effect.ADD));
        blueEffects.get(0).add(new Effect(Effect.FOOD, Effect.ADD));
        endDeck.add(new Card(24, yellowEffects, redEffects, blueEffects));

        // Card 25
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.BULLET, Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.FOOD, Effect.ADD));
        redEffects.get(0).add(new Effect(Effect.FOOD, Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.ADD_CARD));
        redEffects.get(1).add(new Effect(Effect.ADD_CARD));
        blueEffects = null;
        endDeck.add(new Card(25, yellowEffects, redEffects, blueEffects));

        // Card 26
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(new Token(Token.TENT), Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.FOOD, Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.SWAP_CARD));
        yellowEffects.add(new Effect(Effect.SKIP_CARD));
        redEffects = null;
        blueEffects = null;
        endDeck.add(new Card(26, yellowEffects, redEffects, blueEffects));

        // Card 27
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.SWAP_CARD));
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(new Token(Token.TENT), Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.KILL));
        blueEffects = null;
        endDeck.add(new Card(27, yellowEffects, redEffects, blueEffects));

        // Card 28
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.HEALTH, Effect.REMOVE));
        yellowEffects.add(new Effect(new Token(Token.TENT), Effect.ADD));
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(new Token(Token.COMPASS), Effect.REMOVE));
        blueEffects = null;
        endDeck.add(new Card(28, yellowEffects, redEffects, blueEffects));

        // Card 29
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.FOOD, Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.ADD_CARD));
        redEffects.get(0).add(new Effect(Effect.MOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.FOOD, Effect.REMOVE));
        redEffects.get(1).add(new Effect(new Token(Token.COMPASS), Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.MOVE));
        blueEffects = new ArrayList<>();
        blueEffects.add(new ArrayList<>());
        blueEffects.get(0).add(new Effect(new Token(Token.LEAF), Effect.REMOVE));
        blueEffects.get(0).add(new Effect(Effect.SWAP_CARD));
        blueEffects.get(0).add(new Effect(Effect.SWAP_CARD));
        endDeck.add(new Card(29, yellowEffects, redEffects, blueEffects));

        // Card 30
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.BULLET, Effect.REMOVE));
        redEffects.get(0).add(new Effect(new Token(Token.COMPASS), Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(new Token(Token.LEAF), Effect.REMOVE));
        blueEffects = null;
        endDeck.add(new Card(30, yellowEffects, redEffects, blueEffects));

        // Example
        // Card
        yellowEffects = null;
        redEffects = null;
        blueEffects = null;
        //endDeck.add(new Card(0, yellowEffects, redEffects, blueEffects));

        return endDeck;
    }
    private ArrayList<Hero> makeHeroes() {
        ArrayList<Hero> heroes =new ArrayList<>();

        Hero YNES = new Hero(Hero.LEAF,"ynes");
        heroes.add(YNES);
        Hero ROY = new Hero(Hero.LEAF,"roy");
        heroes.add(ROY);
        Hero BESSIE = new Hero(Hero.TENT,"bessie");
        heroes.add(BESSIE);
        Hero TEDDY = new Hero(Hero.TENT,"teddy");
        heroes.add(TEDDY);
        Hero CANDIDO = new Hero (Hero.COMPASS,"candido");
        heroes.add(CANDIDO);
        Hero ISABELLE = new Hero(Hero.COMPASS,"isabelle");
        heroes.add(ISABELLE);

        return heroes;
    }
    private ArrayList<Card> deck;

    private final ArrayList<Card> allCards = makeCards();

    private final Random rand = new Random();

    private final int width = Toolkit.getDefaultToolkit().getScreenSize().width;

    private final int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    private JPanel panel = new JPanel();

    private Team team;

    private JLabel heroLeafImage,heroLeafHP,heroTentImage,heroTentHP,heroCompassImage,heroCompassHP;
}
