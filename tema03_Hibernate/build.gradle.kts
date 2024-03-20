plugins {
    id("java")
    id("org.hibernate.orm") version "6.4.4.Final"
}

group = "dam.m06.act02"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.hibernate:hibernate-core:6.3.1.Final")
    implementation("mysql:mysql-connector-java:8.0.33")
}

