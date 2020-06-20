package e_medikart.com.e_medikart.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.widget.SearchView;
//import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import e_medikart.com.e_medikart.MainActivity;
import e_medikart.com.e_medikart.ProductAdapter;
import e_medikart.com.e_medikart.ProductModel;
import e_medikart.com.e_medikart.R;
import e_medikart.com.e_medikart.network.ApiManager;
import e_medikart.com.e_medikart.network.RequestCode;
import hp.harsh.library.interfaces.OkHttpInterface;
import hp.harsh.library.okhttp.OkHttpRequest;

public class ProductFragment extends Fragment implements OkHttpInterface{
    private ListView listProduct;
    private TextView textViewNoProduct;
    String mStrPath;
    String TAG = "ProductFragment";
    private ProductAdapter productAdapter;
    ArrayList<ProductModel> productModelArrayList;
    private ProductModel productModel;
    SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product, container, false);
        init(rootView);
        return rootView;
    }

    private void init(View rootView) {
        listProduct = (ListView) rootView.findViewById(R.id.list_product);
        textViewNoProduct = (TextView) rootView.findViewById(R.id.txt_no_product);
        searchView = (SearchView) rootView.findViewById(R.id.search);
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
        loadcategory();
    }

    private void loadcategory() {
        new OkHttpRequest(getActivity(), OkHttpRequest.Method.GET, ApiManager.DISPLAY_PRODUCT, RequestCode.CODE_USER_PRODUCT, true, this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Product");
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
                            listProduct.setAdapter(productAdapter = new ProductAdapter(getActivity(), productModelArrayList));
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

   /* @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<ProductModel> filteredModelList = filter(productModelArrayList, newText);
        productAdapter.setFilter(filteredModelList);
        return true;
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
    }*/
}
