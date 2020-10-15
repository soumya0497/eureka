node
{
  try {
   
    stage('checkout') {
      checkout scm
	    
    }
    stage('prepare') {
      sh "git clean -fdx"
	  commit_id = sh(returnStdout: true, script: "git log -n 1 --pretty=format:'%h'").trim()
    }
    
  /*  stage ('Initialize') {
        sh '''
		    
	    echo "JenkinsWorkspace = ${WORKSPACE}"
            echo "PATH = ${PATH}"
            echo "M2_HOME = ${M2_HOME}"
        '''
        }*/
    
	stage ('Initialize Build Env') {
		 env.DEPLOY_YAML_FILE="eureka.yaml"
	}
	
    stage('package') {      
      sh 'mvn clean install -Dmaven.test.skip=true' 	  
    }
	  
    stage('docker publish') {
      echo "creating docker container..."
      docker.withRegistry('https://soumya.azurecr.io', 'acr_cred') {
        	def customImage = docker.build("soumya.azurecr.io/eureka:${commit_id}")
        	/* Push the container to the custom Registry */
		echo "Pushing Docker Container to Private Registry..."
        	customImage.push()
			sh "docker rmi -f soumya.azurecr.io/eureka:${commit_id}"
	}
	
    }
	
	/* stage('Kubernetes Nonprod Deployment'){
	 withKubeConfig([credentialsId: 'testkube']) {
      sh "sed 's/\$COMMIT/${commit_id}/g' ${DEPLOY_YAML_FILE} | kubectl apply -f - -n hca-nonprod "
	 }
	 } */
	 
	 stage('Kubernetes Prod Deployment'){
	 withKubeConfig([credentialsId: 'config']) {
      sh "sed 's/\$COMMIT/${commit_id}/g' ${DEPLOY_YAML_FILE} | kubectl apply -f - "
	 }
	 }
  stage('send success notification') {
   // notifyBuild('SUCCESS')
	currentBuild.result = 'SUCCESS'
  }
  } 
  catch (err) {
    //    notifyBuild('FAILURE')	
currentBuild.result = 'FAILURE'		
}
}
/*
def notifyBuild(String buildStatus) {

//Variable definition
  def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
  def slackmessage="${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})"
  def emailRecipients = "${env.EMAIL_RECIPIENTS}";
  
  
  if (buildStatus == 'SUCCESS') {
    colorCode = '#00FF00'
    email_body='${SCRIPT, template="dxccna_html_sucess.template"}'
  } else if (buildStatus == 'FAILURE') { 
     email_body='${SCRIPT, template="dxccna_html_failure.template"}'
    colorCode = '#FF0000'
  }
  slackSend (channel:'#compliancebuildnotify', color: colorCode, message:slackmessage )
   
   emailext (body: email_body, 
       subject: subject, 
       to: emailRecipients
     )
 }
*/
