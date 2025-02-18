import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.buildkonfig)
    alias(libs.plugins.room)
    alias(libs.plugins.kover)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }

        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        instrumentedTestVariant {
            sourceSetTree.set(KotlinSourceSetTree.test)

            dependencies {
                implementation(libs.core.ktx)
                implementation(libs.compose.ui.test.junit4.android)
                debugImplementation(libs.compose.ui.test.manifest)
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm("desktop")

    room {
        schemaDirectory("$projectDir/schemas")
    }
    
    sourceSets {
        val desktopMain by getting

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.ktor.client.okhttp)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)

            implementation(libs.kotlinx.datetime)
            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)
            implementation("co.touchlab:kermit:2.0.4")
            implementation(libs.compose.material.icons.extended)
            implementation(compose.material3)
            implementation(libs.jetbrains.compose.navigation)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            api(libs.koin.core)

            implementation(libs.bundles.ktor)
            implementation(libs.bundles.coil)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.ktor.client.okhttp)
        }
        nativeMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        commonTest.dependencies {
            implementation(libs.androidx.navigation.testing)
            implementation(libs.kotlin.test)
            implementation(kotlin("test-annotations-common"))
            implementation(libs.assertk)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
        }
        dependencies {
            ksp(libs.androidx.room.compiler)
        }
    }
}

android {
    namespace = "org.ernisernis.ratemoviescmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.ernisernis.ratemoviescmp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

buildkonfig {
    packageName = "org.ernisernis.ratemoviescmp"
    defaultConfigs {
        buildConfigField(STRING, "BASE_URL", "https://api.themoviedb.org/3/")
        val apiKey = project.findProperty("API_KEY") ?: ""
        buildConfigField(STRING, "API_KEY", "$apiKey")
    }
}

compose.desktop {
    application {
        mainClass = "org.ernisernis.ratemoviescmp.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.ernisernis.ratemoviescmp"
            packageVersion = "1.0.0"
        }
    }
}

kover {
    reports {
        verify {
            rule {
                minBound(80)
            }
        }

        filters {
            excludes {
                packages(
                    "*di*",
                    "ratemoviescmp.composeapp.generated.resources",
                    "*.database",
                    "*.dto",
                    "*.mappers",
                    "*.models",
                    "*.domain"
                )
                classes(
                    "*MainActivity*",
                    "*BuildKonfig*",
                    "*ComposableSingletons*",
                    "*MainKt*",
                    "*PreviewsKt*",
                    "*RateMoviesApplication*",
                    "*Route*",
                    "*TopLevelRoute*",
                    "*TopLevelRouteKt*",
                    "*HttpClientFactory*",
                    "*DataError*",
                    "*Result*",
                    "*ResultKt*",
                    "*RmIcons*",
                    "*Dimens*",
                    "*RateMoviesTheme*",
                    "*Typography*",
                    "*UiText*",
                )
            }
        }
    }
}
