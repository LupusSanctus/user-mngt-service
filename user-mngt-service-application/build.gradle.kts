import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val dropwizardVersion: String by rootProject.extra
val dropwizardSwagger: String by rootProject.extra
val dropwizardFlyway: String by rootProject.extra

val kodeinVersion: String by rootProject.extra

plugins {
    kotlin("jvm") version "1.7.21"
    application
}

group = "com.crm"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val userMngtEntryClass = "com.crm.umt.UserMngtServiceApplicationKt"


dependencies {
    api("com.smoketurner:dropwizard-swagger:$dropwizardSwagger")
    implementation("io.dropwizard:dropwizard-core:$dropwizardVersion")
    implementation("io.dropwizard:dropwizard-jdbi3:$dropwizardVersion")

    // github.com/kosi-libs/Kodein#kotlin--jvm-compatibility
    api("org.kodein.di:kodein-di:$kodeinVersion")

    // in order to avoid: ClassNotFoundException: org.h2.Driver
    runtimeOnly("com.h2database:h2:1.4.200")

    // Flyway migration
    implementation("io.dropwizard.modules:dropwizard-flyway:0.7.0-1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    implementation(project(":user-mngt-service-web"))
    implementation(project(":user-mngt-service-api"))
    implementation(project(":user-mngt-service-impl"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set(userMngtEntryClass)
}

(tasks.run) {
    val appConfigPath = "/src/main/resources/applicationConfig.yml"
    val dropwizardEnvType = "server"
    args = listOf("$dropwizardEnvType", "${projectDir}${appConfigPath}")
}