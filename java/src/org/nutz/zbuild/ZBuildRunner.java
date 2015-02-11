package org.nutz.zbuild;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.lang.Lang;
import org.nutz.log.Log;
import org.nutz.log.Logs;

public class ZBuildRunner {

    private static final Log log = Logs.get().setTag("r.zbuild");
    
    protected Zvfs vfs;
    
    public ZBuildRunner(Zvfs vfs) {
        this.vfs = vfs;
    }

    public void run(String...args) throws IOException {
        String projectHome = ".";
        String destHome = null;
        // 首先,看看有啥参数
        if (args.length > 0) {
            // 第一个参数是
            String tmp = args[0];
            if (tmp.startsWith("git@")) {
                Set<String> olds = new HashSet<String>(Arrays.asList(vfs.list(".")));
                vfs.exec(null, "git", "clone", "-depth=1", tmp);
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
                projectHome = newHome;
            }
            if (args.length > 1) {
                destHome = args[1];
            }
        }
        
        // 查找zbuild.conf
        String confPath = projectHome + "/zbuild.conf";
        if (!vfs.exist(confPath)) {
            log.info("create new zbuild.conf");
            try {
                OutputStream out = vfs.write(confPath);
                out.close();
            }
            catch (IOException e) {
            }
        }
        PropertiesProxy pp = new PropertiesProxy(vfs.read(confPath));
        PropertiesProxy cnf = new PropertiesProxy(getClass().getClassLoader().getResourceAsStream("zbuild.conf.tmpl"));
        for (String key : pp.getKeys()) {
            cnf.put(key, pp.get(key));
        }
        // 如果强制覆盖了,那就进去呗
        if (destHome != null) {
            cnf.put("destHome", destHome);
        }
        log.debug("zbuild conf -->\n" + pp.toString());
        
        // 开始检查各种配置是否正确
        
        // 重头戏, 编译java源文件
        
        // 首先, 把依赖的jar找好
        
        // 逐一执行javac
        
        // 拷贝非java源文件
        
        // 替换prop里面的值
        
        // 搞定收工,清理一下
    }
    
    
    public void assertExist(PropertiesProxy pp, String key) {
        if (vfs.exist(pp.get(key)))
            return;
        throw Lang.makeThrow("[%s]  path not exist [%s]", key, pp.get(key));
    }
}
