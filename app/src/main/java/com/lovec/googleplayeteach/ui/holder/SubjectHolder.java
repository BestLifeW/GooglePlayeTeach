package com.lovec.googleplayeteach.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lovec.googleplayeteach.R;
import com.lovec.googleplayeteach.domain.SubjectInfo;
import com.lovec.googleplayeteach.http.HttpHelper;
import com.lovec.googleplayeteach.utils.BitmapHelper;
import com.lovec.googleplayeteach.utils.UIUtils;

/**
 * 专题
 * Created by lovec on 2016/8/28.
 */
public class SubjectHolder extends BaseHolder<SubjectInfo> {

    private View view;
    private ImageView ivPic;
    private TextView tvTitle;
    private BitmapUtils mBitmapUtils;

    @Override
    public View initView() {
        view = UIUtils.inflate(R.layout.list_item_subject);
        ivPic = (ImageView) view.findViewById(R.id.iv_pic);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        mBitmapUtils = BitmapHelper.getBitmapUtils();

        return view;
    }

    @Override
    public void refreshView(SubjectInfo data) {
        tvTitle.setText(data.des);
        mBitmapUtils.display(ivPic, HttpHelper.URL + "image?name=" + data.url);
    }
}
