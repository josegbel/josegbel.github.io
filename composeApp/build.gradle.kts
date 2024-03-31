import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    
    alias(libs.plugins.jetbrainsCompose)
}

kotlin {

    targets.forEach {
        it.compilations.all {
            kotlinOptions {
                freeCompilerArgs += "-Xopt-in=androidx.compose.ui.ExperimentalComposeUiApi"
                freeCompilerArgs += "-Xopt-in=org.jetbrains.compose.resources.ExperimentalResourceApi"
            }
        }
    }

    js {
        moduleName = "composeApp"
        binaries.executable()
        browser {
            useCommonJs()
            commonWebpackConfig {
                outputFileName = "$moduleName.js"
            }
        }
    }

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

                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(project.projectDir.path)
                        add(project.projectDir.path + "/commonMain/")
                        add(project.projectDir.path + "/wasmJsMain/")
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

//****************************************************************************
// keeping these to use on deployment branch distribution
tasks.register("moveWasmJsDistribution", Copy::class) {
    dependsOn("wasmJsBrowserDistribution")

    from("${rootDir}/composeApp/build/dist/wasmJs/productionExecutable")
    into("$rootDir")
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.named("wasmJsBrowserDistribution") {
    finalizedBy("moveWasmJsDistribution")
}

tasks.register("moveJsDistribution", Copy::class) {
    dependsOn("jsBrowserDistribution")

    from("${rootDir}/composeApp/build/dist/js/productionExecutable")
    into("$rootDir")
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.named("jsBrowserDistribution") {
    finalizedBy("moveJsDistribution")
}
