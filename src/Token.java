import java.util.*;

public class Token {

    public Token(int token) {
        tokens.add(token);
    }

    public Token(int token1, int token2) {
        tokens.add(token1);
        tokens.add(token2);
    }

    public Token(int token1, int token2, int token3) {
        tokens.add(token1);
        tokens.add(token2);
        tokens.add(token3);
    }

    public boolean checkToken(int n) {
        for (Integer i : tokens) if (i==n) return true;
        return false;
    }

    private ArrayList<Integer> tokens = new ArrayList<>();

    public static final int LEAF = 1;

    public static final int TENT = 2;

    public static final int COMPASS = 3;
}
