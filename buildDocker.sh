#!/bin/bash

# Get javaee7-angular

cd javaee7-angular
mvn clean install

# Get swagger-ui

cd ..

cp javaee7-angular/target/javaee7-angular.war .

docker container rm -f myresto
docker build -t myresto -f Dockerfile .
docker run  --hostname myresto --name myresto -p 8080:8080 -p 9990:9990 -v test.h2.db:/tmp/ -d myresto