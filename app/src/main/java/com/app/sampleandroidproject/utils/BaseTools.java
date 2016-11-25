package com.app.sampleandroidproject.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseTools {
    public static Dialog dialog;

    /**
     * @return void
     * @Title: setFullWindow
     * @Description: (Activity沉溺式全屏SystemBar)
     * @params [activity]
     */
    public final static void setFullWindow(Activity activity) {
        View mDecorView = activity.getWindow().getDecorView();
        mDecorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    /**
     * 获取屏幕【宽度】的像素数
     *
     * @param activity
     * @return
     */
    public final static int getWindowsWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 获取屏幕【高度】的像素数
     *
     * @param activity
     * @return
     */
    public final static int getWindowsHeigh(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     * 检查邮箱地址是否正确
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }

        return flag;
    }

    /**
     * double型返回String，小数点后保留2位
     *
     * @param value
     * @return
     */
    public static String getDecimal(Double value) {
        if (null != value) {
            DecimalFormat df = new DecimalFormat("###,###.##");
            return df.format(value);
        }
        return "0";
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 价钱单位换算
     */
    public static String getPrice(int price) {
        return price / 100 + "." + (price % 100 == 0 ? "00" : (price % 100 < 10 ? ("0" + price % 100) : price % 100));
    }
}
