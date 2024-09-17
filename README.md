# GitHub User List App

    This Android app allows users to view a list of GitHub users, their followers, subscriptions, 
    and user repositories. It follows a clean architecture with layers like Data, Domain, and Presentation, 
    and utilizes Jetpack Compose for the UI. The app integrates Room for local database storage and 
    Retrofit with Moshi for API interactions. Dependency injection is managed by Hilt, and the app is 
    structured for easy unit testing.

Project Structure

    app/
    ├── data/                     # Data layer for API and database handling
    │    ├── api/                 # GitHub API service interfaces
    │    ├── database/            # Room database and DAOs
    │    ├── model/               # Data models representing API responses and database entities
    │    └── repository/          # Repository interfaces and implementations for fetching data
    ├── domain/                   # Business logic layer
    │    ├── usecase/             # Use cases that encapsulate application logic
    │    └── model/               # Domain models representing business logic
    ├── presentation/             # UI layer (Jetpack Compose screens, adapters, and view models)
    │    ├── adapter/             # Compose adapters for displaying data in lists
    │    ├── viewModel/           # ViewModels handling the UI logic
    │    └── activity/            # Activities, navigation, and UI composition
    ├── di/                       # Hilt dependency injection setup
    ├── test/                     # Unit tests and UI tests
    ├── utils/                    # Utility classes
    └── build.gradle              # Project build configuration

Features :-

    Fetch GitHub Users: Lists users from the GitHub API.
    User Details: Displays detailed information about a specific user.
    Followers & Subscriptions: Lists followers and subscriptions for each user.
    Local Caching: Saves user data using Room for offline access.
    Error Handling: Comprehensive error handling for network and database operations.
    Android Testing: Unit tests for ViewModels, and UI tests for Compose screens.

Technologies Used

    Kotlin: Primary language for Android development.
    MVVM Architecture: Separation of concerns via ViewModel, Repository, and Data layers.
    Jetpack Compose: Modern Android UI toolkit for building native UI.
    Room Database: Caches user information locally for offline access.
    Retrofit: For making network requests to the GitHub API.
    Moshi : for JSON serialization/deserialization
    Dagger Hilt: Dependency injection framework.
    Coroutines/Flow: For asynchronous programming.
    JUnit and MockK for unit tests
    ViewModel: To store and manage UI-related data.

Setup and Installation

    Prerequisites
    Android Studio: Version Dolphin or later.
    Kotlin: The app is built entirely using Kotlin.
    GitHub API Token: You will need to create a personal access token from GitHub to interact with its
    API. See the GitHub documentation for more details.

Steps

    Clone the Repository:
    git clone https://github.com/androidtutorialonline/Android_Compose_MVVM.git

Build and Run:

    Open the project in Android Studio.
    Click "Sync Project with Gradle Files".
    Run the app on an emulator or physical device.

Dependencies

    The project uses the following major dependencies:
    Jetpack Compose for UI.
    Dagger Hilt for dependency injection.
    Retrofit for network requests.
    Room for local database storage.
    Coroutines and Flow for asynchronous programming.
    Coil for image loading.

Key Components

    Data Layer
    Retrofit Service: API service interfaces for interacting with the GitHub REST API.
    Room Database: Caches user information, followers, and repositories for offline access.
    Repository: Abstracts data fetching logic from API and database.
    ViewModel Layer
    ItemViewModel: Responsible for managing UI-related data in the lifecycle-conscious way.
    FollowersViewModel: Fetches follower data and exposes it to the UI.
    UI Layer
    ItemListScreen: Displays a list of GitHub users fetched from the API.
    FollowerAdapter: Displays a list of followers using a Compose LazyColumn.

Error Handling

    The project defines several custom errors in the AppError class:
    NetworkError: Indicates a network-related issue.
    InternetError: Occurs when there's no active internet connection.
    DatabaseError: Reflects issues in the local Room database.
    UnknownError: A generic error for unhandled cases.

Testing

    The project includes tests for both unit and UI layers:

Unit Tests

    ViewModel Tests: Test ViewModel logic independently of the UI and data sources.
    Repository Tests: Mock API and database interactions to ensure repository correctness.

UI Tests (Jetpack Compose)

    Compose Testing: Use the Compose Testing library to test UI elements.
    Hilt Test Rules: Ensures dependencies are properly injected during tests.

Test Setup

    JUnit: Used for all unit testing.
    Mockito: For mocking dependencies in ViewModel and Repository tests.
    Jetpack Compose Testing: For UI testing with Jetpack Compose components.
    Hilt Android Testing: For testing classes that use dependency injection.

Running Tests

    You can run the unit and UI tests via Android Studio

API Details

    This app fetches data from the GitHub REST API. API service interfaces are defined in the api/ folder.
    
    Endpoints Used:
    
    /users - Fetches list of users.
    /users/{username} - Fetches details of a specific user.
    /users/{username}/followers - Fetches followers of a user.
    /users/{username}/subscriptions - Fetches repositories the user is subscribed to.
