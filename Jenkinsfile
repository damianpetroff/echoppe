pipeline {
	agent any
	stages {
		stage('Build') {
			agent {
				docker {
					image 'maven:3-alpine'
				}
			}
			
			steps {
				sh '(cd ./echoppe/; mvn clean package)'
			}
		}
		
		stage('Test') {
			steps {
				echo 'Testing'
			}
		}
		
		stage('Deploy') {
			steps {
				echo 'Deploying'
			}
		}
	}
}