#!/bin/bash

# Get javaee7-angular

cd javaee7-angular
mvn clean install

# Get swagger-ui

cd ..

cp javaee7-angular/target/javaee7-angular.war .

docker build -t myresto -f Dockerfile .
docker tag myresto delisea/myresto:latest
docker push delisea/myresto:latest
oc delete all -l app=myresto
oc new-app delisea/myresto:latest
oc expose svc myresto
