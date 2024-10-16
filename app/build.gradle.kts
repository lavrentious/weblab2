/*
val MAIN_CLASS = "ru.lavrent.weblab2.Main"

plugins {
    application
    id("com.github.johnrengelman.shadow") version "8.1.1"
    java
    war
}

repositories {
    gradlePluginPortal()
}

dependencies {
    // This dependency is used by the application.
    implementation("com.google.guava:guava:32.1.1-jre")
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation(fileTree("libs") { include("*.jar") })
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set(MAIN_CLASS)
}
tasks.shadowJar {
    archiveBaseName.set("server")
    archiveClassifier.set("")
    minimize()
}

tasks.jar {
    enabled = false
    manifest.attributes["Main-Class"] = MAIN_CLASS
    dependsOn("shadowJar")
}
*/

plugins {
    java
    war
    id("com.github.node-gradle.node") version "3.0.1"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("com.google.guava:guava:32.1.1-jre")
    // implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("jakarta.platform:jakarta.jakartaee-web-api:9.0.0")
    implementation("jakarta.servlet:jakarta.servlet-api:6.0.0")
    implementation("jakarta.json:jakarta.json-api:2.1.1")
    implementation("jakarta.json.bind:jakarta.json.bind-api:3.0.0")
    implementation("jakarta.ejb:jakarta.ejb-api:4.0.1")
}

node {
    version.set("18.0.0")  // Use the version of Node.js you prefer
    npmVersion.set("8.0.0") // npm version
    download.set(true)      // Automatically download Node.js and npm
}

tasks.register<Exec>("compileTypeScript") {
    group = "build"
    description = "Compile TypeScript into JavaScript"
    workingDir = file("${projectDir}")
    commandLine("pnpm", "run", "bundle")
}

tasks.named("build") {
    dependsOn("compileTypeScript")
}
