import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization") version "1.8.10"
    id("com.android.library")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            xcf.add(this)
        }
    }

    sourceSets {
        val ktorVersion = "2.2.4"
        val coroutinesVersion = "1.6.4"
        val kermitVersion = "2.0.0-RC3"
        val commonMain by getting {
            dependencies {
                // Ktor
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-logging:$ktorVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

                // Serialization
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.4.1")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")

                //Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

                // Logging
                implementation("co.touchlab:kermit:$kermitVersion")

                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies { implementation("io.ktor:ktor-client-okhttp:$ktorVersion") }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "com.boundinteractive.kmmsample"
    compileSdk = 33
    defaultConfig {
        minSdk = 26
        targetSdk = 33
    }
}