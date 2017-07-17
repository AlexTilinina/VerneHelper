package com.example.user.vernehelper.EyeFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.vernehelper.R;

/**
 * Created by Home on 17.07.2017.
 */

public class EyeExerciseFragmentTwo extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_eye_exercise_two,null);
        ((TextView)v.findViewById(R.id.text_view_description_exercise)).setText("Вверх,вниз,вправо,влево");
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
