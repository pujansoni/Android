package layoutdesign.com.spinner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    String item = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.spn1);
        Button button = (Button) findViewById(R.id.btn1);
        //Spinner click listener
        spinner.setOnItemSelectedListener(this);
        //Spinner Drop down elements
        final List<String> categories = new ArrayList<String>();
        categories.add("Select Language");
        categories.add("Android");
        categories.add("PHP");
        categories.add(".NET");
        categories.add("Python");
        categories.add("JAVA");
        //Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categories);
        //Drop down layout style-list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(item!="Select Language"){
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("arrayList", item);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //on selecting a spinner item
        item = parent.getItemAtPosition(position).toString();
        if (parent.getItemAtPosition(position).equals("Select Language")){

        }else {
            Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        }
        //Showing selected item
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
