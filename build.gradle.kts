plugins {
    kotlin("jvm") version "1.9.23"
    `maven-publish`
}

group = "kr.kro.chordzero"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven {
        name = "papermc-repo"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        name = "sonatype"
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.6-R0.1-SNAPSHOT")
    implementation("kr.kro.chordzero:mcdsl:1.0-SNAPSHOT")
}

publishing {
    repositories {
        mavenLocal()
    }
    publications {
        create<MavenPublication>("Maven") {
            from(components["kotlin"])
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}