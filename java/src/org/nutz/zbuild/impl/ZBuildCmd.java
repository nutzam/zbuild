package org.nutz.zbuild.impl;

import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.zbuild.Zvfs;

public interface ZBuildCmd {

    void exec(Zvfs vfs, PropertiesProxy pp, String...args);
}
