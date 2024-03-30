package payback.group.buildlogic.convention.plugin

import payback.group.buildlogic.convention.JvmDefaults
import payback.group.buildlogic.convention.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import payback.group.buildlogic.convention.libs

class JvmLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
            }
            extensions.configure<JavaPluginExtension> {
                sourceCompatibility = JvmDefaults.JVM_TARGET
                targetCompatibility = JvmDefaults.JVM_TARGET
            }
            configureKotlin()
            dependencies {
                add("implementation", libs.findLibrary("coroutines.core").get())
            }
        }
    }
}