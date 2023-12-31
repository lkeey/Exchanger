plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "aleshka.developement.exchanger"
    compileSdk = 34

    defaultConfig {
        applicationId = "aleshka.developement.exchanger"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    lint {
        baseline = file("lint-baseline.xml")
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
        jvmTarget = JavaVersion.VERSION_17.toString()
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

dependencies {

    val nav = "2.7.6"
    val destinations = "1.9.53"
    val haze = "0.4.1"
    val coil = "2.5.0"
    val diagrams = "2.1.0"
    val retrofit = "2.9.0"
    val koin = "3.5.0"
    val controller = "0.27.0"

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Splash API
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Navigation
    implementation("androidx.navigation:navigation-compose:$nav")

    // Compose Destinations
    implementation("io.github.raamcosta.compose-destinations:core:$destinations")
    ksp("io.github.raamcosta.compose-destinations:ksp:$destinations")

    // Glasmorphism
    implementation("dev.chrisbanes.haze:haze-jetpack-compose:$haze")

    // Async Image
    implementation("io.coil-kt:coil-compose:$coil")

    // Diagrams
    implementation("co.yml:ycharts:$diagrams")

    // Retrofit & GSON
    implementation("com.squareup.retrofit2:converter-gson:$retrofit")
    implementation("com.squareup.retrofit2:retrofit:$retrofit")

    // DI koin
    implementation("io.insert-koin:koin-androidx-compose:$koin")

    // System Bar Color
    implementation("com.google.accompanist:accompanist-systemuicontroller:$controller")
    implementation("com.google.accompanist:accompanist-navigation-material:$controller")

}
