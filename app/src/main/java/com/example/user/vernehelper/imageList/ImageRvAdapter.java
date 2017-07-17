package com.example.user.vernehelper.imageList;

import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.vernehelper.R;

import java.util.List;

/**
 * Created by Tony on 17.07.2017.
 */

public class ImageRvAdapter extends RecyclerView.Adapter<ImageRvAdapter.ModelItemViewHolder> {
    @Override
    public ModelItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_photocard,parent,false);
        ModelItemViewHolder mivh = new ModelItemViewHolder(v);
        return mivh;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(ModelItemViewHolder holder, int position) {
    holder.description.setText(items.get(position).getDescription());
    holder.personPhoto.setImageURI(items.get(position).getPhotoUri());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public List<ModelItem> items;
    public ImageRvAdapter(List<ModelItem> items){
        this.items = items;
    }

    public class ModelItemViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView description;
        ImageView personPhoto;
        Uri uri;
        public ModelItemViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card);
            description = (TextView) itemView.findViewById(R.id.title);
            personPhoto = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
