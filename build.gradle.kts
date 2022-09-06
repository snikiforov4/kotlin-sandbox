plugins {
    kotlin("jvm")
    application
}

group = "snykyforov"
version = "1.0"

repositories {
    mavenCentral()
}

subprojects {
    repositories { mavenCentral() }

    val javaLanguageVersion: String by project
    kotlin {
        jvmToolchain {
            languageVersion.set(JavaLanguageVersion.of(javaLanguageVersion))
        }
    }
}