package com.lovec.googleplayeteach.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.lovec.googleplayeteach.R;
import com.lovec.googleplayeteach.domain.AppInfo;
import com.lovec.googleplayeteach.http.protocol.HomeDetailProtocol;
import com.lovec.googleplayeteach.ui.holder.DetailAppInfoHolder;
import com.lovec.googleplayeteach.ui.holder.DetailSafeHolder;
import com.lovec.googleplayeteach.ui.view.LoadingPage;
import com.lovec.googleplayeteach.utils.UIUtils;

public class HomeDetiailActivity extends AppCompatActivity {

    private LoadingPage mLoadingPage;
    private String packageName;
    private AppInfo data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mLoadingPage = new LoadingPage(this) {
            @Override
            public View onCreateSuccessView() {
                return HomeDetiailActivity.this.onCreateSuccessView();
            }

            @Override
            protected ResultState onLoad() {
                return HomeDetiailActivity.this.onLoad();
            }
        };
        setContentView(mLoadingPage);
        packageName = getIntent().getStringExtra("packageName");
        mLoadingPage.loadData();
    }

    public View onCreateSuccessView() {
        //初始化成功的信息;
        View view = UIUtils.inflate(R.layout.page_home_detail);
        //初始化应用信息模块
        FrameLayout flDetailAppInfo = (FrameLayout) view
                .findViewById(R.id.fl_detail_appinfo);

        //动态的给布局填充的页面
        DetailAppInfoHolder AppInfoHolder = new DetailAppInfoHolder();

        flDetailAppInfo.addView(AppInfoHolder.getRootView());
        AppInfoHolder.setData(data);
        FrameLayout flSafeAppInfo = (FrameLayout) view
                .findViewById(R.id.fl_detail_safe);
        DetailSafeHolder safeHolder = new DetailSafeHolder();
        safeHolder.setData(data);
        flSafeAppInfo.addView(safeHolder.getRootView());
        return view;
    }

    public LoadingPage.ResultState onLoad() {
        //请求网络对象
        HomeDetailProtocol protocol = new HomeDetailProtocol(packageName);
        data = protocol.getData(0);

        if (data != null) {
            return LoadingPage.ResultState.STATE_SUCCESS;
        } else {
            return LoadingPage.ResultState.STATE_ERROR;
        }
    }
}
