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
    dependencies {
        implementation("ch.qos.logback:logback-classic")
        implementation("com.google.code.gson:gson")

        implementation("org.eclipse.jetty.ee10:jetty-ee10-servlet")
        implementation("org.eclipse.jetty:jetty-server")
        implementation("org.eclipse.jetty.ee10:jetty-ee10-webapp")
        implementation("org.eclipse.jetty:jetty-security")
        implementation("org.eclipse.jetty:jetty-http")
        implementation("org.eclipse.jetty:jetty-io")
        implementation("org.eclipse.jetty:jetty-util")
        implementation("org.freemarker:freemarker")

        testImplementation("org.junit.jupiter:junit-jupiter-engine")
        testImplementation("org.junit.jupiter:junit-jupiter-params")
        testImplementation("org.assertj:assertj-core")
        testImplementation("org.mockito:mockito-junit-jupiter")
    }
}