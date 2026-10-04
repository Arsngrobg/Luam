import org.gradle.api.plugins.JavaApplication
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    kotlin("jvm") version "2.3.0" apply false
}

// GLOBAL CONFIGURATION
subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    extensions.configure<KotlinJvmProjectExtension> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_25)
        }
    }

    repositories {
        mavenCentral()
    }
}

// PROJECT
project(":luam-compiler")

project(":luam-driver") {
    apply(plugin = "application")

    dependencies {
        add("implementation", "com.github.ajalt.clikt:clikt:5.0.1")
    }

    extensions.configure<JavaApplication> {
        mainClass.set("io.github.arsngrobg.luam.driver.LuamMainKt")
    }
}
