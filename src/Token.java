import java.util.ArrayList;

public class Token {

    public Token(ArrayList<Integer> tokens) {
        this.tokens=tokens;
    }

    public boolean checkToken(int n) {
        for (Integer i : tokens) if (i==n) return true;
        return false;
    }

    private ArrayList<Integer> tokens;

    public static final int LEAF = 1;

    public static final int TENT = 2;

    public static final int COMPASS = 3;
}
