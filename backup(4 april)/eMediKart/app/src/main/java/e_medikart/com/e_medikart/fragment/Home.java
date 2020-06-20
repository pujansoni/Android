package e_medikart.com.e_medikart.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import e_medikart.com.e_medikart.CustomSwipeAdapter;
import e_medikart.com.e_medikart.R;
/**
 * Created by lenovo on 09-02-2018.
 */

public class Home extends Fragment {
    ViewPager viewPager;
    CustomSwipeAdapter adapter;
    private static int currentPage=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        adapter = new CustomSwipeAdapter(rootView.getContext());
        viewPager.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Home");
    }
}
