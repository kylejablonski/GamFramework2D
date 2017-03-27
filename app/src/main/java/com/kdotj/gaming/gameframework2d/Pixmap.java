package com.kdotj.gaming.gameframework2d;

/**
 * Created by kyle.jablonski on 3/22/17.
 */

public interface Pixmap {

    int getWidth();

    int getHeight();

    Graphics.PixmapFormat getFormat();

    void dispose();
}
