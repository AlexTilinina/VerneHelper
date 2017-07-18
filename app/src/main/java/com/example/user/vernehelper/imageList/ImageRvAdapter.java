package com.example.user.vernehelper.imageList;

import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.vernehelper.DataBase.DBHelper;
import com.example.user.vernehelper.R;

import java.util.List;

/**
 * Created by Tony on 17.07.2017.
 */

public class ImageRvAdapter extends RecyclerView.Adapter<ImageRvAdapter.ModelItemViewHolder> {
    public List<ModelItem> items;
    ImageList imglist = new ImageList();




    @Override
    public ModelItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_photocard, parent, false);
         ModelItemViewHolder  mivh = new ModelItemViewHolder(v);
        return mivh;
    }

    @Override
    public void onBindViewHolder(final ModelItemViewHolder holder, final int position) {
        holder.description.setText(items.get(position).getDescription());
        holder.personPhoto.setImageURI(items.get(position).getPhotoUri());

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PopupMenu popup = new PopupMenu(v.getContext(), v);
//                popup.inflate(R.menu.photo_card_menu);
//                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        switch (item.getItemId()) {
//                            case R.id.delete_card:
//                                items.remove(position);
//                                notifyItemRemoved(position);
//                                imglist.deleteFromDB(position);
//                                return true;
//
//
//                        }
//                        return false;
//                    }
//                });
//                popup.show();
            }
//        });
//    }


    public ImageRvAdapter(List<ModelItem> items) {
        this.items = items;
    }


    public class ModelItemViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView description;
        ImageView personPhoto;
        View view;

        public ModelItemViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.card);
            description = (TextView) itemView.findViewById(R.id.title);
            personPhoto = (ImageView) itemView.findViewById(R.id.image);
        }
    }
    public void onItemDismiss(int position) {
        if(position!=-1 && position<items.size())
        {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }
    @Override
    public int getItemCount() {
        return (null != items ? items.size() : 0);
    }

}
