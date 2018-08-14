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

    float weight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        weight = 0.0f;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TypefaceProvider.registerDefaultIconSets();

        getWeight();

        output_func();

        TextView textView = (TextView)findViewById(R.id.textView11);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        textView.setText(sp.getString("SaveString", null), BufferType.NORMAL);

        /*
        TextView textView2 = (TextView)findViewById(R.id.textView13);


        textView2.setText(Float.toString(weight));*/

        BootstrapButton saveButton = (BootstrapButton) findViewById(R.id.button2);
        saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                save_ButtonClick();
            }
        });

        BootstrapButton updateButton = (BootstrapButton) findViewById(R.id.button_update);
        updateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                TextView textView2 = (TextView)findViewById(R.id.textView13);
                getWeight();
                if (weight < 0) {
                    textView2.setText("0");
                }else {
                    textView2.setText(Float.toString(weight));
                }
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

        //this.getWeight();

        EditText editText = (EditText)findViewById(R.id.editText8);



        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        //sp.edit().putString("SaveString", Float.toString(weight) + " : " + editText.getText().toString()).commit();
        sp.edit().putString("SaveString", editText.getText().toString()).commit();
        output_func();
    }

    private void getWeight() {

        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    URL url = null;
                    InputStreamReader isr = null;
                    //String ip = "172.217.25.227";
                    String ip = "192.168.100.167";

                    String text = "";
                    Log.d("test","test");
                    try {
                        Log.d("test2","test2");
                        url = new URL("http",ip,80,"/");
                        Log.d("test3","test3");
                        InputStream is = url.openStream();
                        Log.d("test4","test4");
                        isr = new InputStreamReader(is,"UTF-8");

                        while(true) {
                            int i = isr.read();
                            if (i == -1) {
                                break;
                            }
                            text = text + (char)i;
                        }
                        //Log.d("internet:html",text);

                        weight = Float.parseFloat(text);
                        //Log.d("test2","test2");
                    }catch (Exception e) {
                        Log.d("internet:error",e.getMessage());
                    }finally {
                        try {
                            isr.close();
                        }catch (Exception e) {
                            Log.d("internet:error",e.getMessage());
                        }
                    }

                } catch (Exception e) {
                    Log.e("thread", e.getMessage());
                }
            }
        });
        thread.start();
        Log.d("internet","20");

    }


}

