pipeline {

    agent any


    environment {

        IMAGE_NAME = "gopicorp/node-app-js"

        IMAGE_TAG = "${BUILD_NUMBER}"

    }


    tools {
        nodejs "NodeJS"
    }


    stages {


        stage('Install Dependencies') {

            steps {

                sh 'npm install'

            }
        }


        stage('Test') {

            steps {

                sh 'npm test'

            }
        }


        stage('Docker Build') {

            steps {

                sh '''
                docker build \
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


                    sh '''

                    echo $DOCKER_PASS | docker login \
                    -u $DOCKER_USER \
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