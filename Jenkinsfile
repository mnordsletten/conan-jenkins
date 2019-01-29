def folderName = 'conanTest'
stage('Create folders') {
  node() {
    jobDsl scriptText: """
      folder("${folderName}") {
        displayName('ConanTest')
        description('Build jobs for all IncludOS dependencies built with Conan')
        views {
          listView('dependencies') {
            jobs {
              regex('deps-.+')
            }
            columns {
              status()
              weather()
              name()
              lastSuccess()
              lastFailure()
              lastDuration()
              buildButton()
            }
          }
          listView('libraries') {
            jobs {
              regex('libraries-.+')
            }
            columns {
              status()
              weather()
              name()
              lastSuccess()
              lastFailure()
              lastDuration()
              buildButton()
            }
          }
          listView('tools') {
            jobs {
              regex('tools-.+')
            }
            columns {
              status()
              weather()
              name()
              lastSuccess()
              lastFailure()
              lastDuration()
              buildButton()
            }
          }
        }
      }
    """
  }

}
stage('Create jobs') {
    node('conan_worker') {
      checkout scm

      def url = sh(returnStdout: true, script: 'git config remote.origin.url').trim()
      def regexSuffix = ~/\/Jenkinsfile$/
      def files = findFiles(glob: '**/Jenkinsfile')
      def jobs = [:]
      for (def file: files) {
        if ("${file.path}" == "Jenkinsfile") {
          echo "Skipping root Jenkinsfile"
          continue
        }
        String name = "${file.path}" - regexSuffix
        name = name.replace("/", "-")
        name = "${folderName}/${name}"
        def path = "${file.path}"

        jobs["${name}"] = {
          jobDsl scriptText: """
            pipelineJob("${name}") {
              description("Pipeline for ${name}")
              definition {
                cpsScm {
                  lightweight(true)
                  scm {
                    git {
                      remote {
                        url("${url}")
                      }
                      branch('simple-parallel')
                      scriptPath("${path}")
                      extensions {
                        cleanBeforeCheckout()
                      }
                    }
                  }
                }
              }
            }
            """
            build "${name}"
          }
      }
      parallel jobs

    }
}
