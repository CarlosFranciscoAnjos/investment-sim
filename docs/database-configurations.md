# Database Configurations


## MySQL

### docker container

    docker run --name mysql-db -p 3306:3306 -e MYSQL_ROOT_PASSWORD=verysecretpassword mysql

    docker exec -it mysql-db bash

    mysql -u root -pverysecretpassword

### Users, Privileges & Database

#### Select Users

    SELECT user, host FROM mysql.user;

#### Create User

    CREATE USER 'investment-sim'@'localhost' IDENTIFIED BY 'sim2021';

#### Grant Privileges

    GRANT ALL PRIVILEGES ON *.* TO 'investment-sim'@'localhost';

    GRANT ALTER, CREATE, DELETE, DROP, INSERT, SELECT, TRIGGER, UPDATE ON *.* TO 'investment-sim'@'localhost';

    FLUSH PRIVILEGES;

    SHOW GRANTS FOR 'investment-sim'@'localhost';

#### Create Database

    CREATE DATABASE simdb;
    SHOW DATABASES;
    USE simdb;


## Postgres

### docker container

    docker run --name postgres-db-v1 -p 5432:5432 -e POSTGRES_PASSWORD=verysecretpassword -d postgres:13
