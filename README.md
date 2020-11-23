---
title: Quarkus TPAE Rest client
subtitle: For the maxrest API
---

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

The release of this project is performed by using the [`net.researchgate.release` Gradle plugin](https://plugins.gradle.org/plugin/net.researchgate.release),
 by launching the following instruction.

```bash
$ ./gradlew release -PGitHubUsername=<github_user> -PGitHubPassword=<github_token>
```

It already includes the artifact publication

# Publish package to GitHub

To manually publish the artifacts to GitHub launch the following instruction.

```bash
$ ./gradlew publish -PGitHubUsername=<github_user> -PGitHubPassword=<github_token>
```

# How to use this project

This project can be used as a library, by adding it to the project dependency. Since it is being published in GitHub,
GitHub itself must be added as a Maven Repository.

## Dependencies

### Gradle

Add GitHub as a maven repository

```groovy
repositories {
  maven {
    name "GitHub"
    url "https://maven.pkg.github.com/trikorasolns/quarkus-tpae-maxrest"
  }
}
```

Add the dependency

```groovy
dependencies {
  implementation([group: 'com.trikorasolutions.quarkus.tpae.rest', name: 'quarkus-tpae-maxrest', version: tkrQuarkusTpaeVersion, changing: true])
}
```

### Maven

TBD

## Development

TBD
