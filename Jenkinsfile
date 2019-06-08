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
    }
     post {
		     always {
		          sh "docker stop calculator"
		     }
		}
}