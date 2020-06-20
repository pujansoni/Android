package layoutdesign.com.listener_1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by Pujan on 26-01-2018.
 */

public class SplashScreen extends AppCompatActivity {
    final int SPLASH_SCREEN = 3000;//standard is to set the splash screen ie the welcome activity for 3 seconds
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        //Here whenever we are using thread i.e runnable method we will use handler along with it
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Here we are using intent because we want to go from one screen to another
                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                //here we are using the finish method because we want to remove the welcome activity from the stack so when we press the back button in the main activity it does not display the splash screen again
                finish();
            }
        },SPLASH_SCREEN);
    }
}
