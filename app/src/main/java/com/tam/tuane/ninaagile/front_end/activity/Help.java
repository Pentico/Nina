package com.tam.tuane.ninaagile.front_end.activity;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tam.tuane.ninaagile.R;
import com.tam.tuane.ninaagile.front_end.fragments.MakeOrder;
import com.tam.tuane.ninaagile.front_end.fragments.sendemail;

public class Help extends AppCompatActivity implements View.OnClickListener {

    private Button feedbackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        setupActionBar();
        setupBtn();
    }

    private void setupBtn() {

        feedbackBtn = (Button)findViewById(R.id.feedback);
        feedbackBtn.setOnClickListener(this);
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.feedback:
                emailfragment();
                break;
        }
    }

    private void emailfragment() {

        Fragment fragment = new sendemail();
        getSupportFragmentManager().
                beginTransaction().replace(R.id.helpLayout,
                fragment).commit();

    }
}
