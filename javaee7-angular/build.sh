#!/bin/bash

WORKDIR=$(pwd)

echo $WORKDIR

mvn clean install

cp target/javaee7-angular.war ..