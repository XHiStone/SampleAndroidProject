# SampleAndroidProject
Android基础框架，使用了一些流行的开源框架
##框架说明
###1.网络请求框架
RxJava+Retrofit2+Okhttp3
```
    compile 'com.squareup.okhttp3:logging-interceptor:3.2.0'
    compile 'com.squareup.retrofit2:retrofit:2.0.2'
    compile 'com.squareup.retrofit2:adapter-rxjava:2.0.2'
    compile 'com.squareup.retrofit2:converter-gson:2.0.2'
    compile 'io.reactivex:rxjava:1.1.8'
```
###2.消息推送框架
Otto
```
    compile 'com.squareup:otto:1.3.8'
```
###3.View绑定框架
rxbinding
```
    compile 'com.jakewharton.rxbinding:rxbinding:0.4.0'
```
###4.依赖注入框架
Dagger2（使模块间解耦尤其适合MVP模式）
```
    apt 'com.google.dagger:dagger-compiler:2.2'
    provided 'org.glassfish:javax.annotation:10.0-b28'
    compile 'com.google.dagger:dagger:2.2'
```
###5.数据库框架
greenDAO 最强大的ORM框架，速度快
```
    compile 'org.greenrobot:greendao:3.1.1'
    compile 'org.greenrobot:greendao-generator:3.1.0'
    compile 'net.zetetic:android-database-sqlcipher:3.5.2'
```
###6.Json快速解析框架
fastjson 淘宝开源框架，大量Json数据快速解析
```
    compile 'com.alibaba:fastjson:1.2.16'
```
###7.图片加载框架
fresco是facebook出的一款最大限度节省空间和CPU时间，它含有3级缓存
```
    compile 'com.facebook.fresco:fresco:0.13.0'
    compile 'com.facebook.stetho:stetho-okhttp3:1.3.1'
    compile "com.facebook.fresco:imagepipeline-okhttp3:0.12.0+"
```
###8.分包工具框架
multidex针对构建超过65K个方法进行分包处理
```
    compile 'com.android.support:multidex:1.0.1'
```
####虽然我们开发起来multidex是一个极好的东西，但是multidex还是存在自己的局限性，我们在开发测试之前要清楚局限性是什么：
  8.1如果二DEX文件太大，安装分割dex文件是一个复杂的过程，可能会导致应用程序无响应（ANR）的错误。
      在这种情况下，你应该尽量的减小dex文件的大小和删除无用的逻辑，而不是完全依赖于multidex。
<br /><br />
  8.2在Android 4.0设备（API Level 14）之前，由于Dalvik linearalloc bug（问题22586），multidex很可能是无法运行的。
     如果希望运行在Level 14之前的Android系统版本，请先确保完整的测试和使用。
     应用程序使用了multiedex配置的，会造成使用比较大的内存。当然，可能还会引起dalvik虚拟机的崩溃(issue 78035)。
<br /><br />
  8.3对于应用程序比较复杂的，存在较多的library的项目。multidex可能会造成不同依赖项目间的dex文件函数相互调用，找不到方法。

###9.内存泄漏检测工具
leakcanary
```
    debugCompile 'com.squareup.leakcanary:leakcanary-android:1.4-beta2'
    testCompile 'com.squareup.leakcanary:leakcanary-android-no-op:1.4-beta2'
    releaseCompile 'com.squareup.leakcanary:leakcanary-android-no-op:1.4-beta2'
```
###10.View注解框架
Butterknife 最实用的注解框架生成代码来执行查询，而不是缓慢的反射,。调用 bind委托这个生成的代码,你可以看到和调试。
```
    compile 'com.jakewharton:butterknife:8.0.1'
    //butterknife 8.0.1版本将compiler分离，需要apt插件
    apt 'com.jakewharton:butterknife-compiler:8.0.1'
```
#Gradle配置
```
buildscript {
    repositories {
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
        classpath 'org.greenrobot:greendao-gradle-plugin:3.1.1'
    }
}
```
插件
```
apply plugin: 'com.neenbedankt.android-apt'
apply plugin: 'org.greenrobot.greendao'
```
```
 buildTypes {
            ...
        lintOptions {
            warning 'InvalidPackage'
        }
    }
```
