import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack

val ktorVersion = "1.6.7"
val reactVersion = "17.0.2-pre.299-kotlin-1.6.10"

plugins {
    //kotlin("multiplatform") version "1.6.10"
    //application //to run JVM part
    kotlin("plugin.serialization") version "1.6.10"
    //id("org.jetbrains.compose") version "1.1.0-rc01"
    //id("kotlin2js") version "1.6.20-M1"
    kotlin("js") version "1.6.10"
}

group = "me.jose"
version = "1.0"

repositories {
    mavenCentral()
}

kotlin {
    js {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
        binaries.executable()
    }
}
dependencies {
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react:17.0.2-pre.297-kotlin-1.6.10")
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-dom:17.0.2-pre.297-kotlin-1.6.10")
    implementation(npm("react", "17.0.2"))
    implementation(npm("react-dom", "17.0.2"))
    implementation("org.jetbrains.kotlin-wrappers:kotlin-react-css:17.0.2-pre.298-kotlin-1.6.10")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
}

tasks.register("stage") {
    dependsOn("build")
}
