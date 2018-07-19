package com.weight_measurement.kayatani.weight_measurement;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Debug;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.EditText;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.content.Intent;
import android.widget.Button;

import android.app.AlertDialog;
import android.content.DialogInterface;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;

import android.widget.AutoCompleteTextView;
import android.widget.ArrayAdapter;

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
    }

    private void output_func(){
        TextView textView = (TextView)findViewById(R.id.textView11);
        SharedPreferences sp2 = PreferenceManager.getDefaultSharedPreferences(this);
        textView.setText(sp2.getString("SaveString", null), BufferType.NORMAL);
    }

    private void save_ButtonClick() {
        // 保存


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
        sp.edit().putString("SaveString", editText.getText().toString()).commit();
        output_func();


    }

}
