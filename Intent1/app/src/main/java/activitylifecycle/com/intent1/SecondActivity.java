package activitylifecycle.com.intent1;

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
        String name = i.getStringExtra("Name");
        String number = i.getStringExtra("Number");
        String h1 = "Your name is "+name+" & number is "+number;
        text = (TextView) findViewById(R.id.txt1);
        text.setText(h1);
    }
}
