package com.tam.tuane.ninaagile.front_end.activity;

import android.content.res.Resources;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tam.tuane.ninaagile.R;

import java.io.IOException;
import java.io.InputStream;

public class TermsActivity extends AppCompatActivity {

    private TextView mTextView;
    private static Resources res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        mTextView = (TextView)findViewById(R.id.txtTerms2);
        res = getResources();


        try {
            mTextView.setText(getDataFromRawFiles(R.raw.terms));
        } catch (IOException e) {

        }

        setupActionBar();
    }


    private  String getDataFromRawFiles(int id) throws IOException {
        InputStream in_s = res.openRawResource(id);

        byte[] b = new byte[in_s.available()];
        in_s.read(b);
        String value = new String(b);

        return value;
    }

    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
