package com.app.sampleandroidproject.utils;

import android.net.Uri;
import android.support.v4.content.ContextCompat;

import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.view.DrawableProgressBar;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * trunk
 * com.iss.innoz.utils
 *
 * @Author: xie
 * @Time: 2016/11/4 13:59
 * @Description:
 */


public class FrescoUtil {

    public static void displayImage(SimpleDraweeView image, String url) {
        displayImage(image, url, true);
    }

    public static void displayImage(SimpleDraweeView image, String s, boolean showProgress) {
        displayImage(image, s, showProgress, ScalingUtils.ScaleType.CENTER_CROP);
    }

    public static void displayImage(SimpleDraweeView image, String s,
                                    boolean showProgress, ScalingUtils.ScaleType type) {
        GenericDraweeHierarchyBuilder builder =
                new GenericDraweeHierarchyBuilder(image.getResources());
        GenericDraweeHierarchy hierarchy;
        hierarchy = builder
                .setActualImageScaleType(type)//最终图片剪切模式
                .setPressedStateOverlay(ContextCompat.getDrawable(image.getContext(), R.drawable.preview_hint_bg))
                .build();
        if (showProgress) {
            hierarchy.setProgressBarImage(new DrawableProgressBar(image.getWidth(), image.getHeight()),
                    ScalingUtils.ScaleType.FIT_XY);
        }
        image.setHierarchy(hierarchy);
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(s))
                .setProgressiveRenderingEnabled(true)
                .build();
        displayImage(image, request);
    }

    public static void displayImage(SimpleDraweeView image,
                                    ImageRequest request) {
        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(image.getController())
                .build();
        image.setController(controller);
    }
}
