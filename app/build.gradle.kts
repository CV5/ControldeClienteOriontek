plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
//    id("kotlin-kapt")
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.cv5.controldecliente"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.cv5.controldecliente"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
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

    sourceSets {
        getByName("androidTest"){
            assets.srcDirs(File(projectDir, "schemas"))
        }
    }


    buildFeatures{
        viewBinding = true
        dataBinding = true
    }
}

dependencies {


    implementation ("androidx.room:room-runtime:2.5.2")
    ksp ("androidx.room:room-compiler:2.5.2")
//    kapt ("androidx.room:room-compiler:2.6.0-rc01")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    // Kotlin Extensions and Coroutines support for Room
    implementation ("androidx.room:room-ktx:2.5.2")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("androidx.room:room-rxjava2:2.5.2")
    androidTestImplementation ("androidx.room:room-testing:2.5.2")
}