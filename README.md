# Vaadin Component Starter

An archetype for your reusable component projects. Introduces three Gradle projects:
* The actual component sources (both Java and JavaScript) placed in the [my-component](my-component) module;
  this module builds as a jar which you can publish to your Nexus and depend on in your projects.
* A [test-webapp](test-webapp) is a webapp which runs the component itself, in order for you to
  be able to develop the component further. This module is not published anywhere, and it uses
  the [Gretty](https://plugins.gradle.org/plugin/org.gretty) plugin.
* A [test-webapp-embedded](test-webapp-embedded) is a webapp which runs the component itself, in order for you to
  be able to develop the component further. This module is not published anywhere, and it uses
  embedded jetty: it starts fast and you launch it as a simple `main()` method.

Both the development and production modes are supported. Also, the project
demoes packaging itself both into a flatten uberjar and a zip file containing
a list of jars and a runner script. See "Packaging for production" below
for more details.

> Looking for Maven version? [vaadin-component-flow](https://github.com/mvysny/component-starter-flow)

## Developing

Clone this github repository and import the project to the IDE of your choice as a Gradle project. You need to have Java 11+ installed.

### WAR

The `test-webapp` is a regular WAR project. You can use the [Gretty](https://plugins.gradle.org/plugin/org.gretty) plugin
to run the project easily from command-line. If you have Intellij Ultimate, you can also run the project
in any servlet container, for example Tomcat 9.

Instructions to use Gretty+Jetty follow:

1. Run Jetty: `./gradlew clean build test-webapp:appRun` from the command-line (alternatively run the `appRunWar` task, see below)
2. The test webapp will be running on [http://localhost:8080](http://localhost:8080).
3. Press ENTER to stop Jetty gracefully; press CTRL+C to kill jetty.

To develop the component on the fly with no IDE:

1. Make changes to `my-component.js`
2. Jetty should be able to pick up the changes after two seconds, spit out lots of errors and then restart the webapp.
3. The browser should automatically reload the page. Sometimes you'll have to reload the page manually.

To develop the component on the fly with IDE:

1. Import the project into your IDE
2. Make changes to `MyComponent.java` or `my-component.js`
3. Compile changes (IDEA: CTRL+F9)
4. Jetty should be able to pick up the changes after two seconds, spit out lots of errors and then restart the webapp.
5. The browser should automatically reload the page.

Java Debugging: TODO

#### Troubleshooting

Q: The webpage is being reloaded constantly.

A: It happens when you run `./gradlew clean build` in another terminal. Alternatively
   you may have multiple gradle processes running. Kill them all via `./gradlew --stop` then try again.

Q: Icons/images/SVGs are not loaded from `my-component/src/main/resources/META-INF/resources` (the resource jar stuff).

A: Jetty only scans `WEB-INF/lib/*.jar` for resource jars. When using the `appRun` task, Jetty runs
   in the exploded-war mode where the `my-component.jar` is not placed into `WEB-INF/lib/` as a jar file;
   instead it's added as exploded onto classpath. Use `appRunWar` task instead of `appRun`.

### Embedded

To run quickly from the command-line in development mode:

1. Run `./gradlew test-webapp-embedded:run`
2. The test webapp will be running on [http://localhost:8080](http://localhost:8080).

To run the app from your IDE:

1. Import the project into your IDE
2. Run `./gradlew` in the project, to configure Vaadin for npm mode.
3. Run/Debug the `Main` class as an application (run the `main()` method).
   The app will use npm to download all javascript libraries (will take a long time)
   and will start in development mode.
4. Your app will be running on [http://localhost:8080](http://localhost:8080).

> **Info:** **Eclipse**+BuildShip may need a workaround in order for this project to work,
> please see [this vaadin thread](https://vaadin.com/forum/thread/18241436) for more info.
> This applies to **Visual Studio Code** as well since it also uses Eclipse bits and BuildShip
> underneath - see [Bug #4](https://github.com/mvysny/vaadin14-embedded-jetty-gradle/issues/4)
> for more details.

See [Main.java](test-webapp-embedded/src/main/java/com/vaadin/starter/skeleton/Main.java)
for details on how Jetty is configured for embedded mode.

Advantages of the embedded setup:

* Starts fast
* Easy to run and debug in any IDE
* Changes in Java class files are picked in debug mode automatically - rebuild the classes (IDEA: CTRL+F9)
  then reload the page to see the new version.
  * Alternatively follow the [Vaadin hotswap-agent](https://vaadin.com/docs/latest/configuration/live-reload/hotswap-agent)
    tutorial for even better hot reload.

To develop the component on the fly:

1. Import the project into your IDE
2. Run `./gradlew test-webapp-embedded:run`
3. Make changes to `MyComponent.java` or `my-component.js`
4. Compile changes (IDEA: CTRL+F9)
5. Jetty should be able to pick up the changes after two seconds, spit out lots of errors and then restart the webapp.
6. The browser should automatically reload the page.

To develop the component on the fly from your IDE (this way you can debug your Java code also):

1. Import the project into your IDE
2. Run `./gradlew` in the project, to configure Vaadin for npm mode.
3. Run/Debug the `Main` class as an application (run the `main()` method).
   The app will use npm to download all javascript libraries (will take a long time)
   and will start in development mode.
4. Your app will be running on [http://localhost:8080](http://localhost:8080).
5. Make changes to `MyComponent.java` or `my-component.js`
6. Compile changes (IDEA: CTRL+F9)
7. Jetty should be able to pick up the changes after two seconds, spit out lots of errors and then restart the webapp.
8. The browser should automatically reload the page.

#### Missing `/src/main/webapp`?

Yeah, since we're not packaging to WAR but to uberjar/zip+jar, the `webapp` folder needs to be
served from the jar itself, and therefore it needs to reside in `src/main/resources/webapp`.

## Packaging for production

To package in production mode:

1. `./gradle build -Pvaadin.productionMode`

The project packages itself as a zip file with dependencies. The file is
in `build/distributions/vaadin-embedded-jetty-gradle.zip`

## Running in production mode

To build&run the zip file:

1. `./gradle build -Pvaadin.productionMode`
2. `cd build/distributions/`
3. `unzip vaadin-embedded-jetty-gradle.zip`
4. `cd vaadin-embedded-jetty-gradle/bin`
5. `./vaadin-embedded-jetty-gradle`

Head to [localhost:8080/](http://localhost:8080).
