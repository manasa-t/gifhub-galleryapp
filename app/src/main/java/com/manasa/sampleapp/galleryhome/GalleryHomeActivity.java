package com.manasa.sampleapp.galleryhome;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.manasa.sampleapp.R;
import com.manasa.sampleapp.ui.DisplayActivity;
import com.manasa.sampleapp.ui.Main2Activity;

public class GalleryHomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private  final String TAG = GalleryHomeActivity.class.getSimpleName();

    private GridView mGridView;

    private final String GIF_POSITION =  "position";

    private LinearLayout noGifLayout;

    private GifAdapter mAdapter;

    private Button makeGif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gallery_home);
        setToolbar();
        mGridView = (GridView)findViewById(R.id.gridview);
        noGifLayout = (LinearLayout)findViewById(R.id.no_gif_layout);
        makeGif = (Button)findViewById(R.id.make_gif_button);
        setData();
    }

    private void setData(){
         mAdapter = new GifAdapter(this);
        Log.d(TAG,"adapter count "+mAdapter.getCount());
        if(mAdapter.getCount()>0) {
            mGridView.setVisibility(View.VISIBLE);
            mGridView.setAdapter(mAdapter);
            mGridView.setOnItemClickListener(this);
            noGifLayout.setVisibility(View.GONE);
        }else{
            mGridView.setVisibility(View.GONE);
            noGifLayout.setVisibility(View.VISIBLE);
            makeGif.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(GalleryHomeActivity.this, Main2Activity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
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
        Log.d(TAG,"sending path "+mAdapter.getItem(position).toString());
        intent.putExtra(GIF_POSITION,mAdapter.getItem(position).toString());
        startActivity(intent);
    }
}
