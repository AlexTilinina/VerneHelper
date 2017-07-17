package com.example.user.vernehelper.imgList;

import android.graphics.PorterDuff;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tony on 17.07.2017.
 */

public class ModelItem {
    private String description;
    private Uri photoUri;

    public ModelItem(String description, Uri photoUri) {
        this.description = description;
        this.photoUri = photoUri;
    }

    public String getDescription() {
        return description;
    }

    public Uri getPhotoUri() {
        return photoUri;
    }

    private List<ModelItem> items;
    private void initializeData(){
        items = new ArrayList<>();
    }
}


