package com.personal.nutdanai.rxandroidretrofit.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.personal.nutdanai.rxandroidretrofit.R;
import com.personal.nutdanai.rxandroidretrofit.presenters.MainPresenter;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public class SecondActivity extends RxAppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d(SecondActivity.this.getClass().getSimpleName(), "onCreate");

        MainPresenter presenter = new MainPresenter(this);
//        presenter.loadPostWithDelay(5000, 99999);
        presenter.getBadRequest(400);
    }

}
