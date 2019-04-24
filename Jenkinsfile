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
			agent {
				docker {
						image 'maven:3-alpine'
					}
				}
			steps {
				echo 'Quality Testing'
				sh '(mvn clean test)'
				sh '(mvn sonar:sonar -Dsonar.projectKey=damianpetroff_echoppe -Dsonar.organization=damianpetroff-github -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=b372b1d4260ce8995f3963484c30baf2210b809a)'
			}
		}

		stage('IntegrationTest') {
			agent {
				docker {
					image 'lucienmoor/katalon-for-jenkins:latest'
					args '-p 9999:9090'
				}
			}
			steps {
				echo 'Integration Testing'
				unstash "app"
                sh 'java -jar target/Thymeleaf-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &'
                sh 'sleep 30'
			    sh 'chmod +x ./runTestKatalon.sh'
			    sh './runTestKatalon.sh'
			    cleanWs()
			}
			post {
				always {
                    echo 'always clean up'
                    deleteDir()
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
