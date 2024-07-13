pipeline {
    agent any

    environment {
        APP_NAME = 'Student-Record-Ui-app'
        // Set Maven home to the configured Maven tool in Jenkins
        MAVEN_HOME = tool 'Maven3'
        // Docker Hub credentials ID
        DOCKER_HUB_CREDENTIALS = '34070370-6077-41e8-9f70-9aa79fa5b2fe'
        DOCKER_CONFIG = "${env.HOME}/.docker"

    }

    stages {
        stage('cleanws') {
             steps {
                  // Clean workspace
                  cleanWs();
              }
         }

        stage('Checkout') {
            steps {
                // Checkout the code from the repository
                git 'https://github.com/yaredgh/Student-Record-Ui-app.git'
            }
        }

        stage('Build') {
            steps {
                // Build the project using Maven
                sh "${MAVEN_HOME}/bin/mvn clean install"
            }
        }

        stage('Test') {
            steps {
                // Run tests using Maven
                sh "${MAVEN_HOME}/bin/mvn test"
            }
        }

        stage('Package') {
            steps {
                // Package the application using Maven
                sh "${MAVEN_HOME}/bin/mvn package"
            }
        }

        stage('Docker Build & Publish') {
                    steps {
                        withEnv(["PATH+EXTRA=/usr/local/bin"]) {
                            script {
                                docker.withRegistry('https://index.docker.io/v1/', DOCKER_HUB_CREDENTIALS) {
                                    def app = docker.build("yaredgidey/cicd:${env.BUILD_NUMBER}")
                                    app.push("${env.BUILD_NUMBER}")
                                    app.push("latest")
                                }
                            }
                        }
                    }
                }


            post {
                always {
                    // Clean up the workspace after the build
                    cleanWs()
                }
            }
}
