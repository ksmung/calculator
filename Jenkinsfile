pipeline {
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
                  sh "docker run -d --rm -p 8765:8080 --name calculator ksmung/calculator"
             }
        }
        stage("Acceptance test") {
             steps {
                  sleep 60
                  sh "chmod +x acceptance_test.sh"
                  sh "./acceptance_test.sh"
             }
        }
    }
     post {
		     always {
		          sh "docker stop ksmung/calculator"
		     }
		}
}