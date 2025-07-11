# JoieFull - Your Fashion Companion

JoieFull is an Android application designed to help users browse, discover, and manage their favorite clothing items. It provides a seamless experience for exploring fashion products, viewing details, and keeping track of items you love.

## ✨ Features

*   **Browse Products:** Smoothly scroll through a list of clothing items.
*   **Product Details:** View detailed information about each product, including price, rating, and description.
*   **Favorites:** Mark items as favorites for easy access later.
*   **Interactive Ratings:** Rate products and see average ratings.
*   **User Comments:** Add and view comments for products.
*   **Modern UI:** Built with Jetpack Compose for a beautiful and responsive user interface.
*   **Offline First (Optional - if you implement it):** Access your data even when offline, with data syncing when connectivity is restored.

## 📸 Screenshots

<!-- Add 2-3 compelling screenshots of your app in action. -->
<!-- Replace these with actual links to your images. You can upload them to your GitHub repo (e.g., in a /screenshots folder) or an image hosting service. -->

| Home Screen                                                                                         | Product Detail                             | 
|:----------------------------------------------------------------------------------------------------| :----------------------------------------- | 
| ![Home Screen Screenshot](https://i.postimg.cc/vHXSfsQv/Capture-d-e-cran-2025-07-11-a-11-15-29.png) | ![Detail Screen Screenshot](https://i.postimg.cc/qMp5NRdR/Capture-d-e-cran-2025-07-11-a-11-15-46.png) |

## 🛠️ Built With

*   **[Kotlin](https://kotlinlang.org/):** First-class and official programming language for Android development.
*   **[Jetpack Compose](https://developer.android.com/jetpack/compose):** Android’s modern toolkit for building native UI.
*   **[Coroutines](https://kotlinlang.org/docs/coroutines-overview.html):** For asynchronous programming.
*   **[Flow](https://kotlinlang.org/docs/flow.html):** For reactive data streams.
*   **[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel):** To store and manage UI-related data in a lifecycle-conscious way.
*   **[StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow):** For observable state-holder Flow.
*   **[Navigation Compose](https://developer.android.com/jetpack/compose/navigation):** For navigating between composables.
*   **[Coil](https://coil-kt.github.io/coil/):** Image loading library for Android backed by Kotlin Coroutines.
*   **[Retrofit](https://square.github.io/retrofit/) (If used):** Type-safe HTTP client for Android and Java.
*   **[Room](https://developer.android.com/training/data-storage/room) (If used):** Persistence library for local data storage.
*   **[MockK](https://mockk.io/) (For testing):** Mocking library for Kotlin.
*   **[Turbine](https://github.com/cashapp/turbine) (For testing):** Small testing library for kotlinx.coroutines Flow.
*   **Material Design 3:** For UI components and styling.

## ⚙️ Getting Started

### Prerequisites

*   Android Studio Iguana | 2023.2.1 or newer (or specify your version).
*   JDK 17 (Java Development Kit).
*   Android SDK with API level XX (e.g., API 33) or higher.

### Installation & Setup

1.  **Clone the repository:**
2.  **Open in Android Studio:**
    *   Open Android Studio.
    *   Click on `Open` (or `File > Open...`).
    *   Navigate to the cloned `joiefull` directory and select it.
3.  **Build the project:**
    *   Let Android Studio sync Gradle and download dependencies.
    *   Click `Build > Make Project` or run the app on an emulator/device.