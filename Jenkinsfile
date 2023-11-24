pipeline {
    agent any

    environment {
        // Define SSH server configuration
        SSH_SERVER = 'Weblogic-server' // Name of the SSH server configuration in Jenkins
        SSH_USERNAME = 'admin'
        SSH_PASSWORD = 'admin'
        REMOTE_DIRECTORY = '/tmp/clover'
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
                script {
                    // Run Maven build
                    def mavenHome = tool 'Maven'
                    def mavenCMD = "${mavenHome}/bin/mvn"

                    // Use 'withMaven' step to run Maven goals
                    withMaven(
                        maven: 'Maven', // Maven tool name defined in Jenkins
                        goals: 'clean install'
                    )
                }
            }
        }


        stage('Publish and deploy') {
            steps {
                script {
                    

                    // Transfer the deployment script to the remote server using the Publish Over SSH plugin
                    publishOverSSH(
                        failOnError: true,
                        publishers: [
                            sshPublisher(
                                configName: 'Weblogic-server', // Specify the SSH configuration defined in Jenkins
                                transfers: [
                                    sshTransfer(
                                        sourceFiles: "**/*.war",
                                        execCommand: "/home/admin/weblogic12.2.1.4/Oracle/Middleware/Oracle_Home/oracle_common/common/bin/deploy.sh", // Execute the script on the remote server
                                        remoteDirectory: "/clover"
                                    )
                                ]
                            )
                        ]
                    )
                }
            }
        }
    }



   post {
        success {
            echo 'Build and transfer successful!'
        }
        failure {
            echo 'Build or transfer failed!'
        }
    }
}
