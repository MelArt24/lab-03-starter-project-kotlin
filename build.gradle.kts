plugins {
    kotlin("jvm") version "1.9.10"
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.exposed:exposed-core:0.41.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.41.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.41.1")
    implementation("org.postgresql:postgresql:42.7.2")
}

application {
    mainClass.set("MainKt")
}

tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveFileName.set("app.jar")
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
}