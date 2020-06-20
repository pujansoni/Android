package layoutdesign.com.adapter_base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    List<ListModel> listItems;

    public static final String[] lang = new String[] {"Android", ".Net", "PHP", "Python", "Database"};

    public static final Integer[] images = {
            R.drawable.android_icon,
            R.drawable.net_icon,
            R.drawable.php_icon,
            R.drawable.python_icon,
            R.drawable.database_icon
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lst_view);

        listItems = new ArrayList<ListModel>();
        for(int i = 0; i < lang.length; i++) {
            ListModel item = new ListModel(images[i], lang[i]);
            listItems.add(item);
        }

        ListBaseAdapter adapter = new ListBaseAdapter(this, listItems);
        listView.setAdapter(adapter);
    }
}
