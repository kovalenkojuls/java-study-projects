import org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES

plugins {
    idea
    java
    id("io.spring.dependency-management")
    id ("org.springframework.boot")
}

project.group = "ru.julia"
project.version = "0.1"

val testcontainersBom: String by project
val jsr305: String by project
val guava: String by project
val wiremock: String by project
val r2dbcPostgresql: String by project

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
    }

    apply(plugin = "io.spring.dependency-management")
    dependencyManagement {
        dependencies {
            imports {
                mavenBom(BOM_COORDINATES)
                mavenBom("org.testcontainers:testcontainers-bom:$testcontainersBom")
            }
            dependency("com.google.guava:guava:$guava")
            dependency("com.google.code.findbugs:jsr305:$jsr305")
            dependency("com.github.tomakehurst:wiremock-standalone:$wiremock")
            dependency("io.r2dbc:r2dbc-postgresql:$r2dbcPostgresql")
        }
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.compilerArgs.addAll(listOf("-Xlint:all,-serial,-processing"))
    }
}

subprojects {
    plugins.apply(JavaPlugin::class.java)
    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.compilerArgs.addAll(listOf("-Xlint:all,-serial,-processing"))
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging.showExceptions = true
        reports {
            junitXml.required.set(true)
            html.required.set(true)
        }
    }
}