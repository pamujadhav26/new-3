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
                    sshPublisher(publishers: [sshPublisherDesc(configName: 'Weblogic-server', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: 'echo "hello"', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '/tmp/clover', remoteDirectorySDF: false, removePrefix: '/target', sourceFiles: '**/*.war')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
                              
         }                   
        }

    }
}
}
