package com.app.sampleandroidproject.ui;

import android.os.Bundle;
import android.widget.ImageView;

import com.app.sampleandroidproject.R;
import com.app.sampleandroidproject.ui.base.BaseActivity;

import butterknife.BindView;

/**
 * SampleAndroidProject
 * com.app.sampleandroidproject.ui
 *
 * @Author: xie
 * @Time: 2016/11/28 15:38
 * @Description:
 */


public class SVGActivity extends BaseActivity {
    @BindView(R.id.img_svg)
    ImageView imgSvg;
    @BindView(R.id.img_svg_40)
    ImageView imgSvg40;

    @Override
    protected int getContentResource() {
        return R.layout.activity_svg;
    }

    @Override
    protected void startWork(Bundle savedInstanceState) {
        setTittleText("SVG");
        imgSvg.setImageDrawable(getResources().getDrawable(R.drawable.ic_dingyue_200dp));
        imgSvg40.setImageDrawable(getResources().getDrawable(R.drawable.ic_dingyue_400dp));

    }

    @Override
    protected void stopWork() {

    }

}
