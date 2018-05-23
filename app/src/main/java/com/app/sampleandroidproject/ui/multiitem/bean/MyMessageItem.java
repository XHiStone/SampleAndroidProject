package com.app.sampleandroidproject.ui.multiitem.bean;

/**
 * SampleAndroidProject
 * com.app.sampleandroidproject.ui.multiitem.bean
 *
 * @Author: xie
 * @Time: 2017/4/24 11:23
 * @Description:
 */


public class MyMessageItem {
    private String createtime;
    private String USER_NEWS_ID;
    private String USER_NEWS_TITLE;
    private String USER_NEWS_CONTENT;
    private int USER_NEWS_ISREAD;
    private int USER_NEWS_TYPE;
    private boolean isDate;

    public boolean isDate() {
        return isDate;
    }

    public void setDate(boolean date) {
        isDate = date;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUSER_NEWS_ID() {
        return USER_NEWS_ID;
    }

    public void setUSER_NEWS_ID(String USER_NEWS_ID) {
        this.USER_NEWS_ID = USER_NEWS_ID;
    }

    public String getUSER_NEWS_TITLE() {
        return USER_NEWS_TITLE;
    }

    public void setUSER_NEWS_TITLE(String USER_NEWS_TITLE) {
        this.USER_NEWS_TITLE = USER_NEWS_TITLE;
    }

    public String getUSER_NEWS_CONTENT() {
        return USER_NEWS_CONTENT;
    }

    public void setUSER_NEWS_CONTENT(String USER_NEWS_CONTENT) {
        this.USER_NEWS_CONTENT = USER_NEWS_CONTENT;
    }

    public int getUSER_NEWS_ISREAD() {
        return USER_NEWS_ISREAD;
    }

    public void setUSER_NEWS_ISREAD(int USER_NEWS_ISREAD) {
        this.USER_NEWS_ISREAD = USER_NEWS_ISREAD;
    }

    public int getUSER_NEWS_TYPE() {
        return USER_NEWS_TYPE;
    }

    public void setUSER_NEWS_TYPE(int USER_NEWS_TYPE) {
        this.USER_NEWS_TYPE = USER_NEWS_TYPE;
    }
}
