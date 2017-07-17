package com.example.user.vernehelper.imgList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.vernehelper.R;

public class ImageList extends AppCompatActivity {
    //// TODO: 17.07.2017 Initialize
    RecyclerView imgRecycler;
    LinearLayoutManager verticalLinearLayoutManager;
    LinearLayoutManager horizontalLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

        // TODO: 17.07.2017 Initialize recycler
        imgRecycler = (RecyclerView) findViewById(R.id.img_recycler);
        verticalLinearLayoutManager = new LinearLayoutManager(this);
        horizontalLinearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        imgRecycler.setLayoutManager(verticalLinearLayoutManager);

    }
}
