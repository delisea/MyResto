#!/bin/bash

# Author: Didier DONSEZ
# License ASL 2.0

# Build script for the Wildfly container containing javaee7-angular simple web app and swagger UI

COMPONENT_VERSION="1.0"
COMPONENT_NAME="wildfly-app"
COMPONENT_INSTANCE=1
# DOCKERFILE=${COMPONENT_NAME}.df
DOCKERFILE=Dockerfile

WORKDIR=$(pwd)

wait_for_host_port(){
	echo "Wait for $1:$2 ..."
	until nc -vzw 2 $1 $2 &>/dev/null;
	do
	   echo "Wait for $1:$2"
	   sleep 1
	done
	echo "$1:$2 ready"
}

# Get javaee7-angular

cd javaee7-angular
mvn clean install

# Get swagger-ui

cd $WORKDIR

cp javaee7-angular/target/javaee7-angular.war .

docker image remove -f ${COMPONENT_NAME}-${COMPONENT_INSTANCE}:${COMPONENT_VERSION}
docker container rm -f ${COMPONENT_NAME}-${COMPONENT_INSTANCE}

docker pull jboss/wildfly
docker build -t ${COMPONENT_NAME}-${COMPONENT_INSTANCE}:${COMPONENT_VERSION} -f $DOCKERFILE .

docker run  --hostname ${COMPONENT_NAME}-${COMPONENT_INSTANCE} --name ${COMPONENT_NAME}-${COMPONENT_INSTANCE} -p 8080:8080 -p 9990:9990 -v test.h2.db:/tmp/ -d ${COMPONENT_NAME}-${COMPONENT_INSTANCE}:${COMPONENT_VERSION}
wait_for_host_port localhost 8080
open http://localhost:8080/
open http://localhost:9990/