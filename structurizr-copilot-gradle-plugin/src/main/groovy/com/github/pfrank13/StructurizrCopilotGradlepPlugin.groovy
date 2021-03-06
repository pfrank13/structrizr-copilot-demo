/*
 * This Groovy source file was generated by the Gradle 'init' task.
 */
package com.github.pfrank13

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * A simple 'hello world' plugin.
 */
public class StructurizrCopilotGradlepPlugin implements Plugin<Project> {
  public void apply(Project project) {
    // Register a task
    project.tasks.register("greeting") {
      doLast {
        println("Hello from plugin 'com.github.pfrank13.greeting'")
      }
    }
  }
}
