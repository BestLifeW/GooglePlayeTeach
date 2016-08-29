package com.lovec.googleplayeteach.utils;

import com.lidroid.xutils.BitmapUtils;

/**
 * Created by lovec on 2016/8/28.
 */
public class BitmapHelper {
    private static BitmapUtils mBitmapUtils = null;

    //单例
    public static BitmapUtils getBitmapUtils() {
        if (mBitmapUtils == null) {
            synchronized (BitmapHelper.class) {
                if (mBitmapUtils == null) {
                    mBitmapUtils = new BitmapUtils(UIUtils.getContext());
                }
            }

        }
        return mBitmapUtils;
    }
}