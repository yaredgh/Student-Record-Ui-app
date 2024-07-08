pipeline {
    agent any

    environment {
        // Set any environment variables if needed
        MAVEN_HOME = tool 'Maven3' // Assumes Maven is configured in Jenkins
    }

    stages {
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
                script {
                    // Build Docker image
                    def app = docker.build("yaredgidey/cicd:tagname:${env.BUILD_NUMBER}")

                    // Push Docker image to Docker Hub
                    docker.withRegistry('https://index.docker.io/v1/', '34070370-6077-41e8-9f70-9aa79fa5b2fe') {
                        app.push("${env.BUILD_NUMBER}")
                        app.push("latest")
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                // Deploy the application (e.g., to Kubernetes, ECS, etc.)
                // This example assumes a simple deployment step
                sh 'echo "Deploying application..."'
                // Add your deployment scripts/commands here
            }
        }
    }

    post {
        always {
            // Clean workspace after build
            cleanWs()
        }
        success {
            // Notify of successful build (e.g., via email, Slack, etc.)
            echo 'Build successful!'
        }
        failure {
            // Notify of failed build (e.g., via email, Slack, etc.)
            echo 'Build failed!'
        }
    }
}
