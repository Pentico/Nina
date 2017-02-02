package com.tam.tuane.ninaagile.front_end.fragments;


import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tam.tuane.ninaagile.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class sendemail extends Engine {


    private EditText emailBody, emailSubject;
    private Button sendButton;

    public sendemail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (container != null){
            container.removeAllViews();
        }
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sendemail, container, false);

        addButtons(view);

        return view;
    }

    private void addButtons(View view) {
        emailBody = (EditText)view.findViewById(R.id.emailbody);
        emailSubject = (EditText)view.findViewById(R.id.emailsubject);
        sendButton = (Button)view.findViewById(R.id.emalSendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });

    }


    private void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO)
                .setData(new Uri.Builder().scheme("mailto").build())
                .putExtra(Intent.EXTRA_EMAIL, new String[]{ "Nina <hello@itsworht50Bucks.com>" })
                .putExtra(Intent.EXTRA_SUBJECT, emailSubject.getText().toString())
                .putExtra(Intent.EXTRA_TEXT, emailBody.getText().toString());

        ComponentName emailApp = intent.resolveActivity(getActivity().getPackageManager());
        ComponentName unsupportedAction = ComponentName.unflattenFromString("com.android.fallback/.Fallback");
        if (emailApp != null && !emailApp.equals(unsupportedAction))
            try {
                // Needed to customise the chooser dialog title since it might default to "Share with"
                // Note that the chooser will still be skipped if only one app is matched
                Intent chooser = Intent.createChooser(intent, "Send email with");
                startActivity(chooser);
                return;
            }
            catch (ActivityNotFoundException ignored) {
            }

        Toast.makeText(getActivity(), "Couldn't find an email app and account", Toast.LENGTH_LONG)
                .show();
    }

}
