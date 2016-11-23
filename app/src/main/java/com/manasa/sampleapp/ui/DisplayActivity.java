package com.manasa.sampleapp.ui;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private int position;

    private Button shareOnFbButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
       /* RelativeLayout parent = (RelativeLayout)findViewById(R.id.activity_display);
        if(getImputStream() != null) {
            GifDecoderView moviewView = new GifDecoderView(this, getImputStream());
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            moviewView.setLayoutParams(params);
            parent.addView(moviewView);
        }*/

        position = getIntent().getIntExtra(GIF_POSITION,1);
        gifImageView = (GifImageView)findViewById(R.id.gif_image_view);

        if(getBitmapBytes(position) != null) {
            gifImageView.setBytes(getBitmapBytes(position));
        }

        shareOnFbButton = (Button)findViewById(R.id.share_on_fb);
        shareOnFbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uriToImage = Environment.getExternalStorageDirectory()+"/mygif_"+
                        position+".gif";
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
        });

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




    private byte[] getBitmapBytes(int indx){
        String path = Environment.getExternalStorageDirectory()+"/mygif_"+indx+".gif";
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
