package e_medikart.com.e_medikart.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import e_medikart.com.e_medikart.ProductAdapter;
import e_medikart.com.e_medikart.ProductModel;
import e_medikart.com.e_medikart.R;
import e_medikart.com.e_medikart.network.ApiManager;
import e_medikart.com.e_medikart.network.RequestCode;
import e_medikart.com.e_medikart.network.RequestParam;
import hp.harsh.library.interfaces.OkHttpInterface;
import hp.harsh.library.okhttp.OkHttpRequest;

/**
 * Created by lenovo on 3/25/2018.
 */

public class CategoryProductFragment extends AppCompatActivity implements OkHttpInterface {

    private ListView listProduct;
    private TextView textViewNoProduct;
    private SearchView searchView;
    String mStrPath;
    String TAG = "CategoryProductFragment";
    private ProductAdapter productAdapter;
    ArrayList<ProductModel> productModelArrayList;
    private ProductModel productModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_product);
        init();
    }


    private void init() {

        Intent i = getIntent();
        String manufacturer = i.getStringExtra("MENU_ID");
        Log.e(TAG, "++++manufacturer++++" + manufacturer);

        listProduct = (ListView) findViewById(R.id.list_product);
        textViewNoProduct = (TextView) findViewById(R.id.txt_no_product);
        searchView = (SearchView) findViewById(R.id.search);
        SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<ProductModel> filteredModelList = filter(productModelArrayList, newText);

                productAdapter.setFilter(filteredModelList);
                return true;
            }
        };
        searchView.setOnQueryTextListener(onQueryTextListener);
        loadcategory(manufacturer);
    }

    private void loadcategory(String manufacturer) {

        new OkHttpRequest(this,
                OkHttpRequest.Method.POST,
                ApiManager.DISPLAY_PRODUCT_MANUFACTURER,
                RequestParam.manufacturer(manufacturer),
                RequestParam.getNull(),
                RequestCode.CODE_USER_PRODUCT,
                true,
                this);
    }

    @Override
    public void onOkHttpStart(int requestId) {

    }

    @Override
    public void onOkHttpSuccess(int requestId, int statusCode, String response) {
        Log.e(TAG, "onLoopjSuccess==1" + response);
        if (response != null) {
            try {
                JSONObject jsonFetchAllProduct = new JSONObject(response);
                if (jsonFetchAllProduct.has("success")) {
                    int code = jsonFetchAllProduct.getInt("success");
                    Log.e(TAG, "code==" + code);
                    if (code == 1) {
                        Log.e(TAG, "code==1" + code);
                        productModelArrayList = new ArrayList<>();
                        if (jsonFetchAllProduct.has("path")) {
                            mStrPath = jsonFetchAllProduct.getString("path");
                        }
                        if (jsonFetchAllProduct.has("subcategory")) {
                            JSONArray jsonArrayData = jsonFetchAllProduct.getJSONArray("subcategory");
                            Log.e(TAG, "" + jsonArrayData.length());
                            for (int i = 0; i < jsonArrayData.length(); i++) {
                                Log.e(TAG, "" + i + "==========");
                                productModel = new ProductModel();
                                JSONObject dataObject = jsonArrayData.getJSONObject(i);
                                Log.i("contactsObject", "++" + dataObject);
                                if (dataObject.has("product_photo")) {
                                    String mProductPhoto = String.valueOf(dataObject.getString("product_photo"));
                                    Log.e(TAG, "Product Photo == " + mProductPhoto);
                                    productModel.setProductPhoto(mProductPhoto);
                                }
                                if (dataObject.has("product_name")) {
                                    String mProductName = String.valueOf(dataObject.getString("product_name"));
                                    Log.e(TAG, "Product Name == " + mProductName);
                                    productModel.setProductName(mProductName);
                                }
                                if (dataObject.has("product_use")) {
                                    String mProductUse = String.valueOf(dataObject.getString("product_use"));
                                    Log.e(TAG, "Product Use == " + mProductUse);
                                    productModel.setProductUse(mProductUse);
                                }
                                if (dataObject.has("product_sideeffects")) {
                                    String mProductSideeffects = String.valueOf(dataObject.getString("product_sideeffects"));
                                    Log.e(TAG, "Product Use == " + mProductSideeffects);
                                    productModel.setProductUse(mProductSideeffects);
                                }
                                if (dataObject.has("product_content")) {
                                    String mProductContent = String.valueOf(dataObject.getString("product_content"));
                                    Log.e(TAG, "Product Use == " + mProductContent);
                                    productModel.setProductUse(mProductContent);
                                }
                                if (dataObject.has("product_pregsafety")) {
                                    String mProductPregsafety = String.valueOf(dataObject.getString("product_pregsafety"));
                                    Log.e(TAG, "Product Use == " + mProductPregsafety);
                                    productModel.setProductUse(mProductPregsafety);
                                }
                                productModelArrayList.add(productModel);
                            }
                        }
                    }
                }
                this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (productModelArrayList.size() > 0) {
                            for (int i = 0; i < productModelArrayList.size(); i++) {
                                Log.e("Name" + i + ": ", productModelArrayList.get(i).getProductName());
                                listProduct.setAdapter(productAdapter = new ProductAdapter(CategoryProductFragment.this, productModelArrayList));
                            }
                            textViewNoProduct.setVisibility(View.GONE);
                        } else {
                        }
                    }
                });
            } catch (Exception e) {
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

    private List<ProductModel> filter(List<ProductModel> models, String query) {
        query = query.toLowerCase();
        final List<ProductModel> filteredModelList = new ArrayList<>();
        for (ProductModel model : models) {
            final String text = model.getProductName().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}
