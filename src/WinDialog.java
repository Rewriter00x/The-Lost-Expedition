import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WinDialog extends JDialog {
    public WinDialog(Frame frame, boolean modal, String title, String labelText) {
        super(frame, modal);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("glass.png"));
            this.setIconImage(img);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            img = ImageIO.read(new File("jungle2.png"));
            this.setContentPane(new JLabel(new ImageIcon(img)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setLayout(null);
        setTitle(title);
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("OCR A Extended", Font.PLAIN, 15));
        label.setBounds(28,25,150,40);
        JButton button = new JButton("exit");
        button.setBounds(0,80,82,20);
        button.setFont(new Font("OCR A Extended", Font.PLAIN, 10));
        button.setBackground(new Color(130, 204, 217));
        button.setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        JButton button1 = new JButton("play");
        button1.setBounds(82,80,83,20);
        button1.setFont(new Font("OCR A Extended", Font.PLAIN, 10));
        button1.setBackground(new Color(130, 204, 217));
        button1.setBorder(BorderFactory.createLineBorder(new Color(6, 63, 94)));
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                dispose();
                MainFrame frame = new MainFrame();
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
                frame.setVisible(true);
            }
        });
        add(label);
        add(button);
        add(button1);
        pack();
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-getSize().width)/2,(Toolkit.getDefaultToolkit().getScreenSize().height-getSize().height)/2);
    }
}
