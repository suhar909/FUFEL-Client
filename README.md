# FUFEL CLIENT

A small Fabric utility client for **Minecraft Java 1.21.11**. It provides a configurable HUD and a ClickGUI for client-side information modules.

## Included in this starter build

- HUD overlay with FUFEL branding
- FPS display
- Player coordinates
- Right Shift opens the ClickGUI
- Toggle buttons for HUD, FPS and coordinates
- Settings saved to `config/fufel-client.properties`
- No packet manipulation, anti-cheat evasion, hidden process behaviour, or server-side advantage features

## Requirements

- Minecraft Java Edition 1.21.11
- JDK 21
- Gradle 8.14 or newer (if you do not use the Gradle wrapper)
- IntelliJ IDEA Community or another Java IDE

Fabric's 1.21.11 setup guide specifies JDK 21. The project uses Mojang mappings and the Fabric HUD element API.

## Build

1. Install JDK 21 and make sure `java -version` reports version 21.
2. Install Gradle, or generate a wrapper once from the project folder:
   `gradle wrapper --gradle-version 8.14`
3. Run:
   - Windows: `gradlew.bat build`
   - macOS/Linux: `./gradlew build`
4. The mod JAR will be in `build/libs/`. Use the JAR without `-sources` in its name.

The first build downloads Minecraft and Fabric dependencies, so it needs an internet connection.

## Install

1. Install Fabric Loader for Minecraft 1.21.11 using the official Fabric Installer.
2. Put the built `fufel-client-1.0.0.jar` in the `mods` folder of the Minecraft game directory.
3. Also install the matching Fabric API for 1.21.11 in the same `mods` folder.
4. Launch the Fabric 1.21.11 profile.

## Controls

- `Right Shift` — open/close ClickGUI
- Click a module button to enable/disable it.
- Press `Done` or Escape to close the GUI.

## Project layout

```text
src/
  main/resources/fabric.mod.json
  client/java/dev/fufel/client/
    FufelClient.java
    ClickGuiScreen.java
    ClientConfig.java
    HudRenderer.java
    ModuleState.java
```

## Notes

This is a clean starter project, not a claim that a JAR has already been compiled or tested in a live Minecraft installation. The first Gradle build validates the source against the locally resolved 1.21.11 toolchain. If a dependency version has been superseded, update the matching property in `gradle.properties` using Fabric's version page.


## Build automatically on GitHub

The repository includes `.github/workflows/build.yml`.

1. Create a new GitHub repository.
2. Upload the contents of this project folder to the repository (the `.github` folder must be included).
3. Open the repository's **Actions** tab.
4. Select **Build FUFEL CLIENT**. It runs automatically after a push; you can also use **Run workflow**.
5. When the run finishes successfully, open the run and download the artifact named `FUFEL-CLIENT-Minecraft-1.21.11`.
6. The downloaded artifact is a ZIP containing the built mod JAR.

The workflow uses Java 21, Gradle 8.14 and GitHub Actions artifact upload. A failed workflow means the source or dependency configuration needs fixing; it does not produce a usable JAR.
