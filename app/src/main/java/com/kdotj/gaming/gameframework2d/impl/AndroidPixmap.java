package com.kdotj.gaming.gameframework2d.impl;

import android.graphics.Bitmap;

import com.kdotj.gaming.mrnom.framework.Graphics;
import com.kdotj.gaming.mrnom.framework.Pixmap;

/**
 * Created by kyle.jablonski on 3/24/17.
 */

public class AndroidPixmap implements Pixmap {

    Bitmap bitmap;
    Graphics.PixmapFormat pixmapFormat;

    public AndroidPixmap(Bitmap bitmap , Graphics.PixmapFormat pixmapFormat){
        this.bitmap = bitmap;
        this.pixmapFormat = pixmapFormat;
    }

    @Override
    public int getWidth() {
        return bitmap.getWidth();
    }

    @Override
    public int getHeight() {
        return bitmap.getHeight();
    }

    @Override
    public Graphics.PixmapFormat getFormat() {
        return pixmapFormat;
    }

    @Override
    public void dispose() {
        bitmap.recycle();
    }
}
