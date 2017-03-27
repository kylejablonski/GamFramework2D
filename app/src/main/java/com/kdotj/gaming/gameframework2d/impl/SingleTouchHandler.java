package com.kdotj.gaming.gameframework2d.impl;

import android.view.MotionEvent;
import android.view.View;

import com.kdotj.gaming.gameframework2d.Input;
import com.kdotj.gaming.gameframework2d.Pool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyle.jablonski on 3/23/17.
 */

public class SingleTouchHandler implements TouchHandler {

    boolean isTouched;
    int touchX;
    int touchY;
    Pool<Input.TouchEvent> touchEventsPool;
    List<Input.TouchEvent> touchEvents = new ArrayList<>();
    List<Input.TouchEvent> touchEventsBuffer = new ArrayList<>();
    float scaleX;
    float scaleY;

    public SingleTouchHandler(View view, float scaleX, float scaleY) {
        Pool.PoolObjectFactory<Input.TouchEvent> factory = new Pool.PoolObjectFactory<Input.TouchEvent>() {
            @Override
            public Input.TouchEvent createObject() {
                return new Input.TouchEvent();
            }
        };
        touchEventsPool = new Pool<>(factory, 100);
        view.setOnTouchListener(this);
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public boolean isTouchDown(int pointer) {
        synchronized (this){
            if(pointer == 0){
                return isTouched;
            }else{
                return false;
            }
        }
    }

    @Override
    public int getTouchX(int pointer) {
        synchronized (this){
            return touchX;
        }
    }

    @Override
    public int getTouchY(int pointer) {
        synchronized (this){
            return touchY;
        }
    }

    @Override
    public List<Input.TouchEvent> getTouchEvents() {
        synchronized (this){
            int len = touchEvents.size();
            for(int i = 0; i < len; i++){
                touchEventsPool.free(touchEvents.get(i));
            }
            touchEvents.clear();
            touchEvents.addAll(touchEventsBuffer);
            touchEventsBuffer.clear();
            return touchEvents;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        synchronized (this){
            Input.TouchEvent touchEvent = touchEventsPool.newObject();
            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    touchEvent.type = Input.TouchEvent.TOUCH_DOWN;
                    isTouched = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    touchEvent.type = Input.TouchEvent.TOUCH_DRAG;
                    isTouched = true;
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    touchEvent.type = Input.TouchEvent.TOUCH_UP;
                    isTouched = false;
                    break;
            }

            touchEvent.x = touchX = (int) (event.getX() * scaleX);
            touchEvent.y = touchY = (int) (event.getY() * scaleY);
            touchEventsBuffer.add(touchEvent);
            return true;
        }
    }
}
