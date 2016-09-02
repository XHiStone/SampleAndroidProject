package com.app.sampleandroidproject.beans.result;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

/**
 * RxJavaDemo
 * com.isoftstone.rxjavademo.beans.result
 *
 * @Author: xie
 * @Time: 2016/8/19 14:25
 * @Description:
 */
@Entity
public class SysUserResponseVo {
    public int bindingStatus;
    public String deptId;
    public String deptName;
    public String email;
    public String homeTown;
    public int isGesturePassword;
    public int isSound;
    public int isStopSpeak;
    public int isVibration;
    public String mobile;
    public String personalSign;
    public String postId;
    public String postLevelName;
    public String postName;
    public String schoolName;
    public String sex;
    @Transient
    public String token;
    public String userId;
    public String userName;
    @Generated(hash = 664217492)
    public SysUserResponseVo(int bindingStatus, String deptId, String deptName,
            String email, String homeTown, int isGesturePassword, int isSound,
            int isStopSpeak, int isVibration, String mobile, String personalSign,
            String postId, String postLevelName, String postName,
            String schoolName, String sex, String userId, String userName) {
        this.bindingStatus = bindingStatus;
        this.deptId = deptId;
        this.deptName = deptName;
        this.email = email;
        this.homeTown = homeTown;
        this.isGesturePassword = isGesturePassword;
        this.isSound = isSound;
        this.isStopSpeak = isStopSpeak;
        this.isVibration = isVibration;
        this.mobile = mobile;
        this.personalSign = personalSign;
        this.postId = postId;
        this.postLevelName = postLevelName;
        this.postName = postName;
        this.schoolName = schoolName;
        this.sex = sex;
        this.userId = userId;
        this.userName = userName;
    }
    @Generated(hash = 1779678585)
    public SysUserResponseVo() {
    }
    public int getBindingStatus() {
        return this.bindingStatus;
    }
    public void setBindingStatus(int bindingStatus) {
        this.bindingStatus = bindingStatus;
    }
    public String getDeptId() {
        return this.deptId;
    }
    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
    public String getDeptName() {
        return this.deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getHomeTown() {
        return this.homeTown;
    }
    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }
    public int getIsGesturePassword() {
        return this.isGesturePassword;
    }
    public void setIsGesturePassword(int isGesturePassword) {
        this.isGesturePassword = isGesturePassword;
    }
    public int getIsSound() {
        return this.isSound;
    }
    public void setIsSound(int isSound) {
        this.isSound = isSound;
    }
    public int getIsStopSpeak() {
        return this.isStopSpeak;
    }
    public void setIsStopSpeak(int isStopSpeak) {
        this.isStopSpeak = isStopSpeak;
    }
    public int getIsVibration() {
        return this.isVibration;
    }
    public void setIsVibration(int isVibration) {
        this.isVibration = isVibration;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getPersonalSign() {
        return this.personalSign;
    }
    public void setPersonalSign(String personalSign) {
        this.personalSign = personalSign;
    }
    public String getPostId() {
        return this.postId;
    }
    public void setPostId(String postId) {
        this.postId = postId;
    }
    public String getPostLevelName() {
        return this.postLevelName;
    }
    public void setPostLevelName(String postLevelName) {
        this.postLevelName = postLevelName;
    }
    public String getPostName() {
        return this.postName;
    }
    public void setPostName(String postName) {
        this.postName = postName;
    }
    public String getSchoolName() {
        return this.schoolName;
    }
    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
}
