package com.kotlin.example.demo.taobaodetail;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2016/8/4.
 */
public class SideScrollView extends ScrollView{
    private Context mContext;
    public SideScrollView(Context context) {
        super(context);
        mContext=context;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        SideListView.dispatchSwitch=false;
//        Log.i("scrollView","dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        Log.i("scrollView","onInterceptTouchEvent");
//        SideListView.dispatchSwitch=false;
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i("scrollView","onTouchEvent");
        SideListView.dispatchSwitch=false;
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                smoothScrollTo(0,getLayoutParams().height);
//            }
//        },200);
        return super.onTouchEvent(ev);
    }

}
