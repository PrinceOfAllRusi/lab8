/*
 * This file was generated by the Gradle 'init' task.
 *
 * This project uses @Incubating APIs which are subject to change.
 */

plugins {
    id("multilib.kotlin-application-conventions")
    kotlin("jvm") version "1.7.20"
    id("org.openjfx.javafxplugin") version "0.0.13"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
javafx {
    version = "11.0.2"
    modules = mutableListOf("javafx.controls", "javafx.graphics")
}

dependencies {
    implementation("io.insert-koin:koin-core:3.3.3")
    testImplementation(kotlin("test"))
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.11.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.+")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation("org.postgresql:postgresql:9.3-1100-jdbc4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    api(project(":utilities"))
}

application {
    mainClass.set("multilib.server.ServerKt")
}