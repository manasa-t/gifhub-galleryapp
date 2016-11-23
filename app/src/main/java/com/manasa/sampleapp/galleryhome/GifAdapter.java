package com.manasa.sampleapp.galleryhome;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.felipecsl.gifimageview.library.GifImageView;
import com.manasa.sampleapp.R;
import com.manasa.sampleapp.utils.PreferenceManager;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by manasa on 16/11/16.
 */

public class GifAdapter extends BaseAdapter {

    private Context mContext;

    private String TAG = GifAdapter.class.getSimpleName();

    private ArrayList<String> paths;

    public GifAdapter(Context pContext){
        this.mContext = pContext;
        paths =  new ArrayList<>();
        getPaths();
    }

    private void getPaths(){
        if(PreferenceManager.getInstance(mContext).getGifCount()> 1) {
            for (int i = 1; i <= PreferenceManager.getInstance(mContext).getGifCount(); i++) {
                String p = Environment.getExternalStorageDirectory() + "/mygif_" + i + ".gif";
                Log.d(TAG,"each path "+i+" p");
                paths.add(p);
            }
        }else{
            String p = Environment.getExternalStorageDirectory() + "/mygif_1.gif";
            paths.add(p);
        }
    }
    @Override
    public int getCount() {
        return paths.size();
    }

    @Override
    public Object getItem(int position) {
        return paths.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.gif_grid_item,null,false);
             viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(getBitmapBytes(position)!= null) {
            Log.d(TAG,"bitmap found at "+position);
            viewHolder.gifImageView.setBytes(getBitmapBytes(position));
            Log.d(TAG,"frames duration "+ viewHolder.gifImageView.getFramesDisplayDuration());
          //  viewHolder.gifImageView.setFramesDisplayDuration(500);
            viewHolder.gifImageView.startAnimation();
        }else
            Log.d(TAG,"bitmap not found at "+position);
        return convertView;
    }



    private   class ViewHolder{
        private GifImageView gifImageView;

        public ViewHolder(View view){
            this.gifImageView = (GifImageView) view.findViewById(R.id.gif_image_view);
        }
    }

    private byte[] getBitmapBytes(int indx){
        String path = paths.get(indx);
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
