public class Hero {
    public Hero(int EXPERIENCE,String NAME){
        this.EXPERIENCE=EXPERIENCE;
        this.NAME=NAME;
        this.HP=4;
    }
    public boolean isAlive(){
        if (HP!=0) return true;
        return false;
    }
    public boolean hpIsMax(){
        if (HP==4) return true;
        return false;
    }
    public void heal(){
        if(isAlive()) {
            int x = HP+1;
            if (x <= 4) HP++;
            else HP = 4;
        }
    }
    public void damage(){
        int x =HP-1;
        if(x>=0) HP--;
        else HP=0;
    }
    public void die(){HP=0;}

    public int getEXPERIENCE(){return EXPERIENCE;}
    public int getHP(){return HP;}
    private final String NAME;
    private final int EXPERIENCE;
    private int HP;

    public static final int LEAF = 1;
    public static final int TENT = 2;
    public static final int COMPASS = 3;
}
