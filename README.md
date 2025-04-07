# Project Index

## Project Overview
This is a Java-based test automation project using Selenium WebDriver, TestNG, and Cucumber for behavior-driven development (BDD).

## Project Structure

```
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── pages/         # Page Object Model classes
│   │       ├── testrunner/    # Test runner configurations
│   │       └── utils/         # Utility classes and helpers
│   │
│   └── test/
│       └── java/
│           ├── features/      # Cucumber feature files
│           ├── hooks/         # Cucumber hooks for setup/teardown
│           └── stepdefinitions/ # Step definition implementations
│
├── target/                    # Compiled output directory
├── .git/                      # Git version control
├── .idea/                     # IDE configuration
├── pom.xml                    # Maven project configuration
├── testng.xml                 # TestNG configuration
└── extent-config.xml          # Extent Reports configuration
```

## Key Components

### Main Source (`src/main/java/`)
- **pages/**: Contains Page Object Model classes for web elements and page interactions
- **testrunner/**: Test execution configurations and runners
- **utils/**: Helper classes, utilities, and common functionality

### Test Source (`src/test/java/`)
- **features/**: Cucumber feature files written in Gherkin syntax
- **hooks/**: Cucumber hooks for test setup and teardown
- **stepdefinitions/**: Implementation of step definitions for Cucumber scenarios

### Configuration Files
- `pom.xml`: Maven dependencies and project configuration
- `testng.xml`: TestNG test suite configuration
- `extent-config.xml`: Extent Reports configuration for test reporting

## Build and Test
This project uses Maven for dependency management and build automation. Tests can be executed using TestNG with Cucumber integration. 