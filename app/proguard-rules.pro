#-----------基本配置--------------
# 代码混淆压缩比，在0~7之间，默认为5，一般不需要改
-optimizationpasses 5

# 混淆时不使用大小写混合，混淆后的类名为小写
-dontusemixedcaseclassnames

# 指定不去忽略非公共的库的类
-dontskipnonpubliclibraryclasses

# 指定不去忽略非公共的库的类的成员
-dontskipnonpubliclibraryclassmembers

# 不做预校验，可加快混淆速度
# preverify是proguard的4个步骤之一
# Android不需要preverify，去掉这一步可以加快混淆速度
-dontpreverify

# 不优化输入的类文件
-dontoptimize

# 混淆时生成日志文件，即映射文件
-verbose

# 指定映射文件的名称
-printmapping proguardMapping.txt

#混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

# 保护代码中的Annotation不被混淆
-keepattributes *Annotation*

# 忽略警告
-ignorewarnings

# 保护泛型不被混淆
-keepattributes Signature

# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable

#-----------需要保留的东西--------------
# 保留所有的本地native方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

# 保留了继承自Activity、Application、Fragment这些类的子类
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService

# support-v4
-dontwarn android.support.v4.**
-keep class android.support.v4.** { *; }
-keep interface android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
# support-v7
-dontwarn android.support.v7.**                                             #去掉警告
-keep class android.support.v7.** { *; }                                    #过滤android.support.v7
-keep interface android.support.v7.app.** { *; }
-keep public class * extends android.support.v7.**

#----------------保护指定的类和类的成员，但条件是所有指定的类和类成员是要存在------------------------------------
-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# 保持自定义控件类不被混淆，指定格式的构造方法不去混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# 保持自定义控件类不被混淆
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
    *** get*();
}

# 保留在Activity中的方法参数是View的方法
# 从而我们在layout里边编写onClick就不会被影响
-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}

# 保留枚举 enum 类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# 保留 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# 保留 Serializable 不被混淆
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# 不混淆资源类
-keepclassmembers class **.R$* { *; }

# 对于带有回调函数onXXEvent()的，不能被混淆
-keepclassmembers class * {
    void *(**On*Event);
}
# WebView
-keepclassmembers class fqcn.of.javascript.interface.for.Webview {
   public *;
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.WebViewClient {
    public void *(android.webkit.WebView, jav.lang.String);
}

# 保留实体类和成员不被混淆(根据具体情况修改entity的路径)
-keep class com.zerlings.gabeisfaker.db.**{*;}


#-----------常用第三方类库的混淆添加--------------
#eventbus
-keepattributes *Annotation*

-keepclassmembers class ** {
    @de.greenrobot.event.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum de.greenrobot.event.ThreadMode { *; }
#过滤fastjson
-keep class com.alibaba.fastjson.** { *; }
-dontwarn com.alibaba.fastjson.**

#过滤okhttp
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**

#过滤glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
#retrofit
-dontwarn okio.**
-dontwarn javax.annotation.**
#解决使用Retrofit+rxJava联网时，在6.0系统出现java.lang.InternalError奔溃的问题:http://blog.csdn.net/mp624183768/article/details/79242147
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
#过滤butterknife项目
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}
-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
#greendao3.2.0,此是针对3.2.0，如果是之前的，可能需要更换下包名
-keep class org.greenrobot.greendao.**{*;}
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties


#-----------常用第三方SDK的混淆添加--------------
#友盟分享需要混淆的处理
 -dontshrink
 -dontoptimize
 -dontwarn com.google.android.maps.**
 -dontwarn android.webkit.WebView
 -dontwarn com.umeng.**
 -dontwarn com.tencent.weibo.sdk.**
 -dontwarn com.facebook.**
 -keep public class javax.**
 -keep public class android.webkit.**
 -dontwarn android.support.v4.**
 -keep enum com.facebook.**
 -keepattributes Exceptions,InnerClasses,Signature
 -keepattributes *Annotation*
 -keepattributes SourceFile,LineNumberTable

 -keep public interface com.facebook.**
 -keep public interface com.tencent.**
 -keep public interface com.umeng.socialize.**
 -keep public interface com.umeng.socialize.sensor.**
 -keep public interface com.umeng.scrshot.**

 -keep public class com.umeng.socialize.* {*;}


 -keep class com.facebook.**
 -keep class com.facebook.** { *; }
 -keep class com.umeng.scrshot.**
 -keep public class com.tencent.** {*;}
 -keep class com.umeng.socialize.sensor.**
 -keep class com.umeng.socialize.handler.**
 -keep class com.umeng.socialize.handler.*
 -keep class com.umeng.weixin.handler.**
 -keep class com.umeng.weixin.handler.*
 -keep class com.umeng.qq.handler.**
 -keep class com.umeng.qq.handler.*
 -keep class UMMoreHandler{*;}
 -keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
 -keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
 -keep class im.yixin.sdk.api.YXMessage {*;}
 -keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}
 -keep class com.tencent.mm.sdk.** {
    *;
 }
 -keep class com.tencent.mm.opensdk.** {
    *;
 }
 -keep class com.tencent.wxop.** {
    *;
 }
 -keep class com.tencent.mm.sdk.** {
    *;
 }
 -dontwarn twitter4j.**
 -keep class twitter4j.** { *; }

 -keep class com.tencent.** {*;}
 -dontwarn com.tencent.**
 -keep class com.kakao.** {*;}
 -dontwarn com.kakao.**
 -keep public class com.umeng.com.umeng.soexample.R$*{
     public static final int *;
 }
 -keep public class com.linkedin.android.mobilesdk.R$*{
     public static final int *;
 }
 -keepclassmembers enum * {
     public static **[] values();
     public static ** valueOf(java.lang.String);
 }

 -keep class com.tencent.open.TDialog$*
 -keep class com.tencent.open.TDialog$* {*;}
 -keep class com.tencent.open.PKDialog
 -keep class com.tencent.open.PKDialog {*;}
 -keep class com.tencent.open.PKDialog$*
 -keep class com.tencent.open.PKDialog$* {*;}
 -keep class com.umeng.socialize.impl.ImageImpl {*;}
 -keep class com.sina.** {*;}
 -dontwarn com.sina.**
 -keep class  com.alipay.share.sdk.** {
    *;
 }

 -keepnames class * implements android.os.Parcelable {
     public static final ** CREATOR;
 }

 -keep class com.linkedin.** { *; }
 -keep class com.android.dingtalk.share.ddsharemodule.** { *; }
 -keepattributes Signature

