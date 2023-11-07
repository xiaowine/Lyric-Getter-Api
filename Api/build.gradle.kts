@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}
val apiVersion = "6"
android {
    namespace = "cn.lyric.getter.api"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
        buildConfigField("int", "API_VERSION", apiVersion)
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
}
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.github.xiaowine"
                artifactId = "Lyric-Getter-Api"
                version = apiVersion

                from(components["release"])
            }
        }
    }
}

dependencies {
	        implementation 'com.github.xiaowine:Lyric-Getter-Api:6.0.0dev'
	}
