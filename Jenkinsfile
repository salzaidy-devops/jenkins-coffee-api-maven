def gv

pipeline {

    agent any

    tools {
        maven 'mvn-3.9'

    }

    stages {
        stage("build jar") {
            steps {
                script {
                    echo "building the application..."
                    sh "mvn package"
                }
            }
        }

        stage("build image") {
            steps {
                script {
                    echo "building the docker image..."
                    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
                        sh 'docker build -t salzaidy/coffee-api:1.0 .'
                        sh 'echo $PASS | docker login -u $USER --password-stdin'
                        sh 'docker push salzaidy/coffee-api:1.0'
                    }
                }
            }
        }

        stage("deploy") {
            steps {
                script {
                    echo "deploying the application"
                }
            }
        }
    }
}