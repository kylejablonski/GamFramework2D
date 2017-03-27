package com.kdotj.gaming.gameframework2d.impl;

import android.view.View;

import com.kdotj.gaming.mrnom.framework.Input;

import java.util.List;

/**
 * Created by kyle.jablonski on 3/23/17.
 */

public interface TouchHandler extends View.OnTouchListener {

    boolean isTouchDown(int pointer);

    int getTouchX(int pointer);

    int getTouchY(int pointer);

    List<Input.TouchEvent> getTouchEvents();

}
