#!/bin/bash

WORKDIR=$(pwd)

mvn clean install

cp target/javaee7-angular.war ..