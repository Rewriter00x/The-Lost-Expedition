import java.util.ArrayList;

public class Team {
    public Team(int FOOD,Hero one,Hero two,Hero three){
        this.FOOD =FOOD;
        this.TEAM=new ArrayList<>();
        TEAM.add(one);TEAM.add(two);TEAM.add(three);
        this.BULLETS = 4;
    }
    public void addFood(int n){
        FOOD=FOOD+n;
    }
    public void giveFood(int n){
        int x =FOOD-n;
        if(x>=0) FOOD=FOOD-n;
    }
    public void addBullets(int n){
       BULLETS=BULLETS+n;
    }
    public void giveBullets(int n){
        int x =BULLETS-n;
        if(x>=0) BULLETS=BULLETS-n;
    }
    public void loseExperience(int n){
        for(int i=0;i<3;i++){
            if(TEAM.get(i).getEXPERIENCE()==n) {TEAM.get(i).damage(1);}
        }
    }
    public int getBullets(){return BULLETS;}
    public int getFood(){return FOOD;}

    private int BULLETS;
    private int FOOD;
    private final ArrayList<Hero> TEAM;
}
