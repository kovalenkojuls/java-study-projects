import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel
import org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES

plugins {
    java
    idea
    id("io.spring.dependency-management") version "1.1.4"
    id("org.springframework.boot") version "3.2.5" apply false
}

group = "ru.julia"
version = "1.0"

idea {
    project {
        languageLevel = IdeaLanguageLevel(21)
    }
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

apply(plugin = "io.spring.dependency-management")
dependencyManagement {
    dependencies {
        imports {
            mavenBom(BOM_COORDINATES)
            mavenBom("org.testcontainers:testcontainers-bom:1.19.7")
        }
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.addAll(listOf("-Xlint:all,-serial,-processing"))
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation ("org.openjdk.jmh:jmh-core:1.37")
    testAnnotationProcessor ("org.openjdk.jmh:jmh-generator-annprocess:1.37")
    testImplementation ("org.junit.jupiter:junit-jupiter-engine")
    testImplementation ("org.assertj:assertj-core")
}

tasks.test {
    useJUnitPlatform()
}