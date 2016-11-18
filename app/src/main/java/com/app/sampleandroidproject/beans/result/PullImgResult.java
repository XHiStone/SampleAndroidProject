package com.app.sampleandroidproject.beans.result;

/**
 * trunk
 * com.iss.innoz.bean.result
 *
 * @Author: xie
 * @Time: 2016/11/9 17:27
 * @Description:
 */


public class PullImgResult {

    public String message;

    public ResultEntity result;
    public int success;

    public static class ResultEntity {
        public String name;
        public String type;
        public String size;
        public String url;
    }
}
