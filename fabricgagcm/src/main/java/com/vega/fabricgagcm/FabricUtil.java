package com.vega.fabricgagcm;

import android.content.Context;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by binhbt on 12/14/2016.
 */
public class FabricUtil {
    public static void initFabric(Context ctx){
        Fabric.with(ctx, new Crashlytics());
    }

}
