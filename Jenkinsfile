pipeline {
    agent {
        docker { image 'maven:3-alpine' }
    }
    stages {
        stage('build') {
            steps {
                sh 'echo "Starte Build"'
                sh 'mvn -B -DskipTests clean package'
                sh 'echo "GESCHAFFT!"'
            }
        }
        stage('test') {
            steps {
                sh 'echo "Starte Testen"'
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
