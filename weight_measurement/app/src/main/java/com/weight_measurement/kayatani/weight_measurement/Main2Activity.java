package com.weight_measurement.kayatani.weight_measurement;

import android.app.Application;
import com.beardedhen.androidbootstrap.TypefaceProvider;

// Applicationを継承
public class Main2Activity extends Application {
    @Override public void onCreate() {
        super.onCreate();
        TypefaceProvider.registerDefaultIconSets();
    }
}
