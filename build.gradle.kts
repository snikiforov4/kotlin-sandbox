import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = javaLanguageVersion
    }
}