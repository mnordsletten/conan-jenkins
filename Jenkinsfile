pipeline {
  agent { label 'conan_pipe_worker'}
  parameters {
    string(name: 'Versions', defaultValue: 'v1, v2, v3')
    string(name: 'Architectures', defaultValue: 'release, debug')
    string(name: 'Build_types', defaultValue: 'x86_64, i686')
  }

stages {
  stage('Build') {
    steps {
      script {
        def versions = "${params.Versions}".replaceAll("\\s", "").split(',')
        def architectures = "${params.Architectures}".replaceAll("\\s", "").split(',')
        def build_types = "${params.Build_types}".replaceAll("\\s", "").split(',')

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
