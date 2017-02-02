package com.tam.tuane.ninaagile.front_end.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.tam.tuane.ninaagile.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserAddress extends Engine implements View.OnClickListener{

    public View view;
    private Button btnSubmit;
    public UserAddress() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view  = inflater.inflate(R.layout.fragment_user_address, container, false);

        addListenerOnButton();

        return view;
    }



    private void addListenerOnButton() {
        btnSubmit = (Button)view.findViewById(R.id.processed_button);
        /*service = (RadioGroup)view.findViewById(R.id.Type_service_radiogroup);*/
        Name = (EditText)view.findViewById(R.id.name);
        Email = (EditText)view.findViewById(R.id.email);
        Number = (EditText)view.findViewById(R.id.number);

        Address1 =(EditText)view.findViewById(R.id.address_One);
        Address2 =(EditText)view.findViewById(R.id.address_two);
        spinner_area =(Spinner)view.findViewById(R.id.Areas_spinner);


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

    private void onNext() {

        if (validate(1)) {

            Confirmation fragment = new Confirmation();
            FragmentManager fm = getActivity().getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.framelayout, fragment);
            ft.addToBackStack(null);
            ft.commit();

        }else {

            Toast.makeText(getActivity(), "Error Please retype Info" , Toast.LENGTH_SHORT).show();
        }

    }
}
