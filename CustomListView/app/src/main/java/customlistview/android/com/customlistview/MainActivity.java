package customlistview.android.com.customlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //a list of type hero for holding list item
    List<Hero> heroList;
    //the listview
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializing objects
        heroList = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);
        //adding some values to our list
        heroList.add(new Hero(R.drawable.spiderman, "Spiderman", "Avengers"));
        heroList.add(new Hero(R.drawable.superman, "Superman", "Injustice Gang"));
        heroList.add(new Hero(R.drawable.joker, "Joker", "Injustice Gang"));
        heroList.add(new Hero(R.drawable.captain_america, "Captain America", "Avengers"));
        heroList.add(new Hero(R.drawable.batman, "Batman", "Injustice Gang"));
        heroList.add(new Hero(R.drawable.ironman, "Ironman", "Avengers"));
        //creating the adapter
        MyListAdapter adapter = new MyListAdapter(this, R.layout.my_custom_list, heroList);
        //attaching adapter to the listview
        listView.setAdapter(adapter);
    }
}
