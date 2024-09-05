rootProject.name = "java-rabbitmq"

include("mainService")
include("allServicesModels")
include("approvalService")

pluginManagement {
    val dependencyManagement: String by settings
    val springframeworkBoot: String by settings

    plugins {
        id("io.spring.dependency-management") version dependencyManagement
        id("org.springframework.boot") version springframeworkBoot
    }
}
