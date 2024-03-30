plugins {
    id("app.jvm.library")
}
dependencies {
    testImplementation(libs.junit)
    implementation(libs.coroutines.core)
}
