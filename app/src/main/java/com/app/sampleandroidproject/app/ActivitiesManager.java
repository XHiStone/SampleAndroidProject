package com.app.sampleandroidproject.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * SampleAndroidProject
 * com.app.sampleandroidproject.app
 *
 * @Author: xie
 * @Time: 2016/9/5 10:19
 * @Description:
 */

public class ActivitiesManager {
    /**
     * 栈集合是线程安全的
     */
    private Stack<Activity> stack;
    private final static ActivitiesManager instance = new ActivitiesManager();

    public static ActivitiesManager getInstance() {
        return instance;
    }

    /**
     * 添加Activity入栈
     **/
    public void addActivity(Activity activity) {
        if (stack == null)
            stack = new Stack<>();
        stack.add(activity);
    }

    /**
     * 结束最后一个Activity
     */
    public void finishActivity() {
        Activity activity = stack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定Activity
     */
    public void finishActivity(Activity activity) {
        if (null != activity) {
            stack.remove(activity);
        }
        activity.finish();
    }

    public void removeActivity(Activity activity) {
        if (stack.contains(activity)) {
            stack.remove(activity);
        }
    }

    /**
     * 结束指定类名Activity
     **/
    public void finishActivity(Class<?> cls) {
        for (Activity activity : stack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 退出所有的Activity
     */
    public void finishAllActivity() {
        try {
            for (Activity activity : stack) {
                if (activity != null) {
                    activity.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stack.clear();
        }
    }

    /**
     * 退出应用程序
     */
    public void exitApp(Context context) {
        try {
            finishAllActivity();
            ActivityManager activitysManager = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activitysManager.killBackgroundProcesses(context.getPackageName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public Stack<Activity> getStack() {
        return stack;
    }
}
