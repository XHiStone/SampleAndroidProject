package com.app.sampleandroidproject.utils;

import java.util.Collection;
import java.util.Map;

/**
*@Title: Check
*@Description:  描述
*@date 2016/10/19 15:21
*@auther xie
*/
public class Check {

    public static boolean isEmpty (CharSequence str) {
        return isNull(str) || str.length() == 0;
    }

    public static boolean isEmpty (Object[] os) {
        return isNull(os) || os.length == 0;
    }

    public static boolean isEmpty (Collection<?> l) {
        return isNull(l) || l.isEmpty();
    }

    public static boolean isEmpty (Map<?, ?> m) {
        return isNull(m) || m.isEmpty();
    }

    public static boolean isNull (Object o) {
        return o == null;
    }
}
