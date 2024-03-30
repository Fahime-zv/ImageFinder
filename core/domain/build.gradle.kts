plugins {
    id("app.android.library")
    id("app.koin")
}

android {
    namespace = "payback.group.domain"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:data"))
}