package com.tam.tuane.ninaagile.front_end.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tam.tuane.ninaagile.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Loading_ extends Engine {


    public Loading_() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      /*  if (container != null){
            container.removeAllViews();

        }*/

        getActivity().setTitle("Loading");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_loading_, container, false);
    }

}
