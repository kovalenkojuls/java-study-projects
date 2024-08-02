import org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES

plugins {
    idea
    java
    id("io.spring.dependency-management")
    id ("org.springframework.boot")
    id ("fr.brouillard.oss.gradle.jgitver")
}

project.group = "ru.julia"
project.version = "0.1"

val testcontainersBom: String by project
val protobufBom: String by project
val guava: String by project

apply(plugin = "io.spring.dependency-management")
dependencyManagement {
    dependencies {
        imports {
            mavenBom(BOM_COORDINATES)
            mavenBom("org.testcontainers:testcontainers-bom:$testcontainersBom")
            mavenBom("com.google.protobuf:protobuf-bom:$protobufBom")
        }
        dependency("com.google.guava:guava:$guava")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-test")


    implementation("com.google.code.gson:gson")
}

tasks.test {
    useJUnitPlatform()
}
