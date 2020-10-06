pipeline {
    environment {
        SERVICE_NAME = 'football-service'
    }
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Maven Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t ${SERVICE_NAME} .'
            }
        }
        stage('Run') {
            steps {
                sh 'docker run -p 8080:8080 ${SERVICE_NAME}'
            }
        }
        stage('Cleanup') {
            steps {
                sh 'docker stop $(docker ps -q --filter ancestor=${SERVICE_NAME})'
                sh 'docker image rm -f ${SERVICE_NAME}'
            }
        }
    }
}