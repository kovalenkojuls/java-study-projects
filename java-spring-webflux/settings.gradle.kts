rootProject.name = "java-spring-webflux"

include("source")
include("processor")
include("client")

pluginManagement {
    val dependencyManagement: String by settings
    val springframeworkBoot: String by settings

    plugins {
        id("io.spring.dependency-management") version dependencyManagement
        id("org.springframework.boot") version springframeworkBoot
    }
}

