plugins {
    id("java")
}

group = "me.meiallu"
version = "0.1"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")

    implementation(project(":library"))
}