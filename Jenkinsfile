pipeline {
    agent any

    tools {
        maven 'maven-3.9.6'
    }

    stages {

        stage('Build') {
            steps {
                dir('api-build') {
                    git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                    sh 'mvn -Dmaven.test.failure.ignore=true clean package'
                }
            }
            post {
                success {
                    junit 'api-build/**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts artifacts: 'api-build/target/*.jar', fingerprint: true
                }
            }
        }

        stage('Deploy to QA') {
            steps {
                echo 'Deploy to QA done'
            }
        }

        stage('Regression Automation Tests') {
            steps {
                dir('regression-tests') {
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        git 'https://github.com/Madhavi-Mogulluri/SeleniumFramework.git'
                        sh '''
                           mvn clean test \
                           -Dsurefire.suiteXmlFiles=src/test/resources/Testrunners/testng_regression.xml \
                           -Denv=qa
                        '''
                    }
                }
            }
        }

        stage('Publish Allure Reports') {
            steps {
                allure([
                    includeProperties: false,
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'regression-tests/target/allure-results']]
                ])
            }
        }

        stage('Publish ChainTest Report') {
            steps {
                publishHTML([
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'regression-tests/target/chaintest',
                    reportFiles: 'Index.html',
                    reportName: 'HTML Regression ChainTest Report'
                ])
            }
        }

        stage('Deploy to Stage') {
            steps {
                echo 'Deploy to Stage'
            }
        }

        stage('Sanity Automation Test') {
            steps {
                dir('sanity-tests') {
                    catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                        git 'https://github.com/Madhavi-Mogulluri/SeleniumFramework.git'
                        sh '''
                           mvn clean test \
                           -Dsurefire.suiteXmlFiles=src/test/resources/Testrunners/testng_sanity.xml \
                           -Denv=stage
                        '''
                    }
                }
            }
        }

        stage('Publish Sanity ChainTest Report') {
            steps {
                publishHTML([
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'sanity-tests/target/chaintest',
                    reportFiles: 'Index.html',
                    reportName: 'HTML Sanity ChainTest Report'
                ])
            }
        }

        stage('Deploy to PROD') {
            steps {
                echo 'Deploy to PROD'
            }
        }
    }
}
