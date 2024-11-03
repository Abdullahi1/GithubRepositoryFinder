import com.example.repofinder.GradlePluginsConfig.ANDROID_APPLICATION
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidAppGradlePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.addApplicationPlugin()
        target.addAndroidBlock()
    }
}

internal fun Project.addApplicationPlugin() {
    plugins.apply(ANDROID_APPLICATION)
    plugins.apply("org.jetbrains.kotlin.android")
    pluginManager.apply("com.google.devtools.ksp")
}
