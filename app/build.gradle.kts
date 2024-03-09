plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
//    kotlin("kapt")
//    id("com.google.dagger.hilt.android")
}

val koinAndroidComposeVersion = "3.5.0"

android {
    namespace = "com.rozoomcool.testapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rozoomcool.testapp"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
//        renderscriptSupportModeEnabled = true
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

//kapt {
//    correctErrorTypes = true
//}

dependencies {
    implementation(libs.androidx.compose.material)

//    implementation(libs.hilt.android)
//    implementation(libs.androidx.hilt.navigation.fragment)
//    implementation(libs.androidx.hilt.work)
//    kapt(libs.androidx.hilt.compiler)
//    kapt(libs.hilt.android.compiler)
//    annotationProcessor(libs.androidx.hilt.compiler.v120)
//    implementation(libs.dagger.hilt.android)
//    //noinspection UseTomlInstead
//    kapt("com.google.dagger:hilt-compiler")

    implementation (libs.koin.android)
    implementation (libs.koin.androidx.navigation)
    implementation(libs.koin.androidx.compose)
    testImplementation (libs.koin.test.junit4)

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.material.icons.extended)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}