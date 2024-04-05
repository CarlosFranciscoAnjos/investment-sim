# Investment Simulator

_@CarlosFranciscoAnjos_

## How to Use

### Maven

```bash
mvn clean
mvn -Dmaven.test.skip=true package
java -jar target/*.jar
```

### Docker Compose

```bash
docker compose build
docker compose up

docker compose stop
docker compose down -v
```

## API Specification

### Resources

```
> BASIC AUTH
    admin:admin

> HEALTHCHECKS
    /
    /system

> CRUD OPERATIONS
    /users
    /simulations
    /assets
    /liabilities
```

### Swagger Specification

![swagger](docs/swagger.xml)

### Postman Overview

![postman-capture](docs/images/postman-capture.PNG)

## Application Design

### C2 Diagram

![c2-diagram](docs/diagrams/c2-diagram.svg)

### CI/CD Diagram

![cicd-diagram](docs/diagrams/cicd-diagram.svg)
