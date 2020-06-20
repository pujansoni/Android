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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Pujan on 26-02-2018.
 */

public class Sortedbyprice extends AppCompatActivity {

    ListView unsortedlist;
    LaptopAdapter laptopAdapter;String mn;
    ArrayList<Laptop> laptopList= new ArrayList<Laptop>();
    String jsonString; JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sortedbybrand);

        unsortedlist = (ListView) findViewById(R.id.unsortedlist);

//data being retreived through SharedPreferences .
        SharedPreferences sharedPreferences = getSharedPreferences("SHARED_PREF_NAME", Context.MODE_PRIVATE);
        jsonString = sharedPreferences.getString("jsonString",null);

//conversion of jsonString to jsonArray
        try {
            jsonArray = new JSONArray(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

//sortedJSONArray would contain the sorted JSONObjects.
        JSONArray sortedJsonArray = new JSONArray();
        List<JSONObject> jsonValues = new ArrayList<JSONObject>();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                jsonValues.add(jsonArray.getJSONObject(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
//The following section feeds the sorted data into the sortedJsonArray
        Collections.sort( jsonValues, new Comparator<JSONObject>() {
            //You can change "brand" with "price" if you want to sort by price
            private static final String KEY_NAME = "brand";

            @Override
            public int compare(JSONObject a, JSONObject b) {
                String valA = new String();
                String valB = new String();

                try {
                    valA = (String) a.get(KEY_NAME);
                    valB = (String) b.get(KEY_NAME);
                }
                catch (JSONException e) {
                    //do something
                }

                return valA.compareTo(valB);
                //if you want to change the sort order, simply use the following:
                //return -valA.compareTo(valB);
            }
        });

        for (int i = 0; i < jsonArray.length(); i++) {
            sortedJsonArray.put(jsonValues.get(i));
        }


        for(int i=0;i<sortedJsonArray.length();i++){

            try {
                JSONObject jsonObject = sortedJsonArray.getJSONObject(i);
                String modelname = "Modelname:" + jsonObject.getString("modelname");
                String ram = "Ram:" +jsonObject.getString("ram");
                String os = "Os:" +jsonObject.getString("os");
                String price = "Price:"  + jsonObject.getString("price");
                String screensize = "Screensize:" + jsonObject.getString("screensize");
                String brand = "Brand:" + jsonObject.getString("brand");
//here,data from each JSONObject is copied to an Object of Class Laptop
                Laptop laptop = new Laptop(modelname,ram,os,price,screensize,brand);
//The objects of class Laptop are thereby added into the ArrayList i.e laptopList. This will be used
//as we'll set the adapter to the listView.
                laptopList.add(laptop);

            } catch (JSONException e) {
                e.printStackTrace();
            }


//the list_layout and laptopList are passed to the Adapter here.
            laptopAdapter=new LaptopAdapter(Sortedbyprice.this,R.layout.list_layout, laptopList);

            unsortedlist.setAdapter(laptopAdapter);

            laptopAdapter.notifyDataSetChanged();
        }

    }
}