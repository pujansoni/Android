package layoutdesign.com.adaptar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    String animalList[] = {"Bug","Lion","Tiger","Pigeon"};
    Integer ImageName[] = {
            R.drawable.bug,
            R.drawable.lion,
            R.drawable.tiger,
            R.drawable.pigeon
    };
    ListArrayAdapter listArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.lst_view);
        listArrayAdapter = new ListArrayAdapter(MainActivity.this, animalList, ImageName);
        listView.setAdapter(listArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(MainActivity.this, animalList[position], Toast.LENGTH_LONG).show();
            }
        });
    }
}
