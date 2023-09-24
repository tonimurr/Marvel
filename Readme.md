# Marvel application
This application is built using Clean architecture and MVVM Design pattern, you can find below the structure of the packages, and each package description

- data: the data repository is in charge of everything related to caching and fetching data from the APIs
- domain: the domain repository contains the UseCases and the repositories interface that the data layer implements and contains the models that the presentation layer depends on to present the UI to the end user
- presentation: The presentation layer contains all the activities, fragments, ViewModel, Adapters in order to present the UI to the end user
- di: dependency injection package, includes the AppModule class that initialize Retrofit, Room, repositories and UseCases to be injected later on in the lifecycle of the application
- common: Includes all classes that are used accross all packages
- MarvelApp: The application class

## Data Layer
Package list:

- base: Contains base classes that are used by other packages inside the data layer classes
- db: Contains all the classes that are related to [ROOM](https://developer.android.com/jetpack/androidx/releases/room)  jetpack library
- mappers: Contains all the mapper that transform DTOs to domain models
- remote: Contains the interface that Retrofit implements as service
- repositories: Contains all the repository implementation (Retrofit and database is injected in the repository implementation)
- model: Contains all the models fetch from the APIs and to be saved in the database

## Domain Layer
Package list:

- model: Contains all the models to be used inside the presentation layer
- repositories: Contains the repository interface to be implemented in the data layer repositories package
- usecases: Contains all the UseCases that are called from the ViewModel inside the presentation layer, each UseCase represent an operation that the Data layer handles (repository is injected inside each UseCase)

## Presentation Layer
Package List:

- base: Includes all the base classes that are used in the presentation layer
- common: Includes all reusable components inside the presentation layer
- screen_*: Each screen is represented by a package and each package contains the fragment class, ViewModel, Adapters, etc..
- uimodels: Model classes that are used only for UI purposes (example LoadingUIModel() it's purpose only to indicate for the adapters that at this position you need to present a LoadingViewHolder)

## Third party libraries:

- [Retrofit](https://square.github.io/retrofit/) for api calls
- [Room](https://developer.android.com/jetpack/androidx/releases/room) to persist the data into an SQLite Database
- [Navigation component](https://developer.android.com/guide/navigation/get-started) is used to pass data and navigate between fragments
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency injection
- [Glide](https://github.com/bumptech/glide) image loading framework
- [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle) for the MVVM design pattern

## Installation requirement
No need to configure anything in the project in order to run the application, in case of any question, kindly contact me by email:
tonimurr2@gmail.com