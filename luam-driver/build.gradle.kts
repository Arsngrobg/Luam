plugins {
    kotlin("jvm") version "2.3.0"
    application
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.ajalt.clikt:clikt:5.0.1")
}

application {
    mainClass.set("io.github.arsngrobg.luam.driver.LuamMainKt")
}
