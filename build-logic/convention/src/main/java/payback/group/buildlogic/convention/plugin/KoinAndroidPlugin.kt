package payback.group.buildlogic.convention.plugin

import payback.group.buildlogic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KoinAndroidPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("implementation", libs.findLibrary("koin.android").get())
                add("testImplementation", libs.findLibrary("koin.android.test").get())
            }
        }
    }
}