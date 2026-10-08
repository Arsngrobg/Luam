plugins {
    kotlin("jvm") version "2.3.0"
    application
}

kotlin {
    jvmToolchain(25)
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":luam-compiler"))
    implementation("com.github.ajalt.clikt:clikt:5.0.1")
}

application {
    applicationName = "luam"
    mainClass.set("io.github.arsngrobg.luam.driver.LuamMainKt")
    applicationDefaultJvmArgs = listOf("--enable-native-access=ALL-UNNAMED")
}
