package com.tam.tuane.ninaagile.front_end.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tam.tuane.ninaagile.R;
import com.tam.tuane.ninaagile.front_end.fragments.MakeOrder;

public class MainActivity extends AppCompatActivity {


    private Button _btnClean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        addListenerOnButton();
    }

    private void addListenerOnButton() {

        _btnClean = (Button)findViewById(R.id.clean_button);

        _btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clean();
            }
        });
    }


    private void clean() {

        startActivity(new Intent(this, DrawerActivity.class));
    }
}
