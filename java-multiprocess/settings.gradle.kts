rootProject.name = "java-multiprocess"

include("processes-demo")
include("sockets-demo")
include("rmi-demo")
include("grpc-demo")

pluginManagement {
    val dependencyManagement: String by settings
    val springframeworkBoot: String by settings
    val protobufVer: String by settings

    plugins {
        id("io.spring.dependency-management") version dependencyManagement
        id("org.springframework.boot") version springframeworkBoot
        id("com.google.protobuf") version protobufVer
    }
}
