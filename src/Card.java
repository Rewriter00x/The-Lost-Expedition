import javax.imageio.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;

public class Card {

    public Card(int number, ArrayList<Effect> yellowEffects, ArrayList<ArrayList<Effect>> redEffects, ArrayList<ArrayList<Effect>> blueEffects) throws IOException {
        this.number=number;
        this.yellowEffects=yellowEffects;
        this.redEffects=redEffects;
        this.blueEffects=blueEffects;
        cardImage = ImageIO.read(new File("Cards/card"+number+".png"));
    }

    public void playCard() {
        if (yellowEffects!=null) for (Effect e : yellowEffects) e.doEffect();
        int n=0;// get number of red line
        if (redEffects!=null) for (Effect e: redEffects.get(0)) e.doEffect();
        if (blueEffects!=null) {
            n=0; // get number of blue line (n==-1 is no blue effect)
            if (n!=-1) for (Effect e: blueEffects.get(0)) e.doEffect();
        }
    }

    public int getNumber() { return number; }

    private final int number;
    private final ArrayList<Effect> yellowEffects;
    private final ArrayList<ArrayList<Effect>> redEffects,blueEffects;
    private final BufferedImage cardImage;
}
