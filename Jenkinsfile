pipeline {
  agent { label 'conan_pipe_worker'}
  parameters {
    string(name: 'Greeting', defaultValue: 'hello')
  }

stages {
  stage('Build') {
    steps {
      script {
        def versions = ["v1", "v2"]
        def architectures = ["x86_64", "i686"]
        def build_types = ["release", "debug"]

        def builds = [:]

        for (ver in versions) {
          for (arch in architectures) {
            for (build in build_types) {
              String buildName = "${build}-${arch}-${ver}"

              builds[buildName] = {
                node('conan_pipe_worker') {
                  stage(buildName) {
                    echo buildName
                  }
                }
              }
            }
          }
        }
        parallel builds
      }
    }
  }
}
}
