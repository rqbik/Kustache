plugins {
    kotlin("jvm") version "1.4.10"
    maven
}

group = "com.rqbik"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("com.destroystokyo.paper", "paper-api", "1.16.3-R0.1-SNAPSHOT")
}

tasks {
    compileKotlin { kotlinOptions.jvmTarget = "1.8" }
}