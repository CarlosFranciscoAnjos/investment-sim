# mySql Commands

### Launching Shell

On local machine

    1. Add mysql to Environment Variables
    mysql -u root -pqwerty

On docker container

    docker run --name mysql-db -p 3306:3306 -e MYSQL_ROOT_PASSWORD=qwerty mysql
    docker exec -it mysql-db bash
    mysql -u root -pqwerty

### Users, Privileges & Database

Select Users

    SELECT user, host FROM mysql.user;

Create User

    CREATE USER 'investment-sim'@'localhost' IDENTIFIED BY 'sim2021';

Grant Privileges

    GRANT ALL PRIVILEGES ON *.* TO 'investment-sim'@'localhost';

    GRANT ALTER, CREATE, DELETE, DROP, INSERT, SELECT, TRIGGER, UPDATE ON *.* TO 'investment-sim'@'localhost';

    FLUSH PRIVILEGES;

    SHOW GRANTS FOR 'investment-sim'@'localhost';

Create Database

    CREATE DATABASE simdb;
    SHOW DATABASES;
    USE simdb;


### Table & Queries
