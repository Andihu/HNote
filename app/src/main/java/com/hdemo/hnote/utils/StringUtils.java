package com.hdemo.hnote.utils;

import android.util.Log;

public class StringUtils {

    private StringUtils() {
    }

    /**
     * 创建占位符
     *
     * @param size 数据
     * @return 占位符
     */
    public static String makePlaceholders(int size) {
        if (size <= 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder(size * 2 - 1);
        sb.append("?");
        for (int i = 1; i < size; i++) {
            sb.append(",?");
        }
        return sb.toString();
    }

    public static boolean isNull(String str) {
        return str == null || str.trim().length() == 0
                || str.trim().equalsIgnoreCase("null");
    }

    /**
     * Convert a Java String to byte array using a charset name
     *
     * @param string
     * @param charsetName
     * @return
     */
    public static byte[] stringToBytes(String string, String charsetName) {
        if (string == null) {
            return null;
        }
        try {
            return string.getBytes(charsetName);
        } catch (Throwable e) {
            Log.e("StringUtils","StringUtils.stringToBytes error: " + e, e);
            return string.getBytes();
        }
    }

    public static int getIntValue(String val, int defaultVal) {
        try {
            return StringUtils.isNull(val) ? defaultVal : Integer.parseInt(val);
        } catch (Throwable e) {
            Log.e("StringUtils","StringUtils.getIntValue error: " + e, e);
        }
        return defaultVal;
    }
}
