# AdvancedScoreboard [![](https://jitpack.io/v/blockiyt/advancedscoreboard.svg)](https://jitpack.io/#blockiyt/advancedscoreboard)
AdvancedScoreboard is a Scoreboard (Sideboard) Plugin for Minecraft Java Version 1.7.10-1.17.1. It has Support for PlaceholderAPI but does not need it to work, only if you want to use Placeholders. It uses the [FastBoard Scoreboard API](https://github.com/MrMicky-FR/FastBoard) which is a packet based Scoreboard API to set the Sideboard without interferring other Scoreboards.

# Features

  - Fully customizable scoreboard
  - Set refresh rate
  - Config support

It's powered by the (PlaceholderAPI v2.10.9) and Spigot API.

### Installation

AdvancedScoreboard requires [Spigot](https://www.spigotmc.org/), ([PlaceholderAPI](https://luckperms.net) is not nessecarry) with a Minecraft Server to run.

Download the latest Version of the Plugin [here](https://github.com/blockiyt/advancedscoreboard/releases) and put it into your Plugins folder in your Minecraft Server.

Restart or Reload the Server an you are done!

## API

### Maven
```xml
<dependencies>
	<dependency>
	    <groupId>com.github.blockiyt</groupId>
	    <artifactId>advancedscoreboard</artifactId>
	    <version>Tag</version>
	</dependency>
</dependencies>
```
```xml
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```

### Gradle

```groovy
repositories {
   maven { url 'https://jitpack.io' }
}
```
```groovy
dependencies {
    implementation 'com.github.blockiyt:advancedscoreboard:Tag'
}
```

### Todos

- nothing
