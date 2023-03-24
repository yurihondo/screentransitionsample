plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.yurihondo.cupcake"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
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

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidxJunit)
    androidTestImplementation(libs.espressoCore)
}