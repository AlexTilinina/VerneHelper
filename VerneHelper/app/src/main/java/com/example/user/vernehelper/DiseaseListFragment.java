package com.example.user.vernehelper;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class DiseaseListFragment extends Fragment implements DiseaseAdapter.DiseaseOnClickListener{

    public final static String LIST = "List";
    RecyclerView recyclerView;
    DiseaseAdapter diseaseAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Disease> diseaseList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.disease_list_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        diseaseAdapter = new DiseaseAdapter(diseaseList, this);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setAdapter(diseaseAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onDiseaseClick(Disease disease) {
        //TODO переход на фрагмент с описанием
        DiseaseInfoFragment infoFragment = new DiseaseInfoFragment();
        infoFragment.setRetainInstance(true);
        Bundle args = new Bundle();
        args.putSerializable(DiseaseInfoFragment.INFO, disease);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.description_frame, infoFragment, DiseaseInfoFragment.INFO)
                .commit();
    }

}
