package com.example.user.vernehelper.EyeFragments;


import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.vernehelper.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Home on 17.07.2017.
 */

public class EyeExerciseFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_eye_exercise,null);
        ((TextView)v.findViewById(R.id.text_view_description_exercise)).setText("Быстро и легко моргайте.");
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
