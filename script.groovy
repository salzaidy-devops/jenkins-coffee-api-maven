def testApp() {
    echo "testing the application..."
    sh "mvn test"
    echo "executing pipeline for branch $BRANCH_NAME"
}



def deployApp() {
    echo "deploying the application"
}

return this