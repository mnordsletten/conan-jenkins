pipeline {
  agent { label 'conan_pipe_worker' }

  stages {
    stage('create') {
      steps {
        script {
          jobDsl {

            pipelineJob('test') {
              description("Your App Pipeline") 
              keepDependencies(false)
              definition {
                cpsScm {
                  scriptPath('a/Jenkinsfile')
                  extensions { }  // required as otherwise it may try to tag the repo, which you may not want
                }
              }
            }

          }
        }
      }
    }
  }
}
