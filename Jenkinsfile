properties([pipelineTriggers([githubPush()])])

node('conan-worker-2'){
    git url: 'https://github.com/mnordsletten/conan-jenkins', branch: 'simple-parallel'

    stage("Get project"){
        echo "Hello world 4"
    }
}

def branches = [:]
branches["a"] = {
  node {
    stage("a") {
      script {
        sleep 10
        echo "a"
      }
    }
  }
}
branches["b"] = {
  node {
    stage("b") {
      script {
        sleep 10
        echo "b"
      }
    }
  }
}

parallel branches
