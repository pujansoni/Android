package jsondata.android.com.jsondata;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Pujan on 26-02-2018.
 */

public class UnsortedActivity extends AppCompatActivity {


    ListView unsortedlist;
    LaptopAdapter laptopAdapter;String mn;
    ArrayList<Laptop> laptopList= new ArrayList<Laptop>();
    ArrayList<String> jsonString = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unsorted);

        unsortedlist = (ListView) findViewById(R.id.unsortedlist);


        SharedPreferences sp = getSharedPreferences("SHARED_PREF_NAME", Context.MODE_PRIVATE);
        String jsonString = sp.getString("jsonString",null);
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i=0;i<jsonArray.length();i++){


            try{

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String modelname = "Modelname:" + jsonObject.getString("modelname");
                mn = modelname;
                String ram = "Ram:" +jsonObject.getString("ram");
                String os = "Os:" +jsonObject.getString("os");
                String price = "Price:"  + jsonObject.getString("price");
                String screensize = "Screensize:" + jsonObject.getString("screensize");
                String brand = "Brand:" + jsonObject.getString("brand");
                Laptop laptop = new Laptop(modelname,ram,os,price,screensize,brand);
                laptopList.add(laptop);

            }catch (Exception e){


            }
        }


        laptopAdapter=new LaptopAdapter(UnsortedActivity.this,R.layout.list_layout, laptopList);

        unsortedlist.setAdapter(laptopAdapter);

        laptopAdapter.notifyDataSetChanged();




    }

}