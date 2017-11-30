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
					cat customization/execute.sh | tr -d "\r" > customization/temp.sh
					mv customization/temp.sh customization/execute.sh
				
					COMPONENT_VERSION="1.0"
					COMPONENT_NAME="wildfly-app"
					COMPONENT_INSTANCE=1
					DOCKERFILE=Dockerfile

					docker image remove -f ${COMPONENT_NAME}-${COMPONENT_INSTANCE}:${COMPONENT_VERSION}  || true
					docker container rm -f ${COMPONENT_NAME}-${COMPONENT_INSTANCE}  || true

					docker pull jboss/wildfly
					docker build -t ${COMPONENT_NAME}-${COMPONENT_INSTANCE}:${COMPONENT_VERSION} -f $DOCKERFILE .

					docker run  --hostname ${COMPONENT_NAME}-${COMPONENT_INSTANCE} --name ${COMPONENT_NAME}-${COMPONENT_INSTANCE} -p 8080:8080 -d ${COMPONENT_NAME}-${COMPONENT_INSTANCE}:${COMPONENT_VERSION}
				'''
            }
        }
    }
}

