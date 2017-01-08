package com.personal.nutdanai.rxandroidretrofit.presenters;


import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.personal.nutdanai.rxandroidretrofit.models.Post;
import com.personal.nutdanai.rxandroidretrofit.service.Service;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {

    private RxAppCompatActivity view;
    private Service service;

    public MainPresenter(RxAppCompatActivity view) {
        this.view = view;
        this.service = new Service();
    }

    public void loadPostWithDelay(Integer ms) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadPost();
            }
        }, ms);
    }

    public void loadPost() {

        service.getServiceApi()
                .getPost(1)
                .compose(view.<Post>bindToLifecycle())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(MainPresenter.this.getClass().getSimpleName(), "onSubscribe");
                    }

                    @Override
                    public void onNext(Post post) {
                        Log.d(MainPresenter.this.getClass().getSimpleName(), "onNext");
                        logAsJsonStringUsingObject(post);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(MainPresenter.this.getClass().getSimpleName(), "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(MainPresenter.this.getClass().getSimpleName(), "onComplete");
                    }
                });
    }

    private void logAsJsonStringUsingObject(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(object);
        Log.d(MainPresenter.this.getClass().getSimpleName(), json);
    }

}
