package com.tam.tuane.ninaagile.front_end.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.tam.tuane.ninaagile.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Welcome extends Engine {

    private View view;
    private Button _btnClean;

    public Welcome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_welcome, container, false);

        addListenerOnButton();

        return view;
    }

    private void addListenerOnButton() {

        _btnClean = (Button)view.findViewById(R.id.clean_button);

        _btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                clean();
            }
        });
    }


    private void clean() {

        Fragment fragment = new MakeOrder();
        getActivity().getSupportFragmentManager().
                beginTransaction().replace(R.id.framelayout,
                fragment).addToBackStack(null).commit();
    }

}
