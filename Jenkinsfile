pipeline {
      environment {
        registry = "ksmung/calculator"
        registryCredential = "docker_id"
      }
     agent any
     tools {
        maven 'Maven3.6.1' 
      }
     stages {
          stage("Checkout") {
               steps {
                    git url: 'https://github.com/ksmung/calculator.git'
               }
          }
          stage("Compile") {
              steps{
                  sh "mvn compile"
              }
          }
          stage("Unit test") {
              steps{
                  sh "mvn test"
              }
          }
          stage("Code coverage") {
              steps {
                  sh "mvn jacoco:report"
                  publishHTML (target: [
               reportDir: 'target/site/jacoco',
               reportFiles: 'index.html',
               reportName: "JaCoCo Report"
                ])
              }
          }
          stage("Package") {
			     steps {
			          sh "mvn package"
			         }
			    }

  			stage("Docker build") {
  			     steps {
  			          sh "docker build -t ksmung/calculator ."
  			       }
  			   }

        stage("Deploy to staging") {
             steps {
                  sh "docker-compose up -d"
             }
        }
        stage("Acceptance test") {
             steps {
                  sleep 120
                  sh "chmod +x acceptance_test.sh"
                  sh "./acceptance_test.sh"
             }
        }
    }
     post {
		     always {
		          sh "docker-compose down"
		     }
		}
}