package org.nutz.zbuild.impl;

import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.zbuild.Zvfs;

public class ZBuildInitCmd implements ZBuildCmd {

    public ZBuildInitCmd() {}

    public void exec(Zvfs vfs, PropertiesProxy pp, String...args) {
        String root = args[0];
        String[] dirs = new String[]{"src", "test", "conf", "jars", "ROOT"};
        for (String name : dirs) {
            vfs.mkdir(root + "/" + name);
        }
    }

}
