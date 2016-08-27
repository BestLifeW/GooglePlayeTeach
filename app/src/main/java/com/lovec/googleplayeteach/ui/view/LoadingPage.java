package com.lovec.googleplayeteach.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.lovec.googleplayeteach.R;
import com.lovec.googleplayeteach.utils.UIUtils;

/**
 * Created by lovec on 2016/8/27.
 */
public abstract class LoadingPage extends FrameLayout {

    public static final int STATE_LOAD_UNDO = 1; //未加载
    public static final int STATE_LOAD_LOADING = 2;//正在加载
    public static final int STATE_LOAD_ERROR = 3;//加载失败
    public static final int STATE_LOAD_EMPTY = 4;//数据为空
    public static final int STATE_LOAD_SUCCESS = 5;//加载成功

    private int mCurrentState = STATE_LOAD_UNDO; //当前状态
    private View mLoadingPage;
    private View mErrorPage;
    private View mEmptyPage;
    private View mSuccessPage;


    public LoadingPage(Context context) {
        super(context);
        initView();
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /*
    * 初始化布局
    * */
    private void initView() {
        //加载中的布局
        if (mLoadingPage == null) {
            mLoadingPage = UIUtils.inflate(R.layout.page_loading);
            addView(mLoadingPage);
        }
        //加载失败的布局
        if (mErrorPage == null) {
            mErrorPage = UIUtils.inflate(R.layout.page_error);
            addView(mErrorPage);
        }
        //加载数据为空
        if (mEmptyPage == null) {
            mEmptyPage = UIUtils.inflate(R.layout.page_empty);
            addView(mEmptyPage);
        }
        showRightPage();
    }

    //根据当前状态显示哪个布局
    private void showRightPage() {

        mLoadingPage.setVisibility((mCurrentState == STATE_LOAD_UNDO || mCurrentState == STATE_LOAD_LOADING) ? View.VISIBLE : View.GONE);

        mErrorPage.setVisibility((mCurrentState == STATE_LOAD_ERROR) ? View.VISIBLE : View.GONE);

        mEmptyPage.setVisibility(mCurrentState == STATE_LOAD_EMPTY ? View.VISIBLE : View.GONE);

        if (mSuccessPage == null && mCurrentState == STATE_LOAD_SUCCESS) {
            mSuccessPage = onCreateSuccessView();

            if (mSuccessPage != null) {
                addView(mSuccessPage);
            }
        }
        if (mSuccessPage != null) {
            mSuccessPage.setVisibility(mCurrentState == STATE_LOAD_SUCCESS ? View.VISIBLE : View.GONE);
        }
    }

    public abstract View onCreateSuccessView();

    public void loadData() {

        if (mCurrentState != STATE_LOAD_LOADING) { //如果当前没有加载，就开始加载数据
            mCurrentState = STATE_LOAD_LOADING;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    final ResultState resultState = onLoad();
                    UIUtils.runOnUIThread(new Runnable() {
                        @Override
                        public void run() {
                            if (resultState != null) {
                                mCurrentState = resultState.getState();//网络结束 更新网络状态
                                showRightPage();
                            }
                        }
                    });
                }
            }).start();
        }
    }

    //加载网络数据，返回请求值网络结束后的状态 Meizu
    protected abstract ResultState onLoad();

    public enum ResultState {
        STATE_SUCCESS(STATE_LOAD_SUCCESS),
        STATE_EMPTY(STATE_LOAD_EMPTY),
        STATE_ERROR(STATE_LOAD_ERROR);

        private int state;

        ResultState(int state) {
            this.state = state;
        }

        public int getState() {
            return state;
        }
    }
}
