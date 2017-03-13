package com.vega.webvttparser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.Settings;

/**
 * Created by binhbt on 10/30/2016.
 */
public class BitmapUtil {
    public static Bitmap getBitmap(String photoPath){
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            //options.inJustDecodeBounds = true;
            //options.inSampleSize = 2;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap bitmap = BitmapFactory.decodeFile(photoPath, options);
            return bitmap;
        }catch (Exception e){
            e.printStackTrace();
            System.gc();
            return null;
        }
    }
}
