package org.nutz.zbuild.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.nutz.zbuild.ZBuildProp;
import org.nutz.zbuild.Zvfs;

public class ZBuildGitCmd implements ZBuildCmd {

    public ZBuildGitCmd() {}

    public void exec(Zvfs vfs, ZBuildProp prop, String... args) {
        Set<String> olds = new HashSet<String>(Arrays.asList(vfs.list(".")));
        vfs.exec(null, "git", "clone", "-depth=1", args[0]);
        String newHome = null;
        for (String name : vfs.list(".")) {
            if (olds.contains(name))
                continue;
            newHome = name;
            break;
        }
        if (newHome == null) {
            throw new RuntimeException("can't clone git repo");
        }
        prop.setProjectHome(newHome);
    }
}
