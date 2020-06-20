package e_medikart.com.e_medikart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.Inflater;

import e_medikart.com.e_medikart.fragment.Categories;
import e_medikart.com.e_medikart.fragment.CategoryProductFragment;

/**
 * Created by Pujan on 06-03-2018.
 */

public class CategoriesAdapter extends BaseAdapter {
    private Context context;
    ArrayList<CategoriesModel> categoriesArrayList;
    LayoutInflater inflater = null;
    CategoriesModel categoriesModel;

    public CategoriesAdapter(Context context, ArrayList<CategoriesModel> categoriesArrayList) {
        this.context = context;
        this.categoriesArrayList = categoriesArrayList;
    }

    @Override
    public int getCount() {
        return categoriesArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return categoriesArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final CategoriesAdapter.ViewHolder v;
        categoriesModel = new CategoriesModel();
        if(view == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
            v = new CategoriesAdapter.ViewHolder();
            v.manufacturerName = (TextView) view.findViewById(R.id.txt_manufacturer_name);
            v.manufacturerDescription = (TextView) view.findViewById(R.id.txt_manufacturer_description);
            view.setTag(v);
        }
        else {
            v = (CategoriesAdapter.ViewHolder) view.getTag();
        }

        final CategoriesModel categoriesModel = (CategoriesModel)getItem(i);
        v.manufacturerName.setText(categoriesArrayList.get(i).getManufacturer_name());
        v.manufacturerDescription.setText(categoriesArrayList.get(i).getManufacturer_description());

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

               Intent i = new Intent(context,CategoryProductFragment.class);
               i.putExtra("MENU_ID",categoriesModel.getManufacturer_id());
               context.startActivity(i);
                    // Bundle bundle=new Bundle();
               // bundle.putString("MENU_ID",categoriesModel.getManufacturer_id());
               // categories.setArguments(bundle);
            }
        });
        return view;
    }

    public static class ViewHolder {
        private TextView manufacturerName;
        private TextView manufacturerDescription;
    }
}
