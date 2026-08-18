\# Employee Management API



A containerized Employee Management REST API built using Spring Boot, MySQL, Docker, and Docker Compose.



This project demonstrates a production-style DevOps workflow including multi-stage Docker builds, unit testing, container security, persistent database storage, and GitHub version control.



\---



\## Architecture



```text

Client / Postman

&#x20;      |

&#x20;      v

Spring Boot Employee API

&#x20;      |

&#x20;      | Port 8080

&#x20;      v

Docker Container

&#x20;      |

&#x20;      | Docker Compose Network

&#x20;      v

MySQL Container

&#x20;      |

&#x20;      v

Docker Volume

mysql-data

```



\---



\## Technology Stack



| Technology | Purpose |

|---|---|

| Java 17 | Application language |

| Spring Boot | REST API framework |

| Spring Data JPA | Database access |

| MySQL 8 | Database |

| Maven | Build and dependency management |

| Docker | Application containerization |

| Docker Compose | Multi-container orchestration |

| JUnit 5 | Unit testing |

| Mockito | Mocking framework |

| Git | Version control |

| GitHub | Source code repository |



\---



\## Project Structure



```text

java-docker-project

│

├── Dockerfile

├── docker-compose.yml

├── pom.xml

├── README.md

├── .dockerignore

├── .gitignore

│

└── src

&#x20;   ├── main

&#x20;   │   ├── java

&#x20;   │   │   └── com/example/demo

&#x20;   │   │       ├── controller

&#x20;   │   │       │   └── EmployeeController.java

&#x20;   │   │       ├── model

&#x20;   │   │       │   └── Employee.java

&#x20;   │   │       ├── repository

&#x20;   │   │       │   └── EmployeeRepository.java

&#x20;   │   │       └── service

&#x20;   │   │           └── EmployeeService.java

&#x20;   │   │

&#x20;   │   └── resources

&#x20;   │       └── application.properties

&#x20;   │

&#x20;   └── test

&#x20;       └── java

&#x20;           └── com/example/demo/service

&#x20;               └── EmployeeServiceTest.java

```



\---



\## API Endpoints



| Method | Endpoint | Description |

|---|---|---|

| GET | `/api/employees` | Get all employees |

| GET | `/api/employees/{id}` | Get employee by ID |

| POST | `/api/employees` | Create an employee |

| PUT | `/api/employees/{id}` | Update an employee |

| DELETE | `/api/employees/{id}` | Delete an employee |



\---



\## Run the Application



\### Clone the repository



```bash

git clone <your-github-repository-url>

cd java-docker-project

```



\### Build and start containers



```bash

docker compose up --build -d

```



\### Check container status



```bash

docker compose ps

```



\### Test the API



```bash

curl http://localhost:8080/api/employees

```



For PowerShell:



```powershell

Invoke-RestMethod http://localhost:8080/api/employees

```



\---



\## Run Unit Tests



If Maven is not installed locally, run tests using Docker:



```bash

docker run --rm -v "${PWD}:/app" -w /app maven:3.9-eclipse-temurin-17 mvn test

```



Expected result:



```text

Tests run: 4, Failures: 0, Errors: 0

BUILD SUCCESS

```



\---



\## Docker Build



Build the application image:



```bash

docker build -t employee-api:v3 .

```



Verify that the application runs as a non-root user:



```bash

docker run --rm --entrypoint whoami employee-api:v3

```



Expected output:



```text

spring

```



\---



\## Dockerfile Build Stages



The Dockerfile uses three stages:



```text

TEST

&#x20; |

&#x20; | mvn test

&#x20; v

BUILD

&#x20; |

&#x20; | mvn clean package -DskipTests

&#x20; v

RUNTIME

&#x20; |

&#x20; | Java JRE

&#x20; | Non-root user

&#x20; v

Spring Boot Application

```



Benefits:



\- Unit tests run during the image build.

\- Maven and source code are not included in the runtime image.

\- Application runs as a non-root user.

\- Dependency caching improves build performance.



\---



\## Database Persistence



MySQL data is stored using a Docker volume:



```text

mysql-data

```



This allows data to persist even when containers are stopped and recreated:



```bash

docker compose down

docker compose up -d

```



To completely remove the database and its data:



```bash

docker compose down -v

```



> Warning: `docker compose down -v` deletes the MySQL volume and all stored employee data.



\---



\## CI/CD Roadmap



This project will be extended with:



```text

Developer

&#x20;  |

&#x20;  v

GitHub

&#x20;  |

&#x20;  v

Azure DevOps Pipeline

&#x20;  |

&#x20;  +--> Maven Build

&#x20;  |

&#x20;  +--> Unit Tests

&#x20;  |

&#x20;  +--> Docker Build

&#x20;  |

&#x20;  +--> Security Scanning

&#x20;  |

&#x20;  v

Azure Container Registry

&#x20;  |

&#x20;  v

Azure Kubernetes Service

&#x20;  |

&#x20;  v

Production Deployment

```



Future improvements:



\- Azure DevOps YAML pipeline

\- Azure Container Registry integration

\- AKS deployment

\- Terraform infrastructure provisioning

\- Azure Key Vault integration

\- Kubernetes ConfigMaps and Secrets

\- Health and readiness probes

\- Monitoring and logging



\---



\## Author



\*\*Sukanya\*\*



DevOps / Cloud Engineering Project



