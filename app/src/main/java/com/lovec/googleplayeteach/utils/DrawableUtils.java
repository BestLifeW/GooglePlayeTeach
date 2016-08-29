package com.lovec.googleplayeteach.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

/**
 * Created by lovec on 2016/8/29.
 */
public class DrawableUtils {

    public static GradientDrawable getGradientDrawable(int color , int radius){
        GradientDrawable shape = new GradientDrawable();

        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(radius);
        shape.setColor(color);
        return shape;
    }
    //获取状态选择器
    public static StateListDrawable getSelector(Drawable normal, Drawable press) {
        StateListDrawable selector = new StateListDrawable();
        selector.addState(new int[] { android.R.attr.state_pressed }, press);// 按下图片
        selector.addState(new int[] {}, normal);// 默认图片

        return selector;
    }

    //获取状态选择器
    public static StateListDrawable getSelector(int normal, int press, int radius) {
        GradientDrawable bgNormal = getGradientDrawable(normal, radius);
        GradientDrawable bgPress = getGradientDrawable(press, radius);
        StateListDrawable selector = getSelector(bgNormal, bgPress);
        return selector;
    }

}
