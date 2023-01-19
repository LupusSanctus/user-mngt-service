val dropwizardSwagger: String by rootProject.extra

plugins {
    kotlin("jvm") version "1.7.21"

}

group = "com.crm"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val junitTests = "5.8.1"

dependencies {
    testImplementation("io.dropwizard:dropwizard-testing:1.3.5")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("org.mockito:mockito-inline:3.12.0")

    implementation(project(":user-mngt-service-api"))
    implementation(project(":user-mngt-service-impl"))
    implementation(project(":user-mngt-service-model"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}