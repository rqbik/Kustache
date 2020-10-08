repositories {
    maven("https://gitlab.com/api/v4/groups/karbonpowered/-/packages/maven/")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
    compileOnly("com.karbonpowered", "text", "0.0.1-SNAPSHOT")
    testCompileOnly("com.karbonpowered", "text", "0.0.1-SNAPSHOT")
}