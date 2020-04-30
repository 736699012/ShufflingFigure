package com.example.shufflingfigure;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShufflingAdapter extends PagerAdapter {

    private static final String TAG = "ShufflingAdapter";
    private List<Integer> mList;

    @Override
    public int getCount() {
        if(mList!=null){
            return Integer.MAX_VALUE;
        }
        return 0;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        int realPosition = position%mList.size();
        Log.d(TAG,"instantiateItem "+ position);
        ImageView imageView = new ImageView(container.getContext());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setImageResource(mList.get(realPosition));
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Log.d(TAG,"destroyItem "+ position);
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        Log.d(TAG, String.valueOf("isViewFromObject  "+view==object));
        return view==object;
    }

    public void setData(List<Integer> list) {
        mList = new ArrayList<>();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    public int getDataRealSize(){
        if(mList!=null){
            return mList.size();
        }
        return 0;
    }
}
