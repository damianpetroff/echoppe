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
				sh 'mvn --version'
				sh 'mvn clean package'
			}
		}
		
		stage('QualityTest') {
			agent {
				docker {
						image 'maven:3-alpine'
					}
				}
			steps {
				echo 'Testing'
				script {
					sh ./runTestSonar.sh
				}
			}
		}

		stage('IntegrationTest') {
			agent {
				docker {
					image 'maven:3-alpine'
				}
			}
			steps {
				echo 'Integration Testing'
				script {
					./runTestKatalon.sh
				} 
			}
		}
		
		stage('Deploy') {
			steps {
				echo 'Deploying'
			}
		}
	}
}