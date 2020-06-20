package e_medikart.com.e_medikart.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import e_medikart.com.e_medikart.CustomSwipeAdapter;
import e_medikart.com.e_medikart.HomeAdapter;
import e_medikart.com.e_medikart.ProductAdapter;
import e_medikart.com.e_medikart.ProductModel;
import e_medikart.com.e_medikart.R;
import e_medikart.com.e_medikart.network.ApiManager;
import e_medikart.com.e_medikart.network.RequestCode;
import hp.harsh.library.interfaces.OkHttpInterface;
import hp.harsh.library.okhttp.OkHttpRequest;

/**
 * Created by Pujan on 09-02-2018.
 */

public class Home extends Fragment implements OkHttpInterface{
    ArrayList<ProductModel> productModelArrayList;
    GridView gridView;
    HomeAdapter homeAdapter;
    String mStrPath;
    private ProductModel productModel;
    ViewPager viewPager;
    CustomSwipeAdapter adapter;
    String TAG = "HomeFragment";
    private static int currentPage=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        init(rootView);
        return rootView;
    }

    private void init(View rootView){
        viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        adapter = new CustomSwipeAdapter(rootView.getContext());
        viewPager.setAdapter(adapter);
        gridView = (GridView) rootView.findViewById(R.id.simpleGridView);
        loadcategory();
    }

    private void loadcategory() {
        new OkHttpRequest(getActivity(), OkHttpRequest.Method.GET, ApiManager.DISPLAY_PRODUCT, RequestCode.CODE_USER_PRODUCT, true, this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");
    }

    @Override
    public void onOkHttpStart(int requestId) {
        Log.e(TAG, "onLoopjStart==1" + requestId);
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
                                if(dataObject.has("product_id")) {
                                    String mProductId = String.valueOf(dataObject.getString("product_id"));
                                    Log.e(TAG, "Product Id == " + mProductId);
                                    productModel.setProductId(mProductId);
                                }
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
                                    String mProductSideEffects = String.valueOf(dataObject.getString("product_sideeffects"));
                                    Log.e(TAG, "Product SideEffects == " + mProductSideEffects);
                                    productModel.setProductSideEffects(mProductSideEffects);
                                }
                                if (dataObject.has("product_content")) {
                                    String mProductContent = String.valueOf(dataObject.getString("product_content"));
                                    Log.e(TAG, "Product Content == " + mProductContent);
                                    productModel.setProductContent(mProductContent);
                                }
                                if (dataObject.has("product_pregsafety")) {
                                    String mProductPregsafety = String.valueOf(dataObject.getString("product_pregsafety"));
                                    Log.e(TAG, "Product Pregnancy Safety == " + mProductPregsafety);
                                    productModel.setProductPregsafety(mProductPregsafety);
                                }
                                if (dataObject.has("product_price")) {
                                    String mProductPrice = String.valueOf(dataObject.getString("product_price"));
                                    Log.e(TAG, "Product Price == " + mProductPrice);
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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (productModelArrayList.size() > 0) {
                            for (int i = 0; i < productModelArrayList.size(); i++) {
                                Log.e("Name" + i + ": ", productModelArrayList.get(i).getProductName());
                            }
                            gridView.setAdapter(homeAdapter = new HomeAdapter(getActivity(), productModelArrayList));
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
}
