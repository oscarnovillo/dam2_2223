import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    id("com.apollographql.apollo3").version("3.7.3")
}

group = "org.example"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

dependencies {
    implementation("com.apollographql.apollo3:apollo-runtime:3.7.3")

    // optional: if you want to use the normalized cache
    implementation("com.apollographql.apollo3:apollo-normalized-cache-sqlite:3.7.3")
//    implementation("com.apollographql.apollo3:apollo-coroutines-support:3.7.3")
    // optional: if you just want the generated models and parsers and write your own HTTP code/cache code, you can remove apollo-runtime
    // and use apollo-api instead
    implementation("com.apollographql.apollo3:apollo-api:3.7.3")
    testImplementation(kotlin("test"))

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

apollo {
    service("rickMorty") {
        sourceFolder.set("org/example/rickMorty")
        packageName.set("org.example.rickMorty")
    }
    service("server") {
        sourceFolder.set("org/example/server")
        packageName.set("org.example.server")
    }
    service("localhost") {
        sourceFolder.set("org/example/localhost")
        packageName.set("org.example.localhost")
    }
    // instruct the compiler to generate Kotlin models
    generateKotlinModels.set(true)
}
