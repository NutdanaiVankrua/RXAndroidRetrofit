package com.personal.nutdanai.rxandroidretrofit.presenters;


import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.personal.nutdanai.rxandroidretrofit.models.Error;
import com.personal.nutdanai.rxandroidretrofit.models.Post;
import com.personal.nutdanai.rxandroidretrofit.service.Service;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.io.IOException;
import java.lang.annotation.Annotation;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Converter;

public class MainPresenter {

    private RxAppCompatActivity view;
    private Service service;

    public MainPresenter(RxAppCompatActivity view) {
        this.view = view;
        this.service = new Service();
    }

    public void loadPostWithDelay(Integer ms, final Integer postId) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadPostWithId(postId);
            }
        }, ms);
    }

    public void loadPostWithId(Integer id) {

        service.getServiceApi()
                .getPost(id)
                .compose(view.<Post>bindToLifecycle())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(MainPresenter.this.getClass().getSimpleName(), "Method: loadPostWithId ~> onSubscribe");
                    }

                    @Override
                    public void onNext(Post post) {
                        Log.d(MainPresenter.this.getClass().getSimpleName(), "Method: loadPostWithId ~> onNext");
                        logAsJsonStringUsingObject(post);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(MainPresenter.this.getClass().getSimpleName(), "Method: loadPostWithId ~> onError");
                        if (e instanceof HttpException) {
                            ResponseBody errResponse = ((HttpException) e).response().errorBody();
                            Converter<ResponseBody, Error> converter = service.getRetrofit().responseBodyConverter(Error.class, new Annotation[0]);
                            try {
                                Error error = converter.convert(errResponse);
                                logAsJsonStringUsingObject(error);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {
                        Log.d(MainPresenter.this.getClass().getSimpleName(), "Method: loadPostWithId ~> onComplete");
                    }
                });

    }

    public void getBadRequest(Integer statusCode) {
        service.getServiceApi()
                .getPostBadRequest(statusCode)
                .compose(view.<Post>bindToLifecycle())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(MainPresenter.this.getClass().getSimpleName(), "Method: getBadRequest ~> onSubscribe");
                    }

                    @Override
                    public void onNext(Post post) {
                        Log.d(MainPresenter.this.getClass().getSimpleName(), "Method: getBadRequest ~> onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(MainPresenter.this.getClass().getSimpleName(), "Method: getBadRequest ~> onError");
                        if (e instanceof HttpException) {
                            ResponseBody errResponse = ((HttpException) e).response().errorBody();
                            Converter<ResponseBody, Error> converter = service.getRetrofit().responseBodyConverter(Error.class, new Annotation[0]);
                            try {
                                Error error = converter.convert(errResponse);
                                logAsJsonStringUsingObject(error);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onComplete() {
                        Log.d(MainPresenter.this.getClass().getSimpleName(), "Method: getBadRequest ~> onComplete");
                    }
                });

    }

    private void logAsJsonStringUsingObject(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(object);
        Log.d(MainPresenter.this.getClass().getSimpleName(), json);
    }

}
