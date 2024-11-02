plugins {
    alias(libs.plugins.repofinder.android.compose.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.home"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}