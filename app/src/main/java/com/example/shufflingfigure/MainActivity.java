package com.example.shufflingfigure;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private MyViewPager mShuffling;
    private ShufflingAdapter mShufflingAdapter;
    private static List<Integer> sList = new ArrayList<>();
    private boolean mIsTouch = false;


    static {
        sList.add(R.drawable.p1);
        sList.add(R.drawable.p2);
        sList.add(R.drawable.p3);
        sList.add(R.drawable.p4);
        sList.add(R.drawable.p5);
        sList.add(R.drawable.p6);
        sList.add(R.drawable.p7);
    }

    private Handler mHandler;
    private LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mHandler = new Handler();
    }





    public void testMeasure(){}

    public void test2(){}

    @Override
    public void onAttachedToWindow() {
        Log.d(TAG, "onAttachedToWindow...");
        super.onAttachedToWindow();
        mHandler.post(mRunnable);
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (!mIsTouch) {
                int currentItem = mShuffling.getCurrentItem();
                mShuffling.setCurrentItem(++currentItem, false);
            }
            mHandler.postDelayed(this, 3000);
        }
    };

    @Override
    public void onDetachedFromWindow() {
        Log.d(TAG, "onDetachedFromWindow...");
        super.onDetachedFromWindow();
        mHandler.removeCallbacks(mRunnable);
    }

    private void initView() {
//        1.找到ViewPager组件
        mShuffling = findViewById(R.id.shuffling);
        mShuffling.setOnViewPagerTouchListen(new MyViewPager.OnViewPagerTouchListen() {
            @Override
            public void onViewPagerTouch(boolean isTouch) {
                mIsTouch = isTouch;
            }
        });
//        2.设置适配器
        mShufflingAdapter = new ShufflingAdapter();
        mShufflingAdapter.setData(sList);
        mShuffling.setAdapter(mShufflingAdapter);
        mShuffling.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                int realPosition = 0;
                if (mShufflingAdapter.getDataRealSize() != 0) {
                    realPosition = position % mShufflingAdapter.getDataRealSize();
                }
                selectedPoint(realPosition);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mLinearLayout = findViewById(R.id.points);
        initPoints();
        mShuffling.setCurrentItem(mShufflingAdapter.getDataRealSize() * 100 - 1, false);
    }

    private void selectedPoint(int realPosition) {

        for (int i = 0; i < mLinearLayout.getChildCount(); i++) {
            View point = mLinearLayout.getChildAt(i);
            if (i == realPosition) {
                point.setBackgroundResource(R.drawable.shape_point_selected);
            } else {
                point.setBackgroundResource(R.drawable.shape_point_normal);
            }
        }


    }

    private void initPoints() {
        for (int i = 0; i < mShufflingAdapter.getDataRealSize(); i++) {
            View point = new View(this);
            point.setBackgroundResource(R.drawable.shape_point_normal);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(40, 40);
            params.leftMargin = 20;
            point.setLayoutParams(params);
            mLinearLayout.addView(point);
        }
    }
}
