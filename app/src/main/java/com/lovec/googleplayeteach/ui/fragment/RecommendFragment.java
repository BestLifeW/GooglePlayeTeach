package com.lovec.googleplayeteach.ui.fragment;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lovec.googleplayeteach.http.protocol.RecommendProtocol;
import com.lovec.googleplayeteach.ui.view.LoadingPage;
import com.lovec.googleplayeteach.ui.view.fly.ShakeListener;
import com.lovec.googleplayeteach.ui.view.fly.StellarMap;
import com.lovec.googleplayeteach.utils.UIUtils;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by lovec on 2016/8/27.
 */
public class RecommendFragment extends BaseFragment {

    private ArrayList<String> data;

    @Override
    public View onCreateSuccessView() {
        final StellarMap stellarMap = new StellarMap(UIUtils.getContext());
        stellarMap.setAdapter(new RecommendAdapter());
        stellarMap.setRegularity(6, 9);
        int padding = UIUtils.dip2px(10);
        stellarMap.setInnerPadding(padding, padding, padding, padding);
        //设置默认页面
        stellarMap.setGroup(0, true);


        ShakeListener shake = new ShakeListener(UIUtils.getContext());
        shake.setOnShakeListener(new ShakeListener.OnShakeListener() {
            @Override
            public void onShake() {
                stellarMap.zoomIn();//跳到下一页的数据
            }
        });
        return stellarMap;
    }

    @Override
    public LoadingPage.ResultState onLoad() {

        RecommendProtocol protocol = new RecommendProtocol();
        data = protocol.getData(0);

        return check(data);
    }

    class RecommendAdapter implements StellarMap.Adapter {

        //返回组的个数
        @Override
        public int getGroupCount() {
            return 2;
        }

        //返回某组的个数
        @Override
        public int getCount(int group) {

            int count = data.size() / getGroupCount();
            if (group == getGroupCount() - 1) {
                //最后一页，将除不尽，余下来的数量追加在最后一页
                count += data.size() % getGroupCount();
            }
            return count;
        }

        @Override
        public View getView(int group, int position, View convertView) {
            // 因为position每组都会从0开始计数, 所以需要将前面几组数据的个数加起来,才能确定当前组获取数据的角标位置
            position += (group) * getCount(group - 1);
            String keyword = data.get(position);
            TextView view = new TextView(UIUtils.getContext());
            //随机大小，随机颜色
            Random random = new Random();

            int size = 16 + random.nextInt(10);
            view.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
            //随机颜色
            // r g b, 0-255 -> 30-230, 颜色值不能太小或太大, 从而避免整体颜色过亮或者过暗
            int r = 30 + random.nextInt(200);
            int g = 30 + random.nextInt(200);
            int b = 30 + random.nextInt(200);
            view.setTextColor(Color.rgb(r, g, b));

            view.setText(keyword);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UIUtils.getContext(),data.toString(), Toast.LENGTH_SHORT).show();
                }
            });

            return view;
        }

        //下一组的id
        @Override
        public int getNextGroupOnZoom(int group, boolean isZoomIn) {


            if (isZoomIn) {
                //往下滑加载上一页
                if (group > 0) {
                    group--;
                } else {
                    group = getGroupCount() - 1;
                }
            } else {
                if (group < getGroupCount() - 1) {
                    group++;
                } else {
                    group = 0;
                }
            }

            return 0;
        }
    }
}
