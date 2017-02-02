package com.tam.tuane.ninaagile.back_end;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Map;

/**
 * Created by Tuane on 2016/09/11.
 */
public abstract class validation extends engine {



    private EditText tempValidation;
    private RadioButton tempRadioButton;
    private String tempValidationString;
    private Constants InnerConstants;

    // Holds the info to be validated
    private Map<String, View> information;

    /**
     * Validating the User information
     * @param info Holds
     */
    public void serviceValidations(Map<String,View> info) {

        this.information =info;

        if (information.containsKey(InnerConstants._service)){

            tempRadioButton =(RadioButton)information.get(InnerConstants._service);
            tempValidationString =tempRadioButton.getText().toString();

            if (tempValidationString.equals(InnerConstants._polish)){
                polishValidation();
            }else if (tempValidationString.equals(InnerConstants._wash)){
                canvasValidation();
            }else {
                tempValidation.setError("Service Selection Error");
            }

        }else {
          throw new Error(" Service is not selected");
        }

    } // EOM

    /**
     * When the customer selected polish service
     *
     */
    private void polishValidation(){
        Toast.makeText(validation.this, "In Polish", Toast.LENGTH_LONG).show();
    } //EOM

    /**
     * When the use selected sneakers canvas
     */
    private void canvasValidation(){
        Toast.makeText(validation.this, "In Canvas", Toast.LENGTH_LONG).show();
    } //EOM

    @Override
    public void onStart() {
        super.onStart();
        InnerConstants  = new Constants();

    }

}
