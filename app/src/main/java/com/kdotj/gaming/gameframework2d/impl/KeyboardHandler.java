package com.kdotj.gaming.gameframework2d.impl;

import android.view.KeyEvent;
import android.view.View;

import com.kdotj.gaming.gameframework2d.Input;
import com.kdotj.gaming.gameframework2d.Pool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyle.jablonski on 3/23/17.
 */

public class KeyboardHandler implements View.OnKeyListener {

    boolean [] pressedKeys = new boolean[128];
    Pool<Input.KeyEvent> keyEventPool;
    List<Input.KeyEvent> keyEventsBuffer = new ArrayList<>();
    List<Input.KeyEvent> keyEvents = new ArrayList<>();

    public KeyboardHandler(View view){
        Pool.PoolObjectFactory<Input.KeyEvent> factory = new Pool.PoolObjectFactory<Input.KeyEvent>() {
            @Override
            public Input.KeyEvent createObject() {
                return new Input.KeyEvent();
            }
        };
        keyEventPool = new Pool<>(factory, 100);
        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    public boolean isKeyPressed(int keyCode){
        if(keyCode < 0 || keyCode > 127)
            return false;
        return pressedKeys[keyCode];
    }

    public List<Input.KeyEvent> getKeyEvents(){
        synchronized (this){
            int len = keyEvents.size();
            for(int i = 0; i < len; i++){
                keyEventPool.free(keyEvents.get(i));
            }
            keyEvents.clear();
            keyEvents.addAll(keyEventsBuffer);
            keyEventsBuffer.clear();
            return keyEvents;
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_MULTIPLE){
            return false;
        }

        synchronized (this){
            Input.KeyEvent keyEvent = keyEventPool.newObject();
            keyEvent.keyCode = keyCode;
            keyEvent.keyChar = (char) event.getUnicodeChar();
            if(event.getAction() == KeyEvent.ACTION_DOWN){
                keyEvent.type = KeyEvent.ACTION_DOWN;
                if(keyCode > 0 && keyCode < 127){
                    pressedKeys[keyCode] = true;
                }
            }
            if(event.getAction() == KeyEvent.ACTION_UP){
                keyEvent.type = KeyEvent.ACTION_UP;
                if(keyCode > 0 && keyCode < 127){
                    pressedKeys[keyCode] = false;
                }
            }
            keyEventsBuffer.add(keyEvent);
        }
        return false;
    }
}
