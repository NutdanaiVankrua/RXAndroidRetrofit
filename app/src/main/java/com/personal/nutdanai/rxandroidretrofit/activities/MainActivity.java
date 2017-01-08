package com.personal.nutdanai.rxandroidretrofit.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.personal.nutdanai.rxandroidretrofit.R;
import com.personal.nutdanai.rxandroidretrofit.helpers.RedirectHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RedirectHelper.redirectSecondActivity(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(MainActivity.this.getClass().getSimpleName(), "onRestart");
    }

}
