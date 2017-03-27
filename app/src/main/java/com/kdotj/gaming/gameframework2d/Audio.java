package com.kdotj.gaming.gameframework2d;

/**
 * Created by kyle.jablonski on 3/22/17.
 */

public interface Audio {

    Music newMusic(String fileName);

    Sound newSound(String fileName);
}
