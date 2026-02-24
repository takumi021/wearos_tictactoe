# Luxe TicTacToe for Wear OS

A stylish TicTacToe experience for round watches (including Galaxy Watch Ultra) built with **Jetpack Compose for Wear OS** and **Material 3 for Wear**.

## Highlights

- Expressive neon visuals with animated gradients built for OLED displays
- Full game flow: turns, winner detection, draw detection, one-tap reset
- Modernized Android stack (AGP/Kotlin/Compose BOM updated)
- Clean single-activity Kotlin code for easy customization

## Build on your laptop

> Per your request, nothing is compiled in this environment.

1. Install Android Studio (current stable)
2. Install Android SDK Platform 35 and Wear OS SDK components
3. Open this project folder
4. Let Gradle sync (it will fetch the latest declared dependencies)
5. Deploy to your Galaxy Watch Ultra (wireless debugging) or a Wear emulator

## Key files

- `app/src/main/java/com/example/wearostictactoe/MainActivity.kt` — UI + game logic
- `app/build.gradle.kts` — modern dependency/plugin versions
- `app/src/main/AndroidManifest.xml` — watch launcher activity
