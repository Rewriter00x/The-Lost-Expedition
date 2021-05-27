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
    public void loseExperience(int n){
        for(int i=0;i<3;i++){
            if(TEAM.get(i).getEXPERIENCE()==n) {TEAM.get(i).damage();}
        }
    }
    public int getBullets(){return BULLETS;}
    public int getFood(){return FOOD;}

    private int BULLETS;
    private int FOOD;
    private final ArrayList<Hero> TEAM;
}
