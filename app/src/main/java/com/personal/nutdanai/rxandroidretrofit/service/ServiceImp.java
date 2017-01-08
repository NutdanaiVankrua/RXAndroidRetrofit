package com.personal.nutdanai.rxandroidretrofit.service;


import com.personal.nutdanai.rxandroidretrofit.models.Post;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServiceImp {

    @GET("posts/{id}")
    Observable<Post> getPost(@Path("id") Integer id);

    //region Bad Request Test
    @GET("status/{code}")
    Observable<Post> getPostBadRequest(@Path("code") Integer code);
    //endregion

}
