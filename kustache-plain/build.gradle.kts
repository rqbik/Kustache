repositories {
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly("com.destroystokyo.paper", "paper-api", "1.16.3-R0.1-SNAPSHOT")
    testCompileOnly("net.md-5", "bungeecord-chat", "1.16-R0.3")
}