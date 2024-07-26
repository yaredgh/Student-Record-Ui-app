pipeline {
    agent any

    environment {
        APP_NAME = 'Student-Record-Ui-app'
        MAVEN_HOME = tool 'Maven3'
        DOCKER_HUB_CREDENTIALS = '34070370-6077-41e8-9f70-9aa79fa5b2fe'
        DOCKER_CONFIG = "${env.HOME}/.docker"
        DOCKER_IMAGE = 'yaredgidey/cicd'
        DEPLOYMENT_FILE = "dev/deployment.yaml"
    }

    stages {
        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }

        stage('Checkout') {
            steps {
                git 'https://github.com/yaredgh/Student-Record-Ui-app.git'
            }
        }

        stage('Build') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn clean install"
            }
        }

        stage('Test') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn test"
            }
        }

        stage('Package') {
            steps {
                sh "${MAVEN_HOME}/bin/mvn package"
            }
        }

        stage('Docker Build & Publish') {
            steps {
                script {
                    try {
                        docker.withRegistry('https://index.docker.io/v1/', DOCKER_HUB_CREDENTIALS) {
                            def app = docker.build("${DOCKER_IMAGE}:${env.BUILD_NUMBER}")
                            app.push("${env.BUILD_NUMBER}")
                            app.push("latest")
                        }
                    } catch (Exception e) {
                        error "Docker Build & Publish failed: ${e.message}"
                    }
                }
            }
        }

        stage('Update Deployment File') {
            steps {
                script {
                    try {
                        sh """
                        # For macOS
                        sed -i '' 's|image: .*|image: ${DOCKER_IMAGE}:${env.BUILD_NUMBER}|' ${DEPLOYMENT_FILE}
                        # For Linux (comment out the macOS version above and use this line if on Linux)
                        # sed -i 's|image: .*|image: ${DOCKER_IMAGE}:${env.BUILD_NUMBER}|' ${DEPLOYMENT_FILE}
                        """
                    } catch (Exception e) {
                        error "Failed to update deployment file: ${e.message}"
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    try {
                        sh """
                        kubectl apply -f ${DEPLOYMENT_FILE}
                        """
                    } catch (Exception e) {
                        error "Kubernetes Deployment failed: ${e.message}"
                    }
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
