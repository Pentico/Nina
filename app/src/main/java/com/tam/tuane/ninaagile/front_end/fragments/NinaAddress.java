package com.tam.tuane.ninaagile.front_end.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.tam.tuane.ninaagile.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NinaAddress extends Engine implements View.OnClickListener {


    private View view;
    private Button btnSubmit;

    public NinaAddress() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container != null){
            container.removeAllViews();

        }
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_nina_address, container, false);

        addListenerOnButton();

        return  view;
    }

    private void addListenerOnButton() {
        btnSubmit = (Button)view.findViewById(R.id.processed_button);
        /*service = (RadioGroup)view.findViewById(R.id.Type_service_radiogroup);*/
        Name = (EditText)view.findViewById(R.id.name);
        Email = (EditText)view.findViewById(R.id.email);
        Number = (EditText)view.findViewById(R.id.number);


        btnSubmit.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.processed_button:
                onNext();
                break;

        }

    }

    /**
     * Moving to the next fragment collecting User data !
     */
    private void onNext() {

        if (validate(2)) {


            Confirmation fragment = new Confirmation();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.framelayout, fragment);
            ft.addToBackStack(null);
            ft.commit();

        }
    }
}
