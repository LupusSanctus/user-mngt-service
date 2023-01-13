val mapstructVersion: String by rootProject.extra


plugins {
    val kotlinJvmVersion = "1.7.21"

    kotlin("jvm") version kotlinJvmVersion
    // for annotated code generation in compile time
    // provides mapstruct code generation
    kotlin("kapt") version kotlinJvmVersion
}

group = "com.crm"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// TBD: remove extra dependencies
dependencies {
    implementation("org.mapstruct:mapstruct:${mapstructVersion}.Final")
    annotationProcessor("org.mapstruct:mapstruct-processor:${mapstructVersion}.Final")
    kapt("org.mapstruct:mapstruct-processor:${mapstructVersion}.Final")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    implementation(project(":user-mngt-service-model"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}