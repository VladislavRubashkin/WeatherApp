WeatherApp

Приложение "Прогноз погоды" с реализованными функциями по получению данных с сервера www.weatherapi.com.

Технологии:

    Kotlin
    Coroutines
    Room
    Dagger2
    Retrofit2
    GsonConverter
    HttpLoggingInterceptor
    Picasso

Дополнительно:

    Clean-architecture
    MVVM
    XML(верстка)
    LiveData
    ViewBinding
    Git

Зависимости:
    implementation 'androidx.core:core-ktx:1.12.0'
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.11.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'

    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.7.0"

    implementation "androidx.room:room-runtime:2.6.1"
    implementation "androidx.room:room-ktx:2.6.1"
    kapt "androidx.room:room-compiler:2.6.1"

    implementation("com.google.dagger:dagger:2.48.1")
    kapt("com.google.dagger:dagger-compiler:2.48.1")

    implementation 'com.google.android.gms:play-services-location:21.1.0'

    implementation "com.squareup.retrofit2:retrofit:2.7.0"
    implementation "com.squareup.retrofit2:converter-gson:2.7.0"
    implementation "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"

    implementation 'com.squareup.picasso:picasso:2.71828'

    implementation 'com.squareup.okhttp3:okhttp:4.7.2'
    implementation 'com.squareup.okhttp3:logging-interceptor:4.7.2'
