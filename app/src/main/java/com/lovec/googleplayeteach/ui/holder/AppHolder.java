package com.lovec.googleplayeteach.ui.holder;

import android.text.format.Formatter;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.lovec.googleplayeteach.R;
import com.lovec.googleplayeteach.domain.AppInfo;
import com.lovec.googleplayeteach.http.HttpHelper;
import com.lovec.googleplayeteach.utils.BitmapHelper;
import com.lovec.googleplayeteach.utils.UIUtils;

/**
 * 应用Holder
 * Created by lovec on 2016/8/27.
 */
public class AppHolder extends BaseHolder<AppInfo> {

    private TextView tvContent;
    private TextView tvName;
    private TextView tvSize;
    private TextView tvDes;
    private ImageView ivIcon;
    private RatingBar rbStar;
    private BitmapUtils mBitmapUtils;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.list_item_home);

        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvSize = (TextView) view.findViewById(R.id.tv_size);
        tvDes = (TextView) view.findViewById(R.id.tv_des);
        ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
        rbStar = (RatingBar) view.findViewById(R.id.rb_star);
        mBitmapUtils = BitmapHelper.getBitmapUtils();
        return view;
    }

    @Override
    public void refreshView(AppInfo data) {
        tvName.setText(data.name);
        tvDes.setText(data.des);
        tvSize.setText(Formatter.formatShortFileSize(UIUtils.getContext(), data.size));
        rbStar.setRating(data.stars);
        mBitmapUtils.display(ivIcon, HttpHelper.URL + "image?name=" + data.iconUrl);
    }
}
