package com.kdotj.gaming.gameframework2d.impl;

import android.content.res.AssetManager;
import android.os.Environment;

import com.kdotj.gaming.gameframework2d.FileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by kyle.jablonski on 3/22/17.
 */

public class AndroidFileIO implements FileIO {

    AssetManager assetManager;
    String externalStoragePath;

    public AndroidFileIO(AssetManager assetManager){
        this.assetManager = assetManager;
        this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    @Override
    public InputStream readAsset(String fileName) throws IOException {
        return assetManager.open(fileName);
    }

    @Override
    public InputStream readFile(String fileName) throws IOException {
        return new FileInputStream(externalStoragePath + fileName);
    }

    @Override
    public OutputStream writeFile(String filename) throws IOException {
        return new FileOutputStream(externalStoragePath + filename);
    }
}
