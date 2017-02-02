package com.tam.tuane.ninaagile.front_end.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.tam.tuane.ninaagile.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Confirmation extends Engine implements View.OnClickListener {


    private View view;
    private Button btn_success;
    private TextView uniqueCode, txtName, txtEmail, txtNumber, txtDelivery, textAdd1,txtAdd2, txtAdd3;

    public Confirmation() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*if (container != null){
            container.removeAllViews();

        }*/
        getActivity().setTitle("Confirmation");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_confirmation, container, false);

        addOnEventListeners();

        return view;
    }

    private void addOnEventListeners() {
        btn_success = (Button)view.findViewById(R.id.btnSuccessful);

        uniqueCode = (TextView)view.findViewById(R.id.unique_code);
        uniqueCode.setText(refCode);
        txtName = (TextView) view.findViewById(R.id.name);
        txtName.setText(name_);
        txtEmail = (TextView)view.findViewById(R.id.email);
        txtEmail.setText(email);
        txtNumber = (TextView) view.findViewById(R.id.number);
        txtNumber.setText(number);
        textAdd1 = (TextView)view.findViewById(R.id.address_One);
        textAdd1.setText(address1);
        txtAdd2 = (TextView)view.findViewById(R.id.address_two);
        txtAdd2.setText(address2);
        txtAdd3 =(TextView)view.findViewById(R.id.address_three);
        txtAdd3.setText(address3);
        txtDelivery = (TextView)view.findViewById(R.id.delivery_choice);
        txtDelivery.setText(delivery_name);
        btn_success.setOnClickListener(this);
    }


    /**
     * Moving to another fragment, after verification
     */

    private void onNext() {


/*
        FragmentManager fm = getFragmentManager(); // or 'getSupportFragmentManager();'
        int count = fm.getBackStackEntryCount();
        for(int i = 0; i < count; ++i) {
            fm.popBackStack();
        }*/
/*
        FragmentManager manager =getActivity().getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
            manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }*/



       /* while (getActivity().getSupportFragmentManager().getBackStackEntryCount() > 0){
            getActivity().getSupportFragmentManager().popBackStackImmediate();
        }
*/
        onConnectActivity.removeBackStack();

        Payment_method fragment = new Payment_method();
            getActivity().getSupportFragmentManager().
                    beginTransaction()
                    .replace(R.id.framelayout,
                            fragment).commit();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnSuccessful :
                onNext();
                break;
            default:
                break;
        }
    }
}
