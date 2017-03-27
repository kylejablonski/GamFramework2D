package com.kdotj.gaming.gameframework2d.impl;

import android.media.SoundPool;

import com.kdotj.gaming.gameframework2d.Sound;


/**
 * Created by kyle.jablonski on 3/22/17.
 */

public class AndroidSound implements Sound {

    int soundId;
    SoundPool soundPool;

    public AndroidSound(SoundPool soundPool, int soundId){
        this.soundPool = soundPool;
        this.soundId = soundId;
    }

    @Override
    public void play(float volume) {
        soundPool.play(soundId, volume, volume, 0, 0, 1);
    }

    @Override
    public void dispose() {
        soundPool.unload(soundId);
    }
}
