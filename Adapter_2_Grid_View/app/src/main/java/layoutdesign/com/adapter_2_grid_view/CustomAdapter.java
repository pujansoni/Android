package layoutdesign.com.adapter_2_grid_view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Pujan on 31-01-2018.
 */

class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<Flag> listItems;

    public CustomAdapter(MainActivity mainActivity, ArrayList<Flag> listItems) {
        this.context = mainActivity;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int i) {
        return listItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listItems.indexOf(getItem(i));
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view == null) {
            view = mInflater.inflate(R.layout.activity_column, null);
            holder = new ViewHolder();
            holder.textView = (TextView) view.findViewById(R.id.txt_view);
            holder.imageView = (ImageView) view.findViewById(R.id.img_view);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }
        final Flag listItem = (Flag) getItem(i);
        holder.textView.setText(listItem.getFlag());
        holder.imageView.setImageResource(listItem.getImage());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "Flag "+listItem.getFlag(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
}
