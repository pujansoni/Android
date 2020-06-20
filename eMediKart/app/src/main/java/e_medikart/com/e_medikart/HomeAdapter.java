package e_medikart.com.e_medikart;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import e_medikart.com.e_medikart.fragment.Home;
import e_medikart.com.e_medikart.network.ApiManager;

/**
 * Created by Pujan on 20-04-2018.
 */

public class HomeAdapter extends BaseAdapter{
    private Context mCtx;
    ArrayList<ProductModel> productModelArrayList;
    LayoutInflater inflater = null;
    ProductModel productModel;
    //private ArrayList<ProductModel> arrayList;
    String productId, productContent, productSideEffects, productPregsafety, productPrice, manufacturerName, productUse;
    public HomeAdapter(Context context, ArrayList<ProductModel> productModelArrayList) {
        this.mCtx = context;
        this.productModelArrayList = productModelArrayList;
    }

    @Override
    public int getCount() {
        return productModelArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return productModelArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final HomeAdapter.ViewHolder v;
        productModel = new ProductModel();
        if(view == null){
            inflater = (LayoutInflater) mCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.home_recycler_item, null);
            v = new HomeAdapter.ViewHolder();
            v.productPhoto = (ImageView) view.findViewById(R.id.imgView);
            v.productName = (TextView) view.findViewById(R.id.textViewName);
            v.productPrice = (TextView) view.findViewById(R.id.textViewPrice);
            view.setTag(v);
        }
        else {
            v = (HomeAdapter.ViewHolder) view.getTag();
        }
        final ProductModel listItem = (ProductModel) getItem(i);

        Glide.with(mCtx).load(ApiManager.IMAGE_URL + productModelArrayList.get(i).getProductPhoto()).into(v.productPhoto);
        v.productName.setText(productModelArrayList.get(i).getProductName());
        v.productPrice.setText("Rs." + productModelArrayList.get(i).getProductPrice());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                productContent = productModelArrayList.get(i).getProductContent();
                productSideEffects = productModelArrayList.get(i).getProductSideEffects();
                productPregsafety = productModelArrayList.get(i).getProductPregsafety();
                productPrice = productModelArrayList.get(i).getProductPrice();
                manufacturerName = productModelArrayList.get(i).getManufacturerName();
                productId = productModelArrayList.get(i).getProductId();
                productUse = productModelArrayList.get(i).getProductUse();


                /*Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), Integer.parseInt(ApiManager.IMAGE_URL + productModelArrayList.get(i).getProductPhoto()));
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG,100, stream);
                byte[] byteArray = stream.toByteArray();*/
                String strPath = ApiManager.IMAGE_URL + productModelArrayList.get(i).getProductPhoto();
                Log.e("======bytearray======","bytearray"+strPath);
                Log.e("PRODUCT_ID","response "+productModelArrayList.get(i).getProductId());
                Log.e("PRODUCT_ID","Product name "+productModelArrayList.get(i).getProductName());

                Intent i = new Intent(mCtx, ProductDisplay.class);
                i.putExtra("NAME", v.productName.getText());
                i.putExtra("PHOTO",strPath );
                i.putExtra("DESCRIPTION", productUse);
                i.putExtra("CONTENT", productContent);
                i.putExtra("SIDEEFFECTS", productSideEffects);
                i.putExtra("PREGSAFETY", productPregsafety);
                i.putExtra("PRICE", productPrice);
                i.putExtra("MANUFACTURERNAME", manufacturerName);
                i.putExtra("PRODUCT_ID",productId);
                mCtx.startActivity(i);
            }
        });
        return view;
    }

    public static class ViewHolder {
        private TextView productName,productPrice;
        private ImageView productPhoto;
    }
}
