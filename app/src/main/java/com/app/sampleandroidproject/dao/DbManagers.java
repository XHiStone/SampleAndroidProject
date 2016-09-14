package com.app.sampleandroidproject.dao;

import android.content.Context;

import com.app.sampleandroidproject.beans.result.DaoMaster;
import com.app.sampleandroidproject.beans.result.DaoSession;
import com.app.sampleandroidproject.beans.result.SysUserResponseVo;
import com.app.sampleandroidproject.beans.result.SysUserResponseVoDao;

import org.greenrobot.greendao.database.Database;

import java.util.List;

/**
 * SampleAndroidProject
 * com.app.sampleandroidproject.dao
 *
 * @Author: xie
 * @Time: 2016/9/8 9:37
 * @Description:
 */

public class DbManagers {

    private static DbManagers instance;
    private SysUserResponseVoDao userDao;
    private DaoSession daoSession;

    public DbManagers(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "sample-db", null);
        Database db = devOpenHelper.getWritableDb();
//        Database db = helper.getEncryptedWritableDb("pwd");
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        if (userDao == null) userDao = daoSession.getSysUserResponseVoDao();
    }

    public static DbManagers getDbManagers(Context context) {
        if (instance == null) {
            instance = new DbManagers(context);
        }
        return instance;
    }

    public void insertUser(SysUserResponseVo user) {
        userDao.insert(user);
    }

    public List<SysUserResponseVo> queryUser() {
        List<SysUserResponseVo> userResponseVo = userDao.queryBuilder()
//                .where(SysUserResponseVoDao.Properties.UserName.eq("张二月"))
                .build().list();
        return userResponseVo;
    }

    public String usersCount() {
        return String.valueOf(userDao.count());
    }

    public void deleteUsers() {
        userDao.deleteAll();
    }

    public void deleteUser(Long key) {
        userDao.deleteByKey(key);
    }


    public void upUser(SysUserResponseVo user) {
        userDao.update(user);
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
