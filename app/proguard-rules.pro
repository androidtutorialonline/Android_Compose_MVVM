# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class org.mockito.** { *; }
-keep class androidx.** { *; }

-keep class com.androidapps.composeMVVM.pojo.** {*;}

-keep class androidx.compose.ui.test.junit4.AndroidComposeTestRule { *; }
-keep class com.androidapps.composeMVVM.ItemListScreenTest { *; }

# Hilt
-dontwarn dagger.hilt.**
-keep class dagger.hilt.** { *; }
-keep @dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories$Factory { *; }

# Keep Kotlin metadata for reflection
-keep class kotlin.Metadata { *; }