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
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    private int dir=1;
    private int startY;
    private int endY;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i("scrollView","onTouchEvent");
        SideListView.dispatchSwitch=false;

        int action=ev.getAction();
        switch(action){
            case MotionEvent.ACTION_DOWN:
                startY= (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                endY= (int) ev.getY();
                break;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dir=(endY-startY)>0?0:1;
                Log.i("scrollView","dir "+dir);
                smoothScrollTo(0,MainActivity.height*dir);
            }
        },500);
        return super.onTouchEvent(ev);
    }

}
