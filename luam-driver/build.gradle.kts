plugins {
    application
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(project(":luam-parser"))
    implementation(project(":luam-codegen"))
}

application {
    mainClass = "dev.arsngrobg.luam.LuamDriverKt"
}
