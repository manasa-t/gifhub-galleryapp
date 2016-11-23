package com.manasa.sampleapp.galleryhome;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.manasa.sampleapp.R;
import com.manasa.sampleapp.ui.DisplayActivity;
import com.manasa.sampleapp.ui.Main2Activity;

public class GalleryHomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private GridView mGridView;

    private final String GIF_POSITION =  "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gallery_home);
        setToolbar();
        mGridView = (GridView)findViewById(R.id.gridview);

    }

    @Override
    protected void onResume() {
        super.onResume();
        GifAdapter mAdapter = new GifAdapter(this);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(this);
    }

    private void setToolbar(){

        Toolbar toolbar = (Toolbar)findViewById(R.id.gallery_toolbar);
        setSupportActionBar(toolbar);
        ImageView cameraIcon = (ImageView)toolbar.findViewById(R.id.camera_icon);
        cameraIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GalleryHomeActivity.this, Main2Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(GalleryHomeActivity.this, DisplayActivity.class);
        intent.putExtra(GIF_POSITION,position+1);
        startActivity(intent);
    }
}
