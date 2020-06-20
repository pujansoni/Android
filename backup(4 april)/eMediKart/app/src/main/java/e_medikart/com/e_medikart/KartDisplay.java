package e_medikart.com.e_medikart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

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

public class KartDisplay extends AppCompatActivity implements OkHttpInterface{

    private ListView listProduct;
    private TextView textViewNoProduct;
    private TextView grandTotal;
    String mStrPath;
    int grandtotal = 0;
    String TAG = "KartDisplay";
    private ProductAdapter productAdapter;
    ArrayList<ProductModel> productModelArrayList;
    private ProductModel productModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        setContentView(R.layout.display_kart);
        listProduct = (ListView) findViewById(R.id.view);
        grandTotal = (TextView) findViewById(R.id.final_price);

        final String prefUserId = PreferenceManager.getprefUserId(KartDisplay.this);
        loadcategory(prefUserId);
        super.onCreate(savedInstanceState);
    }
    private void setgrandtotal(int grandtotal)
    {
        grandTotal.setText(grandtotal);
    }
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
                                    productModel.setProductSideeffects(mProductSideeffects);
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
                            setgrandtotal(grandtotal);

                        }

                        else{
                        }
                        grandTotal.setText(grandtotal);
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
