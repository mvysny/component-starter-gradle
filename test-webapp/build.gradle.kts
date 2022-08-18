plugins {
    war
    id("org.gretty")
    id("com.vaadin")
}

gretty {
    contextPath = "/"
    servletContainer = "jetty9.4"
    scanDir(File(rootDir, "my-component/src/main/resources")) // watch the my-component.js file for changes
}

dependencies {
    // Vaadin
    implementation("com.vaadin:vaadin-core:${properties["vaadin_version"]}")
    implementation(project(":my-component"))
    providedCompile("javax.servlet:javax.servlet-api:4.0.1")

    // logging
    implementation("org.slf4j:slf4j-simple:${properties["slf4j_version"]}")
}
