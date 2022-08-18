plugins {
    war
    id("org.gretty")
    id("com.vaadin")
}

gretty {
    contextPath = "/"
    servletContainer = "jetty9.4"
}

dependencies {
    // Vaadin
    implementation("com.vaadin:vaadin-core:${properties["vaadin_version"]}")
    providedCompile("javax.servlet:javax.servlet-api:4.0.1")

    // logging
    // currently we are logging through the SLF4J API to SLF4J-Simple. See src/main/resources/simplelogger.properties file for the logger configuration
    implementation("org.slf4j:slf4j-simple:${properties["slf4j_version"]}")
    implementation("org.slf4j:slf4j-api:${properties["slf4j_version"]}")
}

