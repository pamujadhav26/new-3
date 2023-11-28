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
        stage('Transfer Files to Remote Server') {
            steps {
                script {
                    sshPublisher(publishers: [sshPublisherDesc(configName: 'Weblogic-server',
		 transfers: [sshTransfer(cleanRemote: false, 
		 excludes: '', 
		 execCommand: '''/home/admin/weblogic12.2.1.4/Oracle/Middleware/Oracle_Home/oracle_common/common/bin/deploy.sh;/tmp/clover/copy-code.sh''', 
		 execTimeout: 120000, flatten: false, 
		 makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', 
		 remoteDirectory: '/tmp/clover', remoteDirectorySDF: false, removePrefix: '/target', sourceFiles: '**/*.war')], 
		 usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
                    
                              
         }                   
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
                              "pattern": "target/(*.war)",
                              "target": "libs-release-local/{1}",
                              "flat": "false",
                              "props" : "${properties}",
                              "exclusions": [ "*.sha1", "*.md5"]
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
