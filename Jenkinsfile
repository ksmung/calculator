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
     }
}