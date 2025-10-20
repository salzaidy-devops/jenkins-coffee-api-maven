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
                    gv.buildJar()
                }
            }
        }

        stage("build image") {
            when {
                    // conditional execution of steps, example run only if its active branch name is 'main'
                expression {
                    env.BRANCH_NAME == 'main' || env.BRANCH_NAME == 'master'
                }
            }
            steps {
                script {
                    gv.buildImage()
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