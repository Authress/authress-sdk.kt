# Authress SDK for Kotlin and the JVM
This is the Authress SDK used to integrate with the authorization as a service provider Authress at https://authress.io.

## Usage
The package is publish in the [Github maven repository](https://github.com/authress/authress-sdk/raw/repository) @ https://github.com/authress/authress-sdk/raw/repository

```
dependencies {
    implementation("io.authress:authress-sdk")
}
```

## Contribution
### Requires

* Kotlin 1.4.30
* Gradle 7.1

Run

```
./gradlew build
./gradlew check assemble
```

This runs all tests and packages the library.