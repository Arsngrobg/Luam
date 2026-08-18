import org.gradle.api.plugins.JavaApplication
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val LUAM_VERSION = "1.0.0"
val BUILD_TIME   = java.time.Instant.now().toString()

plugins {
    kotlin("jvm") version "2.3.0" apply false
}

// GLOBAL CONFIGURATION
subprojects {
    apply(plugin = "java")

    configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(25))
        }
    }

    tasks.withType<JavaCompile>().configureEach {
        options.release.set(25)
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

// PROJECTS (FROM TOP TO BOTTOM)
project(":luam-parser") { /* The bottom of the hierarchy so nothing ever happens */ }

project(":luam-codegen") {
    dependencies {
        add("implementation", project(":luam-parser"))
    }
}

project(":luam-driver") {

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "application")

    dependencies {
        add("implementation", project(":luam-parser"))
        add("implementation", project(":luam-codegen"))
    }

    extensions.configure<KotlinJvmProjectExtension> {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_25)
        }
    }

    extensions.configure<JavaApplication> {
        mainClass.set("dev.arsngrobg.luam.driver.LuamDriverKt")
    }
}
