plugins {
    alias(libs.plugins.repofinder.android.library)
//    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.data"
}

dependencies {

    implementation(projects.core.domain)

    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.moshi)
    implementation(libs.retrofit.coroutine.adapter)
    implementation(libs.retrofit.moshi.converter)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.javax.inject)
    implementation(libs.hilt.android)

    ksp(libs.hilt.compiler)
    ksp(libs.moshi.codegen)

    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mock.web.server)
    testImplementation(libs.kotlinx.coroutines.test)

//    implementation(libs.androidx.core.ktx)
//    implementation(libs.androidx.appcompat)
//    implementation(libs.material)
//
//    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation(libs.androidx.espresso.core)
}