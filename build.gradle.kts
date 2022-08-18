import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    java
    id("org.gretty") version "3.0.8" apply(false) // gretty 4.x only supports jetty11 and tomcat10, both of which only support the new jakarta.* servlet API.
    id("com.vaadin") version "23.1.6" apply(false)
}

defaultTasks("clean", "build")

allprojects {
    group = "com.example.vok"
    version = "1.0-SNAPSHOT"
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply { plugin("java") }

    java {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        testLogging {
            // to see the exceptions of failed tests in Travis-CI console.
            exceptionFormat = TestExceptionFormat.FULL
        }
    }

    dependencies {
        implementation("org.jetbrains:annotations:22.0.0")
        // logging
        implementation("org.slf4j:slf4j-simple:${properties["slf4j_version"]}")
        // tests
        testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
        testImplementation("com.github.mvysny.kaributesting:karibu-testing-v23:1.3.18")
    }
}
