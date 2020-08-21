package com.liujingyao.favorites.Util;

/**
 * 字符串操作工具类，并没有用上
 */
public class StringUtil {
    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str) {
        if(str == null || str.trim().equals("")) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否不为空
     */
    public static boolean isNotEmpty(String str) {
        if(str != null && !str.trim().equals("")) {
            return true;
        }
        return false;
    }
}
