package e_medikart.com.e_medikart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import e_medikart.com.e_medikart.utils.PreferenceManager;

/**
 * Created by lenovo on 08-02-2018.
 */

public class SplashScreen extends AppCompatActivity {
    final int SPLASH_SCREEN = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        final String preferencemanagerId = PreferenceManager.getprefUserId(this);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!preferencemanagerId.equals("") && !preferencemanagerId.isEmpty()){

                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                }else {
                    Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_SCREEN);
    }
}
