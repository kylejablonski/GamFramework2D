package com.kdotj.gaming.gameframework2d.impl;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

import com.kdotj.gaming.gameframework2d.Audio;
import com.kdotj.gaming.gameframework2d.Music;
import com.kdotj.gaming.gameframework2d.Sound;

import java.io.IOException;


/**
 * Created by kyle.jablonski on 3/22/17.
 */

public class AndroidAudio implements Audio {

    AssetManager assetManager;
    SoundPool soundPool;

    public AndroidAudio(Activity activity){
        activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
        assetManager = activity.getAssets();
        soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
    }

    @Override
    public Music newMusic(String fileName) {
        try{
            AssetFileDescriptor assetFileDescriptor = assetManager.openFd(fileName);
         return new AndroidMusic(assetFileDescriptor);
        }catch(IOException ex){
            throw new RuntimeException("Couldn't load music '"+ fileName +"'");
        }
    }

    @Override
    public Sound newSound(String fileName) {
        try{
            AssetFileDescriptor assetFileDescriptor = assetManager.openFd(fileName);
            int soundId = soundPool.load(assetFileDescriptor, 0);
            return new AndroidSound(soundPool, soundId);
        }catch(IOException ex){
            throw new RuntimeException("Couldn't load sound '"+ fileName +"'");
        }
    }
}
