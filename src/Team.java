import java.util.*;

/**
 * Class MainFrame
 * @author Khoroshun Oleksandra
 * This class represents team of 3 heroes and their resourses
 */
public class Team {
    public Team(Hero one,Hero two,Hero three){
        this.TEAM=new ArrayList<>();
        TEAM.add(one);TEAM.add(two);TEAM.add(three);
        this.FOOD = 3;
        this.BULLETS = 3;
    }

    /**
     * Adds food
     */
    public void addFood(){
        FOOD++;
    }

    /**
     * Removes food
     */
    public void giveFood(){
        int x =FOOD-1;
        if(x>=0) FOOD--;
    }

    /**
     * Adds bullet
     */
    public void addBullet(){
       BULLETS++;
    }

    /**
     * Removes bullet
     */
    public void giveBullets(){
        int x =BULLETS-1;
        if(x>=0) BULLETS--;
    }

    /**
     * Adds token
     * @param token token to add
     */
    public void addToken(Token token) {TOKENS.add(token);}

    /**
     * Returns token
     * @param n number of token
     * @return token to return
     */
    public Token getToken(int n) {return TOKENS.get(n);}

    /**
     * Returns all tokens
     * @return arraylist of tokens
     */
    public ArrayList<Token> getTokens() {return TOKENS;}

    /**
     * Removes token
     * @param t token to remove
     * @return true if successfully removes, else false
     */
    public boolean removeToken(Token t) {return TOKENS.remove(t);}

    /**
     * Returns number of bullets
     * @return number of bullets
     */
    public int getBullets(){return BULLETS;}

    /**
     * Returns number of food
     * @return number of food
     */
    public int getFood(){return FOOD;}

    /**
     * Returns hero
     * @param n number of hero
     * @return hero
     */
    public Hero getHero(int n) {return TEAM.get(n);}

    /**
     * Returns hero
     * @param name name of hero
     * @return hero
     */
    public Hero getHero(String name) {
        for (Hero hero : TEAM) if (hero.getNAME().equals(name)) return hero;
        return null;
    }

    /**
     * Returns hero of special token experience
     * @param token token to search
     * @return hero
     */
    public Hero getHeroByToken(int token) {
        for (Hero hero : TEAM) if (hero.getEXPERIENCE()==token) return hero;
        return null;
    }

    /**
     * This method returns true if all alive heroes on max hp
     * @return true if all alive heroes on max hp, else false
     */
    public boolean allAliveOnMaxHP() {
        for (Hero hero : TEAM) if (!hero.hpIsMax() && hero.isAlive()) return false;
        return true;
    }

    /**
     * Searches for tokens containing special experience
     * @param token experience
     * @return true if at least one found
     */
    public boolean findToken(int token) {
        for (Token t : TOKENS) if (t.checkToken(token)) return true;
        return false;
    }

    private int BULLETS;
    private int FOOD;
    private ArrayList<Token> TOKENS = new ArrayList<>();
    private final ArrayList<Hero> TEAM;
}
