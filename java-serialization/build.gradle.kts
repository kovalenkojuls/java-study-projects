import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel
import org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES
import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.ofSourceSet
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

val protoSrcDir = "$projectDir/build/generated"

val testcontainersBom: String by project
val protobufBom: String by project
val guava: String by project
val jmh: String by project
val asm: String by project
val glassfishJson: String by project
val protoc: String by project

plugins {
    java
    idea
    id("com.google.protobuf")
    id("io.spring.dependency-management")
    id("org.springframework.boot") apply false
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
        sourceDirs = sourceDirs.plus(file(protoSrcDir))
    }
}

sourceSets {
    main {
        proto {
            srcDir(protoSrcDir)
        }
    }
}

protobuf {
    generatedFilesBaseDir = protoSrcDir

    protoc {
        artifact = "com.google.protobuf:protoc:$protoc"
    }

    generateProtoTasks {
        ofSourceSet("main")
    }
}

apply(plugin = "io.spring.dependency-management")
dependencyManagement {
    dependencies {
        imports {
            mavenBom(BOM_COORDINATES)
            mavenBom("org.testcontainers:testcontainers-bom:$testcontainersBom")
            mavenBom("com.google.protobuf:protobuf-bom:$protobufBom")
        }
        dependency("org.glassfish:jakarta.json:$glassfishJson")
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
    implementation ("ch.qos.logback:logback-classic")
    implementation("com.google.guava:guava")
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("org.glassfish:jakarta.json")
    implementation("com.google.protobuf:protobuf-java-util")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    compileOnly ("org.projectlombok:lombok")
    annotationProcessor ("org.projectlombok:lombok")
}

tasks.test {
    useJUnitPlatform()
}

afterEvaluate {
    tasks {
        getByName("generateProto").dependsOn(processResources)
    }
}
