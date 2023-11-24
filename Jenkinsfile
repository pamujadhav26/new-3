pipeline {
    agent any
    tools {
        maven "maven"
    }

    stages {
        stage('Checkout') {
            steps {
                // Optionally, if your script is in a version control system
                // For example, if using Git:
                git 'https://github.com/pamujadhav26/new-3.git'
            }
        }
    
        stage('Build with Maven') {
            steps {
                     bat 'mvn clean install'
                    
                }
            }
        stage('Transfer Files to Remote Server') {
            steps {
                script {
                    publishOverSSH(
                        server: Weblogic-server,
                        transfers: [
                            publishOverSSHTransfer (
                                sourceFiles: '**/*.war',
                                remoteDirectory: '/tmp',
                                execCommand: '', // Optional: Command to run on the remote server after file transfer
                              )
                          ]
                        )
                  }
         }                   
        }

    }
}
