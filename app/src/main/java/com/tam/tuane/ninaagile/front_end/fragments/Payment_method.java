package com.tam.tuane.ninaagile.front_end.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.tam.tuane.ninaagile.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Payment_method extends Engine  implements View.OnClickListener{

    private View view;
    private Button _btnMethodPayment, _btnMethodCash, _btnMethodCard;

    public Payment_method() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      /*  if (container != null){
            container.removeAllViews();

        }*/
        getActivity().setTitle("Payment Method");
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_payment_method, container, false);


        /**
         * Poping The entire fragment back stack !!
         */


        addOnEventListeners();
        return view;
    }

    private void addOnEventListeners() {

        _btnMethodCard = (Button)view.findViewById(R.id.Card);
        _btnMethodCash = (Button)view.findViewById(R.id.Cash);

        _btnMethodCash.setOnClickListener(this);
        _btnMethodCard.setOnClickListener(this);
    }


    public void successfull(){

        fetchJSONPOST();


        Fragment fragment = new Notification();
        getActivity().getSupportFragmentManager().
                beginTransaction().replace(R.id.framelayout,
                fragment).addToBackStack(null).commit();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.Card:
                paymentMethod = "Card";
                successfull();
                break;
            case R.id.Cash:
                paymentMethod = "Cash";
                successfull();
        }

    }

   /* public void callParentMethod(){
        getActivity().onBackPressed();
    }


    @Override
    public void onBackPressed() {

    }*/
}
