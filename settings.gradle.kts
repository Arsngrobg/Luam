rootProject.name = "Luam"

include(":luam-parser")
include(":luam-codegen")
include(":luam-driver")

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
