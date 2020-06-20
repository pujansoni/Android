package firebaseauth.android.com.recyclerviewproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//add the recycler view and the card view dependencies in our project
//for the items of the recycler view we will create the card view so we created another separate layout file ie list_item in this case
//every item of the recycler view will be inside the card view. So we will create the layout inside the card view
//And to get the actual data for the list_item we will create the model class. Model class are always created to get the data
//To bind the data to the recycler view we will need an adapter
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);//This line means that every item of the recycler view has a fixed size
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();
        for(int i=0 ; i<=10; i++){
            ListItem listItem = new ListItem("heading "+ (i+1), "description");
            listItems.add(listItem);
        }
        adapter = new MyAdapter(listItems, this);
        recyclerView.setAdapter(adapter);
    }
}
