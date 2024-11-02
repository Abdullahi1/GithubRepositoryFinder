import com.android.build.gradle.BaseExtension
import com.android.build.gradle.LibraryExtension
import com.example.repofinder.AndroidConfig
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


internal fun Project.addAndroidBlock() = this.extensions.getByType<BaseExtension>().run {
    compileSdkVersion(AndroidConfig.COMPILE_SDK_VERSION)
    defaultConfig {
        if (this@run !is LibraryExtension) applicationId = AndroidConfig.APPLICATION_ID

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
    if (this !is LibraryExtension) {
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

}