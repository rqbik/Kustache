plugins {
    kotlin("jvm") version "1.4.10"
    maven
}

val main = project(absoluteProjectPath("kustache"))

allprojects {
    apply(plugin = "kotlin")

    repositories {
        jcenter()
    }

    dependencies {
        if (name != "kustache") implementation(main)
        testImplementation("org.junit.jupiter", "junit-jupiter" , "5.6.2")
    }

    tasks {
        compileKotlin { kotlinOptions.jvmTarget = "1.8" }
        compileTestKotlin { kotlinOptions.jvmTarget = "1.8" }
        test { useJUnitPlatform() }
    }
}
