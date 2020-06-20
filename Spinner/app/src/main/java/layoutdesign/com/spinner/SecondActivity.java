package layoutdesign.com.spinner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Pujan on 01-02-2018.
 */

class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        TextView textView = (TextView) findViewById(R.id.txt2);
        Intent i = getIntent();
        String language = i.getStringExtra("arrayList");
        textView.setText("Selected language is: " + language);
    }
}
