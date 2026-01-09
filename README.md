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

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose) (Material 3)
- **Database**: [SQLDelight](https://cashapp.github.io/sqldelight/) for type-safe local storage.
- **Architecture**: MVVM (Model-View-ViewModel) with Repositories.
- **Asynchronous Flow**: Kotlin Coroutines and StateFlow.
- **Navigation**: Navigation Compose for seamless screen transitions.
- **Theming**: Custom dynamic-aware theme with tailored green color schemes.


## Project Structure

- `ui/`: All Compose screens and reusable components (Topbar, ActionButtons, etc.).
- `viewmodel/`: ViewModels handling state and business logic.
- `database/`: SQLDelight repositories and data entities.
- `navigation/`: App navigation graphs and route definitions.
- `db/`: SQLDelight schema and query definitions (`.sq` files).

---
*Developed with ❤️  for the exotic pet community.*
