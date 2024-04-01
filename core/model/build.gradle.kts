plugins {
    id("app.android.library")
    id("app.koin")
    id("kotlinx-serialization")
}

android {
    namespace = "payback.group.core.model"
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}