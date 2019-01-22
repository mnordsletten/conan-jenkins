properties([pipelineTriggers([githubPush()])])

node('conan-worker-2'){
    git url: 'https://github.com/mnordsletten/conan-jenkins', branch: 'simple-parallel'
}

def versions = ["v1", "v2", "v3"]
def architectures = ["x86_64", "i686", "arm64"]
def build_types = ["release", "debug"]

def builds = [:]

for (ver in versions) {
  for (arch in architectures) {
    for (build in build_types) {
      String buildName = "${build}-${arch}-${ver}"

      builds[buildName] = {
        node('conan_pipe_worker') {
          stage(buildName) {
            script {
              echo buildName
            }
          }
        }
      }
    }
  }
}
parallel builds
