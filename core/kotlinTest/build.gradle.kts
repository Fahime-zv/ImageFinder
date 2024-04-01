plugins {
    id("app.jvm.library")
}

dependencies {
    api(libs.junit)
    api(libs.mockk)
    api(libs.kotlin.test)
    api(libs.kotlin.test.junit)
    api(libs.coroutines.test)

    implementation(project(":core:shared"))
    implementation(libs.coroutines.core)
}