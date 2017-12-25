## AndroidComponent

[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/luojilab/DDComponentForAndroid/pulls)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/luojilab/DDComponentForAndroid/blob/master/LICENSEE) 

### 最新版本

模块|build-gradle|componentlib|router-anno-compiler|router-annotation
---|---|---|---|---
最新版本|[![Download](https://api.bintray.com/packages/zhmqq0527/compbuild/build-gradle/images/download.svg)](https://bintray.com/zhmqq0527/compbuild/build-gradle_latestVersion)|[![Download](https://api.bintray.com/packages/zhmqq0527/compbuild/componentlib/images/download.svg)](https://bintray.com/zhmqq0527/compbuild/componentlib_latestVersion)|[![Download](https://api.bintray.com/packages/zhmqq0527/compbuild/router-anno-compiler/images/download.svg)](https://bintray.com/zhmqq0527/compbuild/router-anno-compiler_latestVersion)|[![Download](https://api.bintray.com/packages/zhmqq0527/compbuild/router-annotation/images/download.svg)](https://bintray.com/zhmqq0527/compbuild/router-annotation_latestVersion)


### 实现功能：
- 组件可以单独调试
- 杜绝组件之前相互耦合，代码完全隔离，彻底解耦
- 组件之间通过接口+实现的方式进行数据传输
- 使用scheme和host路由的方式进行activity之间的跳转
- 自动生成路由跳转路由表
- 任意组件可以充当host，集成其他组件进行集成调试
- 可以动态对已集成的组件进行加载和卸载


### 原理解析
组件化设计思路 [浅谈Android组件化](https://mp.weixin.qq.com/s/RAOjrpie214w0byRndczmg)

原理解释请参考文章[Android彻底组件化方案实践](http://www.jianshu.com/p/1b1d77f58e84)

demo解读请参考文章[Android彻底组件化demo发布](http://www.jianshu.com/p/59822a7b2fad)

### 使用指南
#### 1、主项目引用编译脚本
在根目录的gradle.properties文件中，增加属性：

```ini
mainmodulename=app
```
其中mainmodulename是项目中的host工程，一般为app

在根目录的build.gradle中增加配置

```gradle
buildscript {
    dependencies {
        classpath 'com.luojilab.ddcomponent:build-gradle:1.0.0'
    }
}
```

为每个组件引入依赖库，如果项目中存在basiclib等基础库，可以统一交给basiclib引入

```gradle
compile 'com.luojilab.ddcomponent:componentlib:1.0.0'
```

#### 2、拆分组件为module工程
在每个组件的工程目录下新建文件gradle.properties文件，增加以下配置：

```ini
isRunAlone=true
debugComponent=sharecomponent
compileComponent=sharecomponent
```
上面三个属性分别对应是否单独调试、debug模式下依赖的组件，release模式下依赖的组件。具体使用方式请解释请参见上文第二篇文章

#### 3、应用组件化编译脚本
在组件和host的build.gradle都增加配置：

```gradle
apply plugin: 'com.dd.comgradle'
```

注意：不需要在引用com.android.application或者com.android.library

同时增加以下extension配置：

```gradle
combuild {
    applicationName = 'com.luojilab.reader.runalone.application.ReaderApplication'
    isRegisterCompoAuto = true
}
```
组件注册还支持反射的方式，有关isRegisterCompoAuto的解释请参见上文第二篇文章

#### 4、混淆
在混淆文件中增加如下配置
```
-keep interface * {
  <methods>;
}
-keep class com.luojilab.component.componentlib.** {*;}
-keep class com.luojilab.router.** {*;}
-keep class * implements com.luojilab.component.componentlib.applicationlike.IApplicationLike {*;}
```

关于如何进行组件之间数据交互和UI跳转，请参看 [Wiki](https://github.com/luojilab/DDComponentForAndroid/wiki)

### License

   Copyright 2017  Luojilab

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
