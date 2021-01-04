pipeline {
    agent any

    stages {
        stage('package') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('deploy'){
        	steps{
        		sh 'cp /home/ubuntu/.jenkins/workspace/CuttingBoard/target/cuttingboard.war /home/ubuntu/apache-tomcat-9.0.41/webapps'
        	}
        }
    }
}