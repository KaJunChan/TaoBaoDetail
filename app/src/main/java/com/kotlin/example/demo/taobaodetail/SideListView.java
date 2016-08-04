package com.kotlin.example.demo.taobaodetail;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/8/4.
 */
public class SideListView extends ListView implements AbsListView.OnScrollListener {

    private int state = -1;
    private boolean isReachTop = false;
    public static boolean dispatchSwitch=false;

    public SideListView(Context context) {
        super(context);
        setOnScrollListener(this);
    }


    //用判断ListView上下滑动方向
    private int mLastY = 0;
    private int mCurrY = 0;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastY= (int) ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                mCurrY = (int) ev.getRawY();
                if (state == OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    if(isReachTop){
                        if(mCurrY<mLastY){
                            dispatchSwitch=false;
                        }else{
                            Log.i("ListView","isTop "+isReachTop);
                            dispatchSwitch=true;
                            return false;
                        }
                    }
                }
            case MotionEvent.ACTION_UP:
                break;
        }
        Log.i("ListView","mLastY :"+mLastY);
        Log.i("ListView","mCurrY :"+mCurrY);
        dispatchSwitch=false;
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("ListView", "dispatchTouchEvent switch:"+dispatchSwitch);
        if(dispatchSwitch){
            return false;
        }else{
            return super.dispatchTouchEvent(ev);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        state = i;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("ListView","onInterceptTouchEvent");
        dispatchSwitch=false;
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
        if (i == 0) {
            //第一个出现的Itemposition，说明已到顶部
            isReachTop = true;
        } else {
            isReachTop = false;
        }
    }
}
