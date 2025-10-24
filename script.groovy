def testApp() {
    echo "testing the application..."
    sh "mvn test"
    echo "executing pipeline for branch $BRANCH_NAME"
}

//def buildJar() {
//    echo "building the application..."
//    sh "mvn package"
//}
//
//
//def buildImage() {
//    echo "building the docker image..."
//    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
//        sh 'docker build -t salzaidy/coffee-api:1.0 .'
//        sh 'echo $PASS | docker login -u $USER --password-stdin'
//        sh 'docker push salzaidy/coffee-api:1.0'
//    }
//}


def deployApp() {
    echo "deploying the application"
}

return this