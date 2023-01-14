 def call(){ 
       properties([
          buildDiscarder(logRotator(numToKeepStr: "5")),
       ])
       node {
         stage("build stage") {
            sh "echo building"
            echo "$WORKDIR"
         }
         stage("test stage") {
            sh "echo testing"
         }
         stage("deploy stage") {
            sh "echo deploying"
         }
       }
  }
