package com.lovec.googleplayeteach.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lovec.googleplayeteach.R;
import com.lovec.googleplayeteach.domain.CategoryInfo;
import com.lovec.googleplayeteach.http.HttpHelper;
import com.lovec.googleplayeteach.utils.UIUtils;

/**
 * Created by lovec on 2016/8/31.
 */
public class CategoryHolder extends BaseHolder<CategoryInfo> {

    private TextView tvName1, tvName2, tvName3;
    private ImageView ivIcon1;
    private ImageView ivIcon2;
    private ImageView ivIcon3;
    private LinearLayout llGrid1, llGrid2, llGrid13;
    private BitmapUtils mBitmapUtils;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.list_item_category);
        tvName1 = (TextView) view.findViewById(R.id.tv_name1);
        tvName2 = (TextView) view.findViewById(R.id.tv_name2);
        tvName3 = (TextView) view.findViewById(R.id.tv_name3);

        ivIcon1 = (ImageView) view.findViewById(R.id.iv_icon1);
        ivIcon2 = (ImageView) view.findViewById(R.id.iv_icon2);
        ivIcon3 = (ImageView) view.findViewById(R.id.iv_icon3);
        mBitmapUtils = new BitmapUtils(UIUtils.getContext());


        return view;
    }

    @Override
    public void refreshView(CategoryInfo data) {
        tvName1.setText(data.name1);
        tvName2.setText(data.name2);
        tvName3.setText(data.name3);

        mBitmapUtils.display(ivIcon1, HttpHelper.URL + "image?name=" + data.url1);
        mBitmapUtils.display(ivIcon2, HttpHelper.URL + "image?name=" + data.url2);
        mBitmapUtils.display(ivIcon3, HttpHelper.URL + "image?name=" + data.url3);

    }
}
