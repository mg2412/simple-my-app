pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {

        stage('Checkout Code') {
            steps {
               git branch: 'main',
            url: 'https://github.com/mg2412/simple-my-app.git'
            }
        }

        stage('Build WAR') {
            steps {
                       dir('simple-java-webapp') {
            sh 'mvn clean package'
        }
            }
        }

        stage('Deploy to Tomcat') {
            steps {
                deploy adapters: [
                    tomcat9(
                        credentialsId: 'tomcat-cred',
                        path: '',
                        url: 'http://65.2.34.122:8080'
                    )
                ],
                contextPath: 'simple-my-app',
                war: 'simple-java-webapp/target/simple-webapp.war'
            }
        }
    }
}