# 友盟统计
-keep class com.umeng.commonsdk.** {*;}
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}
-keep public class [com.zongxueguan.naochanle_android].R$*{
public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
### 网易云信聊天：NIM SDK
-dontwarn com.netease.**
-keep class com.netease.** {*;}
-dontwarn org.apache.lucene.**
-keep class org.apache.lucene.** {*;}
-keep class net.sqlcipher.** {*;}

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-keep class **.R$* {*;}

#微信支付混淆
-keep class com.tencent.mm.opensdk.** {
*;
}
-keep class com.tencent.wxop.** {
*;
}
-keep class com.tencent.mm.sdk.** {
*;
}

#JPush配置 混淆
-dontoptimize
-dontpreverify

-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
-keep class * extends cn.jpush.android.helpers.JPushMessageReceiver { *; }

-dontwarn cn.jiguang.**
-keep class cn.jiguang.** { *; }

#高德地图混淆配置
 # 3D 地图 V5.0.0之前：
    -keep   class com.amap.api.maps.**{*;}
    -keep   class com.autonavi.amap.mapcore.*{*;}
    -keep   class com.amap.api.trace.**{*;}

  #  3D 地图 V5.0.0之后：
    -keep   class com.amap.api.maps.**{*;}
    -keep   class com.autonavi.**{*;}
    -keep   class com.amap.api.trace.**{*;}

  #  定位
    -keep class com.amap.api.location.**{*;}
    -keep class com.amap.api.fence.**{*;}
    -keep class com.autonavi.aps.amapapi.model.**{*;}

   # 搜索
    -keep   class com.amap.api.services.**{*;}

   # 2D地图
    -keep class com.amap.api.maps2d.**{*;}
    -keep class com.amap.api.mapcore2d.**{*;}

    #导航
    -keep class com.amap.api.navi.**{*;}
    -keep class com.autonavi.**{*;}


#-----------其他配置--------------
# 对WebView的处理
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, java.lang.String);
}
# 保留JS方法不被混淆
-keepclassmembers class com.example.xxx.MainActivity$JSInterface1 {
    <methods>;
}