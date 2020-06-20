package introscreenslider.android.com.introscreenslider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Pujan on 26-02-2018.
 */

@SuppressLint("ValidFragment")
public class SampleSlide extends Fragment{
    private static final String ARG_LAYOUT_RES_ID = "layoutResId";
    public static SampleSlide = SampleSlide(int layoutResId){
        SampleSlide sampleSlide = new SampleSlide();
        Bundle args = new Bundle();
        args.putInt(ARG_LAYOUT_RES_ID, layoutResId);
        sampleSlide.setArguments(args);
        return sampleSlide;
    }
}
