# Instant Sonar
### Sample app for the Instant Apps SDK

This is a sample project to showcase the basic functionality of the Instant Apps SDK on Android. Check out more information about it on [my blogpost](https://medium.com/@juliozynger).

### How to build

This project uses [SoundCloud's public API](https://developers.soundcloud.com/docs/api/guide) to fetch information around artists and tracks on their database. In order to be able to build it, you'll have to include a `local.properties` file on the project's root folder which contains the following content:
```groovy
sdk.dir=<PATH_TO_SDK>
soundcloudClientId=<SOUNDCLOUD_CLIENT_ID_STRING>
userId=<ARTIST_IDENTIFIER_ON_SOUNDCLOUD_BASE>
```

After defining the project's properties, running either the `gradlew create` task or the fully named `gradlew :sonaria:assembleDebug` will assemble the Instant App `zip` to be installed on devices.

### Project structure

This project is composed by a number of Android modules, which are all bundled in the final `zip` file that represents the Instant App:
 - **sonarartists** & **sonarartistsatom**: Artist library and atom. Will respond to the URL `https://instantsonar.com/artist` which will launch a list of the tracks for the artist defined by the `userId` property on the `.properties` file.
 - **sonartracks** & **sonartracksatom**: Track library and atom. Will respond to the URLs like `https://instantsonar.com/track/<track_id>` and will launch an `Activity` that presents the details of the track defined by the `track_id` query param.
 - **sonarbase** & **sonarbaseatom**: Base library and atom. Contains shared functionality across the other atoms, shared Android resources, as well as the main `AndroidManifest` file that determines the paths and the Instant App configuration.
 - **sonaria**: Builder module for the Instant App `zip` generation.

### Testing

The project contains (very simple) tests, as a proof-of-concept that the usual testing techniques still work in a multi-module environment. In order to run them, simply execute the `gradlew test` task.
