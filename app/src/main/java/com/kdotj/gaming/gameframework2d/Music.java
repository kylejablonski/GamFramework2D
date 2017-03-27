package com.kdotj.gaming.gameframework2d;

/**
 * Created by kyle.jablonski on 3/22/17.
 */

public interface Music {

    void play();

    void stop();

    void pause();

    void setLooping(boolean looping);

    void setVolume(float volume);

    boolean isPlaying();

    boolean isStopped();

    boolean isLooping();

    void dispose();
}
