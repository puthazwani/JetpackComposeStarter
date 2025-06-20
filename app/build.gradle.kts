plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.example.jetpackcomposestarter"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.jetpackcomposestarter"
        minSdk = 35
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
}

dependencies {
    //Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material)
    implementation(libs.compose.material.icons)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)
    //Navigation
    implementation(libs.navigation.compose)
    //Hilt Navigation Compose
    implementation(libs.hilt.navigation.compose)
    //Hilt
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    //Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    //Serialization
    implementation(libs.serialization)
    //Tests
    implementation(libs.androidx.ui.tooling.preview)
    debugImplementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    androidTestImplementation(libs.navigation.testing)
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.truth)
    debugImplementation(libs.ui.test.manifest)

}