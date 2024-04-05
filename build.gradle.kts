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
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.objenesis:objenesis:3.2");
}