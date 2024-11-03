package com.example.repofinder

import org.gradle.api.JavaVersion

object AndroidConfig {
    const val APP_NAME = "Github Repository Finder"
    const val COMPILE_SDK_VERSION = 35
    const val MIN_SDK_VERSION = 24
    const val TARGET_SDK_VERSION = 35
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0.0"
    const val APPLICATION_ID = "com.example.repofinder"
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
}

object JavaConfig {
    val JAVA_VERSION = JavaVersion.VERSION_21
}

object GradlePluginsConfig {
    const val KOTLIN = "kotlin"
    const val KOTLIN_KAPT = "kotlin-kapt"
    const val KOTLIN_ANDROID = "kotlin-android"
    const val ANDROID_LIBRARY = "com.android.library"
    const val ANDROID_APPLICATION = "com.android.application"
    const val HILT = "dagger.hilt.android.plugin"
    const val KOTLIN_PARCELIZE = "kotlin-parcelize"
    const val JETBRAINS_KOTLIN_ANDROID = "org.jetbrains.kotlin.android"
    const val ANDROID_TEST = "com.android.test"
}
