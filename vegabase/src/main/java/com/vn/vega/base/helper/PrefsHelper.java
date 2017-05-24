package com.vn.vega.base.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.vn.vega.base.R;
import com.vn.vega.base.util.VLog;

/**
 * Created by binhbt on 8/1/2016.
 */
public class PrefsHelper {
    static PrefsHelper instance;

    SharedPreferences prefs;

    public static void init(Context context) {
        if (instance == null) {
            instance = new PrefsHelper();
            instance.prefs = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE);
        }
    }

    public static PrefsHelper getInstance() {
        return instance;
    }

    /**
     * Getters *
     */
    public String getString(String key) {
        return prefs.getString(key, "");
    }

    public int getInt(String key) {
        return prefs.getInt(key, -1);
    }

    /**
     * Setters *
     */
    public void saveString(String key, String value) {
        if (value == null) {
            VLog.e("PreferenceHandler", key + ", value is null");
            return;
        }
        prefs.edit().putString(key, value).apply();
    }

    public void saveInt(String key, int value) {
        prefs.edit().putInt(key, value).apply();
    }
}