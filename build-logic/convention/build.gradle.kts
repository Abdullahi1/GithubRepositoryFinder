import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.example.repofinder.buildlogic"


java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "repofinder.android.application"
            implementationClass = "AndroidAppGradlePlugin"
        }

        register("androidLibrary") {
            id = "repofinder.android.library"
            implementationClass = "AndroidLibraryGradlePlugin"
        }

        register("androidComposeLibrary") {
            id = "repofinder.android.compose.library"
            implementationClass = "ComposeAndroidLibraryGradlePlugin"
        }

        register("jvmLibrary") {
            id = "repofinder.jvm.library"
            implementationClass = "KotlinModuleGradlePlugin"
        }
    }
}
