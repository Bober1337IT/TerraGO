# TerraGO

TerraGO is a modern Android application designed for exotic pet enthusiasts to manage their animal collections, track care routines, and monitor growth with ease. Whether you keep spiders, reptiles, or other invertebrates, TerraGO provides a professional and intuitive interface to ensure your pets thrive.

## Features

- **Collection Management**: View your entire collection at a glance with a clean, sticker-style list.
- **Detailed Care Tracking**:
    - **Feeding Logs**: Track when your animals were last fed.
    - **Hydration (Spray)**: Monitor humidity maintenance routines.
    - **Growth Monitoring**:
        - **Spiders**: Smart molting system that auto-increments stages (e.g., L7 → L8).
        - **Others**: Manual size updates in centimeters or custom units.
- **Comprehensive Profiles**: Store nicknames, species information, birth dates, habitat details, and custom notes.
- **Habitat & Species Management**: Easily add and assign pets to specific terrariums and define species-specific requirements.
- **Photo Support**: Add photos to each animal profile for easy identification.
- **Beautiful UI**:
    - Custom **TerraGO Green Theme** optimized for both Light and Dark modes.
    - Modern Material 3 components with intuitive animations and safety confirmation dialogs.

## Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/) (2.0.21)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose) (Material 3)
- **Dependency Injection**: [Hilt](https://dagger.dev/hilt/) for robust and testable architecture.
- **Database**: [SQLDelight](https://cashapp.github.io/sqldelight/) for type-safe local storage.
- **Architecture**: Clean Architecture / MVVM with Repositories and Use Cases.
- **Asynchronous Flow**: Kotlin Coroutines and StateFlow.
- **Navigation**: Navigation Compose with Hilt Integration.

## Project Structure

- `data/`: Data layer implementation.
    - `database/`: SQLDelight configuration, App Initializer, and DB entities.
    - `repositories/`: Implementation of repository interfaces (`AnimalsRepositoryImpl`, etc.).
- `domain/`: Business logic layer.
    - `repository/`: Repository interfaces for total abstraction from data sources.
    - `usecase/`: Reusable business logic components (e.g., `UpdateAnimalFieldUseCase`).
- `presentation/`: UI layer.
    - `ui/`: Compose screens, components, and themes.
    - `viewmodel/`: Hilt-powered ViewModels managing UI state.
- `di/`: Dependency Injection modules (`AppModule`).
- `db/`: SQLDelight schema and query definitions (`.sq` files).
---
*Developed with ❤️  for the exotic pet community.*
