package activitylifecycle.com.intent2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Pujan on 19-01-2018.
 */

public class SecondActivity extends AppCompatActivity {
    TextView text;
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent i = getIntent();
        String firstName = i.getStringExtra("FirstName");
        String lastName = i.getStringExtra("LastName");
        String email = i.getStringExtra("Email");
        String gender = i.getStringExtra("RadioButton");
        String h1 = "Name: "+firstName+" "+lastName+"\nEmail: "+email+"\nGender: "+gender;
        text = (TextView) findViewById(R.id.txt1);
        text.setText(h1);
    }
}
