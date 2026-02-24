# Luxe TicTacToe for Wear OS

A stylish TicTacToe experience for round watches (like Galaxy Watch Ultra) built with **Jetpack Compose for Wear OS** and **Material 3 for Wear** components.

## What you get

- Neon, animated "expressive" look suitable for OLED watch displays
- 3x3 touch grid with turn logic, win detection, and draw handling
- One-tap reset button
- Clean Kotlin/Compose code in a single activity for easy customization

## Open and build on your laptop

> Per your request, nothing is compiled in this environment.

1. Install Android Studio (latest stable)
2. Ensure these are installed:
   - Android SDK Platform 35
   - Wear OS emulator images (optional if deploying straight to watch)
3. Open this folder in Android Studio
4. Let Gradle sync
5. Run `app` on your Galaxy Watch Ultra (developer mode + wireless debugging) or an emulator

## Project structure

- `app/src/main/java/com/example/wearostictactoe/MainActivity.kt` – full UI + game logic
- `app/src/main/AndroidManifest.xml` – watch activity entrypoint
- `app/build.gradle.kts` – Wear Compose + Material3 dependencies

## Suggested tweaks for your watch

- Increase `Cell` size to `44-48dp` if you want bigger tap targets
- Change gradient colors for your favorite style
- Add haptics/audio for moves and win states
