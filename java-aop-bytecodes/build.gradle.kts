import org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    idea
    java
    application
    id("io.spring.dependency-management")
    id ("org.springframework.boot")
    id ("fr.brouillard.oss.gradle.jgitver")
    id("com.github.johnrengelman.shadow")
}

project.group = "ru.julia"
project.version = "0.1"

val testcontainersBom: String by project
val protobufBom: String by project
val guava: String by project
val asm: String by project

apply(plugin = "io.spring.dependency-management")
dependencyManagement {
    dependencies {
        imports {
            mavenBom(BOM_COORDINATES)
            mavenBom("org.testcontainers:testcontainers-bom:$testcontainersBom")
            mavenBom("com.google.protobuf:protobuf-bom:$protobufBom")
        }
        dependency("com.google.guava:guava:$guava")
        dependency("org.ow2.asm:asm-commons:$asm")
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.ow2.asm:asm-commons")
    implementation ("ch.qos.logback:logback-classic")
}

application {
    mainClass = "ru.julia.Main"
}

tasks {
    create<ShadowJar>("addSetterDemoJar") {
        archiveBaseName.set("addSetterDemo")
        archiveVersion.set("")
        archiveClassifier.set("")
        manifest {
            attributes(
                mapOf(
                    "Main-Class" to "ru.julia.aop.instrumentation.addSetter.AddSetterDemo",
                    "Premain-Class" to "ru.julia.aop.instrumentation.addSetter.Agent"
                )
            )
        }
        from(sourceSets.main.get().output)
        configurations = listOf(project.configurations.runtimeClasspath.get())
    }

    create<ShadowJar>("changeActionDemoJar") {
        archiveBaseName.set("changeActionDemo")
        archiveVersion.set("")
        archiveClassifier.set("")
        manifest {
            attributes(mapOf("Main-Class" to "ru.julia.aop.instrumentation.changeAction.ChangeActionDemo",
                "Premain-Class" to "ru.julia.aop.instrumentation.changeAction.Agent"))
        }
        from(sourceSets.main.get().output)
        configurations = listOf(project.configurations.runtimeClasspath.get())
    }

    create<ShadowJar>("proxyASMDemoJar") {
        archiveBaseName.set("proxyASMDemo")
        archiveVersion.set("")
        archiveClassifier.set("")
        manifest {
            attributes(mapOf("Main-Class" to "ru.julia.aop.instrumentation.proxyASM.ProxyASMDemo",
                "Premain-Class" to "ru.julia.aop.instrumentation.proxyASM.Agent"))
        }
        from(sourceSets.main.get().output)
        configurations = listOf(project.configurations.runtimeClasspath.get())
    }

    build {
        dependsOn("addSetterDemoJar", "changeActionDemoJar", "proxyASMDemoJar")
    }
}
