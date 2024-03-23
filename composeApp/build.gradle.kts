import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    
    alias(libs.plugins.jetbrainsCompose)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                    }
                }
            }
        }
        binaries.executable()
    }
    
    sourceSets {
        
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.coroutines)
        }
    }
}

compose.experimental {
    web.application {}
}

tasks.register("moveWasmJsDistribution", Copy::class) {
    // Ensure this task runs after wasmJsBrowserDistribution
    dependsOn("wasmJsBrowserDistribution")

    // Define the source directory
    from("${rootDir}/composeApp/build/dist/wasmJs/productionExecutable")

    // Define the destination directory
    into("${rootDir}/artefacts")

    // Set the strategy to include duplicates, which effectively overwrites files
    duplicatesStrategy = DuplicatesStrategy.INCLUDE

    doFirst {
        // Check if the artefacts directory exists; create it if it doesn't
        val artefactsDir = project.file("artefacts")
        if (!artefactsDir.exists()) {
            artefactsDir.mkdirs()
        }
    }
}

// Optionally, make this task run automatically after wasmJsBrowserDistribution
tasks.named("wasmJsBrowserDistribution") {
    finalizedBy("moveWasmJsDistribution")
}