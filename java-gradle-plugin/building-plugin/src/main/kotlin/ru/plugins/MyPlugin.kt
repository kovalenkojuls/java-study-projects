package ru.plugins

import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.gradle.api.DefaultTask

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.*

abstract class PrintDependenciesTask : DefaultTask() {
    @TaskAction
    fun action() {
        project.extensions.getByType<DependencyManagementExtension>()
            .managedVersions
            .toSortedMap()
            .map { "${it.key}:${it.value}" }
            .forEach(::println)
    }
}

class MyPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        group = "ru.julia"

        repositories {
            mavenLocal()
            mavenCentral()

            /*maven {
                credentials {
                    username = project.property(Versions.packagesPropUserName) as String?
                    password = project.property(Versions.packagesPropPassword) as String?
                }
                url = uri(Versions.packagesUrl)
            }*/
        }

        tasks {
            register("printDependencies", PrintDependenciesTask::class) {
                group = "my plugin"
                description = "Print list of dependencies and versions"
            }
        }
    }
}
