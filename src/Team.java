import java.util.*;

public class Team {
    public Team(Hero one,Hero two,Hero three){
        this.TEAM=new ArrayList<>();
        TEAM.add(one);TEAM.add(two);TEAM.add(three);
        this.FOOD = 3;
        this.BULLETS = 3;
    }
    public void addFood(){
        FOOD++;
    }
    public void giveFood(){
        int x =FOOD-1;
        if(x>=0) FOOD--;
    }
    public void addBullet(){
       BULLETS++;
    }
    public void giveBullets(){
        int x =BULLETS-1;
        if(x>=0) BULLETS--;
    }

    public void addToken(Token token) {TOKENS.add(token);}
    public ArrayList<Token> getTokens() {return TOKENS;}

    public void loseExperience(int n){
        for(Hero hero : TEAM){
            if(hero.getEXPERIENCE()==n) {hero.damage();}
        }
    }
    public int getBullets(){return BULLETS;}
    public int getFood(){return FOOD;}

    public Hero getHero(int n) {return TEAM.get(n);}
    public Hero getHero(String name) {
        for (Hero hero : TEAM) if (hero.getNAME().equals(name)) return hero;
        return null;
    }

    public boolean allOnMaxHP() {
        for (Hero hero : TEAM) if (!hero.hpIsMax()) return false;
        return true;
    }

    public boolean allOnMaxHPorDead() {
        for (Hero hero : TEAM) if (!hero.hpIsMax() && hero.isAlive()) return false;
        return true;
    }

    private int BULLETS;
    private int FOOD;
    private ArrayList<Token> TOKENS = new ArrayList<>();
    private final ArrayList<Hero> TEAM;
}
