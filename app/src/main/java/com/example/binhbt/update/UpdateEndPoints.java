package com.example.binhbt.update;

import com.example.binhbt.myapplication.model.User;

import java.util.List;
import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by binhbt on 8/17/2016.
 */
public interface UpdateEndPoints {
    String API_ENDPOINT = "http://192.168.42.122/app/";
    String URL_DOWNLOAD_APP = "http://192.168.42.122/app/app1.0.apk";
    @GET("version.php")
    public Observable<VersionApp>
    checkVersion();

    @GET("user_{userId}.json")
    public Observable<User>
    userEntityById(@Path("userId") int userId);


    @FormUrlEncoded
    @POST("signup")
    public Observable<User> signUp(@FieldMap Map<String, String> params);

    @GET("list_user")
    public Observable<User> listUser(@QueryMap Map<String, String> params);
}