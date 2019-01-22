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
      echo "a"
    }
  }
}
branches["b"] = {
  node {
    stage("b") {
      echo "b"
    }
  }
}

parallel branches
