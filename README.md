# Introduction

This project is our [reactive rest client](https://quarkus.io/guides/rest-client) implementation for the Tivoli Process Automation Engine (TPAE) `maxrest` API.
It's a common engine for IBM Maximo Asset Management and IBM Control Desk.

# Configure dependency

## Gradle

```groovy
maven("https://raw.githubusercontent.com/trikorasolns/quarkus-tpae-maxrest/BRANCH/releases")
```

# Build

```bash
$ ./gradlew clean  quarkusBuild
```

> NOTE: The [jandex](https://github.com/kordamp/jandex-gradle-plugin) plugin is used to help in the CDI process of this library.

# Run

To run the application in development mode launch.

```bash
$ ./gradlew quarkusDev
```

# Test

The test target is the rest client target itself. It's defined in the `application.properties` file.

```bash
$ ./gradlew check
```

# Release

## Gradle Release

```bash
$ ./gradlew release
```

## Publish package to GitHub

```bash
$ ./gradlew publish -PGitHubUsername=<github_user> -PGitHubPassword=<github_token>
```

## Maven Local

TBD

```bash
$ ./gradlew quarkusBuild jar publishToMavenLocal
```