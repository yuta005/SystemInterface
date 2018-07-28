package com.weight_measurement.kayatani.weight_measurement;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TypefaceProvider.registerDefaultIconSets();



        output_func();

        TextView textView = (TextView)findViewById(R.id.textView11);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        textView.setText(sp.getString("SaveString", null), BufferType.NORMAL);

        BootstrapButton saveButton = (BootstrapButton) findViewById(R.id.button2);
        saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                save_ButtonClick();
            }
        });

        //getApplicationInfo().flags = ApplicationInfo.FLAG_DEBUGGABLE;
    }

    private void output_func(){
        TextView textView = (TextView)findViewById(R.id.textView11);
        SharedPreferences sp2 = PreferenceManager.getDefaultSharedPreferences(this);
        textView.setText(sp2.getString("SaveString", null), BufferType.NORMAL);
    }

    private void save_ButtonClick() {
        // 保存

        int weight = this.getWeight();

        EditText editText = (EditText)findViewById(R.id.editText8);

        /*int slength = editText.getText().toString().length();



        if(slength > 10) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            // アラートダイアログのタイトルを設定します
            alertDialogBuilder.setTitle("Error");
            // アラートダイアログのメッセージを設定します
            alertDialogBuilder.setMessage("Too many characters(less than 10)");
            // アラートダイアログの肯定ボタンがクリックされた時に呼び出されるコールバックリスナーを登録します
            alertDialogBuilder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
        }*/

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.edit().putString("SaveString", Integer.toString(weight) + " : " + editText.getText().toString()).commit();
        output_func();
    }

    private int getWeight() {

        //String ip = "192.168.20.2";
        //String ip = "172.217.25.227";
        //int port = 80;
        int weight = -1;

        //String url = "http://www.ipentec.com/default.aspx";

        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    URL url = null;
                    InputStreamReader isr = null;
                    //String ip = "172.217.25.227";
                    String ip = "www.lab.ime.cmc.osaka-u.ac.jp";
                    try {
                        url = new URL("http",ip,80,"/");
                        InputStream is = url.openStream();
                        isr = new InputStreamReader(is,"UTF-8");
                        String text = "";
                        while(true) {
                            int i = isr.read();
                            if (i == -1) {
                                break;
                            }
                            //System.out.print((char)i);
                            text = text + (char)i;
                        }
                        Log.d("internet:html",text);
                    }catch (Exception e) {
                        Log.d("internet:error",e.getMessage());
                    }finally {
                        try {
                            isr.close();
                        }catch (Exception e) {
                            Log.d("internet:error",e.getMessage());
                        }
                    }

                    /*
                    String url = "http://www.casareal.co.jp";
                    OkHttpClient client = new OkHttpClient();

                    try {
                        Log.d("internet","1");
                        Request request = new Request.Builder().url(url).build();
                        Log.d("internet","2");
                        Call call = client.newCall(request);
                        Log.d("internet","3");
                        Response response = call.execute();
                        Log.d("internet","4");
                        ResponseBody body = response.body();
                        Log.d("internet","5");
                    }catch (IOException e) {
                        Log.d("internet",e.getMessage());
                    }*/

                } catch (Exception e) {
                    Log.e("thread", e.getMessage());
                }
            }
        });
        thread.start();
        Log.d("internet","20");


        /*
        //URL url;
        InputStreamReader isr = null;

        String url = "http://www.casareal.co.jp";
        OkHttpClient client = new OkHttpClient();

        try {
            Log.d("internet","1");
            Request request = new Request.Builder().url(url).build();
            Log.d("internet","2");
            Call call = client.newCall(request);
            Log.d("internet","3");
            Response response = call.execute();
            Log.d("internet","4");
            ResponseBody body = response.body();
            Log.d("internet","5");
        }catch (IOException e) {
            Log.d("internet","IO Error");
        }



        URL url = null;
        HttpURLConnection urlConnection ;


        try {


            Log.d("internet","1");
            url = new URL("https",ip,port,"/");
            Log.d("internet","2");
            urlConnection = (HttpURLConnection) url.openConnection();
            Log.d("internet","3");

            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(50000);
            urlConnection.setDoOutput(true);
            Log.d("internet","5");
            urlConnection.connect();

            Log.d("internet","4");

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Log.d("internet","5");
            //readStream(in);
            while(true) {
                int i = in.read();
                if (i == -1) {
                    break;
                }
                //System.out.print((char)i);
                weight = -2;
            }
            urlConnection.disconnect();
        }catch (Exception e) {
            //System.out.println(e.getMessage());
            //Log.d("internet",e.getMessage());
            Log.d("internet","Error");
        } finally {

        }*/


        return weight;
    }


}

