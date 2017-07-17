package com.example.user.vernehelper.imageList;

import android.net.Uri;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tony on 17.07.2017.
 */

public class ModelItem  {
    private String description;
    private Uri photoUri;
    private String stringUri = photoUri.toString();


    public String getStringUri() {
        return stringUri;
    }

    public List<ModelItem> getItems() {
        return items;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhotoUri(Uri photoUri) {
        this.photoUri = photoUri;
    }

    public void setStringUri(String stringUri) {
        this.stringUri = stringUri;
    }

    public void setItems(List<ModelItem> items) {
        this.items = items;
    }

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
