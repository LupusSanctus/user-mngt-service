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
val mockkTest = "1.13.3"
val jacksonModuleKotlin = "2.10.5"

dependencies {
    testImplementation("io.dropwizard:dropwizard-testing:2.0.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("org.mockito:mockito-inline:3.12.0")
    testImplementation("io.mockk:mockk:$mockkTest")
    testImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonModuleKotlin")
    testImplementation(kotlin("test"))

    implementation(project(":user-mngt-service-api"))
    implementation(project(":user-mngt-service-impl"))
    implementation(project(":user-mngt-service-model"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}