pipeline {
    agent any
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
        stage('deploy'){
        	sh "curl -v -u admin:password -T "target/lp.war" 'http://localhost:1080/manager/text/deploy?path=/LibraryProject&update=true'"
        }
    }
}
