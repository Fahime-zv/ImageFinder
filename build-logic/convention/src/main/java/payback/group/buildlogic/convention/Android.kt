package payback.group.buildlogic.convention

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Configure base Kotlin with Android options
 */
@Suppress("UnstableApiUsage")
internal fun Project.configureKotlinAndroid(commonExtension: CommonExtension<*, *, *, *, *>, ) {
    commonExtension.apply {
        compileSdk = AndroidDefaults.COMPILE_SDK

        defaultConfig {
            minSdk = AndroidDefaults.MIN_SDK
            testOptions.targetSdk = AndroidDefaults.TARGET_SDK
            lint.targetSdk = AndroidDefaults.TARGET_SDK
            testInstrumentationRunner = AndroidDefaults.TEST_INSTRUMENTATION_RUNNER
        }

        packaging {
            resources {
                excludes += "META-INF/*"
            }
        }

        compileOptions {
            // Up to Java 11 APIs are available through desugaring
            // https://developer.android.com/studio/write/java11-minimal-support-table
            sourceCompatibility = JvmDefaults.JVM_TARGET
            targetCompatibility = JvmDefaults.JVM_TARGET
        }
    }

    configureKotlin()

    dependencies {
//
//        add("testImplementation", project(":core:test:kotlin-test"))
//        add("androidTestImplementation", project(":core:test:kotlin-test"))

        add("androidTestImplementation", libs.findLibrary("androidx.test.ext").get())
        add("androidTestImplementation", libs.findLibrary("androidx.test.runner").get())
        add("androidTestImplementation", libs.findLibrary("kotlin.test").get())
        add("androidTestImplementation", libs.findLibrary("mockk.android").get())

    }

}