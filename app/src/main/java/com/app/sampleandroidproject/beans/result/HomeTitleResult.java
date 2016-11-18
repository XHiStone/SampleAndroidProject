package com.app.sampleandroidproject.beans.result;

import java.util.List;

/**
 * trunk
 * com.iss.innoz.bean.result
 *
 * @Author: xie
 * @Time: 2016/11/7 16:24
 * @Description:
 */


public class HomeTitleResult {

    public String message;
    public String records;
    public int success;

    public List<ResultEntity> result;

    public static class ResultEntity {
        public String id;
        public String title;
        public String picUrl;
        public String informationSource;
        public String description;
        public String time;
        public String url;
        public int viewNum;
        public Object content;
        public String infoType;
        public List<String> tags;
    }
}
