#! /user/bin/env groovy
@Library('jenkins-shared-library')

// trying if pipeline will run
def gv

pipeline {

    agent any

    tools {
        maven 'mvn-3.9'

    }

    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
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
            when {
                    // conditional execution of steps, example run only if its active branch name is 'main'
                expression {
                    env.BRANCH_NAME == 'main' || env.BRANCH_NAME == 'master'
                }
            }
            steps {
                script {
                    buildJar()
                }
            }
        }

        stage("build and push image") {
            when {
                    // conditional execution of steps, example run only if its active branch name is 'main'
                expression {
                    env.BRANCH_NAME == 'main' || env.BRANCH_NAME == 'master'
                }
            }
            steps {
                script {
                    buildImage 'salzaidy/coffee-api:3.2'
                    dockerLogin()
                    dockerPush 'salzaidy/coffee-api:3.2'
                }
            }
        }

        stage("deploy") {
            when {
                    // conditional execution of steps, example run only if its active branch name is 'main'
                expression {
                    env.BRANCH_NAME == 'main' || env.BRANCH_NAME == 'master'
                }
            }
            steps {
                script {
                   gv.deployApp()
                }
            }
        }
    }
}