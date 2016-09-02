package com.app.sampleandroidproject.beans;

import java.util.List;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.beans.result
 *
 * @Author: xie
 * @Time: 2016/8/19 15:42
 * @Description:
 */

public class PagerBean<T> {
    public int currPage;
    public int offsetIndex;
    public int pageSize;
    public int totalCount;
    public int totalPage;
    public List<T> content;
}
