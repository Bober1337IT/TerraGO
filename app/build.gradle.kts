plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.terrago.app"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.terrago.app"
        minSdk = 24
        targetSdk = 36
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

    // Register SQLDelight generated folders as Kotlin source directories.
    // This allows Hilt/KSP to "see" and resolve the generated TerraGoDatabase class
    // during the compilation process, preventing 'NonExistentClass' errors.
    sourceSets {
        getByName("main") {
            kotlin.srcDir("build/generated/sqldelight/code/TerraGoDatabase/debug")
            kotlin.srcDir("build/generated/sqldelight/code/TerraGoDatabase/release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    // UI & Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.compose.material:material-icons-extended")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.32.0")

    // Core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)

    // SQLDelight
    implementation(libs.sqldelight.runtime)
    implementation(libs.sqldelight.android.driver)
    implementation(libs.sqldelight.coroutines)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // Others
    implementation(libs.coil.compose)
    implementation(libs.work.runtime.ktx)
    implementation(libs.android.image.cropper)
    implementation(libs.androidx.compose.material.icons.core)

    // Testy
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}

// Resolve "Implicit Dependency" and Race Condition issues.
// We explicitly tell Gradle that the KSP task (used by Hilt) must wait for
// the SQLDelight generator to finish creating the database interface.
tasks.withType<com.google.devtools.ksp.gradle.KspTaskJvm> {
    if (name.contains("Debug")) {
        dependsOn("generateDebugTerraGoDatabaseInterface")
    }
    if (name.contains("Release")) {
        dependsOn("generateReleaseTerraGoDatabaseInterface")
    }
}

sqldelight{
    databases{
        create(name = "TerraGoDatabase") {
            packageName.set("com.terrago.app.db")
        }
    }
}