import com.android.build.gradle.BaseExtension
import com.example.repofinder.AndroidConfig
import com.example.repofinder.GradlePluginsConfig.ANDROID_LIBRARY
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class ComposeAndroidLibraryGradlePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.addComposeLibraryPlugin()
        target.addComposeLibraryBlock()
    }
}

internal fun Project.addComposeLibraryPlugin() {
    plugins.apply(ANDROID_LIBRARY)
    plugins.apply( "org.jetbrains.kotlin.plugin.compose")
}

internal fun Project.addComposeLibraryBlock() = this.extensions.getByType<BaseExtension>().run {
    compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)
    defaultConfig {

        minSdk = AndroidConfig.MIN_SDK_VERSION
        targetSdk = AndroidConfig.TARGET_SDK_VERSION
        versionCode = AndroidConfig.VERSION_CODE
        versionName = AndroidConfig.VERSION_NAME
        testInstrumentationRunner = AndroidConfig.TEST_INSTRUMENTATION_RUNNER
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }



    project.tasks.withType(KotlinCompile::class.java).configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
            freeCompilerArgs.addAll(
                "-Xstring-concat=inline"
            )
        }
    }

    buildFeatures.buildConfig = true

    //Compose Features
    buildFeatures.apply {
        compose = true
    }

    composeOptions.apply {
        kotlinCompilerExtensionVersion = "1.5.7"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}