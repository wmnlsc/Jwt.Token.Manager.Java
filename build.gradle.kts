plugins {
    id("java-library")
    id("maven-publish")
}

group = "com.bbhub.jwt.token.manager"
version = "1.0.2"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            pom {
                name.set(project.name)
                description.set("A library for managing JSON Web Tokens (JWTs)")
                url.set("https://github.com/wmnlsc/Jwt.Token.Manager.Java")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        id.set("wmnlsc")
                        name.set("Jose Beltre")
                        email.set("jbeltre158@gmail.com")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/wmnlsc/Jwt.Token.Manager.Java.git")
                    developerConnection.set("scm:git:ssh://git@github.com:wmnlsc/Jwt.Token.Manager.Java.git")
                    url.set("https://github.com/wmnlsc/Jwt.Token.Manager.Java")
                }
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()
}