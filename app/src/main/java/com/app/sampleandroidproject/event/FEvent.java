package com.app.sampleandroidproject.event;

/**
 * trunk
 * com.iss.innoz.event
 *
 * @Author: xie
 * @Time: 2016/10/17 16:29
 * @Description:
 */


public class FEvent implements IEvent{
    public String error;

    public FEvent(String error) {
        this.error = error;
    }
}
