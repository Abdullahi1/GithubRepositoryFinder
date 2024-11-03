plugins {
//    alias(libs.plugins.android.library)
    alias(libs.plugins.repofinder.android.compose.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.users"
}

dependencies {

    implementation(projects.core.domain)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}