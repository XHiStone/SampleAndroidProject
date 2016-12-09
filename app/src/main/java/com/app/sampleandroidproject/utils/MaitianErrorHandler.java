package com.app.sampleandroidproject.utils;


import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.app.BaseApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: MaitianErrorHandler
 * @Description: 网络错误信息
 * @date 2016/9/1 10:57
 * @auther xie
 */
public class MaitianErrorHandler {

    // ---- Http ----
    private static final String MSG_NET_EOF = BaseApplication.getContext().getString(R.string.msg_net_eof);
    private static final String MSG_NET_OFF = BaseApplication.getContext().getString(R.string.msg_net_off);
    private static final String MSG_SOCKET_TIME_OUT = BaseApplication.getContext().getString(R.string.msg_socket_timeout);
    private static final String MSG_CONN_TIME_OUT = BaseApplication.getContext().getString(R.string.msg_connection_timeout);
    private static final String MSG_CHECK_NET = BaseApplication.getContext().getString(R.string.msg_check_net);
    private static final String MSG_NONE_ERROR = BaseApplication.getContext().getString(R.string.msg_none_error);
    private static final String MSG_BAD_REQUEST = BaseApplication.getContext().getString(R.string.msg_bad_request);
    private static final String MSG_UNKNOWNS_HOST = BaseApplication.getContext().getString(R.string.msg_unknow_host);
    private static final String MSG_SYNTAX_ERROR = BaseApplication.getContext().getString(R.string.msg_syntax_error);
    private static final String MSG_CLIENT_ERROR = BaseApplication.getContext().getString(R.string.msg_client_error);
    private static final String MSG_SERVER_ERROR = BaseApplication.getContext().getString(R.string.msg_server_error);
    private static final String MSG_CONNECT_ERROR = BaseApplication.getContext().getString(R.string.msg_connect_error);
    private static final String MSG_PERMISSION_DENY = BaseApplication.getContext().getString(R.string.msg_perssion_deny);
    private static final String MSG_CLIENT_TIMEOUT = BaseApplication.getContext().getString(R.string.msg_client_timeout);
    private static final String MSG_GATEWAY_TIMEOUT = BaseApplication.getContext().getString(R.string.msg_gateway_timeout);

    // ---- Download ----
    private static final String MSG_DOWNLOAD_ENTITY = BaseApplication.getContext().getString(R.string.msg_download_entity);
    private static final String MSG_DOWNLOAD_URL = BaseApplication.getContext().getString(R.string.msg_download_url);

    // ---- Database ----
    private static final String MSG_DB_CREATE_FAILURE = BaseApplication.getContext().getString(R.string.msg_db_create_failure);
    private static final String MSG_DB_DELETE_FAILURE = BaseApplication.getContext().getString(R.string.msg_db_delete_failure);

    public static Map<String, String> EMS = new HashMap<>();

    static {
        EMS.put("0", MSG_NET_OFF);
        EMS.put("1", MSG_CHECK_NET);
        EMS.put("2", MSG_CLIENT_ERROR);
        EMS.put("3", MSG_SERVER_ERROR);
        EMS.put("4", MSG_NONE_ERROR);
        EMS.put("5", MSG_NET_EOF);
        EMS.put("6", MSG_BAD_REQUEST);
        EMS.put("7", MSG_SOCKET_TIME_OUT);
        EMS.put("8", MSG_UNKNOWNS_HOST);
        EMS.put("9", MSG_CONN_TIME_OUT);
        EMS.put("10", MSG_SYNTAX_ERROR);
        EMS.put("11", MSG_CONNECT_ERROR);
        EMS.put("12", MSG_PERMISSION_DENY);
        EMS.put("13", MSG_CLIENT_TIMEOUT);
        EMS.put("14", MSG_GATEWAY_TIMEOUT);

        EMS.put("15", MSG_DOWNLOAD_ENTITY);
        EMS.put("16", MSG_DOWNLOAD_URL);

        EMS.put("17", MSG_DB_CREATE_FAILURE);
        EMS.put("18", MSG_DB_DELETE_FAILURE);
    }
}
