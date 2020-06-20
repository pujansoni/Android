package layoutdesign.com.adapter_2_grid_view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    ArrayList<Flag> listItems;
    public static final String[] flag = new String[] {"Australia", "Brazil", "Canada", "India", "Japan", "Srilanka", "UK", "US", "Wales"};
    public static final Integer[] images = {
            R.drawable.australia_flag,
            R.drawable.brazil_flag,
            R.drawable.canada_flag,
            R.drawable.india_flag,
            R.drawable.japan_flag,
            R.drawable.srilanka_flag,
            R.drawable.uk_flag,
            R.drawable.us_flag,
            R.drawable.wales_flag
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.grid_view);
        listItems = new ArrayList<Flag>();
        for(int i = 0; i < flag.length; i++) {
            Flag item = new Flag(images[i], flag[i]);
            listItems.add(item);
        }

        CustomAdapter adapter = new CustomAdapter(this, listItems);
        gridView.setAdapter(adapter);
    }
}
