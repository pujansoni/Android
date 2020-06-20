package e_medikart.com.e_medikart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import e_medikart.com.e_medikart.network.ApiManager;

/**
 * Created by Pujan on 07-03-2018.
 */

public class ProductAdapter extends BaseAdapter{
    private Context context;
    ArrayList<ProductModel> productModelArrayList;
    LayoutInflater inflater = null;
    ProductModel productModel;
    //private ArrayList<ProductModel> arrayList;
    String productId, productContent, productSideEffects, productPregsafety, productPrice, manufacturerName;

    public ProductAdapter(Context context, ArrayList<ProductModel> productModelArrayList) {
        this.context = context;
        this.productModelArrayList = productModelArrayList;
        //this.arrayList = new ArrayList<ProductModel>();
        //this.arrayList.addAll(productModelArrayList);
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
        final ProductAdapter.ViewHolder v;
        productModel = new ProductModel();
        if(view == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_product, null);
            v = new ProductAdapter.ViewHolder();
            v.productPhoto = (ImageView) view.findViewById(R.id.img_product);
            v.productName = (TextView) view.findViewById(R.id.txt_product_name);
            v.productUse = (TextView) view.findViewById(R.id.txt_product_description);
            view.setTag(v);
        }
        else {
            v = (ProductAdapter.ViewHolder) view.getTag();
        }
        final ProductModel listItem = (ProductModel) getItem(i);

        Glide.with(context).load(ApiManager.IMAGE_URL + productModelArrayList.get(i).getProductPhoto()).into(v.productPhoto);
        v.productName.setText(productModelArrayList.get(i).getProductName());
        v.productUse.setText(productModelArrayList.get(i).getProductUse());
           view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                productContent = productModelArrayList.get(i).getProductContent();
                productSideEffects = productModelArrayList.get(i).getProductSideEffects();
                productPregsafety = productModelArrayList.get(i).getProductPregsafety();
                productPrice = productModelArrayList.get(i).getProductPrice();
                manufacturerName = productModelArrayList.get(i).getManufacturerName();
                productId = productModelArrayList.get(i).getProductId();


                /*Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), Integer.parseInt(ApiManager.IMAGE_URL + productModelArrayList.get(i).getProductPhoto()));
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG,100, stream);
                byte[] byteArray = stream.toByteArray();*/
                String strPath = ApiManager.IMAGE_URL + productModelArrayList.get(i).getProductPhoto();
                Log.e("======bytearray======","bytearray"+strPath);
                Log.e("PRODUCT_ID","response "+productModelArrayList.get(i).getProductId());
                Log.e("PRODUCT_ID","Product name "+productModelArrayList.get(i).getProductName());

                Intent i = new Intent(context, ProductDisplay.class);
                i.putExtra("NAME", v.productName.getText());
                i.putExtra("PHOTO",strPath );
                i.putExtra("DESCRIPTION", v.productUse.getText());
                i.putExtra("CONTENT", productContent);
                i.putExtra("SIDEEFFECTS", productSideEffects);
                i.putExtra("PREGSAFETY", productPregsafety);
                i.putExtra("PRICE", productPrice);
                i.putExtra("MANUFACTURERNAME", manufacturerName);
                i.putExtra("PRODUCT_ID",productId);
                context.startActivity(i);
            }
        });

        return view;
    }

//    public void filter(String charText) {
//        charText = charText.toLowerCase(Locale.getDefault());
//        productModelArrayList.clear();
//        if (charText.length() == 0) {
//            productModelArrayList.addAll(arrayList);
//        } else {
//            for (ProductModel wp : arrayList) {
//                if (wp.getProductName().toLowerCase(Locale.getDefault()).contains(charText)) {
//                    productModelArrayList.add(wp);
//                }
//            }
//        }
//        notifyDataSetChanged();
//    }

    public void setFilter(List<ProductModel> countryModels) {
        productModelArrayList = new ArrayList<>();
        productModelArrayList.addAll(countryModels);
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        private TextView productName, productUse;
        private ImageView productPhoto;
    }
}

