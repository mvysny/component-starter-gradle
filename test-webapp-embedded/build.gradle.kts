plugins {
    application
    id("com.vaadin")
}

dependencies {
    // Vaadin
    implementation("com.vaadin:vaadin-core:${properties["vaadin_version"]}")
    implementation(project(":my-component"))

    // logging
    implementation("org.slf4j:slf4j-simple:${properties["slf4j_version"]}")

    // Embedded Jetty dependencies
    implementation("org.eclipse.jetty:jetty-webapp:${properties["jetty_version"]}")
    implementation("org.eclipse.jetty.websocket:websocket-javax-server:${properties["jetty_version"]}")
}

application {
    mainClass.set("com.vaadin.starter.skeleton.Main")
}
