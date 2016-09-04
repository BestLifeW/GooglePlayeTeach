package com.lovec.googleplayeteach.ui.fragment;

import java.util.HashMap;

/*
 * 生产fragment的工厂
 * Created by lovec on 2016/8/27.
 */
public class FragmentFactory {

    private static HashMap<Integer, BaseFragment> mFragmentMap = new HashMap<Integer, BaseFragment>();

    public static BaseFragment createFragment(int pos) {
        //先从集合中，如果没有才创建对象0000000000000000000000000000000000000
        BaseFragment fragment = mFragmentMap.get(pos);
        if (fragment == null) {
            switch (pos) {
                case 0:
                    fragment = HomeFragment.newInstance();
                    break;
                case 1:
                    fragment = AppFragment.newInstance();
                    break;
                case 2:
                    fragment = new GameFragment();
                    break;
                case 3:
                    fragment = new SubjectFragment();
                    break;
                case 4:
                    fragment = new RecommendFragment();
                    break;
                case 5:
                    fragment = new CategoryFragment();
                    break;
                case 6:
                    fragment = new HotFragment();
                    break;
                default:
                    break;
            }
        }
        mFragmentMap.put(pos, fragment);
        return fragment;
    }
}
