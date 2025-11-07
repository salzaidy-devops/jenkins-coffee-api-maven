#!/usr/bin/env groovy

library identifier: 'jenkins-shared-library@main', retriever: modernSCM(
    [$class: 'GitSCMSource',
    remote: 'https://github.com/salzaidy-devops/jenkins-shared-library.git',
    credentialsID: 'github-credentials'
    ]
)

def gv

pipeline {

    agent any

    tools {
        maven 'mvn-3.9'

    }

    environment {
        IMAGE_NAME = 'salzaidy/aws-coffee-api:2.0'
    }

    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                    echo "testing trigger for webhooks..."
                }
            }
        }
        stage("test") {
            steps {
                script {
                    gv.testApp()
                }
            }
        }

        stage("build jar") {
            steps {
                script {
                    echo 'Building JAR file...'
                    buildMavenJar()
                }
            }
        }

        stage("build docker image") {
            steps {
                script {
                    echo 'Building Docker image...'
                    buildImage(env.IMAGE_NAME)
                    dockerLogin()
                    dockerPush(env.IMAGE_NAME)
                }
            }
        }

        stage("Deploy") {
            steps {
                script {
                    echo 'copying Docker compose file...'
                    // def dockerComposeCMD = "docker-compose -f docker-compose.yaml up --detach"
                    def dockerComposeCMD = "docker compose --file docker-compose.yaml up --detach"
                    
                    sshagent(['ec2-server-key']) {
                        // this flag is to avoid host key verification issue
                        sh "scp docker-compose.yaml ec2-user@3.17.150.175:/home/ec2-user"
                        sh "ssh -o StrictHostKeyChecking=no ec2-user@3.17.150.175 ${dockerComposeCMD}"
                    }
                }
            }
        }
    }
}
