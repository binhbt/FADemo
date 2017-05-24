package com.vega.webvttparser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by binhbt on 10/27/2016.
 */
public class SubVtt {
    private String startTime;
    private String stopTime;
    private String spriteSheetUrl;
    private int x;
    private int y;
    private int w;
    private int h;
    private long startTimeStamp;
    private long endTimeStamp;
    public SubVtt() {
    }

    public SubVtt(String startTime, String stopTime, String spriteSheetUrl, int x, int y, int w, int h) {
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.spriteSheetUrl = spriteSheetUrl;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        String strStart = startTime.replace(".000","");
        String strEnd = stopTime.replace(".000","");
        startTimeStamp = stringToTime(strStart);
        endTimeStamp = stringToTime(strEnd);
//        Log.e("startTimeStamp", startTime+startTimeStamp);
//        Log.e("endTimeStamp", stopTime+endTimeStamp);
    }
    public static long stringToTime(String time){
            String ls[] = time.split(":");
        int hour =0;
        int min = 0;
        int sec =0;
        if (ls != null && ls.length >2){
            hour= convertStrToInt(ls[0]);
            min = convertStrToInt(ls[1]);
            sec = convertStrToInt(ls[2]);
        }
        return hour*3600+min*60+sec;
    }
    public static int convertStrToInt(String time){
        if (time.length() ==1){
            return Integer.parseInt(time);
        }
        if (time.length() ==2){
            if (time.startsWith("0")){
                if (time.equals("00")){
                    return 0;
                }
                time = time.replace("0","");
                return Integer.parseInt(time);
            }else{
                return Integer.parseInt(time);
            }
        }
        return 0;
    }
    public static long stringToTime(String time, String format){
        try {
            DateFormat formatter = new SimpleDateFormat(format);
            Date date = (Date) formatter.parse(time);
            return date.getTime();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public long getStartTimeStamp() {
        return startTimeStamp;
    }

    public void setStartTimeStamp(long startTimeStamp) {
        this.startTimeStamp = startTimeStamp;
    }

    public long getEndTimeStamp() {
        return endTimeStamp;
    }

    public void setEndTimeStamp(long endTimeStamp) {
        this.endTimeStamp = endTimeStamp;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStopTime() {
        return stopTime;
    }

    public void setStopTime(String stopTime) {
        this.stopTime = stopTime;
    }

    public String getSpriteSheetUrl() {
        return spriteSheetUrl;
    }

    public void setSpriteSheetUrl(String spriteSheetUrl) {
        this.spriteSheetUrl = spriteSheetUrl;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
}
