plugins {
    id("java")
}

group = "dam.m06.act02"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.hibernate:hibernate-core:5.4.30.Final")
    implementation("mysql:mysql-connector-java:8.0.33")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}