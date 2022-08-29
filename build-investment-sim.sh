#!/bin/sh
mvn clean
mvn -Dmaven.test.skip=true package