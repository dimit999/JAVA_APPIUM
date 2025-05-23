# Java Appium Automation Framework

## Prerequisites

- **Java:** 17+ -> 21 (recommend 21)
- **Maven:** 3.8+
- **Node.js & npm:** (for Appium server)
- **Appium:** 2.x (install globally: `npm install -g appium`)
- **Android SDK** (for Android tests)
- **Xcode & Carthage** (for iOS tests, macOS only)

## Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/dimit999/JAVA_APPIUM.git
   cd java_appium
   ```
2. Install dependencies and run tests:
   ```sh
   mvn clean install -DskipTests
   ```
3. config.properties: APK_FILE_PATH -> Update path to your local file: 
4. Start Appium server:
   ```sh
   appium --address 127.0.0.1 --port 4723 --session-override 
   ```
5. Open your Emulator or Simulator: by default it is Pixel_7_Pro_API_35 from Android Studio

## Running Tests

- **Run all tests (default config):**
  ```sh
  mvn clean test
  ```
- **Override config on the fly:**
  ```sh
  mvn test -DDEVICE_NAME=Pixel_7_Pro_API_35 -DPLATFORM=ANDROID
  ```
- **Generate allure report:**
Generate (to be sure that you did `brew install allure`)
`allure generate target/allure-results --clean -o target/allure-report`

Open Allure report
`allure open target/allure-report`

## Code Quality Check (without running tests)

- **Run Checkstyle:**
  `mvn clean verify -DskipTests`

## Framework Features

- **Cross-platform:** Supports both Android and iOS automation.
- **Configurable:** Uses `config.properties` for easy runtime configuration and system property overrides.
- **Parallel Execution:** JUnit 5 + Maven Surefire with configurable parallelism: template is ready.
- **Device Locking:** Ensures safe single-device execution even with parallel test scheduling.
- **Page Object Pattern:** All screens and elements use the Page Object Model for maintainability.
- **Factory Pattern:** Dynamic screen and driver instantiation via factories.
- **ThreadLocal Driver:** Isolates driver per test thread.
- **JSON-based Device Config:** Device capabilities managed via JSON for flexibility.
- **Extensible:** Easy to add new platforms, devices, or test types.

## Design Patterns Used

- **Page Object Model (POM):** Encapsulates UI screens and elements.
- **Factory Pattern:** For screen and driver instantiation.
- **Singleton Pattern:** For configuration loading (Config/ConfigLoader).
- **ThreadLocal:** For thread-safe driver management in parallel tests.
- **Synchronized Locking:** For serializing device access in parallel test runs.

## Notes

- All configuration is managed in `src/main/resources/config.properties` and can be overridden via command line.
- Device capabilities are defined in `src/main/java/config/devices.json`.
- Test results: generate allure
- For iOS, run on macOS with Xcode and required simulators/devices.
