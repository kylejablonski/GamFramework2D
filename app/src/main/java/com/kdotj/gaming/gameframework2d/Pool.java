package com.kdotj.gaming.gameframework2d;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyle.jablonski on 3/23/17.
 */

public class Pool<T> {


    public interface PoolObjectFactory<T>{
        T createObject();
    }

    public List<T> freeObjects;
    public PoolObjectFactory<T> factory;
    public int maxSize;

    public Pool(PoolObjectFactory<T> factory, int maxSize){
        this.factory = factory;
        this.maxSize = maxSize;
        freeObjects = new ArrayList<>(maxSize);
    }

    public T newObject(){
        T object = null;
        if(freeObjects.size() < maxSize){
            object = factory.createObject();
        }else{
            object = freeObjects.remove(freeObjects.size() - 1);
        }
        return object;
    }

    public void free(T object){
        if(freeObjects.size() < maxSize)
            freeObjects.add(object);
    }
}
