#!/usr/bin/env groovy

def build(versions, architectures, build_types, conanfile_path) {
  versions = "${versions}".replaceAll("\\s", "").split(',')
  architectures = "${architectures}".replaceAll("\\s", "").split(',')
  build_types = "${build_types}".replaceAll("\\s", "").split(',')

  unstash 'all'

  def builds = [:]

  for (ver in versions) {
    for (arch in architectures) {
      for (build in build_types) {
        String buildName = "${build}-${arch}-${ver}"

        builds[buildName] = """
                echo "Path to read: ${conanfile_path}"
                echo "in here now"
                cat "${conanfile_path}"
              """

      }
    }
  }
  return builds
}

def conanfile_path(jenkinsfile_path, version) {
  def regexSuffix = ~/\/Jenkinsfile$/
  def path = "${jenkinsfile_path}" - regexSuffix
  def conanfile = "${path}/${version}/conanfile.py"
  return conanfile
}

def upload(target) {
   echo "Uploading to: ${target}"
}



return this;
