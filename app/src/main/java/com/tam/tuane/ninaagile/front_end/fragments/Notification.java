package com.tam.tuane.ninaagile.front_end.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tam.tuane.ninaagile.R;
import com.tam.tuane.ninaagile.front_end.activity.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class Notification extends Engine {


    private Button responseBtn;
    private TextView textView;

    public Notification() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (container != null){
            container.removeAllViews();

        }

        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        getActivity().setTitle("Notification");

        responseBtn = (Button)view.findViewById(R.id.debug_response);
        textView = (TextView)view.findViewById(R.id.debug_query);
        responseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainAvtivity();
            }
        });
        responseBtn.setVisibility(View.INVISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                serverResponseHandler ();
            }
        }, 5000);


        return view;
    }


    public void serverResponseHandler () {

        if(success == 1){

            textView.setText(" Order Received, The Nina team will be in contact with you.");

        }else{

            textView.setText(" Connection Problem.\n Please try again, if the error persists " +
                    "send a feedback so we can resolve the issue. ");

        }

        responseBtn.setVisibility(View.VISIBLE  );
        com.tam.tuane.ninaagile.front_end.fragments.Engine.onConnectActivity.removeBackStack();

    }


    private void goToMainAvtivity()
    {

        onRefresh();
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

}
