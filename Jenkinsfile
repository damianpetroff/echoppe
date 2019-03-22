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
				stash name: "app", includes: "**"
			}
		}
		
		stage('QualityTest') {
			docker {
					image 'maven:3-alpine'
				}
			steps {
				echo 'Testing'
				sh 'mvn clean test'
				sh 'cmod +x ./runTestSonar.sh'
				./runTestSonar.sh
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
				unstash "app"
				sh 'sleep 10'
				sh 'cmod +x ./runTestKatalon.sh'
				./runTestKatalon.sh
			}
		}
		
		stage('Deploy') {
			steps {
				echo 'Deploying'
			}
		}
	}
}