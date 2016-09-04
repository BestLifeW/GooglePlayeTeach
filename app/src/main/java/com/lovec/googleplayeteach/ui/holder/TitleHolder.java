package com.lovec.googleplayeteach.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.lovec.googleplayeteach.R;
import com.lovec.googleplayeteach.domain.CategoryInfo;
import com.lovec.googleplayeteach.utils.UIUtils;

/**
 * Created by lovec on 2016/8/31.
 */
public class TitleHolder extends BaseHolder<CategoryInfo> {

    private TextView tvTitel;

    @Override
    public View initView() {

        View view = UIUtils.inflate(R.layout.list_item_title);
        tvTitel = (TextView) view.findViewById(R.id.tv_title);
        return view;
    }

    @Override
    public void refreshView(CategoryInfo data) {
        tvTitel.setText(data.title);
    }
}
