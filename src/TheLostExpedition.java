import java.awt.*;

public class TheLostExpedition {
    public static void main(String[] args) {
        /*MainFrame frame = new MainFrame();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);*/
        new GameFrame(1,1).setVisible(true);
    }
}
