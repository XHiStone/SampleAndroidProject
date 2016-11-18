package com.app.sampleandroidproject.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA in maimai_a
 * cn.maitian.app.local
 *
 * @Author: xie
 * @Time: 2016/8/5 10:02
 * @Description:
 */
public class Passer<T> implements IPasser<T> {

    private static final Map<String, Object> map = new HashMap<>();

    @Override
    public void save (String id, T bean) {
        map.put(id, bean);
    }

    @Override
    public T get (String id) {
        if(map.containsKey(id)) {
            return (T) map.get(id);
        }
        return null;
    }

    @Override
    public T remove (String id) {
        if(map.containsKey(id)) {
            return (T) map.remove(id);
        }

        return null;
    }

    @Override
    public boolean contain (String id) {
        return map.containsKey(id);
    }
}
