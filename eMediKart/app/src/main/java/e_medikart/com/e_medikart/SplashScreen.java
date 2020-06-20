package e_medikart.com.e_medikart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import e_medikart.com.e_medikart.utils.PreferenceManager;

/**
 * Created by Pujan on 08-02-2018.
 */

public class SplashScreen extends AppCompatActivity {
    final int SPLASH_SCREEN = 3000;
    PreferenceManager preferenceManager;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        Handler handler = new Handler();
        str = preferenceManager.getprefUserId(this);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("User Id",str);

                if(!str.isEmpty() &&  !str.equals("")) {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_SCREEN);
    }
}
