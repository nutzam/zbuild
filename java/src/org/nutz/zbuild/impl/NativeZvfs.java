package org.nutz.zbuild.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.zbuild.Zvfs;

public class NativeZvfs implements Zvfs {

    public String[] list(String root) {
        return new File(root).list();
    }

    @Override
    public boolean isDir(String path) {
        return new File(path).isDirectory();
    }

    @Override
    public boolean canRead(String path) {
        return new File(path).isFile() && new File(path).canRead();
    }

    @Override
    public InputStream read(String path) throws IOException {
        return new FileInputStream(path);
    }

    @Override
    public OutputStream write(String path) throws IOException {
        File f = new File(path);
        Files.createFileIfNoExists(f);
        return new FileOutputStream(f);
    }

    @Override
    public long length(String path) {
        return new File(path).length();
    }

    @Override
    public int exec(String outpath, String... args) {
        try {
            return Runtime.getRuntime().exec(args).waitFor();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 255;
    }

    public boolean exist(String path) {
        return new File(path).exists();
    }

    @Override
    public void mkdir(String path) {
        Files.createDirIfNoExists(path);
    }

    @Override
    public void mkfile(String path) {
        try {
            Files.createFileIfNoExists(path);
        }
        catch (IOException e) {
            throw Lang.wrapThrow(e);
        }
    }
    
    @Override
    public void rm(String path) {
        File f = new File(path);
        if (f.isDirectory())
            Files.deleteDir(f);
        else
            Files.deleteFile(f);
    }
}
