package org.nutz.zbuild;

import org.nutz.zbuild.impl.NativeZvfs;

public class ZBuild {
    
    public static final String PROJECT_HOME = "proj_home";
    public static final String DEST_HOME = "dest_home";

    public static void main(String[] args) throws Throwable {
        try {
            Class.forName("org.nutz.Nutz");
            new ZBuildRunner(new NativeZvfs()).run(args);
        }
        catch (ClassNotFoundException e) {
            // TODO 下载Nutz.jar,然后运行
        }
    }

}
