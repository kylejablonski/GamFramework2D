package com.kdotj.gaming.gameframework2d.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by kyle.jablonski on 3/24/17.
 */

public class AndroidFastRenderView extends SurfaceView implements Runnable {

    AndroidGame game;
    Bitmap frameBuffer;
    Thread renderThread;
    SurfaceHolder surfaceHolder;
    volatile boolean running = false;

    public AndroidFastRenderView(AndroidGame game, Bitmap frameBuffer){
        super(game);
        this.game = game;
        this.frameBuffer = frameBuffer;
        this.surfaceHolder = getHolder();
    }

    public void resume(){
        running = true;
        renderThread = new Thread(this);
        renderThread.start();
    }

    public void pause(){
        running = false;
        while(true){
            try{
                renderThread.join();
                break;
            }catch(InterruptedException e){

            }
        }
    }

    @Override
    public void run() {
        Rect dstRect = new Rect();
        long startTime = System.nanoTime();
        while(running){
            if(!surfaceHolder.getSurface().isValid()){
                continue;
            }
            float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
            startTime = System.nanoTime();
            game.getCurrentScreen().update(deltaTime);
            game.getCurrentScreen().present(deltaTime);

            Canvas canvas = surfaceHolder.lockCanvas();
            canvas.getClipBounds(dstRect);
            canvas.drawBitmap(frameBuffer, null, dstRect, null);
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
}
