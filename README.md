# Authress SDK for Kotlin and the JVM
This is the Authress SDK used to integrate with the authorization as a service provider Authress at https://authress.io.

## Getting Started
The package is published in the [Github maven repository](https://github.com/authress/authress-sdk/raw/repository) @ https://github.com/authress/authress-sdk/raw/repository

```
dependencies {
    implementation("authress:authress-sdk")
}
```

* Maven - add the following to your pom.xml:

```xml
<repositories>
    <repository>
        <id>github-repository</id>
        <url>https://maven.pkg.github.com/Authress/authress-sdk.kt/</url>
    </repository>     
</repositories>

<dependency>
  <groupId>authress</groupId>
  <artifactId>authress-sdk</artifactId>
  <version>1.0.0</version>
</dependency>
```

### Kotlin example

#### Authorize using a user token
```kotlin
import io.authress.client.apis.*

// create an instance of the API class during service initialization
// Replace ACCOUNT_ID with the Authress accountId
const authressClient = AuthressClient("https://ACCOUNT_ID.api-REGION.authress.io")

// on api route
// [route('/resources/<resourceId>')]
fun getResource(resourceId: kotlin.String) {
  // Get the user token and pass it to authress
  const authorizationToken = request.headers.get('authorization')
  authressClient.setToken(authorizationToken)

  // Check Authress to authorize the user
  try {
    authressClient.userPermissions.authorizeUser(userId, "resources/$resourceId", "READ")
  } catch (e: ClientException) {
    // User doesn't have access
    return 404
  } catch (e: ServerException) {
    println("5xx response calling UserPermissionsApi#authorizeUser")
    return 503
  }

  // On success, continue with the route code to load resource and return it
  // ...
  return 200
}
```

#### Authorize with a service client
```kotlin
import io.authress.client.apis.*
import io.authress.client.infrastructure.*

// create an instance of the API class during service initialization
// Replace AccountId with the Authress domain for your account

// Create a service client in the Authress management portal and past the access token here
// This will generate a token automatically instead of passing the user token to the api
const accessToken = 'eyJrZXlJ....'
const authressClient = AuthressClient("https://ACCOUNT_ID.api-REGION.authress.io", ServiceClientTokenProvider(accessToken))

// on api route
// [route('/resources/<resourceId>')]
fun getResource(resourceId: kotlin.String) {
  // Check Authress to authorize the user
  try {
    authressClient.userPermissions.authorizeUser(userId, "resources/$resourceId", "READ")
  } catch (e: ClientException) {
    // User doesn't have access
    return 404
  } catch (e: ServerException) {
    println("5xx response calling UserPermissionsApi#authorizeUser")
    return 503:
  }

  // On success, continue with the route code to load resource and return it
  // ...
  return 200
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