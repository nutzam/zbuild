package org.nutz.zbuild.impl;

import org.nutz.zbuild.ZBuildProp;
import org.nutz.zbuild.Zvfs;

public class ZBuildBuildCmd implements ZBuildCmd {

    public ZBuildBuildCmd() {}

    public void exec(Zvfs vfs, ZBuildProp prop, String... args) {
        // 开始检查各种配置是否正确
        
        // 重头戏, 编译java源文件
        
        // 首先, 把依赖的jar找好
        
        // 逐一执行javac
        
        // 拷贝非java源文件
        
        // 替换prop里面的值
        
        // 搞定收工,清理一下
    }

}
