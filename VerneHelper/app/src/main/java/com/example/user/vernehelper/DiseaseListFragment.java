package com.example.user.vernehelper;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.vernehelper.DataBase.DBHelper;
import com.example.user.vernehelper.DataBase.DiseaseTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class DiseaseListFragment extends Fragment implements DiseaseAdapter.DiseaseOnClickListener{

    public final static String LIST = "List";
    RecyclerView recyclerView;
    DiseaseAdapter diseaseAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Disease> diseaseList;
    DiseaseInfoFragment infoFragment;
    DBHelper helper;
    DiseaseTable diseaseTable;
    SQLiteDatabase db;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.disease_list_fragment, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        diseaseTable = new DiseaseTable();
        helper = new DBHelper(getContext());
        db = helper.getWritableDatabase();

        diseaseList = diseaseTable.getAllDiseasesFromDB(db);
        diseaseList = diseaseList.size() == 0 ? createList() : diseaseList;

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        diseaseAdapter = new DiseaseAdapter(diseaseList, this);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setAdapter(diseaseAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onDiseaseClick(Disease disease) {
        infoFragment = new DiseaseInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(DiseaseInfoFragment.DIS, disease);
        infoFragment.setArguments(args);

        if (getActivity().getWindowManager().getDefaultDisplay().getRotation() != Surface.ROTATION_0){
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.description_frame, infoFragment, DiseaseInfoFragment.INFO)
                    .commit();
        } else {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.list_frame, infoFragment, DiseaseInfoFragment.INFO)
                    .addToBackStack(null)
                    .commit();

        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getActivity().getWindowManager().getDefaultDisplay().getRotation() == Surface.ROTATION_0 && infoFragment != null){
            getFragmentManager()
                    .beginTransaction()
                    .hide(infoFragment)
                    .commit();
        }
    }

    public List<Disease> createList(){

        diseaseList = new ArrayList<>();
        List<String> symptoms = new ArrayList<>();
        symptoms.addAll(Arrays.asList(getResources().getStringArray(R.array.diet)));
        diseaseList.add(new Disease("Диета", symptoms, "Диета, которую необходимо соблюдать для поддержания нормального уровня сахара в крови"));
        String [] diseases = getResources().getStringArray(R.array.disease_names);
        String [] disDesc = getResources().getStringArray(R.array.disease_descriptions);
        String [] sympt = getResources().getStringArray(R.array.symptoms);
        for (int i = 0; i < diseases.length; i++) {
            String [] symps = sympt[i].split(", ");
            diseaseList.add(new Disease(diseases[i], Arrays.asList(symps), disDesc[i]));
        }
        for (Disease disease: diseaseList){
            diseaseTable.insert(db, disease);
        }
        return diseaseList;
    }

}
