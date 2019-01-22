properties([pipelineTriggers([githubPush()])])

node('conan-worker-2'){
    git url: 'https://github.com/mnordsletten/conan-jenkins', branch: 'simple-parallel'

    stage("Get project"){
        echo "Hello world 4"
    }
}

def versions = ["v1.1.18", "v1.1.19", "a", "b", "c"]

def branches = [:]

for (v in versions) {
  branches[v] = {

    node('conan_pipe_worker') {
      stage(v) {
        script {
          sleep 5
          echo v
        }
      }
    }
  }
}
parallel branches
