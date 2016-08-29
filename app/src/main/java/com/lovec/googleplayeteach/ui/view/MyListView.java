package com.lovec.googleplayeteach.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by lovec on 2016/8/28.
 */
public class MyListView extends ListView {

    public MyListView(Context context) {
        super(context);
        initView();
    }


    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        this.setSelector(new ColorDrawable());
        this.setCacheColorHint(Color.TRANSPARENT);
        this.setDivider(null);

    }
}
