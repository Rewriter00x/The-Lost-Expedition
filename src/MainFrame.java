import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import javax.swing.BorderFactory;


public class MainFrame extends JFrame{
    public MainFrame(){
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);

        try {
            BufferedImage img = ImageIO.read(new File("background.png"));
            this.setContentPane(new JLabel(new ImageIcon(img)));
        } catch (IOException e) {
            new AnnounceDialog(MainFrame.this,true,"Error","File \"background.png\" not found").setVisible(true);
        }
        try {
            BufferedImage img = ImageIO.read(new File("glass.png"));
            this.setIconImage(img);
        } catch (IOException e) {
            new AnnounceDialog(MainFrame.this,true,"Error","File \"glass.png\" not found").setVisible(true);
        }
        this.getContentPane().setLayout(null);

        name.setBounds(WIDTH/2-WIDTH/4+10,20,WIDTH/2-20,HEIGHT/11);
        name.setText("THE LOST EXPEDITION");
        name.setOpaque(true);
        name.setBackground(new Color(3, 87, 30));
        name.setForeground(new Color(245, 205, 76));
        name.setFont(new Font("OCR A Extended", Font.PLAIN, 30));
        name.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(name);

        authors.setBounds(WIDTH/2-WIDTH/4,HEIGHT-60,WIDTH/2,HEIGHT/18);
        authors.setText("by Burliai Danylo and Khoroshun Oleksandra");
        authors.setOpaque(true);
        authors.setBackground(new Color(3, 87, 30));
        authors.setForeground(new Color(245, 205, 76));
        authors.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        authors.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(authors);

        PANEL = MAINPANEL();
        this.add(PANEL);

        this.setTitle("The Lost Expedition");

    }
    public JPanel MAINPANEL(){
        PANEL = new JPanel();
        PANEL.setSize(WIDTH,HEIGHT);
        PANEL.setLayout(null);
        PANEL.setOpaque(false);

        SINGLE.setBounds(WIDTH/2-WIDTH/4/2,HEIGHT/2-2*(HEIGHT/8)-20,WIDTH/4,HEIGHT/8);
        SINGLE.setText("SINGLEPLAYER");
        SINGLE.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        SINGLE.setBackground(new Color(130, 204, 217));
        SINGLE.setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
        PANEL.add(SINGLE);

        MULTI.setBounds(WIDTH/2-WIDTH/4/2,HEIGHT/2-(HEIGHT/8),WIDTH/4,HEIGHT/8);
        MULTI.setText("MULTIPLAYER");
        MULTI.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        MULTI.setBackground(new Color(130, 204, 217));
        MULTI.setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
        PANEL.add(MULTI);

        HELP.setBounds(WIDTH/2-WIDTH/4/2,HEIGHT/2+20,WIDTH/4,HEIGHT/8);
        HELP.setText("HELP");
        HELP.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        HELP.setBackground(new Color(130, 204, 217));
        HELP.setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
        PANEL.add(HELP);

        EXIT.setBounds(WIDTH/2-WIDTH/4/2,HEIGHT/2+HEIGHT/4-20,WIDTH/4,HEIGHT/8);
        EXIT.setText("EXIT");
        EXIT.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        EXIT.setBackground(new Color(130, 204, 217));
        EXIT.setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
        PANEL.add(EXIT);

        SINGLE.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                if(ae.getSource()==SINGLE) {
                    MainFrame.this.remove(PANEL);
                    PANEL = MODEPANEL();
                    MainFrame.this.add(PANEL);

                    MainFrame.this.revalidate();
                    MainFrame.this.repaint();
                }
            }
        });
        MULTI.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                if(ae.getSource()==MULTI) {
                    MainFrame.this.remove(PANEL);
                    PANEL = MODEPANEL();
                    MainFrame.this.add(PANEL);

                    MainFrame.this.revalidate();
                    MainFrame.this.repaint();
                }
            }
        });
        HELP.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                if(ae.getSource()==HELP) {
                    if (Desktop.isDesktopSupported()) {
                        try {
                            File myFile = new File( "rules.pdf");
                            Desktop.getDesktop().open(myFile);
                        } catch (IOException ex) {
                            new AnnounceDialog(MainFrame.this,true,"Error","File \"rules.pdf\" not found").setVisible(true);
                        }
                    }
                }
            }
        });
        EXIT.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                if(ae.getSource()==EXIT) {
                    System.exit(0);
                }
            }
        });

        return PANEL;

    }

    public JPanel MODEPANEL(){
        PANEL = new JPanel();
        PANEL.setSize(WIDTH,HEIGHT);
        PANEL.setLayout(null);
        PANEL.setOpaque(false);

        EASY.setBounds(WIDTH/2-WIDTH/4/2,HEIGHT/2-2*(HEIGHT/8)-20,WIDTH/4,HEIGHT/8);
        EASY.setText("EASY");
        EASY.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        EASY.setBackground(new Color(130, 204, 217));
        EASY.setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
        PANEL.add(EASY);

        MEDIUM.setBounds(WIDTH/2-WIDTH/4/2,HEIGHT/2-(HEIGHT/8),WIDTH/4,HEIGHT/8);
        MEDIUM.setText("MEDIUM");
        MEDIUM.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        MEDIUM.setBackground(new Color(130, 204, 217));
        MEDIUM.setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
        PANEL.add(MEDIUM);

        HARD.setBounds(WIDTH/2-WIDTH/4/2,HEIGHT/2+20,WIDTH/4,HEIGHT/8);
        HARD.setText("HARD");
        HARD.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        HARD.setBackground(new Color(130, 204, 217));
        HARD.setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
        PANEL.add(HARD);

        BACK.setBounds(WIDTH/2-WIDTH/4/2,HEIGHT/2+HEIGHT/4-20,WIDTH/4,HEIGHT/8);
        BACK.setText("BACK");
        BACK.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        BACK.setBackground(new Color(130, 204, 217));
        BACK.setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
        PANEL.add(BACK);

        EASY.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                if(ae.getSource()==EASY) {

                }
            }
        });
        MEDIUM.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                if(ae.getSource()==MEDIUM) {

                }
            }
        });
        HARD.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                if(ae.getSource()==HARD) {

                }
            }
        });
        BACK.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                if(ae.getSource()==BACK) {
                    MainFrame.this.remove(PANEL);
                    PANEL = MAINPANEL();
                    MainFrame.this.add(PANEL);

                    MainFrame.this.revalidate();
                    MainFrame.this.repaint();
                }
            }
        });

        return PANEL;

    }

    protected JLabel name=new javax.swing.JLabel();
    protected JLabel authors=new javax.swing.JLabel();
    protected JButton SINGLE=new javax.swing.JButton();
    protected JButton MULTI=new javax.swing.JButton();
    protected JButton HELP=new javax.swing.JButton();
    protected JButton EXIT=new javax.swing.JButton();
    protected JPanel PANEL;
    protected JButton EASY=new javax.swing.JButton();
    protected JButton MEDIUM=new javax.swing.JButton();
    protected JButton HARD=new javax.swing.JButton();
    protected JButton BACK=new javax.swing.JButton();
    private final int WIDTH=750;
    private final int HEIGHT=450;
}
