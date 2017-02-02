package com.tam.tuane.ninaagile.front_end.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tam.tuane.ninaagile.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Splash_Screen extends Engine {


    private Button _btnClean;

    public Splash_Screen() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       /* if (container != null){
            container.removeAllViews();

        }*/

        View view = inflater.inflate(R.layout.fragment_splash__screen, container, false);

        addListenerOnButton();

        return view;
    }

    private void addListenerOnButton() {

        _btnClean = (Button)getActivity().findViewById(R.id.clean_button);

        _btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new MakeOrder();
                getActivity().getSupportFragmentManager().
                        beginTransaction().replace(R.id.framelayout,
                        fragment).addToBackStack(null).commit();
            }
        });
    }

}
