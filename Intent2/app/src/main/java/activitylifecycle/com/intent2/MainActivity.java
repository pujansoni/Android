package activitylifecycle.com.intent2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button submit, view;
    EditText fname, lname, email;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        submit = (Button) findViewById(R.id.btn1);
        view = (Button) findViewById(R.id.btn2);
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        email = (EditText) findViewById(R.id.email);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);

        submit.setOnClickListener(this);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btn1:

                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(radioId);
                String strRadio = radioButton.getText().toString();

                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                i.putExtra("FirstName", fname.getText().toString());
                i.putExtra("LastName", lname.getText().toString());
                i.putExtra("Email", email.getText().toString());
                i.putExtra("RadioButton", strRadio);

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
