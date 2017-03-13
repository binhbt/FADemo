package com.vn.vega.net;

/**
 * Created by binhbt on 8/23/2016.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.prefs.Preferences;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ReceivedCookiesInterceptor implements Interceptor {
    public static String PREF_COOKIES ="COOKIES";
    private Context ctx;
     public ReceivedCookiesInterceptor(Context ctx){
         this.ctx = ctx;
     }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
            if (preferences != null) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putStringSet(PREF_COOKIES, cookies);
                editor.commit();
            }
        }

        return originalResponse;
    }
}