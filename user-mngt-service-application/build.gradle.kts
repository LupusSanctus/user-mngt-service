import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

val dropwizardVersion: String by rootProject.extra
val dropwizardSwagger: String by rootProject.extra
val dropwizardFlyway: String by rootProject.extra

val kodeinVersion: String by rootProject.extra

plugins {
    kotlin("jvm") version "1.7.21"
    application
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "com.crm"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val userMngtEntryClass = "com.crm.umt.UserMngtServiceApplicationKt"

tasks.withType<ShadowJar> {
    mergeServiceFiles()
    exclude("META-INF/*.DSA", "META-INF/*.RSA", "META-INF/*.SF")
    manifest {
        attributes["Implementation-Title"] = rootProject.name
        attributes["Implementation-Version"] = rootProject.version
        attributes["Main-Class"] = userMngtEntryClass
    }
    archiveFileName.set("${rootProject.name}-${rootProject.version}.jar")
}

val jdbiVersion = "3.25.0"
val flyWay = "0.7.0-1"
val h2db = "1.4.200"
val jacksonModuleKotlin = "2.10.5"

dependencies {
    api("com.smoketurner:dropwizard-swagger:$dropwizardSwagger")

    implementation("io.dropwizard:dropwizard-core:$dropwizardVersion")
    implementation("io.dropwizard:dropwizard-jdbi3:$dropwizardVersion")

    // registerKotlinModule
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonModuleKotlin")

    // Kotlin JDBI plugins
    implementation("org.jdbi:jdbi3-core:$jdbiVersion")
    implementation("org.jdbi:jdbi3-kotlin:$jdbiVersion")
    implementation("org.jdbi:jdbi3-kotlin-sqlobject:$jdbiVersion")

    // github.com/kosi-libs/Kodein#kotlin--jvm-compatibility
    api("org.kodein.di:kodein-di:$kodeinVersion")

    // in order to avoid: ClassNotFoundException: org.h2.Driver
    runtimeOnly("com.h2database:h2:$h2db")

    // Flyway migration
    implementation("io.dropwizard.modules:dropwizard-flyway:$flyWay")

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