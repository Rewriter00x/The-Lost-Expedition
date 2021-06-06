import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class GameFrame extends JFrame {
    public GameFrame(int difficulty) {
        super();
        this.difficulty=difficulty;
        try {
            Image img = ImageIO.read(new File("jungle.png")).getScaledInstance(width,height, Image.SCALE_SMOOTH);
            this.setContentPane(new JLabel(new ImageIcon(img)));
        } catch (IOException e) {
            new AnnounceDialog(GameFrame.this,true,"Error","File \"jungle.png\" not found").setVisible(true);
        }
        try {
            BufferedImage img = ImageIO.read(new File("glass.png"));
            this.setIconImage(img);
        } catch (IOException e) {
            new AnnounceDialog(GameFrame.this,true,"Error","File \"glass.png\" not found").setVisible(true);
        }
        if (difficulty==3) Hero.setMaxHP(3);
        if (difficulty==1) pathLength=7;
        setSize(width,height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel.setSize(width,height);
        panel.setLayout(null);
        panel.setOpaque(false);
        add(panel);

        heroCardHeight = height/4;
        heroCardWidth = heroCardHeight*2/3;

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

        JLabel ynesLabel=null;
        try {
            ynesLabel = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/Heroes/ynes.png")).getScaledInstance(heroCardWidth,heroCardHeight, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ynesLabel.setBounds(0,0,heroCardWidth,heroCardHeight);
        statsPanel.add(ynesLabel);

        JLabel royLabel=null;
        try {
            royLabel = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/Heroes/roy.png")).getScaledInstance(heroCardWidth,heroCardHeight, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        royLabel.setBounds(heroCardWidth+20,0,heroCardWidth,heroCardHeight);
        statsPanel.add(royLabel);

        JLabel teddyLabel=null;
        try {
            teddyLabel = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/Heroes/teddy.png")).getScaledInstance(heroCardWidth,heroCardHeight, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        teddyLabel.setBounds(0,heroCardHeight+50,heroCardWidth,heroCardHeight);
        statsPanel.add(teddyLabel);

        JLabel bessieLabel=null;
        try {
            bessieLabel = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/Heroes/bessie.png")).getScaledInstance(heroCardWidth,heroCardHeight, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        bessieLabel.setBounds(heroCardWidth+20,heroCardHeight+50,heroCardWidth,heroCardHeight);
        statsPanel.add(bessieLabel);

        JLabel isabelleLabel=null;
        try {
            isabelleLabel = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/Heroes/isabelle.png")).getScaledInstance(heroCardWidth,heroCardHeight, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        isabelleLabel.setBounds(0,(heroCardHeight+50)*2,heroCardWidth,heroCardHeight);
        statsPanel.add(isabelleLabel);

        JLabel candidoLabel=null;
        try {
            candidoLabel = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/Heroes/candido.png")).getScaledInstance(heroCardWidth,heroCardHeight, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        candidoLabel.setBounds(heroCardWidth+20,(heroCardHeight+50)*2,heroCardWidth,heroCardHeight);
        statsPanel.add(candidoLabel);

        panel.add(statsPanel);

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

        manWidth=50;
        manHeight = 80;
        try {
            man = new JLabel(new ImageIcon(ImageIO.read(new File("silhouette.png")).getScaledInstance(manWidth,manHeight, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            new AnnounceDialog(GameFrame.this,true,"Error","File \"silhouette.png\" not found").setVisible(true);
        }
        if(difficulty==1) {
            man.setBounds(((cardsPanel.getWidth()-pathCardWidth*9)/2+pathCardWidth)+(pathCardWidth/2-manWidth/2), 5+(pathCardHeight/2-manHeight/2), manWidth, manHeight);
        }
        else {
            man.setBounds(((cardsPanel.getWidth()-pathCardWidth*9)/2)+(pathCardWidth/2-manWidth/2), 5+(pathCardHeight/2-manHeight/2), manWidth, manHeight);
        }
        cardsPanel.add(man);

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

        JLabel textLabel = new JLabel("Select your characters"){
            {
                setFont(eventFont);
                setBounds(0,height/9,eventPanel.getWidth(),eventFont.getSize());
                setOpaque(true);
                setBackground(new Color(3, 87, 30));
                setForeground(new Color(245, 205, 76));
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        };
        eventPanel.add(textLabel);

        JButton button = new JButton("Start"){
            {
                setFont(eventFont);
                setBounds(eventPanel.getWidth()/4,eventPanel.getHeight()*7/9,eventPanel.getWidth()/2,height/9);
                setFont(new Font("OCR A Extended", Font.PLAIN, 30));
                setBackground(new Color(130, 204, 217));
                setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
            }
        };
        eventPanel.add(button);

        JRadioButton ynes = new JRadioButton("Ynes"){
            {
                setFont(eventFont);
                setBounds(eventPanel.getWidth()/4,height*2/9,eventPanel.getWidth()/2,eventFont.getSize());
                setOpaque(true);
                setBackground(new Color(3, 87, 30));
                setForeground(new Color(245, 205, 76));
                setSelected(true);
            }
        };
        eventPanel.add(ynes);

        JRadioButton roy = new JRadioButton("Roy"){
            {
                setFont(eventFont);
                setBounds(eventPanel.getWidth()/4,height*2/9+eventFont.getSize(),eventPanel.getWidth()/2,eventFont.getSize());
                setOpaque(true);
                setBackground(new Color(3, 87, 30));
                setForeground(new Color(245, 205, 76));
            }
        };
        eventPanel.add(roy);

        new ButtonGroup(){
            {
                add(ynes);
                add(roy);
            }
        };

        JRadioButton teddy = new JRadioButton("Teddy"){
            {
                setFont(eventFont);
                setBounds(eventPanel.getWidth()/4,height*3/9,eventPanel.getWidth()/2,eventFont.getSize());
                setOpaque(true);
                setBackground(new Color(3, 87, 30));
                setForeground(new Color(245, 205, 76));
                setSelected(true);
            }
        };
        eventPanel.add(teddy);

        JRadioButton bessie = new JRadioButton("Bessie"){
            {
                setFont(eventFont);
                setBounds(eventPanel.getWidth()/4,height*3/9+eventFont.getSize(),eventPanel.getWidth()/2,eventFont.getSize());
                setOpaque(true);
                setBackground(new Color(3, 87, 30));
                setForeground(new Color(245, 205, 76));
            }
        };
        eventPanel.add(bessie);

        new ButtonGroup(){
            {
                add(teddy);
                add(bessie);
            }
        };

        JRadioButton isabelle = new JRadioButton("Isabelle"){
            {
                setFont(eventFont);
                setBounds(eventPanel.getWidth()/4,height*4/9,eventPanel.getWidth()/2,eventFont.getSize());
                setOpaque(true);
                setBackground(new Color(3, 87, 30));
                setForeground(new Color(245, 205, 76));
                setSelected(true);
            }
        };
        eventPanel.add(isabelle);

        JRadioButton candido = new JRadioButton("Candido"){
            {
                setFont(eventFont);
                setBounds(eventPanel.getWidth()/4,height*4/9+eventFont.getSize(),eventPanel.getWidth()/2,eventFont.getSize());
                setOpaque(true);
                setBackground(new Color(3, 87, 30));
                setForeground(new Color(245, 205, 76));
            }
        };
        eventPanel.add(candido);

        new ButtonGroup(){
            {
                add(isabelle);
                add(candido);
            }
        };

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                team = new Team(new Hero(Hero.LEAF,ynes.isSelected()?"ynes":"roy"),
                        new Hero(Hero.TENT,teddy.isSelected()?"teddy":"bessie"),
                        new Hero(Hero.COMPASS,isabelle.isSelected()?"isabelle":"candido"));
                drawStats();

                nextStep();
            }
        });

        panel.add(eventPanel);

        Effect.setFrame(this);

    }
    public void moveMan(){
        int x = man.getX();
        x = x+((cardsPanel.getWidth()-pathCardWidth*9)/2+pathCardWidth);
        int xMax;
        if(difficulty==1) xMax = (cardsPanel.getWidth()-pathCardWidth*9)/2+pathCardWidth*8;
        else xMax=(cardsPanel.getWidth()-pathCardWidth*9)/2+pathCardWidth*9;
        if(x<xMax) man.setLocation(x, man.getY());

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
                setBounds(0,height/9,eventPanel.getWidth(),eventFont.getSize());
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
                setBounds(eventPanel.getWidth()/4,height*7/9,eventPanel.getWidth()/2,height/9);
                setFont(new Font("OCR A Extended", Font.PLAIN, 30));
                setBackground(new Color(130, 204, 217));
                setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        nextStep();
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
                setBounds(0,height/9,eventPanel.getWidth(),eventFont.getSize());
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
                setBounds(0,height/9+eventFont.getSize(),eventPanel.getWidth(),eventFont.getSize());
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
                setBounds(eventPanel.getWidth()/4,height*7/9,eventPanel.getWidth()/2,height/9);
                setFont(new Font("OCR A Extended", Font.PLAIN, 30));
                setBackground(new Color(130, 204, 217));
                setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        nextStep();
                    }
                });
            }
        };
        eventPanel.add(button);

        panel.add(eventPanel);
        revalidate();
        repaint();
    }

    public void healPanel() {
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

        if (!team.allOnMaxHPorDead()) {

            JLabel textLabel = new JLabel("Choose who gains hp") {
                {
                    setFont(eventFont);
                    setBounds(0, height / 9, eventPanel.getWidth(), eventFont.getSize());
                    setOpaque(true);
                    setBackground(new Color(3, 87, 30));
                    setForeground(new Color(245, 205, 76));
                    setHorizontalAlignment(SwingConstants.CENTER);
                }
            };
            eventPanel.add(textLabel);

            int n = 0;
            ArrayList<JRadioButton> buttons = new ArrayList<>();
            ButtonGroup group = new ButtonGroup();
            for (int i = 0; i < 3; i++) {
                if (team.getHero(i).isAlive() && !team.getHero(i).hpIsMax()) {
                    int finalN = n;
                    buttons.add(new JRadioButton(team.getHero(i).getNAME()) {
                        {
                            setFont(eventFont);
                            setBounds(eventPanel.getWidth() / 4, height * 2 / 9 + eventFont.getSize() * finalN, eventPanel.getWidth() / 2, eventFont.getSize());
                            setOpaque(true);
                            setBackground(new Color(3, 87, 30));
                            setForeground(new Color(245, 205, 76));
                            if (finalN == 0) setSelected(true);
                        }
                    });
                    eventPanel.add(buttons.get(n));
                    group.add(buttons.get(n));
                    n++;
                }
            }

            int finalN1 = n;
            JButton button = new JButton("OK") {
                {
                    setFont(eventFont);
                    setBounds(eventPanel.getWidth() / 4, height * 7 / 9, eventPanel.getWidth() / 2, height / 9);
                    setFont(new Font("OCR A Extended", Font.PLAIN, 30));
                    setBackground(new Color(130, 204, 217));
                    setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
                    addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (int i = 0; i < finalN1; i++) {
                                if (buttons.get(i).isSelected()) {
                                    team.getHero(buttons.get(i).getText()).heal();
                                    break;
                                }
                            }
                            checkDead();
                            nextStep();
                        }
                    });
                }
            };
            eventPanel.add(button);
        }
        else {
            JLabel textLabel = new JLabel("Can't heal anyone"){
                {
                    setFont(eventFont);
                    setBounds(0,height/9,eventPanel.getWidth(),eventFont.getSize());
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
                    setBounds(eventPanel.getWidth()/4,height*7/9,eventPanel.getWidth()/2,height/9);
                    setFont(new Font("OCR A Extended", Font.PLAIN, 30));
                    setBackground(new Color(130, 204, 217));
                    setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
                    addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            nextStep();
                        }
                    });
                }
            };
            eventPanel.add(button);
        }

        panel.add(eventPanel);
        revalidate();
        repaint();
    }

    public void damegePanel() {
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

        JLabel textLabel = new JLabel("Choose who looses hp"){
            {
                setFont(eventFont);
                setBounds(0,height/9,eventPanel.getWidth(),eventFont.getSize());
                setOpaque(true);
                setBackground(new Color(3, 87, 30));
                setForeground(new Color(245, 205, 76));
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        };
        eventPanel.add(textLabel);

        int n = 0;
        ArrayList<JRadioButton> buttons = new ArrayList<>();
        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i<3; i++) {
            if (team.getHero(i).isAlive()) {
                int finalN = n;
                buttons.add(new JRadioButton(team.getHero(i).getNAME()){
                    {
                        setFont(eventFont);
                        setBounds(eventPanel.getWidth()/4,height*2/9+eventFont.getSize()*finalN,eventPanel.getWidth()/2,eventFont.getSize());
                        setOpaque(true);
                        setBackground(new Color(3, 87, 30));
                        setForeground(new Color(245, 205, 76));
                        if (finalN ==0) setSelected(true);
                    }
                });
                eventPanel.add(buttons.get(n));
                group.add(buttons.get(n));
                n++;
            }
        }

        int finalN1 = n;
        JButton button = new JButton("OK"){
            {
                setFont(eventFont);
                setBounds(eventPanel.getWidth()/4,height*7/9,eventPanel.getWidth()/2,height/9);
                setFont(new Font("OCR A Extended", Font.PLAIN, 30));
                setBackground(new Color(130, 204, 217));
                setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i< finalN1; i++)  {
                            if (buttons.get(i).isSelected()) {
                                team.getHero(buttons.get(i).getText()).damage();
                                break;
                            }
                        }
                        checkDead();
                        nextStep();
                    }
                });
            }
        };
        eventPanel.add(button);

        panel.add(eventPanel);
        revalidate();
        repaint();
    }

    public void killPanel() {
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

        JLabel textLabel = new JLabel("Choose who dies"){
            {
                setFont(eventFont);
                setBounds(0,height/9,eventPanel.getWidth(),eventFont.getSize());
                setOpaque(true);
                setBackground(new Color(3, 87, 30));
                setForeground(new Color(245, 205, 76));
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        };
        eventPanel.add(textLabel);

        int n = 0;
        ArrayList<JRadioButton> buttons = new ArrayList<>();
        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i<3; i++) {
            if (team.getHero(i).isAlive()) {
                int finalN = n;
                buttons.add(new JRadioButton(team.getHero(i).getNAME()){
                    {
                        setFont(eventFont);
                        setBounds(eventPanel.getWidth()/4,height*2/9+eventFont.getSize()*finalN,eventPanel.getWidth()/2,eventFont.getSize());
                        setOpaque(true);
                        setBackground(new Color(3, 87, 30));
                        setForeground(new Color(245, 205, 76));
                        if (finalN ==0) setSelected(true);
                    }
                });
                eventPanel.add(buttons.get(n));
                group.add(buttons.get(n));
                n++;
            }
        }

        int finalN1 = n;
        JButton button = new JButton("OK"){
            {
                setFont(eventFont);
                setBounds(eventPanel.getWidth()/4,height*7/9,eventPanel.getWidth()/2,height/9);
                setFont(new Font("OCR A Extended", Font.PLAIN, 30));
                setBackground(new Color(130, 204, 217));
                setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i< finalN1; i++)  {
                            if (buttons.get(i).isSelected()) {
                                team.getHero(buttons.get(i).getText()).die();
                                break;
                            }
                        }
                        checkDead();
                        nextStep();
                    }
                });
            }
        };
        eventPanel.add(button);

        panel.add(eventPanel);
        revalidate();
        repaint();
    }

    public void foodRemovePanel() {
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

        if (team.getFood()!=0) {
            JLabel textLabel = new JLabel("Food will be removed"){
                {
                    setFont(eventFont);
                    setBounds(0,height/9,eventPanel.getWidth(),eventFont.getSize());
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
                    setBounds(eventPanel.getWidth()/4,height*7/9,eventPanel.getWidth()/2,height/9);
                    setFont(new Font("OCR A Extended", Font.PLAIN, 30));
                    setBackground(new Color(130, 204, 217));
                    setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
                    addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            team.giveFood();
                            nextStep();
                        }
                    });
                }
            };
            eventPanel.add(button);
        }
        else {
            JLabel text1Label = new JLabel("Missing food"){
                {
                    setFont(eventFont);
                    setBounds(0,height/9,eventPanel.getWidth(),eventFont.getSize());
                    setOpaque(true);
                    setBackground(new Color(3, 87, 30));
                    setForeground(new Color(245, 205, 76));
                    setHorizontalAlignment(SwingConstants.CENTER);
                }
            };
            eventPanel.add(text1Label);

            JLabel text2Label = new JLabel("Choose who looses hp"){
                {
                    setFont(eventFont);
                    setBounds(0,height/9+eventFont.getSize(),eventPanel.getWidth(),eventFont.getSize());
                    setOpaque(true);
                    setBackground(new Color(3, 87, 30));
                    setForeground(new Color(245, 205, 76));
                    setHorizontalAlignment(SwingConstants.CENTER);
                }
            };
            eventPanel.add(text2Label);

            int n = 0;
            ArrayList<JRadioButton> buttons = new ArrayList<>();
            ButtonGroup group = new ButtonGroup();
            for (int i = 0; i<3; i++) {
                if (team.getHero(i).isAlive()) {
                    int finalN = n;
                    buttons.add(new JRadioButton(team.getHero(i).getNAME()){
                        {
                            setFont(eventFont);
                            setBounds(eventPanel.getWidth()/4,height*2/9+eventFont.getSize()*finalN,eventPanel.getWidth()/2,eventFont.getSize());
                            setOpaque(true);
                            setBackground(new Color(3, 87, 30));
                            setForeground(new Color(245, 205, 76));
                            if (finalN ==0) setSelected(true);
                        }
                    });
                    eventPanel.add(buttons.get(n));
                    group.add(buttons.get(n));
                    n++;
                }
            }

            int finalN1 = n;
            JButton button = new JButton("OK"){
                {
                    setFont(eventFont);
                    setBounds(eventPanel.getWidth()/4,height*7/9,eventPanel.getWidth()/2,height/9);
                    setFont(new Font("OCR A Extended", Font.PLAIN, 30));
                    setBackground(new Color(130, 204, 217));
                    setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
                    addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (int i = 0; i< finalN1; i++)  {
                                if (buttons.get(i).isSelected()) {
                                    team.getHero(buttons.get(i).getText()).damage();
                                    break;
                                }
                            }
                            checkDead();
                            nextStep();
                        }
                    });
                }
            };
            eventPanel.add(button);
        }

        panel.add(eventPanel);
        revalidate();
        repaint();
    }

    public void tokenRemovePanel(int token) {
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

        if (team.findToken(token)) {
            JLabel textLabel = new JLabel("Choose token to give"){
                {
                    switch (token) {
                        case Token.LEAF: setText("Choose token to give for leaf"); break;
                        case Token.TENT: setText("Choose token to give for tent"); break;
                        case Token.COMPASS: setText("Choose token to give for compass"); break;
                    }
                    setFont(eventFont);
                    setBounds(0,height/9,eventPanel.getWidth(),eventFont.getSize());
                    setOpaque(true);
                    setBackground(new Color(3, 87, 30));
                    setForeground(new Color(245, 205, 76));
                    setHorizontalAlignment(SwingConstants.CENTER);
                }
            };
            eventPanel.add(textLabel);

            int n = 0;
            ArrayList<JRadioButton> buttons = new ArrayList<>();
            ArrayList<Token> ts = new ArrayList<>();
            ButtonGroup group = new ButtonGroup();
            for (int i = 0; i<team.getTokens().size(); i++) {
                if (team.getToken(i).checkToken(token)) {
                    int finalN = n;
                    buttons.add(new JRadioButton(team.getToken(i).toString()){
                        {
                            setFont(eventFont);
                            setBounds(0,height*2/9+eventFont.getSize()*finalN,eventPanel.getWidth(),eventFont.getSize());
                            setOpaque(true);
                            setBackground(new Color(3, 87, 30));
                            setForeground(new Color(245, 205, 76));
                            if (finalN ==0) setSelected(true);
                        }
                    });
                    ts.add(team.getToken(i));
                    eventPanel.add(buttons.get(n));
                    group.add(buttons.get(n));
                    n++;
                }
            }

            int finalN1 = n;
            JButton button = new JButton("OK"){
                {
                    setFont(eventFont);
                    setBounds(eventPanel.getWidth()/4,height*7/9,eventPanel.getWidth()/2,height/9);
                    setFont(new Font("OCR A Extended", Font.PLAIN, 30));
                    setBackground(new Color(130, 204, 217));
                    setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
                    addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (int i = 0; i< finalN1; i++)  {
                                if (buttons.get(i).isSelected()) {
                                    removeExpCard(expCards.get(team.getTokens().indexOf(ts.get(i))));
                                    team.removeToken(ts.get(i));
                                    break;
                                }
                            }
                            nextStep();
                        }
                    });
                }
            };
            eventPanel.add(button);
        }
        else if (team.getHeroByToken(token).isAlive()) {
            JLabel text1Label = new JLabel("You don't have required tokens"){
                {
                    setFont(eventFont);
                    setBounds(0,height/9,eventPanel.getWidth(),eventFont.getSize());
                    setOpaque(true);
                    setBackground(new Color(3, 87, 30));
                    setForeground(new Color(245, 205, 76));
                    setHorizontalAlignment(SwingConstants.CENTER);
                }
            };
            eventPanel.add(text1Label);

            JLabel text2Label = new JLabel("HP of the character will be wasted"){
                {
                    setFont(eventFont);
                    setBounds(0,height/9+eventFont.getSize(),eventPanel.getWidth(),eventFont.getSize());
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
                    setBounds(eventPanel.getWidth()/4,height*7/9,eventPanel.getWidth()/2,height/9);
                    setFont(new Font("OCR A Extended", Font.PLAIN, 30));
                    setBackground(new Color(130, 204, 217));
                    setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
                    addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            team.getHeroByToken(token).damage();
                            checkDead();
                            nextStep();
                        }
                    });
                }
            };
            eventPanel.add(button);
        }
        else {
            JLabel text1Label = new JLabel("No required tokens or character"){
                {
                    setFont(eventFont);
                    setBounds(0,height/9,eventPanel.getWidth(),eventFont.getSize());
                    setOpaque(true);
                    setBackground(new Color(3, 87, 30));
                    setForeground(new Color(245, 205, 76));
                    setHorizontalAlignment(SwingConstants.CENTER);
                }
            };
            eventPanel.add(text1Label);

            JLabel text2Label = new JLabel("2 HPs will be wasted instead"){
                {
                    setFont(eventFont);
                    setBounds(0,height/9+eventFont.getSize(),eventPanel.getWidth(),eventFont.getSize());
                    setOpaque(true);
                    setBackground(new Color(3, 87, 30));
                    setForeground(new Color(245, 205, 76));
                    setHorizontalAlignment(SwingConstants.CENTER);
                }
            };
            eventPanel.add(text2Label);

            int n = 0;
            ArrayList<JRadioButton> buttons = new ArrayList<>();
            ButtonGroup group = new ButtonGroup();
            for (int i = 0; i<3; i++) {
                if (team.getHero(i).isAlive()) {
                    int finalN = n;
                    buttons.add(new JRadioButton(team.getHero(i).getNAME()){
                        {
                            setFont(eventFont);
                            setBounds(eventPanel.getWidth()/4,height*2/9+eventFont.getSize()*finalN,eventPanel.getWidth()/2,eventFont.getSize());
                            setOpaque(true);
                            setBackground(new Color(3, 87, 30));
                            setForeground(new Color(245, 205, 76));
                            if (finalN ==0) setSelected(true);
                        }
                    });
                    eventPanel.add(buttons.get(n));
                    group.add(buttons.get(n));
                    n++;
                }
            }

            int finalN1 = n;
            JButton button = new JButton("OK"){
                {
                    setFont(eventFont);
                    setBounds(eventPanel.getWidth()/4,height*7/9,eventPanel.getWidth()/2,height/9);
                    setFont(new Font("OCR A Extended", Font.PLAIN, 30));
                    setBackground(new Color(130, 204, 217));
                    setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
                    addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            for (int i = 0; i< finalN1; i++)  {
                                if (buttons.get(i).isSelected()) {
                                    team.getHero(buttons.get(i).getText()).damage();
                                    team.getHero(buttons.get(i).getText()).damage();
                                    break;
                                }
                            }
                            checkDead();
                            nextStep();
                        }
                    });
                }
            };
            eventPanel.add(button);
        }

        panel.add(eventPanel);
        revalidate();
        repaint();
    }

    public void swapCardsPanel() {
        if (road.size()>2) {
            panel.remove(eventPanel);

            eventPanel = new JPanel() {
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    try {
                        g.drawImage(ImageIO.read(new File("jungle.png")).getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
                    } catch (IOException e) {
                        new AnnounceDialog(GameFrame.this, true, "Error", "File \"jungle.png\" not found").setVisible(true);
                    }
                }
            };
            eventPanel.setBounds(cardsPanel.getWidth() + cardsPanel.getX(), 0, panel.getWidth() - cardsPanel.getWidth() - cardsPanel.getX(), height);
            eventPanel.setLayout(null);

            JLabel textLabel = new JLabel("Choose cards to swap") {
                {
                    setFont(eventFont);
                    setBounds(0, height / 9, eventPanel.getWidth(), eventFont.getSize());
                    setOpaque(true);
                    setBackground(new Color(3, 87, 30));
                    setForeground(new Color(245, 205, 76));
                    setHorizontalAlignment(SwingConstants.CENTER);
                }
            };
            eventPanel.add(textLabel);

            ArrayList<JRadioButton> buttons = new ArrayList<>();
            ButtonGroup group = new ButtonGroup();
            for (int i = 0; i < road.size()-1; i++) {
                int finalI = i+1;
                buttons.add(new JRadioButton(road.get(finalI).toString()) {
                    {
                        setFont(eventFont);
                        setBounds(eventPanel.getWidth() / 4, height * 2 / 9 + eventFont.getSize() * (finalI-1), eventPanel.getWidth() / 2, eventFont.getSize());
                        setOpaque(true);
                        setBackground(new Color(3, 87, 30));
                        setForeground(new Color(245, 205, 76));
                        if (finalI == 1) setSelected(true);
                    }
                });
                eventPanel.add(buttons.get(i));
                group.add(buttons.get(i));
            }

            buttons.add(new JRadioButton("No swap"){
                {
                    setFont(eventFont);
                    setBounds(eventPanel.getWidth() / 4, height * 2 / 9 + eventFont.getSize() * buttons.size(), eventPanel.getWidth() / 2, eventFont.getSize());
                    setOpaque(true);
                    setBackground(new Color(3, 87, 30));
                    setForeground(new Color(245, 205, 76));
                }
            });
            eventPanel.add(buttons.get(buttons.size()-1));
            group.add(buttons.get(buttons.size()-1));

            JButton button = new JButton("OK") {
                {
                    setFont(eventFont);
                    setBounds(eventPanel.getWidth() / 4, height * 7 / 9, eventPanel.getWidth() / 2, height / 9);
                    setFont(new Font("OCR A Extended", Font.PLAIN, 30));
                    setBackground(new Color(130, 204, 217));
                    setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
                }
            };
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (buttons.get(buttons.size()-1).isSelected()) nextStep();
                    else {
                        int n=0;
                        for (int i = 0; i<buttons.size()-1; i++) {
                            if (buttons.get(i).isSelected()) {
                                n=i+1;
                                break;
                            }
                        }
                        for (JRadioButton b : buttons) b.setEnabled(false);
                        textLabel.setText("Choose second card");

                        ArrayList<JRadioButton> secondButtons = new ArrayList<>();
                        ButtonGroup group1 = new ButtonGroup();
                        for (int i = 0; i<road.size()-1; i++) {
                            int finalI = i+1;
                            if (finalI!=n) {
                                int finalN1 = n;
                                secondButtons.add(new JRadioButton(road.get(finalI).toString()) {
                                    {
                                        setFont(eventFont);
                                        setBounds(eventPanel.getWidth() / 4, height * 4 / 9 + eventFont.getSize() * ((finalI< finalN1)?finalI:finalI-1), eventPanel.getWidth() / 2, eventFont.getSize());
                                        setOpaque(true);
                                        setBackground(new Color(3, 87, 30));
                                        setForeground(new Color(245, 205, 76));
                                        if (finalI == 1 || (finalN1==1 && finalI==2)) setSelected(true);
                                    }
                                });
                                if (i<n) {
                                    eventPanel.add(secondButtons.get(i));
                                    group1.add(secondButtons.get(i));
                                } else {
                                    eventPanel.add(secondButtons.get(i-1));
                                    group1.add(secondButtons.get(i-1));
                                }
                            }
                        }
                        int finalN = n;
                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                for (int i = 0; i<secondButtons.size();i++) {
                                    if (secondButtons.get(i).isSelected()) {
                                        if (i+1>=finalN)
                                            Collections.swap(road, finalN,i+2);
                                        else
                                            Collections.swap(road,finalN,i+1);
                                        break;
                                    }
                                }
                                nextStep();
                            }
                        });
                        button.removeActionListener(this);
                        revalidate();
                        repaint();
                    }
                }
            });
            eventPanel.add(button);

            panel.add(eventPanel);
            revalidate();
            repaint();
        } else textPanel("Not enough cards to swap");
    }

    public void putCardDayPanel() {
        panel.remove(eventPanel);

        eventPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(ImageIO.read(new File("jungle.png")).getScaledInstance(panel.getWidth(),panel.getHeight(), Image.SCALE_SMOOTH),0,0,null);
                } catch (IOException e) {
                    new AnnounceDialog(GameFrame.this,true,"Error","File \"jungle.png\" not found").setVisible(true);
                }
            }
        };
        eventPanel.setBounds(cardsPanel.getWidth()+cardsPanel.getX(),0,panel.getWidth()-cardsPanel.getWidth()-cardsPanel.getX(),height);
        eventPanel.setLayout(null);

        JLabel textLabel = new JLabel("Choose card to put"){
            {
                setFont(eventFont);
                setBounds(0,height/9,eventPanel.getWidth(),eventFont.getSize());
                setOpaque(true);
                setBackground(new Color(3, 87, 30));
                setForeground(new Color(245, 205, 76));
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        };
        eventPanel.add(textLabel);

        ArrayList<JRadioButton> buttons = new ArrayList<>();
        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i<hand.size(); i++) {
            int finalI = i;
            buttons.add(new JRadioButton(hand.get(finalI).toString()) {
                {
                    setFont(eventFont);
                    setBounds(eventPanel.getWidth()/4,height*2/9+eventFont.getSize()* finalI,eventPanel.getWidth()/2,eventFont.getSize());
                    setOpaque(true);
                    setBackground(new Color(3, 87, 30));
                    setForeground(new Color(245, 205, 76));
                    if (finalI == 0) setSelected(true);
                }
            });
            eventPanel.add(buttons.get(i));
            group.add(buttons.get(i));
        }

        JButton button = new JButton("OK"){
            {
                setFont(eventFont);
                setBounds(eventPanel.getWidth()/4,height*7/9,eventPanel.getWidth()/2,height/9);
                setFont(new Font("OCR A Extended", Font.PLAIN, 30));
                setBackground(new Color(130, 204, 217));
                setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i< buttons.size(); i++)  {
                            if (buttons.get(i).isSelected()) {
                                road.add(hand.remove(i));
                                sortCards(road);
                                break;
                            }
                        }
                        nextStep();
                    }
                });
            }
        };
        eventPanel.add(button);

        panel.add(eventPanel);
        revalidate();
        repaint();
    }

    private void putCardNightPanel() {
        panel.remove(eventPanel);

        eventPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(ImageIO.read(new File("jungle.png")).getScaledInstance(panel.getWidth(),panel.getHeight(), Image.SCALE_SMOOTH),0,0,null);
                } catch (IOException e) {
                    new AnnounceDialog(GameFrame.this,true,"Error","File \"jungle.png\" not found").setVisible(true);
                }
            }
        };
        eventPanel.setBounds(cardsPanel.getWidth()+cardsPanel.getX(),0,panel.getWidth()-cardsPanel.getWidth()-cardsPanel.getX(),height);
        eventPanel.setLayout(null);

        JLabel textLabel = new JLabel("Choose card to put"){
            {
                setFont(eventFont);
                setBounds(0,height/9,eventPanel.getWidth(),eventFont.getSize());
                setOpaque(true);
                setBackground(new Color(3, 87, 30));
                setForeground(new Color(245, 205, 76));
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        };
        eventPanel.add(textLabel);

        ArrayList<JRadioButton> buttons = new ArrayList<>();
        ButtonGroup group = new ButtonGroup();
        if (handCards!=3) {
            for (int i = 0; i < hand.size(); i++) {
                int finalI = i;
                buttons.add(new JRadioButton(hand.get(finalI).toString()) {
                    {
                        setFont(eventFont);
                        setBounds(eventPanel.getWidth() / 4, height * 2 / 9 + eventFont.getSize() * finalI, eventPanel.getWidth() / 2, eventFont.getSize());
                        setOpaque(true);
                        setBackground(new Color(3, 87, 30));
                        setForeground(new Color(245, 205, 76));
                        if (finalI == 0) setSelected(true);
                    }
                });
                eventPanel.add(buttons.get(i));
                group.add(buttons.get(i));
            }
        }
        if (comCards!=3) {
            buttons.add(new JRadioButton("Deck card"){
                {
                    setFont(eventFont);
                    setBounds(eventPanel.getWidth() / 4, height * 2 / 9 + eventFont.getSize() * buttons.size(), eventPanel.getWidth() / 2, eventFont.getSize());
                    setOpaque(true);
                    setBackground(new Color(3, 87, 30));
                    setForeground(new Color(245, 205, 76));
                }
            });
            if (buttons.size()==1) buttons.get(0).setSelected(true);
            eventPanel.add(buttons.get(buttons.size()-1));
            group.add(buttons.get(buttons.size()-1));
        }

        JButton button = new JButton("OK"){
            {
                setFont(eventFont);
                setBounds(eventPanel.getWidth()/4,height*7/9,eventPanel.getWidth()/2,height/9);
                setFont(new Font("OCR A Extended", Font.PLAIN, 30));
                setBackground(new Color(130, 204, 217));
                setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
            }
        };
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Card card = null;
                for (int i = 0; i< buttons.size(); i++)  {
                    if (buttons.get(i).isSelected()) {
                        if (i==buttons.size()-1 && comCards<3) {
                            if (deck.size()<1) fillDeck();
                            card = deck.remove(0);
                            comCards++;
                        }
                        else {
                            card = hand.remove(i);
                            handCards++;
                        }
                        break;
                    }
                }
                panel.remove(eventPanel);
                textLabel.setText("Choose where to put card");
                for (JRadioButton b : buttons) b.setEnabled(false);
                JRadioButton left = new JRadioButton("Left"){
                    {
                        setFont(eventFont);
                        setBounds(eventPanel.getWidth() / 4, height * 3 / 9, eventPanel.getWidth() / 2, eventFont.getSize());
                        setOpaque(true);
                        setBackground(new Color(3, 87, 30));
                        setForeground(new Color(245, 205, 76));
                        setSelected(true);
                    }
                };
                eventPanel.add(left);

                JRadioButton right = new JRadioButton("Right"){
                    {
                        setFont(eventFont);
                        setBounds(eventPanel.getWidth() / 4, height * 3 / 9 + eventFont.getSize(), eventPanel.getWidth() / 2, eventFont.getSize());
                        setOpaque(true);
                        setBackground(new Color(3, 87, 30));
                        setForeground(new Color(245, 205, 76));
                    }
                };
                eventPanel.add(right);

                new ButtonGroup(){
                    {
                        add(left);
                        add(right);
                    }
                };

                JLabel cardLabel = null;
                try {
                    cardLabel = new JLabel(new ImageIcon(ImageIO.read(new File("Cards/card" + card.getNumber()+".png")).getScaledInstance(eventPanel.getWidth()/2,eventPanel.getWidth()*3/4, Image.SCALE_SMOOTH)));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                cardLabel.setBounds(eventPanel.getWidth()/4,height*4/9,eventPanel.getWidth()/2,eventPanel.getWidth()*3/4);
                eventPanel.add(cardLabel);

                Card finalCard = card;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (left.isSelected()) road.add(0,finalCard);
                        else road.add(finalCard);
                        nextStep();
                    }
                });

                button.removeActionListener(this);

                panel.add(eventPanel);
                revalidate();
                repaint();
            }
        });
        eventPanel.add(button);

        panel.add(eventPanel);
        revalidate();
        repaint();
    }

    private void nextStep() {
        drawStats();
        drawExpCard();
        checkEnd();
        if (cards) {
            if (day) {
                switch (status) {
                    case 0:
                        status++;

                        fillHand();
                        drawHand();

                        putCard();
                        putCard();
                        drawRoad();

                        textPanel("Two cards were put on deck");
                        break;
                    case 1:
                    case 2:
                        status++;

                        putCardDayPanel();
                        break;

                    case 3:
                        status++;

                        putCard();

                        textPanel("One card was put on desk");
                        break;
                    case 4:
                        status=0;
                        putCardDayPanel();
                        cards=false;
                        break;
                }
            }
            else {
                if (status==0) {
                    putCardDayPanel();
                    handCards=1;
                    comCards=0;
                    status++;
                }
                else {
                    putCardNightPanel();
                    status++;
                    if (status==6) {
                        status=0;
                        cards=false;
                    }
                }
            }
        }
        else {
            if ((currentYellow == null || currentYellow.size() == 0) && (currentRed == null || currentRed.size() == 0) && (currentBlue == null || currentBlue.size() == 0)) {
                if (currentCard!=null) road.remove(0);
                if (road.size() != 0) {
                    currentCard = road.get(0);
                       if (currentCard.getYellowEffects() != null)
                           currentYellow = (ArrayList<Effect>) currentCard.getYellowEffects().clone();
                       else currentYellow = null;
                       if (currentCard.getRedEffects() != null) initRedEffectsPanel();
                       else {
                           currentRed = null;
                           if (currentCard.getBlueEffects() != null) initBlueEffectsPanel();
                           else {
                               currentBlue = null;
                               nextStep();
                           }
                       }
                } else {
                    if (day) day = false;
                    else day = true;
                    cards = true;
                    currentCard=null;
                    currentYellow=null;
                    currentRed=null;
                    currentBlue=null;
                    drawStats();
                    drawExpCard();
                    foodRemovePanel();
                }
            }
            else {
                if (currentYellow != null && currentYellow.size() != 0) {
                    currentYellow.remove(0).doEffect();
                } else if (currentRed != null && currentRed.size() != 0) {
                    currentRed.remove(0).doEffect();
                } else if (currentBlue != null && currentBlue.size() != 0) {
                    currentBlue.remove(0).doEffect();
                }
                else {
                    System.out.println("How TF did we end up here?");
                }
            }
        }

        drawHand();
        drawRoad();
    }

    private void initRedEffectsPanel() {
        panel.remove(eventPanel);

        eventPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(ImageIO.read(new File("jungle.png")).getScaledInstance(panel.getWidth(),panel.getHeight(), Image.SCALE_SMOOTH),0,0,null);
                } catch (IOException e) {
                    new AnnounceDialog(GameFrame.this,true,"Error","File \"jungle.png\" not found").setVisible(true);
                }
            }
        };
        eventPanel.setBounds(cardsPanel.getWidth()+cardsPanel.getX(),0,panel.getWidth()-cardsPanel.getWidth()-cardsPanel.getX(),height);
        eventPanel.setLayout(null);

        JLabel textLabel = new JLabel("Choose red effect line"){
            {
                setFont(eventFont);
                setBounds(0,height/9,eventPanel.getWidth(),eventFont.getSize());
                setOpaque(true);
                setBackground(new Color(3, 87, 30));
                setForeground(new Color(245, 205, 76));
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        };
        eventPanel.add(textLabel);

        ArrayList<JRadioButton> buttons = new ArrayList<>();
        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i<currentCard.getRedEffects().size(); i++) {
            int finalI = i;
            if (!(currentCard.getRedEffects().get(finalI).get(0).getEffect()==Effect.BULLET &&
                    currentCard.getRedEffects().get(finalI).get(0).getMode()==Effect.REMOVE &&
                        team.getBullets()==0)) {
                buttons.add(new JRadioButton("Line " + (finalI + 1)) {
                    {
                        setFont(eventFont);
                        setBounds(eventPanel.getWidth() / 4, height * 2 / 9 + eventFont.getSize() * finalI, eventPanel.getWidth() / 2, eventFont.getSize());
                        setOpaque(true);
                        setBackground(new Color(3, 87, 30));
                        setForeground(new Color(245, 205, 76));
                        if (finalI == 0) setSelected(true);
                    }
                });
                eventPanel.add(buttons.get(i));
                group.add(buttons.get(i));
            }
        }

        JButton button = new JButton("OK"){
            {
                setFont(eventFont);
                setBounds(eventPanel.getWidth()/4,height*7/9,eventPanel.getWidth()/2,height/9);
                setFont(new Font("OCR A Extended", Font.PLAIN, 30));
                setBackground(new Color(130, 204, 217));
                setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i< currentCard.getRedEffects().size(); i++)  {
                            if (buttons.get(i).isSelected()) {
                                currentRed = new ArrayList<>();
                                currentRed=(ArrayList<Effect>) currentCard.getRedEffects().get(i).clone();
                                break;
                            }
                        }
                        if (currentCard.getBlueEffects()!=null) initBlueEffectsPanel();
                        else {
                            currentBlue=null;
                            nextStep();
                        }
                    }
                });
            }
        };
        eventPanel.add(button);

        panel.add(eventPanel);
        revalidate();
        repaint();
    }

    private void initBlueEffectsPanel() {
        panel.remove(eventPanel);

        eventPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(ImageIO.read(new File("jungle.png")).getScaledInstance(panel.getWidth(),panel.getHeight(), Image.SCALE_SMOOTH),0,0,null);
                } catch (IOException e) {
                    new AnnounceDialog(GameFrame.this,true,"Error","File \"jungle.png\" not found").setVisible(true);
                }
            }
        };
        eventPanel.setBounds(cardsPanel.getWidth()+cardsPanel.getX(),0,panel.getWidth()-cardsPanel.getWidth()-cardsPanel.getX(),height);
        eventPanel.setLayout(null);

        JLabel textLabel = new JLabel("Choose blue effect lines"){
            {
                setFont(eventFont);
                setBounds(0,height/9,eventPanel.getWidth(),eventFont.getSize());
                setOpaque(true);
                setBackground(new Color(3, 87, 30));
                setForeground(new Color(245, 205, 76));
                setHorizontalAlignment(SwingConstants.CENTER);
            }
        };
        eventPanel.add(textLabel);

        ArrayList<JCheckBox> buttons = new ArrayList<>();
        for (int i = 0; i<currentCard.getBlueEffects().size(); i++) {
            int finalI = i;
            if (!(currentCard.getBlueEffects().get(finalI).get(0).getEffect()==Effect.BULLET &&
                    currentCard.getBlueEffects().get(finalI).get(0).getMode()==Effect.REMOVE &&
                    team.getBullets()==0)) {
                buttons.add(new JCheckBox("Line " + (finalI + 1)) {
                    {
                        setFont(eventFont);
                        setBounds(eventPanel.getWidth() / 4, height * 2 / 9 + eventFont.getSize() * finalI, eventPanel.getWidth() / 2, eventFont.getSize());
                        setOpaque(true);
                        setBackground(new Color(3, 87, 30));
                        setForeground(new Color(245, 205, 76));
                    }
                });
                eventPanel.add(buttons.get(i));
            }
        }

        JButton button = new JButton("OK"){
            {
                setFont(eventFont);
                setBounds(eventPanel.getWidth()/4,height*7/9,eventPanel.getWidth()/2,height/9);
                setFont(new Font("OCR A Extended", Font.PLAIN, 30));
                setBackground(new Color(130, 204, 217));
                setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
                addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int i = 0; i< currentCard.getBlueEffects().size(); i++)  {
                            if (buttons.get(i).isSelected()) {
                                currentBlue = new ArrayList<>();
                                currentBlue.addAll(currentCard.getBlueEffects().get(i));
                            }
                        }
                        nextStep();
                    }
                });
            }
        };
        eventPanel.add(button);

        panel.add(eventPanel);
        revalidate();
        repaint();
    }

    private void getPanelOfEffect(Effect effect) {

    }

    private void checkDead() {
        for (int i = 0; i<3; i++) if (!team.getHero(i).isAlive() && team.getHero(i).getNAME().charAt(team.getHero(i).getNAME().length()-1)!='d') team.getHero(i).setNAME(team.getHero(i).getNAME()+"_dead");
    }

    public void checkEnd() {
        boolean flag = true;
        if (pathOn==pathLength) new WinDialog(this,true,"Victory","You've won!").setVisible(true);
        for (int i = 0; i<3; i++) if (team.getHero(i).isAlive()) {flag = false; break;}
        if (flag) new WinDialog(this,true,"Defeat","You've lost!").setVisible(true);
    }

    public void addExpCard(String path){
        expCards.add(path);
        drawExpCard();
    }
    private void removeExpCard(String path){
        for(int i=0;i<expCards.size();i++){
            if(expCards.get(i).equals(path)) expCards.remove(i);
        }
        drawExpCard();
    }

    public void putCard() {
        if (deck.size()<1) fillDeck();
        road.add(deck.remove(0));
        sortCards(road);
    }

    public void putCardLast() {
        if (deck.size()<1) fillDeck();
        road.add(deck.remove(0));
    }

    public void removeLastRoadCard() {
        if (road.size()==1) textPanel("Can't remove cards");
        else {
            road.remove(road.size()-1);
            textPanel("Last card removed");
        }
    }

    public void removeNextCard() {
        if (road.size()==1) textPanel("Can't skip card");
        else {
            road.remove(1);
            textPanel("Next card removed");
        }
    }

    private void drawStats() {
        panel.remove(statsPanel);

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

        smallTokenSize=40;

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

        bigTokenSize=80;
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

        expPanel = new JPanel();
        expPanel.setBounds(0,heroCardHeight*2+bigTokenSize*2+15,((this.getWidth()- cardsPanel.getWidth())/2)+10,this.getHeight() - ((height / 2) + 185));
        expPanel.setLayout(null);
        expPanel.setOpaque(false);
        statsPanel.add(expPanel);

        panel.add(statsPanel);
        revalidate();
        repaint();
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
        revalidate();
        repaint();
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
         revalidate();
         repaint();
    }

    private void fillHand() {
        hand = new ArrayList<>();
        if (deck.size()<6) fillDeck();
        for (int i = 0; i<6; i++) hand.add(deck.remove(0));
        sortCards(hand);

    }
    private void drawExpCard(){
        if (expCards.size()>0) {
            expPanel.removeAll();
            int expWidth = (this.getWidth() - cardsPanel.getWidth()) / (2 * expCards.size());
            int expHeight = expWidth * 3 / 2;
            if (expHeight > this.getHeight() - (height / 2 + 210)) {
                expHeight = this.getHeight() - (height / 2 + 210);
                expWidth = expHeight * 2 / 3;
            }
            for (int i = 0; i < expCards.size(); i++) {
                String path = expCards.get(i);
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
    }
    private void fillCards() {
        deck = fillDeck();
    }

    private void sortCards(ArrayList<Card> cards) {
        int n = cards.size();
        for (int i = 0; i<n-1;i++) for (int j = 0; j < n-i-1; j++) if (cards.get(j).getNumber() > cards.get(j+1).getNumber()) Collections.swap(cards,j,j+1);
    }

    private ArrayList<Card> fillDeck() {
        if (playable.size()==0) ;// TODO playable init
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
        redEffects.get(0).add(new Effect(Effect.FOOD, Effect.REMOVE));
        redEffects.get(0).add(new Effect(new Token(Token.TENT), Effect.ADD));
        redEffects.add(new ArrayList<>());
        redEffects.get(1).add(new Effect(Effect.BULLET, Effect.REMOVE));
        redEffects.get(1).add(new Effect(Effect.SWAP_CARD));
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

    public void addFood() {
        team.addFood();
    }

    public void addToken(Token token) {
        team.addToken(token);
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void incPathOn() {
        pathOn++;
    }

    public void addBullet() {
        team.addBullet();
    }

    public void removeBullet() {
        team.giveBullets();
    }

    private final Random rand = new Random();

    private final Font eventFont = new Font("OCR A Extended",Font.PLAIN,15);

    private boolean day = true, cards = true;

    private int difficulty;

    private int status = 0, comCards = 0, handCards = 0;

    private int pathOn = 1, pathLength = 9;

    private int heroCardWidth, heroCardHeight, smallTokenSize, bigTokenSize, pathCardWidth, pathCardHeight, handCardWidth, handCardHeight, roadCardWidth, roadCardHeight,manWidth,manHeight;

    private Card currentCard = null;

    private ArrayList<Effect> currentYellow = null, currentRed = null, currentBlue = null;

    private final ArrayList<Card> allCards = makeCards();

    private ArrayList<String> expCards= new ArrayList<>();

    private ArrayList<Card> playable=(ArrayList<Card>)allCards.clone(), deck = fillDeck(),hand,road = new ArrayList<>();

    private final int width = Toolkit.getDefaultToolkit().getScreenSize().width;

    private final int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    private JPanel panel = new JPanel(),statsPanel,cardsPanel, eventPanel,expPanel;

    private Team team;

    private JLabel heroLeafImage,heroLeafHP,heroTentImage,heroTentHP,heroCompassImage,heroCompassHP,
            foodImage,foodLabel,bulletImage,bulletLabel,hpImage1,hpImage2,hpImage3,leaderImage,morningImage,man;

    private ArrayList<JLabel> handLabels = new ArrayList<>(),roadLabels=new ArrayList<>();

}
