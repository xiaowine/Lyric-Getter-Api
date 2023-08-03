@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "cn.lyric.getter.api.demo"
    compileSdk = 34

    defaultConfig {
        applicationId = "cn.lyric.getter.api.demo"
        minSdk = 26
        targetSdk = 34
        versionCode = 2
        versionName = "1.1"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.majorVersion
    }
}
dependencies {
    implementation(project(mapOf("path" to ":Api")))
}

