package com.kdotj.gaming.gameframework2d;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by kyle.jablonski on 3/22/17.
 */

public interface FileIO {

    InputStream readAsset(String fileName) throws IOException;

    InputStream readFile(String fileName) throws IOException;

    OutputStream writeFile(String filename) throws IOException;

}