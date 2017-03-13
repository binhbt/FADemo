package com.vega.fabricgagcm;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.appsflyer.AppsFlyerLib;

/**
 * Created by binhbt on 12/14/2016.
 */
public class AppFlyerUtil {
    public static void initAppflyer(Context ctx, String apps_flyer_id){
        if (!TextUtils.isEmpty(apps_flyer_id))
            AppsFlyerLib.getInstance().startTracking((Application) ctx, apps_flyer_id);
    }
}
