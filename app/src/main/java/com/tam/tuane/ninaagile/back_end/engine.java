package com.tam.tuane.ninaagile.back_end;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Tuane on 2016/09/11.
 */
public abstract class engine extends AppCompatActivity {


    /**
     * calculating the price of the service requested
     */
    private void calculatePrice(){

    } //EOM

    /**
     * Making a quote for the user to have
     */
    private void makeQuote(){

    } //EOM

    public void sendEmail(String email, String body_email) {

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL ,email);
        i.putExtra(Intent.EXTRA_SUBJECT, "Check");
        i.putExtra(Intent.EXTRA_TEXT ,body_email);

        try{
            startActivity(Intent.createChooser(i, "send mail.."));

        }catch (android.content.ActivityNotFoundException ex){
            Toast.makeText(engine.this, "There are no email clients installed", Toast.LENGTH_SHORT).show();
        }

    } //EOM

    /**
     * the payment for the service
     */
    private void payment(){

    } //EOM

} //EOC
