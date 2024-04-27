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

<img alt="postman-capture" src="docs/images/postman-capture.png" width="1000"/>

## Application Design

### C2 Diagram

<img alt="c2-diagram" src="docs/diagrams/c2-diagram.png"  width="1000"/>

### CI/CD Diagram

<img alt="cicd-diagram" src="docs/diagrams/infrastructure-diagram.png"  width="1000"/>
