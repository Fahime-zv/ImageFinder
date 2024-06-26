import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.agp)
    compileOnly(libs.kgp)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "app.android.library"
            implementationClass = "payback.group.buildlogic.convention.plugin.AndroidLibraryPlugin"
        }
        register("jvmLibrary") {
            id = "app.jvm.library"
            implementationClass = "payback.group.buildlogic.convention.plugin.JvmLibraryPlugin"
        }
        register("koin") {
            id = "app.koin"
            implementationClass = "payback.group.buildlogic.convention.plugin.KoinPlugin"
        }
        register("koinAndroid") {
            id = "app.koin.android"
            implementationClass = "payback.group.buildlogic.convention.pluginn.KoinAndroidPlugin"
        }
    }
}