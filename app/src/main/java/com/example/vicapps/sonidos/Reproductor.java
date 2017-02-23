package com.example.vicapps.sonidos;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class Reproductor extends AppCompatActivity {
    AssetManager assetManager;
    MediaPlayer mediaPlayer;
    String archivo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);
        assetManager = this.getAssets();

        archivo= "cartoon022.mp3";

        loadDescriptor(archivo);

    }

    private void loadDescriptor(String archivo) {
        try {
            mediaPlayer = new MediaPlayer();
            AssetFileDescriptor descriptor = assetManager.openFd(archivo);
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(),descriptor.getStartOffset(),descriptor.getLength());
            mediaPlayer.prepare();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pressPlay(View v){
        if(mediaPlayer==null){
            loadDescriptor(archivo);
        }
        mediaPlayer.start();

    }
    public void pressStop(View v) throws IOException {
        mediaPlayer.stop();
        loadDescriptor(archivo);
    }
    public void pressPause(View v){
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }


    }


    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.release();
    }
}
