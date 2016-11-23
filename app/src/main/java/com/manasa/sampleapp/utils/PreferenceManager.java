package com.manasa.sampleapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by manasa on 7/11/16.
 */

public class PreferenceManager {

    private  final String GIF_COUNT = "gifcount";

    private static PreferenceManager INSTANCE = null;

    private static Context mContext = null;

    public static PreferenceManager getInstance(Context pContext){
        if( INSTANCE == null){
            mContext = pContext;
            INSTANCE = new PreferenceManager();
        }

        return INSTANCE;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return INSTANCE;
    }

    public  void saveGifCount(){
        SharedPreferences preferences = mContext.getSharedPreferences("Gifhub",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        int existingValue = preferences.getInt(GIF_COUNT,0);
        editor.putInt(GIF_COUNT,existingValue+1);
        editor.apply();


    }

    public  int getGifCount(){
        SharedPreferences preferences = mContext.getSharedPreferences("Gifhub",Context.MODE_PRIVATE);
        return preferences.getInt(GIF_COUNT,0);
    }

    public void decrementGifCount(){
        SharedPreferences preferences = mContext.getSharedPreferences("Gifhub",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        int existingValue = preferences.getInt(GIF_COUNT,0);
        editor.putInt(GIF_COUNT,existingValue-1);
        editor.apply();
    }
}
