package org.nutz.zbuild;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 只是个简单的抽象IO层
 * @author wendal(wendal1985@gmail.com)
 *
 */
public interface Zvfs {

    String[] list(String root);

    boolean isDir(String path);
    
    boolean canRead(String path);
    
    InputStream read(String path) throws IOException;
    
    OutputStream write(String path) throws IOException ;
    
    long length(String path);
    
    int exec(String outpath, String...args);
    
    boolean exist(String path);
    
    void mkdir(String path);
    
    void mkfile(String path);
    
    void rm(String path);
}
