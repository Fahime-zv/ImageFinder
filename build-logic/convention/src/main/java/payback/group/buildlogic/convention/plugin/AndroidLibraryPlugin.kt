package payback.group.buildlogic.convention.plugin

import com.android.build.gradle.LibraryExtension
import payback.group.buildlogic.convention.AndroidDefaults
import payback.group.buildlogic.convention.configureKotlinAndroid
import payback.group.buildlogic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                defaultConfig.targetSdk = AndroidDefaults.TARGET_SDK
            }

            dependencies {
                add("implementation",project(":core:shared"))
                add("implementation", libs.findLibrary("coroutines.core").get())
            }
        }
    }
}