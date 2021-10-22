pipeline {
  agent{
    kubernetes {
      label 'jenkinsrun'
      defaultContainer 'builder'
      yaml """
kind: Pod
metadata:
  name: kaniko
spec:
  containers:
  - name: builder
    image: gcr.io/kaniko-project/executor:${KANIKO_EXECUTOR_VERSION} 
    imagePullPolicy: Always
    command:
    - /busybox/cat
    tty: true
    volumeMounts:
      - name: kaniko-secret
        mountPath: /secret
    env:
      - name: GOOGLE_APPLICATION_CREDENTIALS
        value: /secret/kaniko-secret.json         
  restartPolicy: Never
  volumes:
    - name: kaniko-secret
      secret:
        secretName: kaniko-secret
        items:
          - key: key.json
            path: kaniko-secret.json
"""
    }
  }

  environment {
    pw = "pwd"
    sonarqubeScannerHome = tool name: 'sonar', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
    dockerDir = 'asia.gcr.io/mthis-296015/bpjsservice'
  }

  stages {
    stage('preparation'){
      when{
        expression{
          BRANCH_NAME == 'PRD' || BRANCH_NAME == 'QA'
        }
      }
      steps{
        slackSend(color: "good", message: "Build ${env.JOB_NAME} started")
        container(name: 'jnlp', shell: '/bin/sh') {
          checkout scm

          echo "PATH = ${PATH}"
          echo "ls = ${pw}"
          sh "cp -r /home/jenkins/agent/workspace/BpjsService_${BRANCH_NAME}/* /home/jenkins/agent/workspace/BpjsService_${BRANCH_NAME}@tmp/durable-*/"
        }
      }
    }
    	
    stage ('Compile Stage') {
      when{
        expression{
          BRANCH_NAME == 'PRD' || BRANCH_NAME == 'QA'
        }
      }
      tools{
        jdk 'JDK11'
        maven 'M3'
      }
      steps{
        script {
          container(name: 'jnlp', shell: '/bin/sh') {
            sh "mvn -X clean package -Dmaven.test.skip=true"

            def codeBranch = ""
            if(BRANCH_NAME == 'QA'){
                codeBranch = "QA-"
            }
            version = "${codeBranch}V${readMavenPom().getVersion()}"
            sh "sed -i 's/-cprof_service_version=1.0.0/-cprof_service_version=${readMavenPom().getVersion()}/g' Dockerfile"
            echo("TAG_SELECTOR=${version}")
          }
        }
      }
    }
    
    stage('build'){
     when{
        expression{
          BRANCH_NAME == 'PRD' || BRANCH_NAME == 'QA'
        }
      }
      tools{
        jdk 'JDK11'
        maven 'M3'
      }
      steps{
        container(name: 'jnlp', shell: '/bin/sh') {
          sh 'mvn clean package -Dmaven.test.skip=true'
          archiveArtifacts(artifacts: 'target/*.jar', fingerprint: true)
        }
      }
    }

    stage('sonar-scanner') {
     when{
        expression{
          BRANCH_NAME == 'PRD' || BRANCH_NAME == 'QA'
        }
      }
      tools{
        jdk 'JDK11'
      }
      steps{
        container(name: 'jnlp', shell: '/bin/sh') {
          withCredentials([string(credentialsId: 'sonar', variable: 'sonarLogin')]) {
            sh "${sonarqubeScannerHome}/bin/sonar-scanner -e -Dsonar.host.url=http://192.168.222.167:9000 -Dsonar.login=${sonarLogin} -Dsonar.projectName=BpjsService -Dsonar.projectVersion=${env.BUILD_NUMBER} -Dsonar.projectKey=bpjsservice -Dsonar.sources=src/main/  -Dsonar.language=java -Dsonar.java.binaries=."
          }
        }
      }
    }
	    
    stage('PushDocker'){
      when{
        expression{
          BRANCH_NAME == 'PRD' || BRANCH_NAME == 'QA'
        }
      }

      steps{
        script{
          container(name: 'builder', shell: '/busybox/sh') {
            sh 'pwd'
            sh """
                #!/busybox/sh 
                /kaniko/executor --dockerfile `pwd`/Dockerfile  --context `pwd`/ --verbosity debug --insecure --skip-tls-verify --destination ${dockerDir}:${version}
            """
          }   
        }
      }
    }

    stage('Create spinnaker_trigger'){
       when{
        expression{
          BRANCH_NAME == 'PRD'
        }
      }
			steps{
				sh "echo \"image=${dockerDir}:${version}\" > spinnaker_trigger.properties"
				archiveArtifacts 'spinnaker_trigger.properties'
			}
		}
  }

  post {
    success {
      slackSend color: "good", message: "Build ${env.JOB_NAME} success"
    }
    failure {
      script {
        node ('master') {
          log = readFile "${JENKINS_HOME}/jobs/${JOB_NAME.replaceAll("/", "/branches/")}/builds/${BUILD_NUMBER}/log"
        }
        writeFile(file: "log.txt", text: log, encoding: "UTF-8")

        slackUploadFile filePath: "*.txt", initialComment:  """Error :  ${env.JOB_NAME} (${env.BUILD_NUMBER}) has failed, 
Build ${env.BUILD_URL} is failing
"""
      }
    }
  }
}
