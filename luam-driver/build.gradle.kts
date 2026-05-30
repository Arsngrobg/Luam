plugins {
    application
}

dependencies {
    implementation(project(":luam-parser"))
    implementation(project(":luam-codegen"))
}

application {
    mainClass.set("dev.arsngrobg.luam.LuamDriver")
}
