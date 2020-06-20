package e_medikart.com.e_medikart;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
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
    private ArrayList<CategoriesModel> arraylist;

    public CategoriesAdapter(Context context, ArrayList<CategoriesModel> categoriesArrayList) {
        this.context = context;
        this.categoriesArrayList = categoriesArrayList;
        this.arraylist = new ArrayList<CategoriesModel>();
        this.arraylist.addAll(categoriesArrayList);
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        CategoriesAdapter.ViewHolder v;
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

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        categoriesArrayList.clear();
        if (charText.length() == 0) {
            categoriesArrayList.addAll(arraylist);
        } else {
            for (CategoriesModel wp : arraylist) {
                if (wp.getManufacturer_name().toLowerCase(Locale.getDefault()).contains(charText)) {
                    categoriesArrayList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        private TextView manufacturerName;
        private TextView manufacturerDescription;
    }
}
