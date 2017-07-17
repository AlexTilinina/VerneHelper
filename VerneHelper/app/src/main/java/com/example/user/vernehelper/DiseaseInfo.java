package com.example.user.vernehelper;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;

public class DiseaseInfo extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_info);

        DiseaseListFragment listFragment = new DiseaseListFragment();
        listFragment.setRetainInstance(true);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.list_frame, listFragment, DiseaseListFragment.LIST)
                .commit();
    }

}
