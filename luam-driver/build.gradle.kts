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
    maven {
        url = uri("https://libraries.minecraft.net")
    }
}

dependencies {
    implementation(project(":luam-compiler"))
    implementation("com.mojang:brigadier:1.0.18")
}

application {
    applicationName = "luam"
    mainClass.set("io.github.arsngrobg.luam.driver.LuamMainKt")
}
