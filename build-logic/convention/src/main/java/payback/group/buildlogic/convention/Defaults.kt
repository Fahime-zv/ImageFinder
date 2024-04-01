package payback.group.buildlogic.convention

import org.gradle.api.JavaVersion

internal object AndroidDefaults {
    const val COMPILE_SDK = 34
    const val TARGET_SDK = 34
    const val MIN_SDK = 21
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
}

internal object JvmDefaults {
    val JVM_TARGET = JavaVersion.VERSION_11
}