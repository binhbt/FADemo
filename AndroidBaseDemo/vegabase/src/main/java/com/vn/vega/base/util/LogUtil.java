package com.vn.vega.base.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by binhbt on 8/17/2016.
 */
public class LogUtil {
    public static String getExceptionStackTrace(Throwable e){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString(); // stack trace as a string
    }
}
