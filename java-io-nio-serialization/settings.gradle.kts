rootProject.name = "java-io-nio-serialization"

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

