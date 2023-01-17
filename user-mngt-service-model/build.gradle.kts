val dropwizardSwagger: String by rootProject.extra

plugins {
    kotlin("jvm") version "1.7.21"

}

group = "com.crm"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    api("com.smoketurner:dropwizard-swagger:$dropwizardSwagger")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}