import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinBasePlugin

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinter) apply false
    alias(libs.plugins.mavenPublish) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.versionCheck)
    alias(libs.plugins.bcv)
}

allprojects {
    apply(plugin = rootProject.libs.plugins.kotlinter.get().pluginId)

    plugins.withType<KotlinBasePlugin>().configureEach {
        extensions.configure<KotlinProjectExtension> {
            if ("sample" !in project.name) {
                explicitApi()
            }
        }
    }
}

apiValidation {
    ignoredProjects += listOf(
        "sample",
    )
}

// tasks.register<Delete>("clean").configure {
//    delete(rootProject.layout.buildDirectory)
// }
