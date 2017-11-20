package com.vega.webvttparser;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by binhbt on 10/30/2016.
 */
public class SpriteSheetManager {
    public static final int LIMIT = 6;
    static volatile SpriteSheetManager instance;

    public static SpriteSheetManager getDefault(Context ctx) {
        if (instance == null) {
            synchronized (SpriteSheetManager.class) {
                if (instance == null) {
                    instance = new SpriteSheetManager(ctx);
                }
            }
        }
        return instance;
    }

    private WeakHashMap<String, Bitmap> spriteSheetMap = new WeakHashMap<>();
    private Context ctx;

    public SpriteSheetManager(Context ctx) {
        this.ctx = ctx;
    }

    public void put(String filePath) {
        if (spriteSheetMap.size() < LIMIT) {
            spriteSheetMap.put(filePath, BitmapUtil.getBitmap(filePath));
        } else {

            //Remove index 0
            for(Iterator<Map.Entry<String, Bitmap>> it = spriteSheetMap.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, Bitmap> entry = it.next();
                if (entry.getValue() != null){
                    //Log.e("removespritesheet at 0", "remove "+entry.getKey());
                    entry.getValue().recycle();
                    it.remove();
                    break;
                }
            }
        }

    }
    public Bitmap get(String filePath){
        if (spriteSheetMap.get(filePath) == null){
            put(filePath);
        }
        return spriteSheetMap.get(filePath);
    }
    public void release(){
        if (spriteSheetMap ==null || spriteSheetMap.size() ==0) return;
        //Remove index 0
        for(Iterator<Map.Entry<String, Bitmap>> it = spriteSheetMap.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, Bitmap> entry = it.next();
            if (entry.getValue() != null){
                //Log.e("removespritesheet at 0", "remove "+entry.getKey());
                entry.getValue().recycle();
                entry.setValue(null);
                //it.remove();
            }
        }
        spriteSheetMap.clear();
    }
    public static Bitmap getThumb(Context ctx, long position, List<SubVtt> subVttList){
        return getThumbSub(getSub(position, subVttList), ctx);
    }
    public static SubVtt getSub(long position, List<SubVtt> subVttList){
        if (subVttList == null) return null;
        for (SubVtt subVtt:subVttList
             ) {
            if (subVtt.getStartTimeStamp() <=position && position <=subVtt.getEndTimeStamp()){
                return subVtt;
            }
        }
        return null;
    }
    public static Bitmap getThumbSub(SubVtt subVtt, Context ctx){
        if (subVtt == null) return null;
        File f = FileUtil.getExternalFile(ctx, getBase64(subVtt.getSpriteSheetUrl()));
        Bitmap bitmap = SpriteSheetManager.getDefault(ctx).get(f.getAbsolutePath());
        if (bitmap != null) {
            //Bitmap drawableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            Bitmap drawableBitmap = null;
            try {
                drawableBitmap = Bitmap.createBitmap(bitmap, subVtt.getX(), subVtt.getY(), subVtt.getW(), subVtt.getH());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return drawableBitmap;
        }
        return null;
    }
    public static String getBase64(final String input) {
        if (input == null) return null;
        return Base64.encodeToString(input.getBytes(), Base64.URL_SAFE);
    }
}
