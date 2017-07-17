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
    public static final String DIS = "Disease";
    TextView title;
    TextView description;
    TextView symptoms;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.disease_description_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        Disease disease = (Disease) args.getSerializable(DIS);


        title = (TextView) view.findViewById(R.id.title);
        description = (TextView) view.findViewById(R.id.description);
        symptoms = (TextView) view.findViewById(R.id.symptoms);

        if (disease != null){
            title.setText(disease.getName());
            description.setText(disease.getDescription());
            for (String dis: disease.getSymptoms())
                symptoms.setText(symptoms.getText() + dis + "\n");
        }
        else {
            title.setText("Что-то");
            symptoms.setText("Пошло \n Не");
            description.setText("Так");
        }

    }


}
