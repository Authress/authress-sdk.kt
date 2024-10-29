plugins {
    kotlin("jvm") version "1.9.21"
    `maven-publish`
    signing
}

group = "io.authress"
version = System.getenv("VERSION") ?: "0.0.0"

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com")
    }
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
    implementation("com.squareup.moshi:moshi-adapters:1.15.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("org.json:json:20231013")
    implementation("com.google.crypto.tink:tink:1.12.0")
    implementation("com.nimbusds:nimbus-jose-jwt:9.37.3")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
}

kotlin {
    jvmToolchain(17)
}

sourceSets {
    main {
        java.srcDirs("src")
    }
    test {
        java.srcDirs("test")
    }
}

tasks.test {
    useJUnitPlatform()
}

java {
    withJavadocJar()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Authress/authress-sdk.kt")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
        maven {
            name = "OSSRH"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = "WOM6P4pQ"
                password = System.getenv("MAVEN_OSSRH_TOKEN")
            }
        }
    }
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            version = System.getenv("VERSION") ?: "0.0.0"
            pom {
                name.set("Authress SDK")
                description.set("This is the Authress SDK used to integrate with the authorization as a service provider Authress at https://authress.io.")
                url.set("https://github.com/Authress/authress-sdk.kt")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                developers {
                    developer {
                        id.set("authress")
                        name.set("Authress Developers")
                        email.set("developers@authress.io")
                    }
                }
                scm {
                    connection.set("scm:git:git://git@github.com:Authress/authress-sdk.kt.git")
                    url.set("https://github.com/Authress/authress-sdk.kt")
                }
            }
        }
    }
}

signing {
    sign(publishing.publications["mavenJava"])
}