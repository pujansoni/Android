package layoutdesign.com.listener_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements /*View.OnClickListener,*/View.OnTouchListener {
    TextView txt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = (TextView)findViewById(R.id.txt_click);
/*
        txt1.setOnClickListener(this);
*/
        txt1.setOnTouchListener(this);
    }
    /*@Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txt_click:
                txt1.setTextColor(getResources().getColor(R.color.colorAccent));
                break;
        }
    }*/
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                txt1.setTextColor(getResources().getColor(R.color.colorAccent));
                txt1.setBackgroundColor(getResources().getColor(R.color.colorOrange));
                break;
            case MotionEvent.ACTION_UP:
                txt1.setTextColor(getResources().getColor(R.color.colorGreen));
                txt1.setBackgroundColor(getResources().getColor(R.color.colorSky));
                break;
        }
        return true;
    }
}
