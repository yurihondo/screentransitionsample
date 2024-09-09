plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.yurihondo.screentransitionsample"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.yurihondo.screentransitionsample"
        minSdk = 30
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFile(getDefaultProguardFile("proguard-android-optimize.txt"))
            proguardFile(file("proguard-rules.pro"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        compose = true
    }
    packaging {
        jniLibs {
            excludes.addAll(
                listOf(
                    "META-INF/ASL2.0",
                    "META-INF/AL2.0",
                    "META-INF/*.kotlin_module",
                    "META-INF/LGPL2.1"
                )
            )
        }
    }
}

dependencies {
    // Module
    implementation(projects.core.ui)
    implementation(projects.core.common)
    implementation(projects.feature.applepie)
    implementation(projects.feature.bananabread)
    implementation(projects.feature.cupcake)
    implementation(projects.feature.donut)
    implementation(projects.feature.eclair)

    // Compose
    val composeBom = platform(libs.composeBom)
    implementation(composeBom)
    implementation(libs.bundles.compose)
    androidTestImplementation(composeBom)
    androidTestImplementation(libs.composeUiTestJunit4)
    debugImplementation(libs.bundles.composeDebug)

    // AndroidX
    implementation(libs.androidxCore)
    implementation(libs.androidxActivity)

    // Lifecycle
    implementation(libs.lifecycleRuntimeKtx)

    // Navigation
    implementation(libs.navigationCompose)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidxJunit)
    androidTestImplementation(libs.espressoCore)
}
