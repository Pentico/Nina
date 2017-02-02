package com.tam.tuane.ninaagile.front_end.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tam.tuane.ninaagile.R;
import com.tam.tuane.ninaagile.adaptors.Data;
import com.tam.tuane.ninaagile.adaptors.ItemsCard;
import com.tam.tuane.ninaagile.back_end.Constants;
import com.tam.tuane.ninaagile.front_end.activity.MainActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Tuane on 2016/09/24.
 */
public class Engine extends Fragment  {


    public static Spinner  spinner_area;
   /* public View view;*/
    public static CheckBox delivery;
    public static CheckBox Business_address;
    public static EditText Address1;
    public static EditText Address2;
    public static EditText Address3;
    public EditText Name;
    public EditText Email;
    public EditText Number;
    public static int itemCount = 1; // item being selected service .. start with one

    private  static Boolean checkvalid = true;
    private static List<Data> Collection;

    public static String delivery_name;
    public static String address1;
    public static String address2;
    public static String address3;
    public static String paymentMethod;
    public static String refCode;
    public static String name_;
    public static String email;
    public static String number;
    public static double totalPrice =0.00;
    public static Constants Constants = new Constants();


    public static TextView txtvItemCount;
    public static TextView txtvTotalPrice;
    public static Button btnStartOver;
    public volatile static int success = 2;

    public static ItemsCard adapter;
    private final String USER_AGENT = "Mozilla/5.0";
    volatile static StringBuffer response = new StringBuffer();
    volatile static StringBuffer urlPara = new StringBuffer();
    String url = "http://itsworth50bucks.com/nina/create_order.php";

    public String LOG = "NInaEngine";

     /// Getter and Setters
    public static List<Data> getCollection() {
        return Collection;
    }

    public static void setCollection(List<Data> collection) {

        if (Collection == null){
            Collection = new ArrayList<>();
        }
            for (int i = 0; i < collection.size(); i++){
                Collection.add(collection.get(i));

            }

        collection.clear();
    }


    protected void phaseOne(){

        Log.e(LOG,"IN Phase One");

        if (getCollection() != null){

            if (getCollection().size() <= 0  ){
                checkvalid = false;
                Toast.makeText(getContext(), "add 1 or more pair of shoes.",
                        Toast.LENGTH_LONG).show();

            }

          /*  if (delivery.isChecked()){

                // we need to collect the address at of the User
                phaseTwo();

                delivery_name = "Pick n Drop";
            }else{

            }*/

        }else{

            checkvalid =! checkvalid;
            Toast.makeText(getContext(), "add 1 or more pair of shoes.",
                    Toast.LENGTH_LONG).show();

        }


    }

    private void phaseThree() {

        Log.e(LOG,"IN Phase Three");
        name_ = Name.getText().toString();
        email = Email.getText().toString();
        number = Number.getText().toString();

        View focusView = null;
        Name.setError(null);
        Email.setError(null);
        Number.setError(null);


        if (TextUtils.isEmpty(name_)  ){
            Name.setError("Name required");
            checkvalid = false;
            focusView = Name;
        }

        if (TextUtils.isEmpty(email) ){
            Email.setError("Email required");
            checkvalid = false;
            focusView = Email;
        }
        if (TextUtils.isEmpty(number)){
            Number.setError("Number required");
            checkvalid = false;
            focusView = Number;
        }

        if (checkvalid){

            if (!email.contains("@")){
                Email.setError("Enter Correct Email");
                checkvalid = false;
                focusView = Email;
            }

            if (number.length()!= 10){
                Number.setError("Enter valid Cell Number");
                checkvalid = false;
                focusView = Number;
            }
        }

        if (!checkvalid){

            focusView.requestFocus();
        } else{

            // populating the data to send via Post
            setRefCode();

        }
    }

    /**
     * Generating the refCode
     */

    private void setRefCode(){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
        StringBuilder builder = new StringBuilder("nina");
        builder.append(dateFormat.format(
                Calendar.getInstance().getTime()));

        builder.deleteCharAt(6);
        builder.deleteCharAt(8);

        // Random number between 100 and 999
        Random rand = new Random();

        int  n = rand.nextInt(999) + 100;

        builder.append(n);
        refCode = builder.toString();

    }

    private void phaseTwo() {

        Log.e(LOG,"IN Phase two");

        address1 = Address1.getText().toString();
        address2 = Address2.getText().toString();
        Address1.setError(null);
        Address2.setError(null);

        View focusView = null;

        if (TextUtils.isEmpty(address1)  ){
            Address1.setError("address required");
            checkvalid = false;
            focusView = Address1;
        }

        if (TextUtils.isEmpty(address2) ){
            Address2.setError("address required");
            checkvalid = false;
            focusView = Address2;
        }
       /* if (TextUtils.isEmpty(address3)){
            Address3.setError("address required");
            checkvalid = false;
            focusView = Address3;
        }
*/
        address3 = spinner_area.getSelectedItem().toString();
        delivery_name = "Yes";

        if (!checkvalid){

            focusView.requestFocus();
        }else {

             phaseThree();
        }


    }


