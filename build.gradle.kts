import org.gradle.api.plugins.JavaApplication
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val LUAM_VERSION = providers.gradleProperty("luam.version").get()
val BUILD_TIME   = java.time.Instant.now().toString()

plugins {
    kotlin("jvm") version "2.3.0" apply false
}

// GLOBAL CONFIGURATION
subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "java")

    configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(25))
        }
    }

    tasks.withType<JavaCompile>().configureEach {
        options.release.set(25)
    }

    extensions.configure<KotlinJvmProjectExtension> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_25)
        }
    }

    repositories {
        mavenCentral()
    }

    tasks.named<Jar>("jar") {
        manifest {
            attributes(
                "Luam-Version" to LUAM_VERSION,
                "Build-Time"   to BUILD_TIME
            )
        }
    }
}

// PROJECT
project(":luam-compiler") {
    apply(plugin = "application")

    dependencies {
        add("implementation", "com.github.ajalt.clikt:clikt:5.0.1")
    }

    extensions.configure<JavaApplication> {
        mainClass.set("io.github.arsngrobg.luam.driver.LuamMainKt")
    }
}
