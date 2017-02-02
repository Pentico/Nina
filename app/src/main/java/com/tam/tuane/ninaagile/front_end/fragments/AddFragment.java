package com.tam.tuane.ninaagile.front_end.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.tam.tuane.ninaagile.R;
import com.tam.tuane.ninaagile.adaptors.Orders;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Engine {

    private Button addMoreBtn;

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        addButtons(view);

        return view;
    }

    private void addButtons(View view) {
        addMoreBtn = (Button)view.findViewById(R.id.addMoreBtn);
        addMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Orders fragment1 = new Orders();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.items_fragment,fragment1).commit();
            }
        });
    }

}
