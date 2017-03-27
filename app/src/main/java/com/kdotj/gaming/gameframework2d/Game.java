package com.kdotj.gaming.gameframework2d;

import android.content.Context;

/**
 * Created by kyle.jablonski on 3/22/17.
 */

public interface Game {

    Input getInput();

    FileIO getFileIO();

    Graphics getGraphics();

    Audio getAudio();

    void setScreen(Screen screen);

    Screen getCurrentScreen();

    Screen getStartScreen();

    Context getContext();
}
