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
    }
}

