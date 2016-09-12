# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in G:\adt-bundle-windows-x86_64-20140702\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-dontwarn org.apache.**
-keep class org.apache.**{*;}
-dontwarn android.app.**
-keep class android.app.**{*;}
-dontwarn org.codehaus.**
-keep class org.codehaus.**{*;}
-dontwarn net.**
-keep class net.**{*;}
-dontwarn com.androidquery.**
-keep class com.androidquery.**{*;}
-dontwarn java.**
-keep class java.**{*;}
-dontwarn com.baidu.**
-keep class com.baidu.**{*;}
-dontwarn com.aliyun.**
-keep class com.aliyun.**{*;}
-dontwarn com.alibaba.**
-keep class com.alibaba.**{*;}
-dontwarn com.alimm.**
-keep class com.alimm.**{*;}
-dontwarn com.youku.**
-keep class com.youku.**{*;}
-dontwarn android.net.**
-keep class android.net.**{*; }
-dontwarn com.amap.**
-keep class com.amap.**{*; }
-dontwarn android.webkit.**
-keep class android.webkit.**{*;}
-dontwarn com.nostra13.**
-keep class com.nostra13.**{*;}
-dontwarn com.punchbox.**
-keep class com.punchbox.**{*;}
-dontwarn com.anywhere.appbase.**
-keep class com.anywhere.appbase.**{*;}
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
-keep class com.apsoft.scfb.ui.fragments.** { *; }
