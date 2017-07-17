package com.example.user.vernehelper;

import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Home on 17.07.2017.
 */

public class ViewPagerForExe extends ViewPager{


    public ViewPagerForExe(Context context) {
        super(context);
    }

    public ViewPagerForExe(Context context, AttributeSet attrs) {
        super(context, attrs);
        swipeable = true;
    }

    private boolean swipeable;


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (this.swipeable) {

            return super.onTouchEvent(event);

        }

        return false;

    }


    @Override

    public boolean onInterceptTouchEvent(MotionEvent event) {

        if (this.swipeable) {

            return super.onInterceptTouchEvent(event);

        }


        return false;

    }


    public void setSwipeable(boolean swipeable) {

        this.swipeable = swipeable;

    }

}
