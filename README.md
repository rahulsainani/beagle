# DebugMenu (Android)
*Add a smart side drawer to your applications to simplify development.*

**This library is in a very early stage of development.**

### Usage
Add the following to your top level Gradle file:

```groovy
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```

...and this to the module-level build script:

```groovy
debugImplementation "com.github.pandulapeter.debug-menu:debug-menu:0.0.2"
releaseImplementation "com.github.pandulapeter.debug-menu:debug-menu-noop:0.0.2"
```

The library has to be initialized in the Application class. See the [example](https://github.com/pandulapeter/debug-menu/blob/master/example/src/main/java/com/pandulapeter/debugMenuExample/DebugMenuExampleApplication.kt) for details. Also, if you want to properly support back navigation, all activities must check if the drawer consumes the event. This is implemented [here](https://github.com/pandulapeter/debug-menu/blob/master/example/src/main/java/com/pandulapeter/debugMenuExample/MainActivity.kt).

### To do
* Add module with device information
* Add module with OS information
* Add module with OkHttp request / response logging capabilities
* Add module with custom logging capabilities
* Add module with test accounts dialog
* Add support for dynamically changing the modules
* Add support for writing custom modules
* Double-check dependencies, make sure everything is encapsulated
* Double-check the way the drawer is inserted into the layout hierarchy, make sure it doesn't break Activity transitions
* Test with Android 10 gestures

### License
```
Copyright 2019 Pandula Péter

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```