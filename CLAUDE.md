# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this is

A personal portfolio website (josegarcia.xyz) built with Kotlin/WASM and Compose Multiplatform. The UI is a canvas-rendered single-page app deployed to GitHub Pages.

## Commands

```bash
# Development server (hot reload)
./gradlew wasmJsBrowserDevelopmentRun   # WASM target (primary)
./gradlew jsBrowserDevelopmentRun       # JS fallback

# Production build
./gradlew wasmJsBrowserDistribution

# Run tests
./gradlew allTests
./gradlew jsTest
```

## Architecture

### Targets and entry points

Two compilation targets share a `commonMain` source set:
- **WASM** (`wasmJsMain/kotlin/main.kt`): primary target, uses `onWasmReady` + `CanvasBasedWindow`
- **JS** (`jsMain/kotlin/main.kt`): fallback, uses `CanvasBasedWindow` directly

`App.kt` is the UI root composable. It renders a full-screen background image with content overlaid, and provides `CompositionLocal` values for window state (`LocalWindowType`, `LocalWindowSize`).

### Responsive layout

`WindowType` (COMPACT / MEDIUM / EXPANDED) is derived from browser window width (breakpoints: 600px, 840px). `rememberWindowType()` and `rememberWindowSize()` subscribe to resize events. Content switches between `CompactContent` and `ExpandedContent` composables based on `LocalWindowType`.

Text scaling uses a hybrid logarithmic + linear formula in `calculateSizeForScreen()` (`utils/Window.kt`).

### Deployment

GitHub Actions (`.github/workflows/deploy.yml`) runs `wasmJsBrowserDistribution` on push to `main`, then commits the built artifacts back to the repo root. GitHub Pages serves from the root. The custom Gradle tasks in `composeApp/build.gradle.kts` handle copying distribution output to the root directory.
