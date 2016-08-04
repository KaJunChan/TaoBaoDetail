package com.kotlin.example.demo.taobaodetail;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout contentLayout;

    private TextView mFirstView;

    private SideListView mListView;

    private SideScrollView mScrollView;
    public static int height;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        height = getWindowManager().getDefaultDisplay().getHeight();
        int width = getWindowManager().getDefaultDisplay().getWidth();
        mScrollView = new SideScrollView(this);
        contentLayout = new LinearLayout(this);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        FrameLayout.LayoutParams frparams = new FrameLayout.LayoutParams(width, height);
        contentLayout.setLayoutParams(frparams);

        mFirstView = new TextView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        mFirstView.setGravity(Gravity.CENTER);
        mFirstView.setText("FIRST_VIEW");
        mFirstView.setLayoutParams(layoutParams);
        mFirstView.setBackgroundColor(Color.RED);

        layoutParams = new LinearLayout.LayoutParams(width, height / 6);
        final List<View> views = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TextView textView = new TextView(this);
            textView.setText("Pos :" + i);
            textView.setLayoutParams(layoutParams);
            textView.setBackgroundColor(Color.YELLOW);
            views.add(textView);
        }

        mListView = new SideListView(this);
        mListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return views.size();
            }

            @Override
            public Object getItem(int i) {
                return views.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                return views.get(i);
            }
        });

        contentLayout.addView(mFirstView);
        layoutParams = new LinearLayout.LayoutParams(width, height);
        contentLayout.addView(mListView, layoutParams);
        mScrollView.addView(contentLayout);
        addContentView(mScrollView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }
}
