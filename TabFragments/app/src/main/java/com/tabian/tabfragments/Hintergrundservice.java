package com.tabian.tabfragments;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Hintergrundservice extends Service {

    public String bekommeTextVonSeite;
    private String Input;
    private String[] withoutSeps;
    private String[] SH;
    private String SB;
    //SharedPreferences mySPR = this.getApplicationContext().getSharedPreferences("MyPSFLE", Context.MODE_PRIVATE);
    //public final String name = mySPR.getString("Name", "Ich bin der 1.Standardwert");


    public int onStartCommand(Intent intent, int flags, int startId) {



        Toast.makeText(getApplicationContext(),"Test for Duty", Toast.LENGTH_LONG).show();

        //Log.d("Hintergrund","Hintergrundservice: Test for Duty");


        sendToServer("123456789");
        Toast.makeText(getApplicationContext(),"Cotinue",Toast.LENGTH_LONG);







        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public void sendToServer(final String text){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String textparam = "text1=" + URLEncoder.encode(text, "UTF-8");

                    URL scripturl = new URL("https://elektrokiste.000webhostapp.com/SLmanager/Anfrage.php");
                    HttpURLConnection connection = (HttpURLConnection) scripturl.openConnection();
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    connection.setFixedLengthStreamingMode(textparam.getBytes().length);

                    OutputStreamWriter contentWriter = new OutputStreamWriter(connection.getOutputStream());
                    contentWriter.write(textparam);
                    contentWriter.flush();
                    contentWriter.close();

                    InputStream answerInputStream = connection.getInputStream();
                    final String answer = getTextFromInputStream(answerInputStream);

                    SharedPreferences mySPR = getSharedPreferences("MyPSFLE", MODE_PRIVATE);


                    final String name = mySPR.getString("Name", "Ich bin der 1.Standardwert");

                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            Input = answer;
                            withoutSeps = Input.split(";");
                            SH = withoutSeps;
                            Toast.makeText(getApplicationContext(),Input + "Hallo",Toast.LENGTH_LONG);
                            boolean Dienststate = false;


                            for (int i = 0; i < 6;i++){
                                if (SH[2].equals(name)){
                                    //Switch sw = (Switch)findViewById(R.id.switch1);
                                    //sw.setChecked(true);
                                    Toast.makeText(getApplicationContext(),"DIENST",Toast.LENGTH_LONG);
                                }



                            }



                            /*TextView t1 = (TextView) view.findViewById(R.id.textView11);
                            TextView t2 = (TextView) findViewById(R.id.textView12);
                            TextView t3 = (TextView) findViewById(R.id.textView13);
                            TextView t4 = (TextView) findViewById(R.id.textView14);

                            TextView t5 = (TextView) findViewById(R.id.textView15);
                            TextView t6 = (TextView) findViewById(R.id.textView16);
                            t1.setText(SH[0]);
                            t2.setText(SH[1]);
                            t3.setText(SH[2]);
                            t4.setText(SH[3]);

                            t5.setText(SH[4]);
                            t6.setText(SH[5]);*/
                            // t6.setText(bekommeTextVonSeite.toString());
                            //t1.setText(bekommeTextVonSeite);
                            Toast.makeText(getApplicationContext(),"Lade",Toast.LENGTH_LONG);
                        }
                    });
                    answerInputStream.close();
                    connection.disconnect();


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public String getTextFromInputStream(InputStream is){
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();

        String aktuelleZeile;
        try {
            while ((aktuelleZeile = reader.readLine()) != null){
                stringBuilder.append(aktuelleZeile);
                stringBuilder.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString().trim();
    }

    public boolean internetAvailable(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }


}
