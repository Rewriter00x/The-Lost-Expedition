import java.util.*;

/**
 * Class token
 * @author Burliai Danylo
 * This class represents experience token
 */
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

    /**
     * Checks of this token contains specifies experience
     * @param token experience
     * @return true if contains, else false
     */
    public boolean checkToken(int token) {
        for (Integer i : tokens) if (i==token) return true;
        return false;
    }

    /**
     * Returns all tokens
     * @return all tokens
     */
    public ArrayList<Integer> getTokens() {return tokens;}

    public String toString() {
        String res ="Token for: ";
        for (Integer i : tokens) {
            switch (i) {
                case LEAF: res+= "leaf "; break;
                case TENT: res+= "tent "; break;
                case COMPASS: res+= "compass "; break;
            }
        }
        return res;
    }

    private ArrayList<Integer> tokens = new ArrayList<>();

    public static final int LEAF = 1;

    public static final int TENT = 2;

    public static final int COMPASS = 3;
}
