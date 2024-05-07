import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel
import org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES

group = "ru.julia"
version = "1.0-SNAPSHOT"

plugins {
    idea
    java
    id ("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.spring.dependency-management") version "1.1.4"
}

idea {
    project {
        languageLevel = IdeaLanguageLevel(21)
    }
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation ("com.google.guava:guava:33.1.0-jre")
}

allprojects {
    group = "ru.julia"

    apply(plugin = "io.spring.dependency-management")
    dependencyManagement {
        dependencies {
            imports {
                mavenBom(BOM_COORDINATES)
                mavenBom("org.testcontainers:testcontainers-bom:1.19.7")
                mavenBom("com.google.protobuf:protobuf-bom:4.26.1")
            }
            dependency("com.google.guava:guava:33.1.0-jre")
        }
    }
}

buildscript {
    repositories {
        mavenLocal()
        /*maven {
            credentials {
                username = project.property("") as String?
                password = project.property("") as String?
            }
            url = uri("https://gitlab.com/....")
        }*/
    }

    dependencies {
        //https://github.com/... <- link to gitlab on plugin or path:
        classpath ("my-plugin:my-plugin.gradle.plugin:0.1")
    }
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("shadow-jar")
        archiveVersion.set("0.1")
        /*manifest {
            attributes(mapOf("Main-Class" to "ru.julia.Main"))
        }*/
    }

    build {
        dependsOn(shadowJar)
    }
}

apply(plugin = "my-plugin")