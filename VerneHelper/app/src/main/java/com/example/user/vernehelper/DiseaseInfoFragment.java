package com.example.user.vernehelper;

import android.app.Fragment;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by User on 14.07.2017.
 */

public class DiseaseInfoFragment extends Fragment {

    public static final String INFO = "Info";
    TextView title;
    TextView description;
    ListView symptoms;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.disease_description_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Disease disease = (Disease) getArguments().getSerializable(INFO);

        title = (TextView) view.findViewById(R.id.title);
        description = (TextView) view.findViewById(R.id.description);
        symptoms = (ListView) view.findViewById(R.id.symptoms);

        title.setText(disease.getName());
        description.setText(disease.getDescription());
        symptoms.setAdapter(new ArrayAdapter<>(
                view.getContext(),
                android.R.layout.simple_list_item_1,
                disease.getSymptoms()));
    }
}
