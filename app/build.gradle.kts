plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.yurihondo.screentransitionsample"
    compileSdk = 33

    defaultConfig {
        applicationId ="com.yurihondo.screentransitionsample"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packagingOptions {
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
    implementation(project(":feature:applepie"))
    implementation(project(":feature:bananabread"))
    implementation(project(":feature:cupcake"))
    implementation(project(":feature:donut"))
    implementation(project(":feature:eclair"))

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