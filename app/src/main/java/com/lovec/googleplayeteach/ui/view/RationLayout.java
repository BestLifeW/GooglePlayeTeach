package com.lovec.googleplayeteach.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.lovec.googleplayeteach.R;

/**
 * 自定义图片显示比例
 * Created by lovec on 2016/8/28.
 */
public class RationLayout extends FrameLayout {


    private float ratio;

    public RationLayout(Context context) {
        super(context);
    }

    public RationLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //attrs.getAttributeFloatValue("","ratio",-1);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RationLayout);
        //属性名+具体属性名字段名称 自动生成
        ratio = typedArray.getFloat(R.styleable.RationLayout_ratio, -1);
        typedArray.recycle();
    }

    public RationLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //宽度确定，高度不确定，ratio合法，才进行计算
        if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY && ratio > 0) {
            //图片宽度等于左侧-右侧
            int imageWidth = width - getPaddingLeft() - getPaddingRight();
            //图片高度等于图片宽度
            int imageHeight = (int) (imageWidth / ratio + 0.5);
            //图片高度等于
            height = imageHeight + getPaddingTop() + getPaddingBottom();


            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        }
        //按照最新的高度测量
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
