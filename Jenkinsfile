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
        IMAGE_NAME = 'salzaidy/aws-coffee-api:3.0'
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

        stage("increment build number") {
            steps {
                script {
                    // gv.incrementBuildNumber()
                    echo 'incrementing build number...'
                    // sh 'mvn build-helper:parse-version versions:set \
                    //     -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                    //     versions:commit'
                    def matcher = readFile('pom.xml') =~ '<version>(.+?)</version>'
                    def version = matcher ? matcher[0][1] : "0.0.1"
                    echo "Raw version is: ${version}"
                    // env.IMAGE_NAME = "$version-$BUILD_NUMBER"

                    def clearVersion = version.replace('-SNAPSHOT','')
                    echo "Clear version is: ${clearVersion}"

                    def versionWithBuild = "$version-$BUILD_NUMBER"
                    echo "version With Build will be: ${versionWithBuild}"
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
                    // def dockerComposeCMD = "docker compose --file docker-compose.yaml up --detach"

                    def shellcmd = "bash ./server-cmds.sh ${IMAGE_NAME}"
                    def ec2Instance = "ec2-user@3.17.150.175"

                    sshagent(['ec2-server-key']) {
                        sh "scp server-cmds.sh ${ec2Instance}:/home/ec2-user"
                        sh "scp docker-compose.yaml ${ec2Instance}:/home/ec2-user"
                        sh "ssh -o StrictHostKeyChecking=no ${ec2Instance} ${shellcmd}"
                    }
                }
            }
        }
    }
}
