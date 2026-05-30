subprojects {
    plugins.apply("java")

    repositories {
        mavenCentral()
    }

    the<JavaPluginExtension>().apply {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(25))
        }
    }

    // earliest version of java when Mojang introduced datapacks (subject to change)
    tasks.withType<JavaCompile>().configureEach {
        options.compilerArgs.add("-Xlint:-options")
        options.release.set(8)
    }
}
