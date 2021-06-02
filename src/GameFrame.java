import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class GameFrame extends JFrame {
    public GameFrame(int difficulty) {
        super();
        try {
            Image img = ImageIO.read(new File("jungle.png")).getScaledInstance(width,height, Image.SCALE_SMOOTH);
            this.setContentPane(new JLabel(new ImageIcon(img)));
        } catch (IOException e) {
            new AnnounceDialog(GameFrame.this,true,"Error","File \"jungle.png\" not found").setVisible(true);
        }
        if (difficulty==3) Hero.setMaxHP(3);
        if (difficulty==1) pathLength=7;
        team = new Team(new Hero(Hero.LEAF,"ynes"),new Hero(Hero.TENT,"teddy"),new Hero(Hero.COMPASS,"isabelle"));team = new Team(new Hero(Hero.LEAF,"ynes"),new Hero(Hero.TENT,"teddy"),new Hero(Hero.COMPASS,"isabelle"));
        setSize(width,height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel.setSize(width,height);
        panel.setLayout(null);
        panel.setOpaque(false);
        add(panel);

        int heroCardHeight = height/4;
        int heroCardWidth = heroCardHeight*2/3;

        statsPanel = new JPanel(){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(ImageIO.read(new File("jungle.png")).getScaledInstance(panel.getWidth(),panel.getHeight(), Image.SCALE_SMOOTH),0,0,null);
                } catch (IOException e) {
                    new AnnounceDialog(GameFrame.this,true,"Error","File \"jungle.png\" not found").setVisible(true);
                }
            }
        };
        statsPanel.setBounds(0,0,(heroCardWidth+20)*2,height);
        statsPanel.setLayout(null);
        panel.add(statsPanel);

        int fontSize=20;

        heroLeafImage=null;
        try {
            heroLeafImage = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/Heroes/"+team.getHero(0).getNAME()+".png")).getScaledInstance(heroCardWidth,heroCardHeight, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        heroLeafImage.setBounds(0,0,heroCardWidth,heroCardHeight);
        statsPanel.add(heroLeafImage);

        int smallTokenSize=40;

        heroLeafHP = new JLabel(team.getHero(0).getHP()+"/"+Hero.getMaxHP());
        heroLeafHP.setFont(new Font("Arial",Font.PLAIN,fontSize));
        heroLeafHP.setBounds(smallTokenSize+20,heroCardHeight+10,fontSize*2,fontSize);
        heroLeafHP.setOpaque(true);
        heroLeafHP.setBackground(new Color(3, 87, 30));
        heroLeafHP.setForeground(new Color(245, 205, 76));
        statsPanel.add(heroLeafHP);

        try {
            hpImage1 = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/Tokens/health.png")).getScaledInstance(smallTokenSize,smallTokenSize, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        hpImage1.setBounds(10,heroCardHeight,smallTokenSize,smallTokenSize);
        statsPanel.add(hpImage1);

        heroTentImage=null;
        try {
            heroTentImage = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/Heroes/"+team.getHero(1).getNAME()+".png")).getScaledInstance(heroCardWidth,heroCardHeight, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        heroTentImage.setBounds(heroCardWidth+20,0,heroCardWidth,heroCardHeight);
        statsPanel.add(heroTentImage);

        heroTentHP = new JLabel(team.getHero(1).getHP()+"/"+Hero.getMaxHP());
        heroTentHP.setFont(new Font("Arial",Font.PLAIN,fontSize));
        heroTentHP.setBounds(heroCardWidth+smallTokenSize+20,heroCardHeight+10,fontSize*2,fontSize);
        heroTentHP.setOpaque(true);
        heroTentHP.setBackground(new Color(3, 87, 30));
        heroTentHP.setForeground(new Color(245, 205, 76));
        statsPanel.add(heroTentHP);

        try {
            hpImage2 = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/Tokens/health.png")).getScaledInstance(smallTokenSize,smallTokenSize, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        hpImage2.setBounds(heroCardWidth+10,heroCardHeight,smallTokenSize,smallTokenSize);
        statsPanel.add(hpImage2);

        heroCompassImage=null;
        try {
            heroCompassImage = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/Heroes/"+team.getHero(2).getNAME()+".png")).getScaledInstance(heroCardWidth,heroCardHeight, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        heroCompassImage.setBounds(0,heroCardHeight+50,heroCardWidth,heroCardHeight);
        statsPanel.add(heroCompassImage);

        heroCompassHP = new JLabel(team.getHero(2).getHP()+"/"+Hero.getMaxHP());
        heroCompassHP.setFont(new Font("Arial",Font.PLAIN,fontSize));
        heroCompassHP.setBounds(smallTokenSize+20,heroCardHeight*2+60,fontSize*2,fontSize);
        heroCompassHP.setOpaque(true);
        heroCompassHP.setBackground(new Color(3, 87, 30));
        heroCompassHP.setForeground(new Color(245, 205, 76));
        statsPanel.add(heroCompassHP);

        try {
            hpImage3 = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/Tokens/health.png")).getScaledInstance(smallTokenSize,smallTokenSize, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        hpImage3.setBounds(10,heroCardHeight*2+50,smallTokenSize,smallTokenSize);
        statsPanel.add(hpImage3);

        smallTokenSize=60;

        try {
            foodImage = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/Tokens/food.png")).getScaledInstance(smallTokenSize,smallTokenSize, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        foodImage.setBounds(heroCardWidth*3/2-smallTokenSize*2/3,heroCardHeight*3/2-smallTokenSize/2,smallTokenSize,smallTokenSize);
        statsPanel.add(foodImage);

        foodLabel = new JLabel(team.getFood()+"");
        foodLabel.setFont(new Font("Arial",Font.PLAIN,fontSize));
        foodLabel.setBounds(heroCardWidth*3/2+smallTokenSize*1/3,heroCardHeight*3/2-10,fontSize,fontSize);
        foodLabel.setOpaque(true);
        foodLabel.setBackground(new Color(3, 87, 30));
        foodLabel.setForeground(new Color(245, 205, 76));
        statsPanel.add(foodLabel);

        try {
            bulletImage = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/Tokens/bullet.png")).getScaledInstance(smallTokenSize,smallTokenSize, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bulletImage.setBounds(heroCardWidth*3/2-smallTokenSize*2/3,heroCardHeight*3/2+smallTokenSize,smallTokenSize,smallTokenSize);
        statsPanel.add(bulletImage);

        bulletLabel = new JLabel(team.getBullets()+"");
        bulletLabel.setFont(new Font("Arial",Font.PLAIN,fontSize));
        bulletLabel.setBounds(heroCardWidth*3/2+smallTokenSize*1/3,heroCardHeight*3/2+smallTokenSize+20,fontSize,fontSize);
        bulletLabel.setOpaque(true);
        bulletLabel.setBackground(new Color(3, 87, 30));
        bulletLabel.setForeground(new Color(245, 205, 76));
        statsPanel.add(bulletLabel);

        int bigTokenSize=80;
        String s;
        if(day) s = "Cards/Tokens/morning.png";
            else s = "Cards/Tokens/evening.png";
        try {
                morningImage = new JLabel(new ImageIcon(ImageIO.read(new File(s)).getScaledInstance(bigTokenSize,bigTokenSize, Image.SCALE_SMOOTH)));

        } catch (IOException e) {
            e.printStackTrace();
        }
        morningImage.setBounds(bigTokenSize/2,heroCardHeight*2+bigTokenSize+10,bigTokenSize,bigTokenSize);
        statsPanel.add(morningImage);

        try {
            leaderImage = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/Tokens/leader.png")).getScaledInstance(bigTokenSize,bigTokenSize, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        leaderImage.setBounds(bigTokenSize*2+bigTokenSize/2,heroCardHeight*2+bigTokenSize+10,bigTokenSize,bigTokenSize);
        statsPanel.add(leaderImage);

        //-------------------------

        cardsPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(ImageIO.read(new File("table.png")).getScaledInstance(cardsPanel.getWidth(),cardsPanel.getHeight(), Image.SCALE_SMOOTH),0,0,null);
                } catch (IOException e) {
                    new AnnounceDialog(GameFrame.this,true,"Error","File \"table.png\" not found").setVisible(true);
                }
            }
        };
        cardsPanel.setBounds((heroCardWidth+20)*2,0,width-(heroCardWidth+20)*4,height);
        cardsPanel.setLayout(null);
        panel.add(cardsPanel);

        pathCardWidth = cardsPanel.getWidth()/9;
        pathCardHeight = pathCardWidth*3/2;
        JLabel path = null;
        if (difficulty==1) {
            for (int i = 0; i < 7; i++) {
                try {
                    if (i!=6) path = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/Path/path" + (i+1) + ".png")).getScaledInstance(pathCardWidth, pathCardHeight, Image.SCALE_SMOOTH)));
                    else path = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/Path/path9.png")).getScaledInstance(pathCardWidth, pathCardHeight, Image.SCALE_SMOOTH)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                path.setBounds((cardsPanel.getWidth()-pathCardWidth*9)/2+pathCardWidth*(i+1), 5, pathCardWidth, pathCardHeight);
                cardsPanel.add(path);
            }
        }
        else {
            for (int i = 0; i < 9; i++) {
                try {
                    path = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/Path/path" + (i+1) + ".png")).getScaledInstance(pathCardWidth, pathCardHeight, Image.SCALE_SMOOTH)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                path.setBounds((cardsPanel.getWidth()-pathCardWidth*9)/2+pathCardWidth*i, 5, pathCardWidth, pathCardHeight);
                cardsPanel.add(path);
            }
        }

        handCardWidth = cardsPanel.getWidth()/6;
        handCardHeight = handCardWidth*3/2;

        roadCardWidth = cardsPanel.getWidth()/7;
        roadCardHeight = roadCardWidth*3/2;

        fillHand();
        drawHand();

        road = new ArrayList<>();
        road.add(deck.remove(0));
        road.add(deck.remove(0));
        sortCards(road);
        drawRoad();

       eventPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(ImageIO.read(new File("jungle.png")).getScaledInstance(panel.getWidth(),panel.getHeight(), Image.SCALE_SMOOTH),0,0,null);
                } catch (IOException e) {
                    new AnnounceDialog(GameFrame.this,true,"Error","File \"table.png\" not found").setVisible(true);
                }
            }
        };
        eventPanel.setBounds(cardsPanel.getWidth()+cardsPanel.getX(),0,panel.getWidth()-cardsPanel.getWidth()-cardsPanel.getX(),height);
        eventPanel.setLayout(null);
        panel.add(eventPanel);

        textPanel("Hello world","Good game");
        
        expPanel = new JPanel();
        expPanel.setBounds(0,heroCardHeight*2+bigTokenSize*2+15,((this.getWidth()- cardsPanel.getWidth())/2)+10,this.getHeight() - ((height / 2) + 185));
        expPanel.setLayout(null);
        expPanel.setOpaque(false);
        statsPanel.add(expPanel);

        addExpCard("Cards/card1.png");
        addExpCard("Cards/card2.png");
        addExpCard("Cards/card3.png");
        removeExpCard("Cards/card1.png");
        addExpCard("Cards/card4.png");

    }

    public void textPanel(String text) {
        panel.remove(eventPanel);

        eventPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(ImageIO.read(new File("jungle.png")).getScaledInstance(panel.getWidth(),panel.getHeight(), Image.SCALE_SMOOTH),0,0,null);
                } catch (IOException e) {
                    new AnnounceDialog(GameFrame.this,true,"Error","File \"table.png\" not found").setVisible(true);
                }
            }
        };
        eventPanel.setBounds(cardsPanel.getWidth()+cardsPanel.getX(),0,panel.getWidth()-cardsPanel.getWidth()-cardsPanel.getX(),height);
        eventPanel.setLayout(null);

        JLabel textLabel = new JLabel(text){
            {
                setFont(eventFont);
                setBounds(0,100,eventPanel.getWidth(),eventFont.getSize());
                setOpaque(true);
                setBackground(new Color(3, 87, 30));
                setForeground(new Color(245, 205, 76));
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        };
        eventPanel.add(textLabel);

        JButton button = new JButton("OK"){
            {
                setFont(eventFont);
                setBounds(eventPanel.getWidth()/4,eventPanel.getHeight()-100-100,eventPanel.getWidth()/2,100);
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
            }
        };
        eventPanel.add(button);

        panel.add(eventPanel);
        revalidate();
        repaint();
    }

    public void textPanel (String text1, String text2) {
        panel.remove(eventPanel);

        eventPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(ImageIO.read(new File("jungle.png")).getScaledInstance(panel.getWidth(),panel.getHeight(), Image.SCALE_SMOOTH),0,0,null);
                } catch (IOException e) {
                    new AnnounceDialog(GameFrame.this,true,"Error","File \"table.png\" not found").setVisible(true);
                }
            }
        };
        eventPanel.setBounds(cardsPanel.getWidth()+cardsPanel.getX(),0,panel.getWidth()-cardsPanel.getWidth()-cardsPanel.getX(),height);
        eventPanel.setLayout(null);

        JLabel text1Label = new JLabel(text1){
            {
                setFont(eventFont);
                setBounds(0,100,eventPanel.getWidth(),eventFont.getSize());
                setOpaque(true);
                setBackground(new Color(3, 87, 30));
                setForeground(new Color(245, 205, 76));
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        };
        eventPanel.add(text1Label);

        JLabel text2Label = new JLabel(text2){
            {
                setFont(eventFont);
                setBounds(0,100+eventFont.getSize()+5,eventPanel.getWidth(),eventFont.getSize());
                setOpaque(true);
                setBackground(new Color(3, 87, 30));
                setForeground(new Color(245, 205, 76));
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        };
        eventPanel.add(text2Label);

        JButton button = new JButton("OK"){
            {
                setFont(eventFont);
                setBounds(eventPanel.getWidth()/4,eventPanel.getHeight()-100-100,eventPanel.getWidth()/2,100);
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                    }
                });
            }
        };
        eventPanel.add(button);

        panel.add(eventPanel);
        revalidate();
        repaint();
    }

    private void addExpCard(String path){
        expCards.add(path);
        drawExpCard();
    }
    private void removeExpCard(String path){
        for(int i=0;i<expCards.size();i++){
            if(expCards.get(i).equals(path)) expCards.remove(i);
        }
        drawExpCard();
    }

    private void drawRoad() {
        if (roadLabels.size()>0) {
            for (JLabel card : roadLabels) cardsPanel.remove(card);
            roadLabels = new ArrayList<>();
        }
        for (int i = 0; i< road.size(); i++) {
            JLabel card = null;
            try {
                card = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/card" + road.get(i).getNumber() + ".png")).getScaledInstance(roadCardWidth,roadCardHeight, Image.SCALE_SMOOTH)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            card.setBounds((cardsPanel.getWidth()-(roadCardWidth*road.size()))/2+i*roadCardWidth,((cardsPanel.getHeight()-handCardHeight*7/5 + pathCardHeight)-roadCardHeight)/2,roadCardWidth,roadCardHeight);
            cardsPanel.add(card);
            roadLabels.add(card);
        }
    }

    private void drawHand() {
         if (handLabels.size()>0) {
             for (JLabel card : handLabels) cardsPanel.remove(card);
             handLabels = new ArrayList<>();
         }
         for (int i = 0; i<hand.size(); i++) {
             JLabel card = null;
             try {
                 card = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/card" + hand.get(i).getNumber() + ".png")).getScaledInstance(handCardWidth,handCardHeight, Image.SCALE_SMOOTH)));
             } catch (IOException e) {
                 e.printStackTrace();
             }
             card.setBounds((cardsPanel.getWidth()-(handCardWidth*hand.size()))/2+i*handCardWidth,cardsPanel.getHeight()-handCardHeight*7/5,handCardWidth,handCardHeight);
             cardsPanel.add(card);
             handLabels.add(card);
         }
    }

    private void fillHand() {
        hand = new ArrayList<>();
        if (deck.size()<6) fillDeck();
        for (int i = 0; i<6; i++) hand.add(deck.remove(0));
        sortCards(hand);

    }
    private void drawExpCard(){
        expPanel.removeAll();
        int expWidth = (this.getWidth()- cardsPanel.getWidth())/(2*expCards.size());
        int expHeight=expWidth*3/2;
        if(expHeight>this.getHeight()-(height/2+210)){ expHeight=this.getHeight()-(height/2+210);expWidth=expHeight*2/3;}
        for(int i=0;i<expCards.size();i++){
            String path=expCards.get(i);
            try {
                JLabel card = new JLabel(new ImageIcon(ImageIO.read(new File(path)).getScaledInstance(expWidth, expHeight, Image.SCALE_SMOOTH)));
               card.setBounds(expWidth * i, 0, expWidth, expHeight);
                expPanel.add(card);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        expPanel.revalidate();
        expPanel.repaint();

    }
    private void fillCards() {
        deck = fillDeck();
    }

    private void sortCards(ArrayList<Card> cards) {
        int n = cards.size();
        for (int i = 0; i<n-1;i++) for (int j = 0; j < n-i-1; j++) if (cards.get(j).getNumber() > cards.get(j+1).getNumber()) Collections.swap(cards,j,j+1);
    }

    private ArrayList<Card> fillDeck() {
        // playable init
        ArrayList<Card> endDeck = new ArrayList<>();
        while (playable.size()>0) {
            int n = rand.nextInt(playable.size());
            endDeck.add(playable.remove(n));
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

        // Card 31
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(new Token(Token.TENT), Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(Effect.BULLET, Effect.REMOVE));
        blueEffects = null;
        endDeck.add(new Card(31, yellowEffects, redEffects, blueEffects));

        // Card 32
        redEffects = null;
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.FOOD, Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.REMOVE_CARD));
        yellowEffects.add(new Effect(Effect.REMOVE_CARD));
        blueEffects = new ArrayList<>();
        blueEffects.add(new ArrayList<>());
        blueEffects.get(0).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        blueEffects.get(0).add(new Effect(new Token(Token.COMPASS), Effect.REMOVE));
        blueEffects.get(0).add(new Effect(Effect.MOVE));
        blueEffects.add(new ArrayList<>());
        blueEffects.get(1).add(new Effect(Effect.FOOD, Effect.REMOVE));
        blueEffects.get(1).add(new Effect(new Token(Token.TENT), Effect.REMOVE));
        blueEffects.get(1).add(new Effect(Effect.MOVE));
        endDeck.add(new Card(32, yellowEffects, redEffects, blueEffects));

        // Card 33
        redEffects = null;
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(new Token(Token.TENT), Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.HEALTH, Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.HEALTH, Effect.REMOVE));
        blueEffects = new ArrayList<>();
        blueEffects.add(new ArrayList<>());
        blueEffects.get(0).add(new Effect(new Token(Token.LEAF), Effect.REMOVE));
        blueEffects.get(0).add(new Effect(Effect.FOOD, Effect.ADD));
        blueEffects.get(0).add(new Effect(Effect.FOOD, Effect.ADD));
        endDeck.add(new Card(33, yellowEffects, redEffects, blueEffects));

        // Card 34
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.ADD_CARD));
        redEffects.get(0).add(new Effect(Effect.ADD_CARD));
        redEffects.get(0).add(new Effect(new Token(Token.TENT), Effect.ADD));
        redEffects.get(0).add(new Effect(Effect.SKIP_CARD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(new Token(Token.COMPASS), Effect.REMOVE));
        redEffects.get(1).add(new Effect(new Token(Token.COMPASS), Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.MOVE));
        blueEffects = null;
        endDeck.add(new Card(34, yellowEffects, redEffects, blueEffects));

        // Card 35
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.SKIP_CARD));
        yellowEffects.add(new Effect(Effect.SKIP_CARD));
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(new Token(Token.TENT), Effect.REMOVE));
        blueEffects = null;
        endDeck.add(new Card(35, yellowEffects, redEffects, blueEffects));

        // Card 36
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(new Token(Token.COMPASS), Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.SKIP_CARD));
        yellowEffects.add(new Effect(new Token(Token.LEAF), Effect.ADD));
        yellowEffects.add(new Effect(Effect.REMOVE_CARD));
        redEffects = null;
        blueEffects = null;
        endDeck.add(new Card(36, yellowEffects, redEffects, blueEffects));

        // Card 37
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.ADD_CARD));
        redEffects = null;
        blueEffects = new ArrayList<>();
        blueEffects.add(new ArrayList<>());
        blueEffects.get(0).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        blueEffects.get(0).add(new Effect(Effect.FOOD, Effect.ADD));
        blueEffects.get(0).add(new Effect(Effect.FOOD, Effect.ADD));
        endDeck.add(new Card(37, yellowEffects, redEffects, blueEffects));

        // Card 38
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.SWAP_CARD));
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(new Token(Token.TENT), Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(Effect.BULLET, Effect.REMOVE));
        blueEffects = null;
        endDeck.add(new Card(38, yellowEffects, redEffects, blueEffects));

        // Card 39
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.SKIP_CARD));
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(new Token(Token.COMPASS), Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(new Token(Token.COMPASS), Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.MOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(Effect.MOVE));
        redEffects.get(2).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        blueEffects = null;
        endDeck.add(new Card(39, yellowEffects, redEffects, blueEffects));

        // Card 40
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(new Token(Token.LEAF), Effect.ADD));
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.BULLET, Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.FOOD, Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.FOOD, Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.FOOD, Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.get(2).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        blueEffects = null;
        endDeck.add(new Card(40, yellowEffects, redEffects, blueEffects));

        // Card 41
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(new Token(Token.COMPASS), Effect.ADD));
        yellowEffects.add(new Effect(Effect.SKIP_CARD));
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.FOOD, Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(new Token(Token.LEAF), Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.FOOD, Effect.ADD));
        redEffects.get(1).add(new Effect(Effect.FOOD, Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(Effect.BULLET, Effect.REMOVE));
        redEffects.get(2).add(new Effect(Effect.FOOD, Effect.ADD));
        redEffects.get(2).add(new Effect(Effect.FOOD, Effect.ADD));
        blueEffects = null;
        endDeck.add(new Card(41, yellowEffects, redEffects, blueEffects));

        // Card 42
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(new Token(Token.COMPASS), Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.FOOD, Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.HEALTH, Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(Effect.FOOD, Effect.REMOVE));
        redEffects.get(2).add(new Effect(Effect.MOVE));
        blueEffects = null;
        endDeck.add(new Card(42, yellowEffects, redEffects, blueEffects));

        // Card 43
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.HEALTH, Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.SWAP_CARD));
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(new Token(Token.TENT), Effect.REMOVE));
        blueEffects = null;
        endDeck.add(new Card(43, yellowEffects, redEffects, blueEffects));

        // Card 44
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.BULLET, Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.MOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.KILL));
        redEffects.get(1).add(new Effect(Effect.MOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.get(2).add(new Effect(new Token(Token.COMPASS,Token.TENT), Effect.ADD));
        blueEffects = null;
        endDeck.add(new Card(44, yellowEffects, redEffects, blueEffects));

        // Card 45
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.SWAP_CARD));
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(new Token(Token.LEAF), Effect.REMOVE));
        blueEffects = null;
        endDeck.add(new Card(45, yellowEffects, redEffects, blueEffects));

        // Card 46
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.ADD_CARD));
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.BULLET, Effect.ADD));
        redEffects.get(0).add(new Effect(Effect.BULLET, Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.HEALTH, Effect.ADD));
        redEffects.get(1).add(new Effect(new Token(Token.TENT), Effect.ADD));
        blueEffects = null;
        endDeck.add(new Card(46, yellowEffects, redEffects, blueEffects));

        // Card 47
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.FOOD, Effect.REMOVE));
        yellowEffects.add(new Effect(new Token(Token.TENT), Effect.ADD));
        redEffects = null;
        blueEffects = new ArrayList<>();
        blueEffects.add(new ArrayList<>());
        blueEffects.get(0).add(new Effect(new Token(Token.COMPASS), Effect.REMOVE));
        blueEffects.get(0).add(new Effect(Effect.HEALTH, Effect.ADD));
        blueEffects.get(0).add(new Effect(Effect.REMOVE_CARD));
        endDeck.add(new Card(47, yellowEffects, redEffects, blueEffects));

        // Card 48
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.HEALTH, Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.SWAP_CARD));
        yellowEffects.add(new Effect(new Token(Token.LEAF), Effect.ADD));
        redEffects = null;
        blueEffects = new ArrayList<>();
        blueEffects.add(new ArrayList<>());
        blueEffects.get(0).add(new Effect(Effect.BULLET, Effect.REMOVE));
        blueEffects.get(0).add(new Effect(Effect.FOOD, Effect.ADD));
        endDeck.add(new Card(48, yellowEffects, redEffects, blueEffects));

        // Card 49
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(new Token(Token.LEAF), Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.MOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(new Token(Token.COMPASS), Effect.REMOVE));
        redEffects.get(1).add(new Effect(new Token(Token.LEAF), Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(new Token(Token.LEAF), Effect.REMOVE));
        redEffects.get(2).add(new Effect(Effect.HEALTH, Effect.ADD));
        blueEffects = null;
        endDeck.add(new Card(49, yellowEffects, redEffects, blueEffects));

        // Card 50
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.SWAP_CARD));
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(new Token(Token.COMPASS), Effect.REMOVE));
        redEffects.get(0).add(new Effect(Effect.MOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.MOVE));
        redEffects.get(1).add(new Effect(Effect.KILL));
        redEffects.add(new ArrayList<>());
        redEffects.get(2).add(new Effect(Effect.ADD_CARD));
        blueEffects = null;
        endDeck.add(new Card(50, yellowEffects, redEffects, blueEffects));

        // Card 51
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.FOOD, Effect.REMOVE));
        yellowEffects.add(new Effect(new Token(Token.COMPASS,Token.LEAF), Effect.ADD));
        redEffects = null;
        blueEffects = null;
        endDeck.add(new Card(51, yellowEffects, redEffects, blueEffects));

        // Card 52
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.HEALTH, Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.FOOD, Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.SWAP_CARD));
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(new Token(Token.TENT), Effect.REMOVE));
        blueEffects = null;
        endDeck.add(new Card(52, yellowEffects, redEffects, blueEffects));

        // Card 53
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.HEALTH, Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.FOOD, Effect.REMOVE));
        yellowEffects.add(new Effect(Effect.REMOVE_CARD));
        redEffects = null;
        blueEffects = null;
        endDeck.add(new Card(53, yellowEffects, redEffects, blueEffects));

        // Card 54
        yellowEffects = null;
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(new Token(Token.TENT), Effect.REMOVE));
        redEffects.get(0).add(new Effect(new Token(Token.TENT), Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.ADD_CARD));
        redEffects.get(1).add(new Effect(Effect.ADD_CARD));
        blueEffects = null;
        endDeck.add(new Card(54, yellowEffects, redEffects, blueEffects));

        // Card 55
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.FOOD, Effect.REMOVE));
        redEffects = null;
        blueEffects = null;
        endDeck.add(new Card(55, yellowEffects, redEffects, blueEffects));

        // Card 56
        yellowEffects = new ArrayList<>();
        yellowEffects.add(new Effect(Effect.ADD_CARD));
        redEffects = new ArrayList<>();
        redEffects.add(new ArrayList<>());
        redEffects.get(0).add(new Effect(new Token(Token.COMPASS), Effect.REMOVE));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.HEALTH, Effect.REMOVE));
        blueEffects = null;
        endDeck.add(new Card(56, yellowEffects, redEffects, blueEffects));

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

    private final Random rand = new Random();

    private final Font eventFont = new Font("Arial",Font.PLAIN,20);

    private boolean day = true;

    private int pathLength = 9;

    private int pathCardWidth, pathCardHeight, handCardWidth, handCardHeight, roadCardWidth, roadCardHeight;

    private final ArrayList<Card> allCards = makeCards();

    private ArrayList<Card> path=(ArrayList<Card>)allCards.clone();

    private ArrayList<String> expCards= new ArrayList<>();

    private ArrayList<Card> playable=(ArrayList<Card>)allCards.clone(), deck = fillDeck(),hand,road;

    private final int width = Toolkit.getDefaultToolkit().getScreenSize().width;

    private final int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    private JPanel panel = new JPanel(),statsPanel,cardsPanel, eventPanel,expPanel;

    private Team team;

    private JLabel heroLeafImage,heroLeafHP,heroTentImage,heroTentHP,heroCompassImage,heroCompassHP,
            foodImage,foodLabel,bulletImage,bulletLabel,hpImage1,hpImage2,hpImage3,leaderImage,morningImage;

    private ArrayList<JLabel> handLabels = new ArrayList<>(),roadLabels=new ArrayList<>();
}
