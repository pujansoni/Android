package e_medikart.com.e_medikart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import e_medikart.com.e_medikart.network.ApiManager;
import e_medikart.com.e_medikart.network.RequestCode;
import e_medikart.com.e_medikart.network.RequestParam;
import e_medikart.com.e_medikart.utils.PreferenceManager;
import hp.harsh.library.interfaces.OkHttpInterface;
import hp.harsh.library.okhttp.OkHttpRequest;

import static e_medikart.com.e_medikart.R.color.colorPrimary;

/**
 * Created by lenovo on 07-03-2018.
 */

public class ProductDisplay extends AppCompatActivity implements OkHttpInterface{
    private ImageView imageView;
    private TextView textViewName, textViewDescription, textViewSideEffects, textViewPrice, textViewManufacturer, textViewContent, textViewPregSafety;
    private Button addtocart;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_product);
        imageView = (ImageView) findViewById(R.id.img_view);
        textViewName = (TextView) findViewById(R.id.txt1_product_name);
        textViewDescription = (TextView) findViewById(R.id.txt1_product_description);
        textViewPrice = (TextView) findViewById(R.id.price);
        textViewSideEffects = (TextView) findViewById(R.id.sideeffects);
        addtocart = (Button) findViewById(R.id.cart_button);
        Intent i = getIntent();
        String strName = i.getStringExtra("NAME");
        String strDescription = i.getStringExtra("DESCRIPTION");
        String strImg = i.getStringExtra("PHOTO");
        String strPrice = i.getStringExtra("PRICE");
        String strSideeffects = i.getStringExtra("SIDEEFFECTS");
        final String strProductId = i.getStringExtra("PRODUCT_ID");


        final String prefUserId = PreferenceManager.getprefUserId(ProductDisplay.this);


        Glide.with(ProductDisplay.this).load(strImg).into(imageView);

        //imageView.setImageBitmap(bmp);
        textViewName.setText(strName);
        textViewDescription.setText(strDescription);
        textViewPrice.setText("PRICE : " + strPrice + "(per unit)");
        textViewSideEffects.setText("SIDE EFFECTS : " + strSideeffects);

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callforApi(prefUserId,strProductId);
                Intent i = new Intent(ProductDisplay.this,KartDisplay.class);
                startActivity(i);
            }
        });
    }


    private void callforApi(String prefUserId, String strProductId) {
        new OkHttpRequest(ProductDisplay.this,
                OkHttpRequest.Method.POST,
                ApiManager.ADD_TO_KART,
                RequestParam.addtokart("" + prefUserId, strProductId),
                RequestParam.getNull(),
                RequestCode.CODE_ADDTOKART,
                true, this);
    }



    @Override
    public void onOkHttpStart(int requestId) {

    }

    @Override
    public void onOkHttpSuccess(int requestId, int statusCode, String response) {
        Log.e("PRODUCT DISPLAY","response"+response);
        if(response != null)
        {
            try {
                JSONObject fetchResponse = new JSONObject(response);
                if(fetchResponse.has("success"))
                {
                    int code = fetchResponse.getInt("success");
                    if(code == 1)
                    {
                        Toast.makeText(this, "This item added to kart", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(this, "This item is already in kart", Toast.LENGTH_SHORT).show();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onOkHttpFailure(int requestId, int statusCode, String response, Throwable error) {

    }

    @Override
    public void onOkHttpFinish(int requestId) {

    }
}
