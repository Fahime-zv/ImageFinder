plugins {
    id("app.android.library")
    id("app.koin")
    id("kotlinx-serialization")
}

android {
    namespace = "payback.group.data"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:network"))
}


