/**
 * Class hero
 * @author Khoroshun Oleksandra
 * This class represents heroes with their maximum hp and experience
 */
public class Hero {
    public Hero(int EXPERIENCE,String NAME){
        this.EXPERIENCE=EXPERIENCE;
        this.NAME=NAME;
        this.HP=maxHP;
    }

    /**
     * Returns true if hero is alive
     * @return true if hero is alive
     */
    public boolean isAlive(){
        if (HP!=0) return true;
        return false;
    }

    /**
     * Returns true if hero's hp is maxed
     * @return true if hero's hp is maxed
     */
    public boolean hpIsMax(){
        if (HP==maxHP) return true;
        return false;
    }

    /**
     * Heals hero on one hp
     */
    public void heal(){
        if(isAlive()) {
            int x = HP+1;
            if (x <= maxHP) HP++;
            else HP = maxHP;
        }
    }

    /**
     * Damages hero on one hp
     */
    public void damage(){
        int x =HP-1;
        if(x>=0) HP--;
        else HP=0;
    }

    /**
     * Kills hero
     */
    public void die(){HP=0;}

    /**
     * Sets max hp for all heroes
     * @param maxHP max hp to set
     */
    public static void setMaxHP(int maxHP) {Hero.maxHP =maxHP;}

    /**
     * Returns max hp
     * @return max hp
     */
    public static int getMaxHP() {return maxHP;}

    /**
     * Set's name of hero
     * @param NAME name to set
     */
    public void setNAME(String NAME) {this.NAME=NAME;}

    /**
     * Returns hero's experience
     * @return hero's experience
     */
    public int getEXPERIENCE(){return EXPERIENCE;}

    /**
     * Returns hero's hp
     * @return hero's hp
     */
    public int getHP(){return HP;}

    /**
     * Returns hero's name
     * @return hero's name
     */
    public String getNAME() {return NAME;}

    private String NAME;
    private final int EXPERIENCE;
    private int HP;

    public static final int LEAF = 1;
    public static final int TENT = 2;
    public static final int COMPASS = 3;

    private static int maxHP = 4;
}
