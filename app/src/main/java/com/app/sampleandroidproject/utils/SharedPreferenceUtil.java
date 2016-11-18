package com.app.sampleandroidproject.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author ty
 * @createdate 2012-8-17 下午12:22:19
 * @Description: SharedPreference
 */
public class SharedPreferenceUtil {

    public static final String SHARED_PREFERENCE_NAME = "Innoz";   //SharedPreference操作的文件

    /**
     * @param context
     * @param key
     * @param value
     * @author ty
     * @createdate 2012-8-17 下午12:26:01
     * @Description: 保存int数值
     */
    public static void saveInt(Context context, String key, int value) {
        Editor editor = context.getSharedPreferences(
                SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * @param context
     * @param key
     * @return
     * @author ty
     * @createdate 2012-8-17 下午12:28:46
     * @Description: 获取保存的int数值
     */
    public static int getInt(Context context, String key) {
        SharedPreferences shared = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        int value = shared.getInt(key, 0);
        return value;
    }

    /**
     * @param context
     * @param key
     * @param value
     * @author ty
     * @createdate 2012-8-17 下午12:26:01
     * @Description: 保存long数值
     */
    public static void saveLong(Context context, String key, long value) {
        Editor editor = context.getSharedPreferences(
                SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * @param context
     * @param key
     * @return
     * @author ty
     * @createdate 2012-8-17 下午12:28:46
     * @Description: 获取保存的long数值
     */
    public static long getLong(Context context, String key) {
        SharedPreferences shared = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        long value = shared.getLong(key, 0L);
        return value;
    }

    /**
     * @param context
     * @param key
     * @param value
     * @author miaoxin.ye
     * @createdate 2012-10-13 上午11:50:33
     * @Description: 保存boolean值
     */
    public static void saveBoolean(Context context, String key, boolean value) {
        Editor editor = context.getSharedPreferences(
                SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * @param context
     * @param key
     * @return
     * @author miaoxin.ye
     * @createdate 2012-10-13 上午11:51:40
     * @Description: 获取boolean值
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences shared = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        boolean value = shared.getBoolean(key, false);
        return value;
    }

    /**
     * @param context
     * @param key
     * @param value
     * @author ty
     * @createdate 2012-8-17 下午12:26:01
     * @Description: 保存String数值
     */
    public static void saveString(Context context, String key, String value) {
        Editor editor = context.getSharedPreferences(
                SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * @param context
     * @param key
     * @return
     * @author ty
     * @createdate 2012-8-17 下午12:28:46
     * @Description: 获取保存的String数值
     */
    public static String getString(Context context, String key) {
        SharedPreferences shared = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        String value = shared.getString(key, "");
        return value;
    }

    /**
     * @param context
     * @param key
     * @author bo.wang
     * @createdate 2012-10-29 下午2:21:59
     * @Description 清空本地的缓存
     */
    public static void removeString(Context context, String key) {
        SharedPreferences shared = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        Editor editor = shared.edit();
        editor.remove(key);
        editor.commit();
    }


}
