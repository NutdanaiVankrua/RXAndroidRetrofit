package com.personal.nutdanai.rxandroidretrofit.service;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

//    private static final String SERVER_END_POINT = "http://jsonplaceholder.typicode.com/";
    private static final String SERVER_END_POINT = "https://echo.getpostman.com/";
    private ServiceImp serviceApi;
    private Retrofit retrofit;

    public Service() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(SERVER_END_POINT)
                .client(client)
                .build();
        serviceApi = retrofit.create(ServiceImp.class);
    }

    public ServiceImp getServiceApi() {
        return serviceApi;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
