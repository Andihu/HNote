package com.hdemo.hnote.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Copyright (C), 2015-2020
 * FileName: XySpUtil
 * Author: hujian
 * Date: 2020/11/17 12:02
 * History:
 * <author> <time> <version> <desc>
 */
public class SpUtil {
    private static SharedPreferences sSP;
    private static SpUtil mInstance = LazyHolder.INSTANCE;

    public static SpUtil getInstance(Context context) {
        if (sSP == null) {
            sSP = context.getSharedPreferences("base_info", Context.MODE_PRIVATE);
        }
        return mInstance;
    }

    private static class LazyHolder {
        private static final SpUtil INSTANCE = new SpUtil();
    }

    public void put(String key, String value) {
        SharedPreferences.Editor editor = sSP.edit();
        editor.putString(key, value);
        editor.apply();
    }
    public void put(String key, int value) {
        SharedPreferences.Editor editor = sSP.edit();
        editor.putInt(key, value);
        editor.apply();
    }


    public void put(String key, boolean value) {
        SharedPreferences.Editor editor = sSP.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public void remove(String key) {
        SharedPreferences.Editor editor = sSP.edit();
        editor.remove(key);
        editor.apply();
    }

    public String get(String key, String defalut) {
        return sSP.getString(key, defalut);
    }

    public int get(String key,int defaultValue){
        return sSP.getInt(key,defaultValue);
    }

    public boolean get(String key, boolean defalut) {
        return sSP.getBoolean(key, defalut);
    }
}

