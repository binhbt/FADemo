package com.example.binhbt.myapplication.net;
import com.example.binhbt.myapplication.model.User;
import com.example.binhbt.myapplication.model.VersionApp;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 *
 */
public interface EndPoints {
	public static final String API_ENDPOINT = "http://tvbox.vegacdn.vn/tvbox/file/";

	@GET("other_app.json")
	public Observable<List<VersionApp>> listOtherApp();

	@GET("test/user_list.json")
	public Observable<List<User>>
	userEntityList();

	@GET("test/user_{userId}.json")
	public Observable<User>
	userEntityById(@Path("userId") int userId);


	@FormUrlEncoded
	@POST("signup")
	public Observable<User> signUp(@FieldMap Map<String, String> params);

	@GET("list_user")
	public Observable<User> listUser(@QueryMap Map<String, String> params);
}