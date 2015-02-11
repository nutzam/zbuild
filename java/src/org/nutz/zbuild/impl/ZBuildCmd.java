package org.nutz.zbuild.impl;

import org.nutz.zbuild.ZBuildProp;
import org.nutz.zbuild.Zvfs;

public interface ZBuildCmd {

    void exec(Zvfs vfs, ZBuildProp prop, String...args);
}
