pipeline {
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
	stage('Initialize Docker'){
            def dockerHome = tool 'myDocker'
       	    env.PATH = "${dockerHome}/bin:${env.PATH}"
    	}
        stage('Docker Build') { 
            steps {
                sh 'docker build -t football-service .'
            }
        }
	stage('Run') {
            steps {
                sh 'docker run -p 8080:8080 football-service'
            }
        }
    }
}
