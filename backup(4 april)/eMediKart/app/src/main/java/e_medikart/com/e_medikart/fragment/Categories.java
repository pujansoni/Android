package e_medikart.com.e_medikart.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

import e_medikart.com.e_medikart.CategoriesAdapter;
import e_medikart.com.e_medikart.CategoriesModel;
import e_medikart.com.e_medikart.R;
import e_medikart.com.e_medikart.network.ApiManager;
import e_medikart.com.e_medikart.network.RequestCode;
import hp.harsh.library.interfaces.OkHttpInterface;
import hp.harsh.library.okhttp.OkHttpRequest;

/**
 * Created by lenovo on 09-02-2018.
 */

public class Categories extends Fragment implements OkHttpInterface{

    private ListView listCategories;
    private TextView textViewNoCategory;
    String mStrPath;
    String TAG = "CategoriesFragment";
    private CategoriesAdapter categoriesAdapter;
    ArrayList<CategoriesModel> categoriesArrayList;
    private CategoriesModel categoriesModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categories, container, false);
        init(rootView);
        return rootView;
    }

    private void init(View rootView) {
        listCategories = (ListView) rootView.findViewById(R.id.list_category);
        textViewNoCategory = (TextView) rootView.findViewById(R.id.txt_no_category);
        loadcategory();
    }

    private void loadcategory() {

        new OkHttpRequest(getActivity(), OkHttpRequest.Method.GET, ApiManager.DISPLAY_MANUFACTURER, RequestCode.CODE_USER_MANUFACTURER, true, this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Categories");
    }

    @Override
    public void onOkHttpStart(int requestId) {
        Log.e(TAG, "onLoopjStart==1" +requestId);
    }

    @Override
    public void onOkHttpSuccess(int requestId, int statusCode, String response) {
        Log.e(TAG, "onLoopjSuccess==1" + response);
        if(response != null){
            try{
                JSONObject jsonFetchAllCategory = new JSONObject(response);
                if(jsonFetchAllCategory.has("success")){
                    int code = jsonFetchAllCategory.getInt("success");
                    Log.e(TAG, "code==" + code);
                    if(code == 1){
                        Log.e(TAG, "code==1" + code);
                        categoriesArrayList = new ArrayList<>();
                        if(jsonFetchAllCategory.has("path")){
                            mStrPath = jsonFetchAllCategory.getString("path");
                        }
                        if(jsonFetchAllCategory.has("manufacturer")){
                            JSONArray jsonArrayData = jsonFetchAllCategory.getJSONArray("manufacturer");
                            Log.e(TAG, "" + jsonArrayData.length());
                            for(int i = 0; i < jsonArrayData.length(); i++){
                                Log.e(TAG, "" + i + "==========");
                                categoriesModel = new CategoriesModel();
                                JSONObject dataObject = jsonArrayData.getJSONObject(i);
                                Log.i("contactsObject", "++" + dataObject);
                                if(dataObject.has("cat_id")){
                                    String mid = String.valueOf(dataObject.getInt("cat_id"));
                                    Log.e(TAG, "strid==" + mid);
                                    categoriesModel.setCat_id(mid);
                                }
                                if(dataObject.has("manufacturer_id")){
                                    String mStrManufacturerId= String.valueOf(dataObject.getString("manufacturer_id"));
                                    Log.e(TAG, "manufacturer_id==" + mStrManufacturerId);
                                    categoriesModel.setManufacturer_id(mStrManufacturerId);
                                }
                                if(dataObject.has("manufacturer_name")){
                                    String mStrManufacturerName = String.valueOf(dataObject.getString("manufacturer_name"));
                                    Log.e(TAG, "manufacturer_name==" + mStrManufacturerName);
                                    categoriesModel.setManufacturer_name(mStrManufacturerName);
                                }
                                categoriesArrayList.add(categoriesModel);
                            }
                        }
                    }
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(categoriesArrayList.size() > 0){
                            for(int i = 0; i < categoriesArrayList.size(); i++){
                                Log.e("Name" + i + ": ", categoriesArrayList.get(i).getManufacturer_name());
                                listCategories.setAdapter(categoriesAdapter = new CategoriesAdapter(getActivity(), categoriesArrayList));

                            }
                            textViewNoCategory.setVisibility(View.GONE);
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
