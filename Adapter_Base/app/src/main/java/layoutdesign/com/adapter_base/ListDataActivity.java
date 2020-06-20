package layoutdesign.com.adapter_base;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Pujan on 30-01-2018.
 */

class ListDataActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical);
        imageView = (ImageView) findViewById(R.id.img_view);
        textView = (TextView) findViewById(R.id.txt_view);
        Intent i = getIntent();
        String strLang = i.getStringExtra("Lang");
        byte[] byteArray = i.getByteArrayExtra("Picture");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray,0, byteArray.length);
        imageView.setImageBitmap(bmp);
        textView.setText(strLang);
    }
}
