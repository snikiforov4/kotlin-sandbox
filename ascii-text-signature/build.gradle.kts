plugins {
    kotlin("jvm")
    application
}

repositories { mavenCentral() }

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("MainKt")
}

