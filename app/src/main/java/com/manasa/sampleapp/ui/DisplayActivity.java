package com.manasa.sampleapp.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.felipecsl.gifimageview.library.GifImageView;
import com.manasa.sampleapp.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DisplayActivity extends AppCompatActivity {

    private String TAG = DisplayActivity.class.getSimpleName();

    GifImageView gifImageView ;

    private final String GIF_POSITION =  "position";

    private String  path;

    private Button shareOnFbButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        path = getIntent().getStringExtra(GIF_POSITION);
        Log.d(TAG,"path received "+path);
        gifImageView = (GifImageView)findViewById(R.id.gif_image_view);

        if(getBitmapBytes(path) != null) {
            gifImageView.setBytes(getBitmapBytes(path));
        }

        shareOnFbButton = (Button)findViewById(R.id.share_on_fb);
       // shareOnFbButton.setTypeface(Typeface.createFromAsset(getAssets(),"bungamelatiputih.ttf"));
        shareOnFbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();

            }
        });




    }

    private void share(){
        String uriToImage = path;
        Intent waIntent = new Intent(Intent.ACTION_SEND);
        waIntent.setType("image/gif");
        waIntent.setPackage("com.facebook.katana");
        if (waIntent != null) {
            waIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
            startActivity(Intent.createChooser(waIntent, "Share with"));
        } else {
            Toast.makeText(DisplayActivity.this, "Facebook not Installed", Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
       // gifImageView.setFramesDisplayDuration(500);
        gifImageView.startAnimation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        gifImageView.stopAnimation();
    }




    private byte[] getBitmapBytes(String path){
       // String path = Environment.getExternalStorageDirectory()+"/mygif_"+indx+".gif";
        File file = new File(path);
        int size = (int) file.length();
        byte[] bytes = new byte[size];
        try{
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(file));
            buf.read(bytes, 0, bytes.length);
            buf.close();
            return bytes;
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){

        }
        return null;
    }

}
