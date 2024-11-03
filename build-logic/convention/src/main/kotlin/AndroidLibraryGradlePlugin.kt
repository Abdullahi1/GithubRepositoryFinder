import com.example.repofinder.GradlePluginsConfig.ANDROID_LIBRARY
import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidLibraryGradlePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.addLibraryPlugin()
        target.addAndroidBlock()
    }
}

internal fun Project.addLibraryPlugin() {
    plugins.apply(ANDROID_LIBRARY)
    pluginManager.apply("com.google.devtools.ksp")
}