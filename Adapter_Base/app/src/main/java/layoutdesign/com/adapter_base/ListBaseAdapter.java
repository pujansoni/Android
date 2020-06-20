package layoutdesign.com.adapter_base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by Pujan on 30-01-2018.
 */

class ListBaseAdapter extends BaseAdapter {

    Context context;
    List<ListModel> listItems;

    public ListBaseAdapter(Context context, List<ListModel> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listItems.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null) {
            convertView = mInflater.inflate(R.layout.activity_row, null);
            holder = new ViewHolder();
            holder.textView = (TextView) convertView.findViewById(R.id.txt_view);
            holder.imageView = (ImageView) convertView.findViewById(R.id.img_view);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        final ListModel listItem = (ListModel) getItem(position);
        holder.textView.setText(listItem.getLang());
        holder.imageView.setImageResource(listItem.getImage());
        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), listItem.getImage());
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG,100, stream);
                byte[] byteArray = stream.toByteArray();
                Intent i = new Intent(context, ListDataActivity.class);
                i.putExtra("Lang", listItem.getLang());
                i.putExtra("Picture", byteArray);
                context.startActivity(i);
            }
        });
        return convertView;
    }

    private class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
}
