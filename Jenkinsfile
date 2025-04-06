pipeline {
    agent any
    
    environment {
        DOCKER_IMAGE = 'ignite-server'
        DOCKER_TAG = "${BUILD_NUMBER}"
    }
    
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        
        stage('Build Docker Image') {
            steps {
                sh 'mvn dockerfile:build'
            }
        }
        
        stage('Push Docker Image') {
            steps {
                sh 'docker tag ${DOCKER_IMAGE}:latest ${DOCKER_IMAGE}:${DOCKER_TAG}'
                sh 'docker push ${DOCKER_IMAGE}:${DOCKER_TAG}'
                sh 'docker push ${DOCKER_IMAGE}:latest'
            }
        }
    }
} 