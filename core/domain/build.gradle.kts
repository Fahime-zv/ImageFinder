plugins {
    id("app.android.library")
    id("app.koin")
}

android {
    namespace = "payback.group.core.domain"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:data"))
}