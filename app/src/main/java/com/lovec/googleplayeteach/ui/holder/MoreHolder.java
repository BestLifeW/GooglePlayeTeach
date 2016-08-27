package com.lovec.googleplayeteach.ui.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lovec.googleplayeteach.R;
import com.lovec.googleplayeteach.utils.UIUtils;

/**
 * Created by lovec on 2016/8/27.
 */
public class MoreHolder extends BaseHolder<Integer> {
    //加载中的几种状态
    //1,加载更多
    //2,加载更多失败
    //3,没有更多数据
    public static final int STATE_MORE_MORE = 1;
    public static final int STATE_MORE_ERROR = 2;
    public static final int STATE_MORE_NONE = 3;
    private LinearLayout llLoadMore;
    private TextView tvLoadError;

    public MoreHolder(boolean hasMore) {
        setData(hasMore ? STATE_MORE_MORE : STATE_MORE_NONE);
    }


    @Override
    public View initView() {

        View view = UIUtils.inflate(R.layout.list_item_more);

        llLoadMore = (LinearLayout) view.findViewById(R.id.ll_load_more);
        tvLoadError = (TextView) view.findViewById(R.id.tv_load_error);

        return view;
    }

    @Override
    public void refreshView(Integer data) {
        switch (data) {
            case STATE_MORE_MORE:
                //显示加载更多
                llLoadMore.setVisibility(View.VISIBLE);
                tvLoadError.setVisibility(View.GONE);
                break;
            case STATE_MORE_NONE:
                llLoadMore.setVisibility(View.GONE);
                tvLoadError.setVisibility(View.GONE);
                break;
            case STATE_MORE_ERROR:
                llLoadMore.setVisibility(View.GONE);
                tvLoadError.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
}
