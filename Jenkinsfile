//checkout repo
//build recipe
//deploy package
//basically
//for loop multi jobs..

def artifactory_name = "kristianj"
def artifactory_repo = "includeos-develop"
def repo_url = 'https://github.com/hioa-cs/IncludeOS.git'
def repo_branch = 'conan'

def conan_user = 'kristian'
def conan_channel = 'demo'

// binutils
def binutils_version='2.31'
// applies for binutils as in resides in the gnu directory
def recipe_location = 'gnu'

//musl
def musl_version='1.1.20'

node {
   label 'conan_worker'

   def server
   def client
   def serverName

    stage("Get project"){
        //TODO replace with SCM snapshot.. if possible pull only the conan folder
        git branch: repo_branch, url: repo_url
        echo "$PATH"
    }

    stage("Build - binutils"){
          sh """
            echo "creating binutils"
            conan create conan/${recipe_location}/binutils/${binutils_version} ${conan_user}/${conan_channel}
          """
    }

    stage("Build - musl"){
          sh """
            echo "creating musl"
            conan create conan/musl/v1.1.20 conan/musl/${musl_version}@${conan_user}/${conan_channel}
          """
    }
}
