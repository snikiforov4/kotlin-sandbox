plugins {
    kotlin("jvm")
    application
}

group = "snykyforov"
version = "1.0"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain {
        val javaLanguageVersion: String by project
        languageVersion.set(JavaLanguageVersion.of(javaLanguageVersion))
    }
}