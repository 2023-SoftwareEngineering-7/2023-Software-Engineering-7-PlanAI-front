import com.android.build.api.dsl.Packaging

plugins {
    id("com.android.application")

}

android {
    namespace = "com.example.planai_front"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.planai_front"
        minSdk = 26
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    packagingOptions {
        exclude ("META-INF/DEPENDENCIES")
    }

}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("com.google.android.gms:play-services-auth:20.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    implementation("androidx.annotation:annotation-jvm:1.7.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//Material Calendar
    implementation ("com.github.prolificinteractive:material-calendarview:2.0.1")
    implementation ("com.jakewharton.threetenabp:threetenabp:1.1.1")
    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.6.4")
    implementation ("com.squareup.retrofit2:converter-gson:2.6.4")
    implementation ("com.squareup.retrofit2:converter-scalars:2.6.4")
    implementation ("com.squareup.okhttp3:logging-interceptor:3.12.1")

    implementation ("com.google.android.gms:play-services-auth:20.0.0")


    //리사이클러뷰
    implementation("androidx.recyclerview:recyclerview:1.2.1")
    // For control over item selection of both touch and mouse driven selection
    implementation("androidx.recyclerview:recyclerview-selection:1.1.0")
    //implementation("com.android.support:design:28.0.0")

    //Google Calendar
    implementation ("com.google.android.gms:play-services-auth:17.0.0")
    implementation ("com.google.api-client:google-api-client-android:1.30.10")
    implementation ("com.google.apis:google-api-services-calendar:v3-rev340-1.25.0")
    implementation ("com.google.android.gms:play-services-auth:19.0.0")
    implementation ("org.apache.httpcomponents:httpclient:4.5.12")
    implementation ("org.apache.httpcomponents:httpcore:4.4.13")
    implementation ("pub.devrel:easypermissions:3.0.0")
    implementation("com.google.api-client:google-api-client-android:1.22.0") {
        exclude (group =  "org.apache.httpcomponents")
    }
    implementation("com.google.apis:google-api-services-calendar:v3-rev235-1.22.0") {
        exclude (group = "org.apache.httpcomponents")
    }
    implementation ("com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava")

}

