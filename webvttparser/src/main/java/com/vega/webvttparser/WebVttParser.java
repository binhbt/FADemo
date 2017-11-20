package com.vega.webvttparser;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by binhbt on 10/27/2016.
 */
public class WebVttParser {
    public static List<SubVtt> parse(Context ctx, String path){
        String content = FileUtil.readFileAsString(ctx, path);
        String[] strList = content.split("\n\n");
        List<SubVtt> subs = new ArrayList<>();
        for(int i=0; i<strList.length; i++){
            String str = strList[i];
            if (str.contains("WEBVTT")){
                continue;
            }
            subs.add(parse(str));
        }
        return subs;
    }
    public static SubVtt parse(String content){
        String[] lines = content.split("\n");
        if (lines != null && lines.length >1){
            String line1 = lines[0];
            String line2 = lines[1];
            String times[] = line1.split("-->");
            String startTime ="";
            String endtime = "";
            if (times != null && times.length >1){
                startTime = times[0].trim();
                endtime = times[1].trim();
            }
            String spriteSheetData[] = line2.split("#");
            String spriteSheetUrl ="";
            String spriteSeetPos ="";
            if (spriteSheetData != null && spriteSheetData.length >1){
                spriteSheetUrl = spriteSheetData[0];
                spriteSeetPos = spriteSheetData[1];
            }
            spriteSeetPos = spriteSeetPos.replace("xywh=","");
            String lsPos[] = spriteSeetPos.split(",");
            int x =0;
            int y =0;
            int w =0;
            int h =0;
            if (lsPos != null &&lsPos.length >3){
                x = Integer.parseInt(lsPos[0]);
                y = Integer.parseInt(lsPos[1]);
                w = Integer.parseInt(lsPos[2]);
                h = Integer.parseInt(lsPos[3]);
            }
            SubVtt subVtt = new SubVtt(startTime, endtime, spriteSheetUrl, x, y, w, h);
            return subVtt;
        }
        return new SubVtt();
    }

}
