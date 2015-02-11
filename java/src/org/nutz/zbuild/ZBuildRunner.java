package org.nutz.zbuild;

import java.io.IOException;

import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.zbuild.impl.ZBuildBuildCmd;
import org.nutz.zbuild.impl.ZBuildGitCmd;

public class ZBuildRunner {

    private static final Log log = Logs.get().setTag("r.zbuild");
    
    protected Zvfs vfs;
    
    public ZBuildRunner(Zvfs vfs) {
        this.vfs = vfs;
    }

    public void run(String...args) throws IOException {
        String destHome = null;
        ZBuildProp prop = new ZBuildProp(getClass().getClassLoader().getResourceAsStream("zbuild.conf.tmpl"));
        // 首先,看看有啥参数
        if (args.length > 0) {
            // 第一个参数是
            String tmp = args[0];
            if (tmp.startsWith("git@")) {
                new ZBuildGitCmd().exec(vfs, prop, tmp);
            }
            if (args.length > 1) {
                destHome = args[1];
            }
        }
        
        // 查找zbuild.conf
        String confPath = prop.getProjectHome() + "/zbuild.conf";
        if (vfs.exist(confPath)) {
            prop.load(vfs.read(confPath));
        }
        // 如果强制覆盖了,那就进去呗
        if (destHome != null) {
            prop.setDestHome(destHome);
        }
        log.debug("zbuild conf -->\n" + Json.toJson(prop.toMap()));
        
        ZBuildBuildCmd build = new ZBuildBuildCmd();
        build.exec(vfs, prop);
    }
    
    
    public void assertExist(PropertiesProxy pp, String key) {
        if (vfs.exist(pp.get(key)))
            return;
        throw Lang.makeThrow("[%s]  path not exist [%s]", key, pp.get(key));
    }
}
