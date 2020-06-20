package e_medikart.com.e_medikart.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import e_medikart.com.e_medikart.PregSafety;
import e_medikart.com.e_medikart.R;

/**
 * Created by Pujan on 09-02-2018.
 */

public class Customer_Support extends Fragment {
    private Button buttonpregsafety, buttonfaq;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_customer_support, container, false);
        init(rootView);
        return rootView;
    }

    private void init(View rootView) {
        buttonpregsafety = (Button) rootView.findViewById(R.id.btn1_pregsafety);
        buttonfaq = (Button) rootView.findViewById(R.id.btn1_faq);
        buttonpregsafety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), PregSafety.class);
                startActivity(i);
            }
        });
        buttonfaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Customer Support");
    }
}
