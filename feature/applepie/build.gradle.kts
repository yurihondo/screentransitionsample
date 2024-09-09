plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.yurihondo.screentransitionsample.applepie"
    compileSdk = 35

    defaultConfig {
        minSdk = 30

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
}

ksp {
    arg("compose-destinations.codeGenPackageName", "com.yurihondo.screentransitionsample.applepie")
    arg("compose-destinations.moduleName", "applepie")
    arg("compose-destinations.htmlMermaidGraph", "$rootDir/navigation-docs")
}

dependencies {
    //Modules
    implementation(project(":core:ui"))

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

    // Compose Destinations
    implementation(libs.composeDestinationsCore)
    ksp(libs.composeDestinationsKsp)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidxJunit)
    androidTestImplementation(libs.espressoCore)
}
