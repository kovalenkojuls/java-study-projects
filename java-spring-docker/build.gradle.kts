import org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES

plugins {
    idea
    java
    id("io.spring.dependency-management")
    id ("org.springframework.boot")
    id ("com.google.cloud.tools.jib")
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
    implementation ("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-actuator")
    implementation ("org.flywaydb:flyway-core")
    implementation ("org.postgresql:postgresql")

    testImplementation ("org.testcontainers:testcontainers")
    testImplementation ("org.testcontainers:postgresql")
    testImplementation ("org.junit.jupiter:junit-jupiter-api")
    testImplementation ("org.junit.jupiter:junit-jupiter-engine")
    testImplementation ("org.assertj:assertj-core")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
}

jib {
    container {
        creationTime.set("USE_CURRENT_TIMESTAMP")
    }

    from {
        //docker hub mirror
        image = "https://mirror.gcr.io/bellsoft/liberica-openjdk-alpine-musl:21.0.1"
    }

    to {
        image = "registry.gitlab.com/kovalenkojuls/dockerregistry/rest-hello"
        tags = setOf(project.version.toString())
        auth {
            username = System.getenv("GITLAB_USERNAME")
            password = System.getenv("GITLAB_PASSWORD")
        }
    }
}

tasks.getByName("build").dependsOn("jib")

tasks.test {
    useJUnitPlatform()
}
