pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh '''
					cd javaee7-angular
					./build.sh
				'''
            }
        }
        stage('Deploy') {
            steps {
                sh '''
					COMPONENT_VERSION="1.0"
					COMPONENT_NAME="wildfly-app"
					COMPONENT_INSTANCE=1
					DOCKERFILE=Dockerfile

					docker image remove -f ${COMPONENT_NAME}-${COMPONENT_INSTANCE}:${COMPONENT_VERSION}
					docker container rm -f ${COMPONENT_NAME}-${COMPONENT_INSTANCE}

					docker pull jboss/wildfly
					docker build -t ${COMPONENT_NAME}-${COMPONENT_INSTANCE}:${COMPONENT_VERSION} -f $DOCKERFILE .

					docker run  --hostname ${COMPONENT_NAME}-${COMPONENT_INSTANCE} --name ${COMPONENT_NAME}-${COMPONENT_INSTANCE} -p 8090:8090 -p 9990:9990 -d ${COMPONENT_NAME}-${COMPONENT_INSTANCE}:${COMPONENT_VERSION}
				'''
            }
        }
    }
}

