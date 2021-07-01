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
```kotlin
// Import classes:
//import io.authress.client.infrastructure.*
//import io.authress.client.models.*;

val apiInstance = UserPermissionsApi()
val userId : kotlin.String = userId_example // kotlin.String | The user to check permissions on
val resourceUri : kotlin.String = resourceUri_example // kotlin.String | The uri path of a resource to validate, must be URL encoded, uri segments are allowed, the resource must be a full path.
val permission : PermissionObjectSlashpropertiesSlashaction =  // PermissionObjectSlashpropertiesSlashaction | Permission to check, '*' and scoped permissions can also be checked here.
try {
    apiInstance.authorizeUser(userId, resourceUri, permission)
} catch (e: ClientException) {
    println("4xx response calling UserPermissionsApi#authorizeUser")
} catch (e: ServerException) {
    println("5xx response calling UserPermissionsApi#authorizeUser")
}
```

#### Authorize using a user token
```kotlin
const { AuthressClient } = require('authress-sdk');

// create an instance of the API class during service initialization
// Replace DOMAIN with the Authress domain for your account
const authressClient = new AuthressClient({ baseUrl: "https://DOMAIN.api-REGION.authress.io" })

// on api route
// [route('/resources/<resourceId>')]
fun getResource(resourceId) {
  // Get the user token and pass it to authress
  const authorizationToken = request.headers.get('authorization');
  authressClient.setToken(authorizationToken);

  // Check Authress to authorize the user
  try {
    authressClient.userPermissions.authorizeUser(userId, `resources/${resourceId}`, 'READ');
  } catch (error) {
    // Will throw except if the user is not authorized to read the resource
    if (error.status === 404) {
      return 404;
    }
    throw error;
  }

  // On success, continue with the route code to load resource and return it
  return { resource: {}, statusCode: 200 };
}
```

#### Authorize with a service client
```kotlin
const { AuthressClient, ServiceClientTokenProvider } = require('authress-sdk');

// create an instance of the API class during service initialization
// Replace DOMAIN with the Authress domain for your account

// Create a service client in the Authress management portal and past the access token here
// This will generate a token automatically instead of passing the user token to the api
const accessToken = 'eyJrZXlJ....';
const authressClient = new AuthressClient({ baseUrl: "https://DOMAIN.api-REGION.authress.io" }, new ServiceClientTokenProvider(accessToken));

// on api route
// [route('/resources/<resourceId>')]
fun getResource(resourceId) {
  // Check Authress to authorize the user
  try {
    authressClient.userPermissions.authorizeUser(userId, `resources/${resourceId}`, 'READ');
  } catch (error) {
    // Will throw except if the user is not authorized to read the resource
    if (error.status === 404) {
      return 404;
    }
    throw error;
  }

  // On success, continue with the route code to load resource and return it
  return { resource: {}, statusCode: 200 };
}
```

#### Creating resources
When a user creates a resource in your application, we want to ensure that they get access own that resource.

You may receive **User does not have sufficient access to grant permissions to resources** as an error along with the status code **403**. This means that the service client or user jwt does not have access to create the access record. If using a service client, go to the Authress portal and create a one time record which grants the service client `Authress:Owner` to `Resources/` so that it can manage access records for these types of resources.

```kotlin
await authressClient.accessRecords.createRecord({
  name: `Access To New Resource ${NewResourceId}`,
  users: [{ userId: requestUserId }],
  statements: [{
    resources: [{ resourceUri: `Resources/${NewResourceId}` }],
    // Owner by default gives full control over this new resource, including the ability to grant others access as well.
    roles: ['Authress:Owner']
  }]
});
```

#### Verifying a token using the token verifier
```kotlin
const { TokenVerifier } = require('authress-sdk');
const cookieManager = require('cookie');

try {
  // Grab authorization cookie from the request, the best way to do this will be framework specific.
  const cookies = cookieManager.parse(request.headers.cookie || '');
  const userToken = cookies.authorization || request.headers.Authorization.split(' ')[1];
  // Specify your custom domain for tokens. Configurable at https://authress.io/app/#/manage?focus=applications
  // Replacing the domain with your login url
  const userIdentity = await TokenVerifier('https://login.application.com', userToken);
} catch (error) {
  console.log('User is unauthorized', error);
  return { statusCode: 401 };
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