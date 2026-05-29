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
- **Dependency Injection**: [Hilt](https://dagger.dev/hilt/) (2.52) for robust and testable architecture.
- **Database**: [Room](https://developer.android.com/training/data-storage/room) (2.7.1) for type-safe local storage, with KSP code generation.
- **Architecture**: Clean Architecture / MVVM with Repositories and Use Cases.
- **Asynchronous Flow**: Kotlin Coroutines and StateFlow.
- **Navigation**: [Navigation Compose](https://developer.android.com/jetpack/compose/navigation) with Hilt integration.
- **Background Work**: [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) for scheduled reminders.
- **Image Loading**: [Coil](https://coil-kt.github.io/coil/) and an integrated image cropper.

## Architecture

The project follows **Clean Architecture** with a strict dependency rule: both the
presentation and data layers depend on the domain layer, while the domain layer
depends on nothing.

```
Presentation  ─────▶  Domain  ◀─────  Data
 (Compose UI)        (models,        (Room, DAOs,
  ViewModels)        interfaces,      mappers, repo
                     use cases)       implementations)
```

- **Domain** holds pure Kotlin models, repository interfaces, and use cases — free of
  any Room or Compose dependencies.
- **Data** implements the domain repository interfaces using Room, and maps entities
  to domain models.
- **Presentation** consumes the domain layer through ViewModels and renders state with
  Jetpack Compose.

## Project Structure

```
com.terrago.app
├── TerraGoApp.kt            # @HiltAndroidApp — application entry point
├── di/                      # Hilt modules
│   ├── DatabaseModule       # provides Room database + DAOs (@Provides)
│   └── RepositoryModule     # binds repository interfaces to implementations (@Binds)
├── domain/                  # Business logic layer (pure Kotlin)
│   ├── animals/             # AnimalsRepository, model/, usecase/ (UpsertAnimalUseCase)
│   ├── objects/             # ObjectsRepository, TerraObject, UpsertObjectUseCase
│   └── species/             # SpeciesRepository, Species, UpsertSpeciesUserCase
├── data/                    # Data layer implementation
│   ├── local/
│   │   ├── database/        # TerraGoDatabase (@Database)
│   │   ├── entity/          # Room entities (AnimalEntity, ObjectEntity, …)
│   │   ├── dao/             # DAOs + projection/ DTOs for JOIN queries
│   │   ├── mapper/          # Entity ↔ domain model mappers
│   │   └── AppInitializer   # Seeds the default species list on first launch
│   └── repository/          # Repository implementations (AnimalsRepositoryImpl, …)
└── presentation/            # UI layer (MVVM)
    ├── main/                # MainActivity (@AndroidEntryPoint)
    ├── navigation/          # AppNavHost, Screen routes, navigation graphs
    ├── feature/             # animals, animaldetails, animalform screens + ViewModels
    └── shared/              # theme/, reusable components (BottomNavBar, photo, dialogs)
    
   ```
---
*Developed with ❤️  for the exotic pet community.*
