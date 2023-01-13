plugins {
    kotlin("jvm") version "1.7.21"

}

group = "com.crm"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// TBD: remove extra dependencies
dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    implementation(project(":user-mngt-service-api"))
    implementation(project(":user-mngt-service-impl"))
    implementation(project(":user-mngt-service-model"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}