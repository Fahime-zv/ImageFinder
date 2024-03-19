plugins {
    id("app.android.library")
    id("app.koin")
    id("kotlinx-serialization")
}

android {
    namespace = "payback.group.core.network"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
}