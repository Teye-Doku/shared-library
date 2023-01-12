 def call(Map params = null, Closure body = null){ 
       node {
         def cloud = (params?.cloud ? params.cloud : "kubernetes");
         String slaveDefinition = libraryResource("slave.yaml");

         println """
         THE SLAVE@@ USED: 
         $slaveDefinition
         """

         podTemplate(cloud: cloud, yaml: slaveDefinition) {
            generateJenkinsNode(params,body);
         }
       }
  }
void generateJenkinsNode(Map params= null, Closure body = null) {
    node(POD_LABEL) {
        try {
            if(!body){
              defaultComponentBuild(params);
            }else {
                body();
            }
        }catch(err){
            def errorMsg = err.getMessage();
            echo "Exception occurred": + errorMsg
        }
    }
}

void defaultComponentBuild(Map params = null) {
    stage("Init") {
       println "this is the Init stage";
    }
    stage("Maven stage") {
        container('maven') {
          sh 'mvn -version'
        }
    }
}