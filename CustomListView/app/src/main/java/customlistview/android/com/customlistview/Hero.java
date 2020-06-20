package customlistview.android.com.customlistview;

/**
 * Created by Pujan on 24-02-2018.
 */
//This is our model class
public class Hero {
    int image;
    String name, team;
    public Hero(int image, String name, String team){
        this.image = image;
        this.name = name;
        this.team = team;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }
}
