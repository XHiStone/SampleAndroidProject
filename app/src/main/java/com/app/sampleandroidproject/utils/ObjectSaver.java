package com.app.sampleandroidproject.utils;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * trunk
 * com.iss.innoz.utils
 *
 * @Author: xie
 * @Time: 2016/11/10 18:59
 * @Description:
 */


public class ObjectSaver implements ISaver {

    private static AtomicBoolean isLogin = new AtomicBoolean(false);

    @Override
    public void removeAll() {
        isLogin.set(false);

    }

    @Override
    public boolean isLogin() {
        return isLogin.get();
    }

    @Override
    public void setLogin(boolean login) {
        isLogin.set(login);
    }
}
