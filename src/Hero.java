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
    public void heal(int n){
        if(isAlive()) {
            int x = HP + n;
            if (x <= 4) HP = HP + n;
            else HP = 4;
        }
    }
    public void damage(int n){
        int x =HP-n;
        if(x>=0) HP=HP-n;
        else HP=0;
    }
    public void die(){HP=0;}

    public int getEXPERIENCE(){return EXPERIENCE;}
    public int getHP(){return HP;}
    private final String NAME;
    private final int EXPERIENCE;
    private int HP;
}
