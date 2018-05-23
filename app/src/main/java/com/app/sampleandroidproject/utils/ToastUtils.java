package com.example.administrator.myapplication.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Field;

/**
 * Created by xie on 2018/3/27.
 */

public class ToastUtils {
    private static Field sField_TN ;
    private static Field sField_TN_Handler ;
    static {
        try {
            sField_TN = Toast.class.getDeclaredField("mTN");
            sField_TN.setAccessible(true);
            sField_TN_Handler = sField_TN.getType().getDeclaredField("mHandler");
            sField_TN_Handler.setAccessible(true);
        } catch (Exception e) {}
    }

    private static void hook(Toast toast) {
        try {
            Object tn = sField_TN.get(toast);
            Handler preHandler = (Handler)sField_TN_Handler.get(tn);
            sField_TN_Handler.set(tn,new SafelyHandlerWarpper(preHandler));
        } catch (Exception e) {}
    }

    public static void showToast(Context context,CharSequence cs, int length) {
        Toast toast = Toast.makeText(context,cs,length);
        hook(toast);
        toast.show();
    }
    private static class SafelyHandlerWarpper extends Handler {

//        private Handler impl;

        public SafelyHandlerWarpper(Handler impl) {
//            this.impl = impl;
        }

        @Override
        public void dispatchMessage(Message msg) {
            try {
                super.dispatchMessage(msg);
            } catch (Exception e) {
                Log.e("tag","主线程阻塞导致消息发送超时Toast的token被删除无法显示toast");
            }
        }

        @Override
        public void handleMessage(Message msg) {
//            impl.handleMessage(msg);//需要委托给原Handler执行
        }
    }
}
