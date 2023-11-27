def registry = 'https://cloverinfo.jfrog.io/'
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
        
        
        
    
         stage("War Publish") {
        steps {
            script {
                    echo '<--------------- War Publish Started --------------->'
                     def server = Artifactory.newServer url:registry+"/artifactory" ,  credentialsId:"jfrog-cred"
                     def properties = "buildid=${env.BUILD_ID},commitid=${GIT_COMMIT}";
                     def uploadSpec = """{
                          "files": [
                            {
                              "pattern": "target/(*)",
                              "target": "libs-release-local/{1}",
                              "flat": "false",
                              "props" : "${properties}",
                              "exclusions": [ "/*","*.original"]
                            }
                         ]
                     }"""
                     def buildInfo = server.upload(uploadSpec)
                     buildInfo.env.collect()
                     server.publishBuildInfo(buildInfo)
                     echo '<--------------- War Publish Ended --------------->'  
            
            }
        }   
    }  

    }
}
