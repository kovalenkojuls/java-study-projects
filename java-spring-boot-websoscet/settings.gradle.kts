rootProject.name = "java-spring-boot-websoscet"

include("application")
include("messager")
include("messager-starter")
include("websocket")

pluginManagement {
    val johnrengelmanShadow: String by settings

    plugins {
        id("com.github.johnrengelman.shadow") version johnrengelmanShadow
    }
}

