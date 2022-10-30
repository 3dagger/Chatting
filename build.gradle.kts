// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.Essential.AndroidGradleVersion}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Essential.KotlinGradleVersion}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.Jetpack.HiltVersion}")
        classpath("de.mannodermaus.gradle.plugins:android-junit5:${Versions.Essential.JUnitGradleVersion}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.2")
        classpath("com.google.gms:google-services:4.3.14")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}