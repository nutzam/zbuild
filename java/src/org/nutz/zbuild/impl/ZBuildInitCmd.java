package org.nutz.zbuild.impl;

import org.nutz.zbuild.ZBuildProp;
import org.nutz.zbuild.Zvfs;

public class ZBuildInitCmd implements ZBuildCmd {

    public ZBuildInitCmd() {}

    public void exec(Zvfs vfs, ZBuildProp prop, String...args) {
        String root = args[0];
        String[] dirs = new String[]{"src", "test", "conf", "jars", "ROOT"};
        for (String name : dirs) {
            vfs.mkdir(root + "/" + name);
        }
    }

}
