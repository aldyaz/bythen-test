import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    alias(libs.plugins.buildConfig)
}

android {
    namespace = "com.aldyaz.bythenvideo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.aldyaz.bythenvideo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }
        release {
            isMinifyEnabled = true
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

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

buildConfig {
    val props = loadPropertiesFile("local.properties")

    packageName("com.aldyaz.bythenvideo")
    useKotlinOutput()
    
    buildConfigField("String", "PRESET", "\"${props["PRESET"]}\"")
    buildConfigField("String", "API_KEY", "\"${props["API_KEY"]}\"")
}

fun loadPropertiesFile(file: String): Map<*, *> {
    val configPath = "../$file"
    val props: Map<*, *> = if (file(configPath).exists()) {
        Properties().apply {
            load(FileInputStream(file(configPath)))
        }
    } else {
        project.properties
    }
    return props
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.bundles.compose)
    implementation(libs.hilt.android)
    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.net)

    kapt(libs.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}