plugins {
    kotlin("jvm") version "1.5.31"
    `kotlin-dsl`
    id("java-gradle-plugin")
    id("maven-publish")
}

project.group = "ru.julia"
project.version = "0.1"

repositories {
    mavenLocal()
    mavenCentral()
}

gradlePlugin {
    plugins {
        create("my-plugin") {
            id = "my-plugin"
            implementationClass = "ru.plugins.MyPlugin"
        }
    }
}

publishing {
    /*repositories {
        maven {
            credentials {
                username = project.property("") as String?
                password = project.property("") as String?
            }
            url = uri("https://gitlab.com/...")
        }
    }*/
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.spring.gradle:dependency-management-plugin:1.0.11.RELEASE")
    implementation("org.springframework.boot:spring-boot-gradle-plugin:3.2.5")
}

tasks.getByName("build").dependsOn("publishToMavenLocal")
