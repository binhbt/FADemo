package com.vn.vega.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by binhbt on 8/23/2016.
 */
public class AddCookiesInterceptor implements Interceptor {
    private Context ctx;
    public AddCookiesInterceptor(Context ctx){
        this.ctx = ctx;
    }
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        if (sharedPreferences != null) {
            HashSet<String> preferences = (HashSet)sharedPreferences.getStringSet(ReceivedCookiesInterceptor.PREF_COOKIES, new HashSet<>());// Preferences.getDefaultPreferences().getStringSet(Preferences.PREF_COOKIES, new HashSet<>());
            for (String cookie : preferences) {
                builder.addHeader("Cookie", cookie);
//                Log.v("OkHttp", "Adding Header: " + cookie); // This is done so I know which headers are being added; this interceptor is used after the normal logging of OkHttp
            }
        }

        return chain.proceed(builder.build());
    }
}