# Investment Simulator

*@CarlosFranciscoAnjos*

## Setup

### Build Investment-Sim
```
mvn clean
mvn -Dmaven.test.skip=true package
java -jar target/*.jar
```

### Docker Compose
```
docker compose build
docker compose up

docker compose stop
docker compose down -v
```

## Api

### Authentication
```
> BASIC AUTH
admin:admin
```

### Resources

```
> HEALTHCHECKS
    /
    /system

> CRUD OPERATIONS
    /users
    /simulations
    /assets
    /liabilities
```
