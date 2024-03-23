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
    dependsOn("wasmJsBrowserDistribution")

    from("${rootDir}/composeApp/build/dist/wasmJs/productionExecutable")
    into("${rootDir}/artifacts")
    duplicatesStrategy = DuplicatesStrategy.INCLUDE

    doFirst {
        val artifactsDir = project.file("artifacts")
        if (!artifactsDir.exists()) {
            artifactsDir.mkdirs()
        }
    }
}

tasks.named("wasmJsBrowserDistribution") {
    finalizedBy("moveWasmJsDistribution")
}