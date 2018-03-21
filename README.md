#Android Things Board Defaults

[ ![Download](https://api.bintray.com/packages/knobtviker/maven/boards/images/download.svg) ](https://bintray.com/knobtviker/maven/boards/_latestVersion)

Set of utility features describing a specific hardware running Android Things.

##Setup
Add this as dependency in your app module build.gradle:
```
dependencies {
    implementation 'com.knobtviker.android.things.contrib.community:boards:1.0.4'
}
```

##How and what?
Methods available:
- **defaultI2CBus()** - returns default I2C bus depending on your device (I2C1 or I2C2)
- **i2CBuses()** - returns all I2C buses found on your device or an empty list if none found
- **i2CDevices()** - returns all I2C devices found on all I2C buses found, described as objects with bus, address and address as hex fields
- **isI2CDeviceConnected(@NonNull final String bus, @I2CAddress final int address)** - returns true or false after checking if specific device is connected with exact I2C address and I2C bus

##TODO
- Tests.
- Examples.
- Support SPI, GPIO, UART probing.