package com.tam.tuane.ninaagile.back_end;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Tuane on 2016/09/18.
 */
public class JSONParser {

    public String urlString;
    public volatile Boolean parsingComplete = true;
    public String surname;
    public String heroes;
    private final String USER_AGENT = "Mozilla/5.0";


    public JSONParser(String urlString){
        this.urlString = urlString;
    }
    /**
     * Opening the http connection to get the json file from the server
     *
     */
    public void fetchJSONGET( ) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    //Start the query
                    conn.connect();
                    InputStream stream = conn.getInputStream();
                    String data = convertStreamToString(stream);
                    readAndParseJSON(data);
                    stream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        thread.start();
    } //EOM

    public void fetchJSONPOST(){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try{

                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(10000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("User-Agent", USER_AGENT);
                    conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                    String urlParameters = "name=Joe&heroes=whackies";

                    conn.setDoInput(true);

                    // send post request
                    DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                    wr.writeBytes(urlParameters);
                    wr.flush();
                    wr.close();

                   /* //Start the query
                    conn.connect();
                    InputStream stream = conn.getInputStream();
                    String data = convertStreamToString(stream);
                    readAndParseJSON(data);
                    stream.close();*/

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
            JSONObject name = reader.getJSONObject("name");  // The Object am receiving

            surname = name.getString("surname");
            heroes  = name.getString("heroes");


            parsingComplete = false;

        }catch (Exception e) {
            e.printStackTrace();
        }
    } // EOM

}
