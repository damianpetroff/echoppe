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
				sh 'mvn sonar:sonar -Dsonar.projectKey=damianpetroff_echoppe -Dsonar.organization=damianpetroff-github -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=b372b1d4260ce8995f3963484c30baf2210b809a'
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
				sh 'cd ~/Katalon_Studio_Linux_64-6.1.0; katalon -noSplash  -runMode=console -projectPath="./katalonEchoppe/katalonEchoppe.prj" -retry=0 -testSuitePath="Test Suites/New Test Suite" -executionProfile="default" -browserType="Web Service"'
			}
		}
		
		stage('Deploy') {
			steps {
				echo 'Deploying'
			}
		}
	}
}
