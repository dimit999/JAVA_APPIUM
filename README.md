# Java + Appium QA Automation Framework

This framework is designed for cross-platform (Android & iOS) native mobile app automation using Java and Appium, following best practices for 2025.

## Structure
- **src/main/java/base/**: Base classes for screens and elements
- **src/main/java/screens/**: Platform-specific screen implementations
- **src/main/java/elements/**: Platform-specific element implementations
- **src/main/java/config/**: Configuration and capability management
- **src/test/java/**: Test cases and fixtures
- **resources/**: Test data (JSON)

## Key Features
- Abstract classes for screens and elements, extended by platform-specific implementations
- Unified configuration via environment variables and JSON files
- BaseScreen and BaseElement for shared logic
- Test fixture to launch default first screen before each test

## Getting Started
1. Set environment variables as needed (see `src/main/java/config/Config.java`)
2. Place test data in `resources/data.json`
3. Run tests using your preferred runner (e.g., JUnit)

## Commands

check java version:
java --version
brew install openjdk@21
## Core Technologies
- Java 21

how to setup java 21
/usr/libexec/java_home -V
export JAVA_HOME=/opt/homebrew/opt/openjdk@21/libexec/openjdk.jdk/Contents/Home
export PATH=$JAVA_HOME/bin:$PATH
java -version

### 1. Build the project (Maven)
```
mvn clean install
```

### 2. Run all tests
```
mvn test
```

### 3. Run a specific test class
```
mvn -Dtest=YourTestClassName test
```

### 4. Set environment variables (example for Mac/Linux)
```
export PLATFORM=ANDROID
export APK_FILE_PATH=/path/to/app.apk
export IPA_FILE_PATH=/path/to/app.ipa
export APP_FILE_PATH=/path/to/app.app
export CAPTURE_LOG=True
```

### 5. Run tests with environment variables inline
```
PLATFORM=IOS mvn test
```

## Notes
- All configuration is in `src/main/java/config/Config.java`.
- Test data is read from `resources/data.json`.
- Use JUnit for tests.

---
