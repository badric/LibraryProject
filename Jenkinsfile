pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh 'echo "Starte Build"'
                sh 'mvn -B clean package'
                sh 'echo "GESCHAFFT!"'
            }
        }
        stage('test') {
            steps {
                sh 'echo "STarte Testen"'
                sh 'mvn test'
                sh 'echo "GESCHAFFT!"'
            }
            post {
                always {
                    junit 'target/surefire-reports/*xml'
                }
            }
        }
    }
}
