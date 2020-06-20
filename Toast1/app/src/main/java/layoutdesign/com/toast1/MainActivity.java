package layoutdesign.com.toast1;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button submit;
    EditText fname, lname, email;
    RadioGroup radioGroup;
    RadioButton radioButton;
    CheckBox checkBox;
    Button  btnAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          clickButton();
        btnAlert = (Button) findViewById(R.id.btn_alert);
        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("mainActivity","Alert Click");

                AlertDialog.Builder alertbuider = new AlertDialog.Builder(MainActivity.this);

                alertbuider.setTitle("Alert Dialog");
                alertbuider.setMessage("do you want to exist??");
                alertbuider.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(MainActivity.this, "yes is clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                alertbuider.show();

            }
        });




       // Toast.makeText(MainActivity.this, "FirstName"+edit.getText.toString()+"LastName"+edit.getTe, Toast.LENGTH_LONG).show();
    }
    public void clickButton() {
        fname = (EditText) findViewById(R.id.fname);
        lname = (EditText) findViewById(R.id.lname);
        email = (EditText) findViewById(R.id.email);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        checkBox = (CheckBox) findViewById(R.id.chk);
        submit = (Button) findViewById(R.id.btn1);


       /* submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(checkBox.isChecked()){
                            int radioId = radioGroup.getCheckedRadioButtonId();
                            radioButton = (RadioButton) findViewById(radioId);
                            String strRadio = radioButton.getText().toString();
                            Log.e("mainActivity","strRadio"+strRadio);
                         Toast.makeText(MainActivity.this,"FirstName "+fname.getText().toString()+"\nLastName "+lname.getText().toString()+"\nEmail "+email.getText().toString()+"\nGender"+strRadio,Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this,"Please agree to terms and conditions",Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
*/
    }
}
