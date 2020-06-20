package layoutdesign.com.adaptar;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pujan on 27-01-2018.
 */


class ListArrayAdapter extends ArrayAdapter<String> {
    private  Context context;
    private  String[] animallist;
    private  Integer[] ImageName;


    public ListArrayAdapter(Context context, String[] animallist, Integer[] ImageName) {
        super(context, R.layout.activity_row, animallist);
        this.context = context;
        this.animallist = animallist;
        this.ImageName = ImageName;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View View, @NonNull ViewGroup parent) {
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View ListViewSingle = mInflater.inflate(R.layout.activity_row,null,true);
        TextView textView = (TextView) ListViewSingle.findViewById(R.id.txt_view);
        ImageView imageView = (ImageView) ListViewSingle.findViewById(R.id.img_view);
        textView.setText(animallist[position]);
        imageView.setImageResource(ImageName[position]);
        return ListViewSingle;
    };
}
