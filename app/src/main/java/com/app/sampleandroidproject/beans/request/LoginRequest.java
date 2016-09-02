package com.app.sampleandroidproject.beans.request;

import javax.inject.Inject;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.beans.request
 *
 * @Author: xie
 * @Time: 2016/8/19 14:00
 * @Description:
 */

public class LoginRequest {

    public String userLoginName;
    public String password;
    public String bindingImei;
    public String userVersion;
    public int userSystem;


    @Inject
    public LoginRequest() {

    }

    public void setUser(String userLoginName, String password, String bindingImei,
                        String userVersion, int userSystem) {
        this.userLoginName = userLoginName;
        this.password = password;
        this.bindingImei = bindingImei;
        this.userVersion = userVersion;
        this.userSystem = userSystem;
    }

//    public LoginRequest(String userLoginName, String password, String bindingImei) {
//        this.userLoginName = userLoginName;
//        this.password = password;
//        this.bindingImei = bindingImei;
//    }
}
