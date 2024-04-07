plugins {
    id("java")
}

group = "me.meiallu.hermes"
version = "0.1"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")

    implementation("org.objenesis:objenesis:3.2");
}