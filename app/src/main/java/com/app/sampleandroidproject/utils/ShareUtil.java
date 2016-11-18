package com.app.sampleandroidproject.utils;

import android.app.Activity;
import android.text.TextUtils;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMVideo;
import com.umeng.socialize.media.UMusic;

/**
 * trunk
 * com.iss.innoz.utils
 *
 * @Author: xie
 * @Time: 2016/10/25 17:12
 * @Description:
 */


public class ShareUtil {

    private final static ShareUtil shareUtil = new ShareUtil();

    public static ShareUtil getShareUtil() {
        return shareUtil;
    }

    private void shareBase(SHARE_MEDIA share_media, Activity activity, ShareAction shareAction,
                           String title, String content, String url, UMShareListener umShareListener) {
        if (!TextUtils.isEmpty(title)) {
            shareAction.withTitle(title);
        }
        if (!TextUtils.isEmpty(content)) {
            shareAction.withText(content);
        }
        if (!TextUtils.isEmpty(url)) {
            shareAction.withTargetUrl(url);
        }
        shareAction.setPlatform(share_media).setCallback(umShareListener).share();
    }

    public void shareWithText(SHARE_MEDIA share_media, Activity activity, String title, String content,
                              String url, UMShareListener umShareListener) {
        ShareAction shareAction = new ShareAction(activity);
        shareBase(share_media, activity, shareAction, title, content, url, umShareListener);
    }

    public void shareWithPhoto(SHARE_MEDIA share_media, Activity activity, String title,
                               String content, String url, String imageurl, UMShareListener umShareListener) {
        UMImage image = new UMImage(activity, imageurl);
        ShareAction shareAction = new ShareAction(activity);
        shareAction.withMedia(image);
        shareBase(share_media, activity, shareAction, title, content, url, umShareListener);
    }

    public void shareWithMusic(SHARE_MEDIA share_media, Activity activity, String title, String content,
                               String imgurl, String musicurl, UMShareListener umShareListener) {
        UMusic music = new UMusic(musicurl);
        if (!TextUtils.isEmpty(title)) {
            music.setTitle(title);
        }
        if (!TextUtils.isEmpty(content)) {
            music.setDescription(content);
        }
        if (!TextUtils.isEmpty(imgurl)) {
            music.setThumb(imgurl);
        }
        ShareAction shareAction = new ShareAction(activity);
        shareAction.withMedia(music);
        shareAction.setPlatform(share_media).setCallback(umShareListener).share();
    }

    public void shareWithVideo(SHARE_MEDIA share_media, Activity activity, String title, String content, String imgurl,
                               String videourl, UMShareListener umShareListener) {
        UMVideo video = new UMVideo(videourl);
        if (!TextUtils.isEmpty(title)) {
            video.setTitle(title);
        }
        if (!TextUtils.isEmpty(content)) {
            video.setDescription(content);
        }
        if (!TextUtils.isEmpty(imgurl)) {
            video.setThumb(imgurl);
        }
        ShareAction shareAction = new ShareAction(activity);
        shareAction.withMedia(video);
        shareAction.setPlatform(share_media).setCallback(umShareListener).share();
    }


}
