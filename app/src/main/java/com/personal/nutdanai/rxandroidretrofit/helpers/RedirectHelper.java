package com.personal.nutdanai.rxandroidretrofit.helpers;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.personal.nutdanai.rxandroidretrofit.activities.SecondActivity;

public class RedirectHelper {

    public static void redirectSecondActivity(AppCompatActivity sourceActivity) {
        Intent secondActivityIntent = new Intent(sourceActivity, SecondActivity.class);
        sourceActivity.startActivity(secondActivityIntent);
    }

}
