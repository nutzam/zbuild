# zbuild 是什么

我们准备用最轻巧的方式，帮你构建和运行你的 Java 项目。
你需要自行在本地准备依赖包，然后编译部署运行都可以通过 zbuild 这个小程序来做

# zbuild 的使用方式

```
# 编译&部署
zbuild /path/to/project  /path/to/dest

- 如果/path/to/project是个git路径,那么会先clone下来
- 会把项目编译
- 会把相关资源输出到对应目录
- 会生成一个运行脚本
```


# zbuild 的配置

如果你没有给配置文件，zbuild 会自动创建配置项，只要你给一个工程目录。
如果这个工程目录下没有 zbuild.conf，则会自动生成一个。

```sh
projHome=/path/to/project     # 项目的工程目录，支持 ~ 和环境变量, 默认是当前路径
destHome=/path/to/dest        # 项目的目标目录，支持 ~ 和环境变量, 默认是 ${projName}/output
projName=${projName}          # 默认取项目工程目录的名称

# 项目版本，支持一个日期格式的占位符
projVersion=nightly.${yyMMdd_HHmmSS}

# 下面的选项都支持半角逗号分隔的多个目录
build_deps=${projName}/jars   # 项目的依赖 jar，会递归所有非隐藏文件夹
                              # 如果是一个Http URL, 那么会尝试下载并解压到${projName}/jars
build_src=${projName}/src     # 项目的源码目录，目录里所有的东东(除了.java文件)都会被打入 jar
build_conf=${projName}/conf   # 项目的配置文件目录，内容会被输出到 $dest_conf
build_tmp=${destHome}/tmp     # 编译的临时目录，编译完毕会被删除，这个目录用来生成 jar

# 下面和运行相关
dest_bin=${destHome}/bin          # 项目的运行脚本放在哪里
dest_bin_start=${projName}_start  # 项目的启动脚本
dest_bin_stop=${projName}_stop    # 项目的停止脚本
dest_conf=${destHome}/conf        # 项目的配置信息目录
dest_jars=${destHome}/jars        # 项目运行时依赖的 jar 存放的位置
                                  # 当然，它也包括项目编译出来的 jar


# 配置文件的替换
# zbuild 会依次打开 $destHome/conf 下面的所有 properties 文件，
# 看到里面如果有下面的键，就会替换
dest_properties=$destHome/local.properties

```
