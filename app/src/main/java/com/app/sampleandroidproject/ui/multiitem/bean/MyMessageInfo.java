package com.app.sampleandroidproject.ui.multiitem.bean;


public class MyMessageInfo {

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

    public int getUSER_NEWS_TYPE() {
        return USER_NEWS_TYPE;
    }

    public void setUSER_NEWS_TYPE(int uSER_NEWS_TYPE) {
        USER_NEWS_TYPE = uSER_NEWS_TYPE;
    }

    public int getUSER_NEWS_ISREAD() {
        return USER_NEWS_ISREAD;
    }

    public void setUSER_NEWS_ISREAD(int uSER_NEWS_ISREAD) {
        USER_NEWS_ISREAD = uSER_NEWS_ISREAD;
    }

    public String getUSER_NEWS_ID() {
        return USER_NEWS_ID;
    }

    public void setUSER_NEWS_ID(String uSER_NEWS_ID) {
        USER_NEWS_ID = uSER_NEWS_ID;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUSER_NEWS_TITLE() {
        return USER_NEWS_TITLE;
    }

    public void setUSER_NEWS_TITLE(String uSER_NEWS_TITLE) {
        USER_NEWS_TITLE = uSER_NEWS_TITLE;
    }

    public String getUSER_NEWS_CONTENT() {
        return USER_NEWS_CONTENT;
    }

    public void setUSER_NEWS_CONTENT(String uSER_NEWS_CONTENT) {
        USER_NEWS_CONTENT = uSER_NEWS_CONTENT;
    }

}