    /**
     * Validating Data  By Phases :1-3
     * @return
     */
    protected Boolean validate(int phase){

        checkvalid = true;

        Log.e(LOG,"IN validate");

        switch (phase) {
            case 0:
                phaseOne();
                break;
            case 1:
                phaseTwo();
                break;
            case 2:

                // We need to Collect the User details
                address1 ="3rd Floor Orion House";
                address2 = "49 Jorissen Street";
                address3 = "Braamfontein";
                delivery_name = "N\\A";
                phaseThree();

                break;

        }

        return checkvalid;
    }


    /**
     * Refreshes the Card, the user can be sure of the number of items added
     */
    public void onRefresh() {

        totalPrice = 0.00;

        if (Collection != null) {

            onTotalPrice();
            txtvItemCount.setText("Items In Cart : " + Collection.size());
            onItemAddedCart();

            txtvTotalPrice.setText("Total Price : R "+totalPrice);

        }

    }


    /**
     * The total Price of items
     */
    private void onTotalPrice() {

        totalPrice = 0.00;

        for(int i =0; i < Collection.size(); i++){

            if (Collection.get(i).getServiceType().equals(Constants._polish)){
                totalPrice += 30;
            }
            if(Collection.get(i).getServiceType().equals(Constants._wash)) {
                totalPrice += 35;
            }
        }

        if (delivery.isChecked()){
            totalPrice += 9;
        }

    }

    /**
     * Clear the Card of the User
     */
    public void onStartOver() {

        if (Collection!= null && !Collection.isEmpty()){

            getCollection().clear();
            txtvItemCount.setText("Items In Cart : " +getCollection().size());

        }else{
            txtvItemCount.setText("Items In Cart : 0");
        }

        totalPrice = 0.00;
        txtvTotalPrice.setText("Total Price : R "+totalPrice);
        delivery.setChecked(false);
        onItemAddedCart();
    }

    public void onItemAddedCart(){

        if (adapter!= null){
            adapter.set_Collection(Collection);
            adapter.notifyDataSetChanged();
        }



    }

    protected static onConnectActivity onConnectActivity;

    public void setOnConnectActivity(onConnectActivity onConnectActivity){

        this.onConnectActivity = onConnectActivity;
    }

    public interface onConnectActivity{

        void removeBackStack();
    }




    public void fetchJSONPOST(){

        populateUrlQuery();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try{

                    URL urls = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) urls.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("User-Agent", USER_AGENT);
                    conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                    String urlParameters = urlPara.toString();

                    conn.setDoInput(true);

                    // send post request
                    DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                    wr.writeBytes(urlParameters);
                    wr.flush();
                    wr.close();

                    //Start the query

                    conn.connect();
                    InputStream stream = conn.getInputStream();
                    String data = convertStreamToString(stream);
                    readAndParseJSON(data);
                    stream.close();


                   /* BufferedReader in = new BufferedReader(
                            new InputStreamReader(conn.getInputStream()));
                    String inputLine;

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
*/
                    //print result

                }catch (Exception e){

                    e.printStackTrace();
                }
            }
        });

        thread.start();



    }


    // Converting the stream of data to string
    // I get this data from the server php file
    private String convertStreamToString(InputStream stream) {

        Scanner in = new Scanner(stream).useDelimiter("\\A");
        return  in.hasNext() ? in.next() : "";
    } // EOM


    // Reading the JSON data and Passing it
    public void readAndParseJSON(String in){
        try {
            JSONObject reader = new JSONObject(in);
            JSONObject name = reader.getJSONObject("status");  // The Object am receiving

             success = name.getInt("success");


            /*surname = name.getString("surname");
            heroes  = name.getString("heroes");


            parsingComplete = false;*/

        }catch (Exception e) {
            e.printStackTrace();
        }

    } // EOM

    protected void populateUrlQuery(){

        urlPara.delete(0, urlPara.length());
        response.delete(0, response.length());

        urlPara.append("ref=").append(refCode).append("&");
        urlPara.append("name=").append(name_).append("&");
        urlPara.append("number=").append(number).append("&");
        urlPara.append("email=").append(email).append("&");
        urlPara.append("address1=").append(address1).append("&");
        urlPara.append("address2=").append(address2).append("&");
        urlPara.append("address3=").append(address3).append("&");
        urlPara.append("pickndrop=").append(delivery_name).append("&");
        urlPara.append("totalprice=").append(totalPrice).append("&");
        urlPara.append("paymentmethod=").append(paymentMethod).append("&");
        urlPara.append("itemcart=").append(Collection.size()).append("&");

        for(int i = 0; i< Collection.size();i++) {
            urlPara.append("item"+i+"typeshoes=").append(Collection.get(i).getTypeOfShoes()).append("&");
            urlPara.append("item"+i+"typeservice=").append(Collection.get(i).getServiceType()).append("&");
            urlPara.append("item"+i+"typecolor=").append(Collection.get(i).getColorOfShoes()).append("&");
        }

    }


}
