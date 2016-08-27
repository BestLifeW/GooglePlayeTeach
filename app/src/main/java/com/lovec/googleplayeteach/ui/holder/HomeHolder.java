package com.lovec.googleplayeteach.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.lovec.googleplayeteach.R;
import com.lovec.googleplayeteach.domain.AppInfo;
import com.lovec.googleplayeteach.utils.UIUtils;

/**
 * Created by lovec on 2016/8/27.
 */
public class HomeHolder extends BaseHolder<AppInfo> {

    private TextView tvContent;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.list_item_home);

        tvContent = (TextView) view.findViewById(R.id.tv_content);
        return view;
    }

    @Override
    public void refreshView(AppInfo data) {
        tvContent.setText(data.name);
    }
}
