package com.vega.fabricgagcm;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by binhbt on 12/14/2016.
 */
public class GAUtil {
    private static GAUtil instant;
    protected Tracker tracker;
    protected boolean isGaEnable = false;
    public static GAUtil getInstant(Context ctx, String gaId) {
        if (instant == null) {
            synchronized (GAUtil.class) {
                if (instant == null) {
                    instant = new GAUtil();
                    instant.isGaEnable = !TextUtils.isEmpty(gaId);
                    if (instant.isGaEnable) {
                        GoogleAnalytics analytics = GoogleAnalytics.getInstance(ctx.getApplicationContext());
                        analytics.enableAutoActivityReports((Application) ctx.getApplicationContext());
                        instant.tracker = analytics.newTracker(gaId);
                    }
                }
            }
        }
        return instant;
    }
    public static GAUtil getInstant(){
        if (instant == null) {
            instant = new GAUtil();
        }
        return instant;
    }
    public static void setSingleton(Context ctx, String gaId){
        if (instant == null) {
            synchronized (GAUtil.class) {
                if (instant == null) {
                    instant = new GAUtil();
                    instant.isGaEnable = !TextUtils.isEmpty(gaId);

                    if (instant.isGaEnable) {
                        GoogleAnalytics analytics = GoogleAnalytics.getInstance(ctx.getApplicationContext());
                        analytics.enableAutoActivityReports((Application) ctx.getApplicationContext());
                        instant.tracker = analytics.newTracker(gaId);
                    }
                }
            }
        }
    }
    public void sendScreen(String screenName) {
        if (isGaEnable) {
            tracker.setScreenName(screenName);
            tracker.send(new HitBuilders.AppViewBuilder().build());
        }
    }

    public void sendEvent(String category, String action, String label) {
        if (isGaEnable) {
            tracker.send(new HitBuilders.EventBuilder()
                    .setCategory(category)
                    .setAction(action)
                    .setLabel(label)
                    .build());
        }
    }

}
