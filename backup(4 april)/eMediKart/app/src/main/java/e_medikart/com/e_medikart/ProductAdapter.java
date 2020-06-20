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
 * Created by lenovo on 07-03-2018.
 */

public class ProductAdapter extends BaseAdapter{
    private Context context;

    ArrayList<ProductModel> productModelArrayList;
    ArrayList<ProductModel> searchList;
    LayoutInflater inflater = null;
    ProductModel productModel;

    public ProductAdapter(Context context, ArrayList<ProductModel> productModelArrayList) {
        this.context = context;
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
    public View getView(final int i1, View view, ViewGroup viewGroup) {
        final ProductAdapter.ViewHolder v;
        productModel = new ProductModel();
        if(view == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_product, null);
            v = new ProductAdapter.ViewHolder();
            v.productPhoto = (ImageView) view.findViewById(R.id.img_product);
            v.productName = (TextView) view.findViewById(R.id.txt_product_name);
            v.productUse = (TextView) view.findViewById(R.id.txt_product_description);
            v.productprice = (TextView) view.findViewById(R.id.price);
            view.setTag(v);
        }
        else {
            v = (ProductAdapter.ViewHolder) view.getTag();
        }
        final ProductModel listItem = (ProductModel) getItem(i1);

        Glide.with(context).load(ApiManager.IMAGE_URL + productModelArrayList.get(i1).getProductPhoto()).into(v.productPhoto);
        v.productName.setText(productModelArrayList.get(i1).getProductName());
        v.productUse.setText(productModelArrayList.get(i1).getProductUse());
        v.productprice.setText("PRICE :"+productModelArrayList.get(i1).getProductPrice()+" Rs");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), Integer.parseInt(ApiManager.IMAGE_URL + productModelArrayList.get(i).getProductPhoto()));
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG,100, stream);
                byte[] byteArray = stream.toByteArray();*/
                String strPath = ApiManager.IMAGE_URL + productModelArrayList.get(i1).getProductPhoto();
                Log.e("======bytearray======","bytearray"+ApiManager.IMAGE_URL + productModelArrayList.get(i1).getProductPhoto());
                Intent i = new Intent(context, ProductDisplay.class);
                i.putExtra("NAME", v.productName.getText());
                i.putExtra("PHOTO",strPath );
                i.putExtra("DESCRIPTION", v.productUse.getText());
                i.putExtra("PRICE",productModelArrayList.get(i1).getProductPrice());
                i.putExtra("SIDEEFFECTS",productModelArrayList.get(i1).getProductSideeffects());
                i.putExtra("PRODUCT_ID",productModelArrayList.get(i1).getProductId());
                context.startActivity(i);
            }
        });
        return view;
    }
    public void setFilter(List<ProductModel> countryModels) {
        productModelArrayList = new ArrayList<>();
        productModelArrayList.addAll(countryModels);
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        private TextView productName, productUse,productprice;
        private ImageView productPhoto;
    }
}

