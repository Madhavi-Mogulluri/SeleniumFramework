pipeline
{
    agent any

    tools{
        maven 'maven'
        }

    stages
    {
        stage('Build')
        {
            parallel
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post
            {
                success
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }



        stage("Deploy to QA"){
            parallel{
                echo("deploy to qa done")
            }
        }




        stage('Regression Automation Tests') {
            parallel {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/Madhavi-Mogulluri/SeleniumFramework.git'
                    sh "mvn clean test -DsuiteXmlFile=src/test/resources/Testrunners/testng_regression.xml -Denv=qa"

                }
            }
        }


        stage('Publish Allure Reports') {
           parallel {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }


        stage('Publish ChainTest Report'){
            parallel{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false,
                                  keepAll: true,
                                  reportDir: 'target/chaintest',
                                  reportFiles: 'Index.html',
                                  reportName: 'HTML Regression ChainTest Report',
                                  reportTitles: ''])
            }
        }

        stage("Deploy to Stage"){
            parallel{
                echo("deploy to Stage")
            }
        }

        stage('Sanity Automation Test') {
            parallel {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/Madhavi-Mogulluri/SeleniumFramework.git'
                    sh "mvn clean test -DsuiteXmlFile=src/test/resources/Testrunners/testng_sanity.xml -Denv=stage"

                }
            }
        }



        stage('Publish sanity ChainTest Report'){
            parallel{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false,
                                  keepAll: true,
                                  reportDir: 'target/chaintest',
                                  reportFiles: 'Index.html',
                                  reportName: 'HTML Sanity ChainTest Report',
                                  reportTitles: ''])
            }
        }


        stage("Deploy to PROD"){
            parallel{
                echo("deploy to PROD")
            }
        }


    }
}
