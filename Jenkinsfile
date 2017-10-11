pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh 'mvn --version'
				
				sh 'cd javaee7-angular'
				sh 'mvn clean install'
				sh 'cd ..'
				sh 'cp javaee7-angular/target/javaee7-angular.war .'
            }
        }
    }
}

