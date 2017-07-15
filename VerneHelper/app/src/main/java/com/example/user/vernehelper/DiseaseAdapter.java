package com.example.user.vernehelper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by User on 14.07.2017.
 */

public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseAdapter.DiseaseViewHolder>{

    List<Disease> diseases;
    DiseaseOnClickListener listener;

    public DiseaseAdapter(List<Disease> diseases, DiseaseOnClickListener listener) {
        this.diseases = diseases;
        this.listener = listener;
    }

    @Override
    public DiseaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DiseaseViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.disease_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(DiseaseViewHolder holder, int position) {
        final Disease disease = diseases.get(position);
        holder.title.setText(disease.getName());
        String desc = "";
        for (String sympt : disease.getSymptoms()){
            desc += sympt + ", ";
        }
        desc = desc.substring(1).toLowerCase();
        desc = desc.substring(0, desc.length() - 1);
        holder.description.setText(desc);
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDiseaseClick(disease);
            }
        });
    }

    @Override
    public int getItemCount() {
        return diseases.size();
    }

    public class DiseaseViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        TextView description;
        RelativeLayout relativeLayout;

        public DiseaseViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.short_desc);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative_layout);
        }
    }

    interface DiseaseOnClickListener{
        void onDiseaseClick(Disease disease);
    }
}
