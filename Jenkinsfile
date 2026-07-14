pipeline {

    agent any

    environment {
        IMAGE_NAME = "gopicorp/node-app-js"
        IMAGE_TAG  = "${BUILD_NUMBER}"
    }

    tools {
        nodejs "NodeJS"
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'node-app-1',
                    url: 'https://github.com/company/my-app.git'
            }
        }

        stage('Install Dependencies') {
            steps {
                bat 'npm install'
            }
        }

        stage('Test') {
            steps {
                bat 'npm test'
            }
        }

        stage('Docker Build') {
            steps {
                bat '''
                    docker build ^
                    -t ${IMAGE_NAME}:${IMAGE_TAG} .
                '''
            }
        }

        stage('Docker Login and Push') {
            steps {

                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-creds',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                    )
                ]) {

                    bat '''
                        echo %DOCKER_PASS% | docker login ^
                        -u %DOCKER_USER% ^
                        --password-stdin

                        docker push ${IMAGE_NAME}:${IMAGE_TAG}

                        docker logout
                    '''
                }
            }
        }
    }

    post {

        success {
            echo "Docker image pushed successfully"
        }

        failure {
            echo "Pipeline failed"
        }
    }
}