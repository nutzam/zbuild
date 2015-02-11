package org.nutz.zbuild;

import java.io.IOException;
import java.io.InputStream;

import org.nutz.ioc.impl.PropertiesProxy;

public class ZBuildProp extends PropertiesProxy {

    public ZBuildProp() {}

    public ZBuildProp(InputStream in){
        super(in);
    }
    
    public ZBuildProp(Zvfs vfs, String path) throws IOException {
        super(vfs.read(path));
    }
    
    public void load(InputStream in) {
        ZBuildProp p = new ZBuildProp(in);
        for (String key : p.getKeys()) {
            set(key, p.get(key));
        }
    }
    
    public String getProjectHome() {
        return get("proj_home");
    }
    
    public void setProjectHome(String path) {
        set("proj_home", path);
    }
    
    public void setDestHome(String path) {
        set("dest_home", path);
    }
    
    public String getDestHome() {
        return get("dest_home");
    }
}
