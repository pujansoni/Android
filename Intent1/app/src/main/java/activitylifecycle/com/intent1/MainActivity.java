package activitylifecycle.com.intent1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button submit, browser;
    EditText name, number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.name);
        number = (EditText) findViewById(R.id.number);
        submit = (Button) findViewById(R.id.btn1);
        browser = (Button) findViewById(R.id.btn2);

        submit.setOnClickListener(this);
        browser.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn1:


                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("Name", name.getText().toString());
                i.putExtra("Number", number.getText().toString());
                startActivity(i);

                break;
            case R.id.btn2:

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.google.co.in"));
                startActivity(intent);

                break;
        }

    }
}
