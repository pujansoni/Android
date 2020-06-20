package sharedpreference.android.com.sharedpreference;
//We have a class named SharedPreferences in android. It allows us a framework that helps in saving and retrieving persistent key-value pairs of primitive datatypes. So remember the limitations of using Android SharedPreferences,  it only allows primitive data types. The values are saved even when the application is killed.
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

//We have the following steps to save values in sharedpreference
//a) get the sharedpreferences
//b) initialize the editor
//c) put the values
//d) apply the changes
//reading values back is very easy. we have 2 steps
//a) get sharedpreferences
//b) get values
//values are overwritten when we save it again. So to modify a value simply save it again
//deletion can be of 2 types
//a) delete a specific value (By calling the remove() method and passing the key that is to be removed and then apply() method)
//b) delete everything (By simply calling the clear() method and then apply() method)
public class MainActivity extends AppCompatActivity {

    private static final String SHARED_PREF_NAME = "mysharedpref";
    private static final String KEY_NAME = "keyname";
    EditText editText;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editTextName);
        textView = findViewById(R.id.textView);
        findViewById(R.id.buttonSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveName();
                displayName();
            }
        });
    }

    private void saveName(){
        String name = editText.getText().toString();
        if(name.isEmpty()){
            editText.setError("Name required");
            editText.requestFocus();
            return;
        }
        SharedPreferences sp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(KEY_NAME, name);
        editor.apply();
        editText.setText("");
    }

    private  void displayName(){
        SharedPreferences sp = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String name = sp.getString(KEY_NAME, null);
        if(name!=null){
            textView.setText("Welcome "+name);
        }
    }
}
