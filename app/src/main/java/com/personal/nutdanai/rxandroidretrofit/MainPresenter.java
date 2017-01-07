package com.personal.nutdanai.rxandroidretrofit;


import android.util.Log;

import com.personal.nutdanai.rxandroidretrofit.models.Post;
import com.personal.nutdanai.rxandroidretrofit.service.Service;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

class MainPresenter {

    private MainActivity view;
    private Service service;

    MainPresenter(MainActivity view){
        this.view = view;
        this.service = new Service();
    }

    void loadPost() {
        service.getServiceApi()
                .getPost(1)
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


}
