# Rick & Morty API Automation Framework

This project is a **Cucumber-based API automation framework** written in **Java**.  
It tests the [Rick and Morty API](https://rickandmortyapi.com/) and demonstrates **best practices** such as:

- Using **DTO/POJO** classes for API responses
- **Dependency Injection** (via Cucumber and Guice/Picocontainer)
- Handling **single object, list, and error responses** with Gson
- Logging with **SLF4J + Logback**
- Test reporting with **Allure**
- Config-driven setup (`.properties`, `.json`, `.csv` support)
- Extendable design using **OOP & design patterns**

---

## ⚙️ Setup Instructions

### 1. Prerequisites
- **Java 11+** installed
- **Maven 3.6+** installed and available on your `PATH`
- (Optional) **Allure commandline** for advanced reporting

Verify installation:
```bash
java -version
mvn -version
```


## 2. Clone the Repository
```bash

git clone https://github.com/your-username/RickMortyAutomation.git
cd RickMortyAutomation
```

## 3. Install Dependencies

Maven will handle everything:
```bash
    mvn clean install
```


If Maven cached an old dependency, force update:
```bash
    mvn clean install -U
```

## 4. Run Tests

You can run all Cucumber scenarios with:
```bash
    mvn test
```

Or run a specific feature:
```bash
    mvn test -Dcucumber.filter.tags="@character"
```

## Logging

Logs are configured in src/test/resources/logback.xml.