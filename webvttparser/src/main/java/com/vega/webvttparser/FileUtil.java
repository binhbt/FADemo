package com.vega.webvttparser;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by binhbt on 10/27/2016.
 */
public class FileUtil {
    public static void writeStringAsFile(Context ctx, final String fileContents, String path) {
        try {
            FileWriter out = new FileWriter(new File(path));
            out.write(fileContents);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFileAsString(Context ctx, String path) {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(new File(path)));
            while ((line = in.readLine()) != null) stringBuilder.append(line).append("\n");;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
    public static boolean checkFileExist(Context ctx, String fileName) {
        File srt = null;
        try {
            srt = new File(ctx.getCacheDir(), fileName);
            if (srt.exists()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            //Log.e("spriteSheetThumb", "exception in file creation");
        }
        return false;
    }
    public static File getExternalFile(Context ctx, String fileName) {
        File srt = null;
        try {
            srt = new File(ctx.getCacheDir(), fileName);
            if (srt.exists()) {
                return srt;
            }
            srt.createNewFile();
            return srt;
        } catch (Exception e) {
            //Log.e("spriteSheetThumb", "exception in file creation");
        }
        return null;
    }
    public static boolean deleteFile(Context ctx, String fileName){
        File srt = null;
        try {
            srt = new File(ctx.getCacheDir(), fileName);
            if (srt.exists()) {
                srt.delete();
                return true;
            }
            return false;
        } catch (Exception e) {
            //Log.e("spriteSheetThumb", "exception in file creation");
        }
        return false;
    }
}
