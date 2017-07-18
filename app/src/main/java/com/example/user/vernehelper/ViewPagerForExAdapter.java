package com.example.user.vernehelper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.user.vernehelper.EyeFragments.EyeExerciseFragment;
import com.example.user.vernehelper.EyeFragments.EyeExerciseFragmentOne;
import com.example.user.vernehelper.EyeFragments.EyeExerciseFragmentThree;
import com.example.user.vernehelper.EyeFragments.EyeExerciseFragmentTwo;

/**
 * Created by Home on 17.07.2017.
 */

public class ViewPagerForExAdapter extends FragmentPagerAdapter {

    public static final int exCount = 4;

    public ViewPagerForExAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new EyeExerciseFragment();
            case 1:
                return new EyeExerciseFragmentOne();
            case 2:
                return new EyeExerciseFragmentTwo();
            default:
                return new EyeExerciseFragmentThree();
        }
    }

    @Override
    public int getCount() {
        return exCount;
    }
}