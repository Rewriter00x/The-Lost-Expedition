import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WinDialog extends JDialog {
    public WinDialog(Frame frame, boolean modal, String title, String labelText) {
        super(frame, modal);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setLayout(new GridLayout(2,1));
        setTitle(title);
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 26));
        JButton button = new JButton("OK");
        button.setSize(200,100);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {System.exit(0);}
        });
        add(label);
        add(button);
        pack();
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width-getSize().width)/2,(Toolkit.getDefaultToolkit().getScreenSize().height-getSize().height)/2);
    }
}
