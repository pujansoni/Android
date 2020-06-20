package e_medikart.com.e_medikart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.util.ArrayList;

import e_medikart.com.e_medikart.network.ApiManager;
import e_medikart.com.e_medikart.network.RequestCode;
import e_medikart.com.e_medikart.network.RequestParam;
import e_medikart.com.e_medikart.utils.PreferenceManager;
import hp.harsh.library.interfaces.OkHttpInterface;
import hp.harsh.library.okhttp.OkHttpRequest;

/**
 * Created by lenovo on 4/2/2018.
 */

public class KartDisplay extends AppCompatActivity implements OkHttpInterface,View.OnClickListener{
    //paypal code start
    //Paypal intent request code to track onActivityResult method
    public static final int PAYPAL_REQUEST_CODE = 123;
    //Paypal Configuration Object
    private static PayPalConfiguration config = new PayPalConfiguration()
            // Start with mock environment.  When ready, switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(PayPalConfig.PAYPAL_CLIENT_ID);
    //paypal code end
    private ListView listProduct;
    private TextView grandTotal;
    private Button checkout;
    String mStrPath;
    int grandtotal = 0;
    String TAG = "KartDisplay";
    private ProductAdapter productAdapter;
    ArrayList<ProductModel> productModelArrayList;
    private ProductModel productModel;
    private String paymentAmount;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_kart);
        listProduct = (ListView) findViewById(R.id.view);
        grandTotal = (TextView) findViewById(R.id.textViewTotal);
        checkout = (Button) findViewById(R.id.checkout_button);
        checkout.setOnClickListener(this);
        final String prefUserId = PreferenceManager.getprefUserId(KartDisplay.this);
        loadcategory(prefUserId);
        //paypal code start
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);
        //paypal code end
    }
    @Override
    public void onClick(View view) {
        //paypal code start
        getPayment();
        //paypal code end
    }
    //paypal code start
    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    private void getPayment() {
        //String total = grandTotal.getText().toString();
        //Creating a paypalpayment
        PayPalPayment payment = new PayPalPayment(new BigDecimal(Integer.valueOf(grandtotal)), "USD", "eMediKart Fee",
                PayPalPayment.PAYMENT_INTENT_SALE);
        //Creating Paypal Payment activity intent
        Intent intent = new Intent(this, PaymentActivity.class);
        //putting the paypal configuration to the intent
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        //Puting paypal payment to the intent
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        //Starting the intent activity for result
        //the request code will be used on the method onActivityResult
        startActivityForResult(intent, PAYPAL_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        paymentAmount = grandTotal.getText().toString();
        //If the result is from paypal
        if (requestCode == PAYPAL_REQUEST_CODE) {

            //If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {
                //Getting the payment confirmation
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                //if confirmation is not null
                if (confirm != null) {
                    try {
                        //Getting the payment details
                        String paymentDetails = confirm.toJSONObject().toString(4);
                        Log.i("paymentExample", paymentDetails);

                        //Starting a new activity for the payment details and also putting the payment details with intent
                        startActivity(new Intent(this, ConfirmationActivity.class)
                                .putExtra("PaymentDetails", paymentDetails)
                                .putExtra("PaymentAmount", paymentAmount));

                    } catch (JSONException e) {
                        Log.e("paymentExample", "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i("paymentExample", "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }
    }

    //paypal code end

    private void loadcategory(String prefUserId) {
        new OkHttpRequest(KartDisplay.this,
                OkHttpRequest.Method.POST,
                ApiManager.DISPLAY_KART,
                RequestParam.displaykart("" + prefUserId),
                RequestParam.getNull(),
                RequestCode.CODE_DISPLAYKART,
                true, (OkHttpInterface) this);
    }

    @Override
    public void onOkHttpStart(int requestId) {

    }

    @Override
    public void onOkHttpSuccess(int requestId, int statusCode, String response) {
        Log.e(TAG, "onLoopjSuccess==1" + response);
        if(response != null){
            try{
                JSONObject jsonFetchAllProduct = new JSONObject(response);
                if(jsonFetchAllProduct.has("success")){
                    int code = jsonFetchAllProduct.getInt("success");
                    Log.e(TAG, "code==" + code);
                    if(code == 1){
                        Log.e(TAG, "code==1" + code);
                        productModelArrayList = new ArrayList<>();
                        if(jsonFetchAllProduct.has("path")){
                            mStrPath = jsonFetchAllProduct.getString("path");
                        }
                        if(jsonFetchAllProduct.has("subcategory")){
                            JSONArray jsonArrayData = jsonFetchAllProduct.getJSONArray("subcategory");
                            Log.e(TAG, "" + jsonArrayData.length());
                            for(int i = 0; i < jsonArrayData.length(); i++){
                                Log.e(TAG, "" + i + "==========");
                                productModel = new ProductModel();
                                JSONObject dataObject = jsonArrayData.getJSONObject(i);
                                Log.i("contactsObject", "++" + dataObject);
                                if(dataObject.has("product_id")) {
                                    String mProductId = String.valueOf(dataObject.getString("product_id"));
                                    Log.e(TAG, "Product Id == " + mProductId);
                                    productModel.setProductId(mProductId);
                                }
                                if (dataObject.has("manufacturer_name")) {
                                    String mManufacturerName = String.valueOf(dataObject.getString("manufacturer_name"));
                                    Log.e(TAG, "Manufacturer Name== " + mManufacturerName);
                                    productModel.setManufacturerName(mManufacturerName);
                                }
                                if(dataObject.has("product_photo")) {
                                    String mProductPhoto = String.valueOf(dataObject.getString("product_photo"));
                                    Log.e(TAG, "Product Photo == " + mProductPhoto);
                                    productModel.setProductPhoto(mProductPhoto);
                                }
                                if(dataObject.has("product_name")) {
                                    String mProductName = String.valueOf(dataObject.getString("product_name"));
                                    Log.e(TAG, "Product Name == " + mProductName);
                                    productModel.setProductName(mProductName);
                                }
                                if(dataObject.has("product_use")) {
                                    String mProductUse = String.valueOf(dataObject.getString("product_use"));
                                    Log.e(TAG, "Product Use == " + mProductUse);
                                    productModel.setProductUse(mProductUse);
                                }
                                if(dataObject.has("product_sideeffects")) {
                                    String mProductSideeffects = String.valueOf(dataObject.getString("product_sideeffects"));
                                    Log.e(TAG, "Product Sideeffects == " + mProductSideeffects);
                                    productModel.setProductSideEffects(mProductSideeffects);
                                }
                                if(dataObject.has("product_content")) {
                                    String mProductContent = String.valueOf(dataObject.getString("product_content"));
                                    Log.e(TAG, "Product Content == " + mProductContent);
                                    productModel.setProductContent(mProductContent);
                                }
                                if(dataObject.has("product_pregsafety")) {
                                    String mProductPregsafety = String.valueOf(dataObject.getString("product_pregsafety"));
                                    Log.e(TAG, "Product Pregsafety == " + mProductPregsafety);
                                    productModel.setProductPregsafety(mProductPregsafety);
                                }
                                if(dataObject.has("product_price"))
                                {
                                    String mProductPrice = String.valueOf(dataObject.getString("product_price"));
                                    Log.e(TAG,"Product Price == " + mProductPrice);
                                    productModel.setProductPrice(mProductPrice);
                                }
                                if (dataObject.has("manufacturer_name")) {
                                    String mManufacturerName = String.valueOf(dataObject.getString("manufacturer_name"));
                                    Log.e(TAG, "Manufacturer Name== " + mManufacturerName);
                                    productModel.setManufacturerName(mManufacturerName);
                                }
                                productModelArrayList.add(productModel);
                            }
                        }
                    }
                }
                this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(productModelArrayList.size() > 0){
                            for(int i = 0; i < productModelArrayList.size(); i++){
                                Log.e("Name" + i + ": ", productModelArrayList.get(i).getProductName());
                                listProduct.setAdapter(productAdapter = new ProductAdapter(KartDisplay.this, productModelArrayList));
                                grandtotal = grandtotal + Integer.parseInt(productModelArrayList.get(i).getProductPrice());
                            }
                            //textViewNoProduct.setVisibility(View.GONE);
                            Log.e("KartDisplay","grand total is :"+grandtotal);
                            grandTotal.setText("GRAND TOTAL:\t"+grandtotal);
                        }

                        else{
                        }
                    }
                });
            } catch (Exception e){
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
