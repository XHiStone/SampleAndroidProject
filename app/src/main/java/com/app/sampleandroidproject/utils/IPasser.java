package com.app.sampleandroidproject.utils;

/**
 * Created by IntelliJ IDEA in maimai_a
 * cn.maitian.app.local.abs
 *
 * @Author: xie
 * @Time: 2016/8/5 10:02
 * @Description:
 */
public interface IPasser<T> {

    void save(String id, T t);
    T get(String id);
    T remove(String id);
    boolean contain(String id);
}
