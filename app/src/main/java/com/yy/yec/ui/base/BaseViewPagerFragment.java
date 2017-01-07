package com.yy.yec.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.common.widget.LoadStateLayout;
import com.common.widget.PagerTabIndicator;
import com.yy.yec.R;
import com.yy.yec.able.DialogControl;
import com.yy.yec.adapter.ViewPageFragmentAdapter;

import butterknife.Bind;

/**
 * Created by zzq on 2016/9/27.
 */
public abstract class BaseViewPagerFragment extends BaseFragment {
    @Bind(R.id.lcl_StatePager)
    LoadStateLayout mStatePager;
    @Bind(R.id.pt_pagerTabIndicator)
    PagerTabIndicator mPagerTab;
    @Bind(R.id.vp_pager)
    ViewPager mPager;

    @Override
    protected void init(View contentView) {
        super.init(contentView);
        mPager.setOffscreenPageLimit(setLimitVIew());//缓存的页数
        ViewPageFragmentAdapter tabsAdapter = new ViewPageFragmentAdapter(getChildFragmentManager(), mPagerTab, mPager);
        createTabToAdapter(tabsAdapter);//让子类确定添加什么样的内容
    }

    protected int setLimitVIew() {
        return 3;
    }

    protected abstract void createTabToAdapter(ViewPageFragmentAdapter tabsAdapter);

    @Override
    protected void initComplete(View view, @Nullable Bundle savedInstanceState) {
        super.initComplete(view, savedInstanceState);
        if (savedInstanceState != null) {//获得传值或者状态恢复
            int pos = savedInstanceState.getInt("position");
            mPager.setCurrentItem(pos, true);
        }
    }

    public LoadStateLayout getStatePager() {
        return mStatePager;
    }

    public PagerTabIndicator getPagerTab() {
        return mPagerTab;
    }

    public ViewPager getPager() {
        return mPager;
    }

    @Override
    protected int setContentView() {
        return R.layout.fragment_viewpager;
    }

    protected void hideWaitDialog() {
        FragmentActivity activity = getActivity();
        if (activity instanceof DialogControl) {
            ((DialogControl) activity).hideWaitDialog();
        }
    }

//    protected ProgressDialog showWaitDialog() {
//        return showWaitDialog(R.string.loading);
//    }
//
//    protected ProgressDialog showWaitDialog(int resid) {
//        FragmentActivity activity = getActivity();
//        if (activity instanceof DialogControl) {
//            return ((DialogControl) activity).showWaitDialog(resid);
//        }
//        return null;
//    }
//
//    protected ProgressDialog showWaitDialog(String str) {
//        FragmentActivity activity = getActivity();
//        if (activity instanceof DialogControl) {
//            return ((DialogControl) activity).showWaitDialog(str);
//        }
//        return null;
//    }
}