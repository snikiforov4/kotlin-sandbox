rootProject.name = "kotlin-sandbox"
include("ascii-text-signature")

pluginManagement {
    val kotlinVersion: String by settings
    plugins {
        kotlin("jvm") version kotlinVersion
        application
    }
}