package com.tam.tuane.ninaagile.front_end.activity;

import android.content.res.Resources;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tam.tuane.ninaagile.R;

import java.io.IOException;
import java.io.InputStream;

public class PrivacyPolicy extends AppCompatActivity {


    private TextView mTextView;
    private static Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        mTextView = (TextView)findViewById(R.id.txtTerms2);
        res = getResources();


        try {
            mTextView.setText(getDataFromRawFiles(R.raw.terms));
        } catch (IOException e) {

        }

        setupActionBar();
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Reading the text file
     * @param id
     * @return
     * @throws IOException
     */
    private  String getDataFromRawFiles(int id) throws IOException {
        InputStream in_s = res.openRawResource(id);

        byte[] b = new byte[in_s.available()];
        in_s.read(b);
        String value = new String(b);

        return value;
    }
}
