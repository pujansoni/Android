package recyclerviewnew.android.com.recyclerviewnew;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
//for the items of the recycler view we need a separate layout file ie: list_layout
//Here we don’t have a single item for our list, and that is why it can’t be stored in a String array. We need a class to store all the attributes that we have for item in our list. Here I have product as the List Item and each product is as shown in the model class Product.java
//for adapter we create a new java file named as ProductAdapter

public class MainActivity extends AppCompatActivity {
    //this is the JSON data url, make sure you are using the correct ip else it wont work
    private static final String URL_PRODUCTS = "http://192.168.29.201/MyApi/Api.php";
    //a list to store all the products
    List<Product> productList;
    //the recyclerview
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //initializing the productlist
        productList = new ArrayList<>();
        //this method will fetch and parse json to display it in recyclerview
        loadProducts();
    }

    private void loadProducts() {
        //Creating a string request. The request type is get defined by first parameter
        //the url is defined in the second parameter. Then we have a response listener and a error listener
        //In response listener we will get the json response as a string
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);
                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {
                                //getting product object from json array
                                JSONObject product = array.getJSONObject(i);
                                //adding the product to product list
                                productList.add(new Product(
                                product.getInt("id"),
                                product.getString("title"),
                                product.getString("shortdesc"),
                                product.getDouble("rating"),
                                product.getDouble("price"),
                                product.getString("image")
                                ));
                            }
                            //creating adapter object and setting it to recyclerview
                            ProductAdapter adapter = new ProductAdapter(MainActivity.this, productList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
