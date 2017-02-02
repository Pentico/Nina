package com.tam.tuane.ninaagile.front_end.fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tam.tuane.ninaagile.R;
import com.tam.tuane.ninaagile.adaptors.Cart;
import com.tam.tuane.ninaagile.adaptors.Data;
import com.tam.tuane.ninaagile.adaptors.ItemsCard;
import com.tam.tuane.ninaagile.adaptors.OrderItems;
import com.tam.tuane.ninaagile.adaptors.Orders;
import com.tam.tuane.ninaagile.listeners.ShoesOnItemSelectedListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MakeOrder extends Engine implements View.OnClickListener {

    private Button btnSubmit;
    private View view;

    // Progress Dialog
    private ProgressDialog pDialog;


    public MakeOrder() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       /* if (container != null){
            container.removeAllViews();

        }*/
        getActivity().setTitle("Data capture");
       /* getActivity().getActionBar().setIcon(R.mipmap.ic_launcher);*/


        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.fragment_make_order, container, false);

        addListenerOnButton();
        /*addListenerOnSpinnerItemSelection();*/
        addFragment();
        onStartOver();
        /*try {
            sendPost();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error sending DATA", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }*/

        return view;

    }

    private void addFragment() {

        Orders fragment1 = new Orders();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.items_fragment,fragment1).commit();

        Cart fragment2 = new Cart();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.card_layout,fragment2).commit();

    }

    private void addListenerOnSpinnerItemSelection() {
       /* spinner_shoes = (Spinner)view.findViewById(R.id.type_shoe_spinner);
        spinner_colors = (Spinner)view.findViewById(R.id.type_color_spinner);
        spinner_shoes.setOnItemSelectedListener(new ShoesOnItemSelectedListener()); */
    }

    private void addListenerOnButton() {
        btnSubmit = (Button)view.findViewById(R.id.processed_button);
       /* btnRefresh = (Button)view.findViewById(R.id.refresh);*/
        btnStartOver = (Button)view.findViewById(R.id.startOver);
        txtvItemCount = (TextView)view.findViewById(R.id.itemCount);
        txtvTotalPrice = (TextView)view.findViewById(R.id.totalPrice);
        delivery = (CheckBox)view.findViewById(R.id.delivery_checkbox);
        /*service = (RadioGroup)view.findViewById(R.id.Type_service_radiogroup);*/
        Business_address = (CheckBox)view.findViewById(R.id.buisness_address);
        Business_address.setVisibility(View.GONE);


        btnSubmit.setOnClickListener(this);
        btnStartOver.setOnClickListener(this);
       /* btnRefresh.setOnClickListener(this);*/
        delivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (compoundButton.isChecked()){

                    totalPrice += 9;
                    txtvTotalPrice.setText("Total Price : R "+totalPrice);
                }else{
                    totalPrice -= 9;
                    if (totalPrice<0)
                        totalPrice = 0.00;
                    txtvTotalPrice.setText("Total Price : R "+totalPrice);
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
         switch (view.getId()) {
            case R.id.processed_button:
                onNext();
                break;
             /*case R.id.refresh:
                 onRefresh();
                 break;*/
             case R.id.startOver:
                 onStartOver();
                 break;

        }
    }



    private void onNext() {


       /* service_type = (RadioButton)view.findViewById(service.getCheckedRadioButtonId());*/

        if (validate(0)) {

            if (delivery.isChecked()){

                UserAddress fragment = new UserAddress();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framelayout, fragment).addToBackStack(null)
                        .commit();

            }else {
                NinaAddress fragment =new NinaAddress();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framelayout, fragment).addToBackStack(null)
                        .commit();
            }

        }
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

}
