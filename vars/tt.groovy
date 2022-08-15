#!/usr/bin/env groovy
// println(System.getProperty("java.ext.dirs"))

import io.kevin197011.cicd.Config
import io.kevin197011.cicd.Gitlab
import io.kevin197011.cicd.DeployDatabase

def call() {

    def project = Config.project
    def appName = Config.appName
    def appPath = Config.appPath
    def appConfig = Config.appConfig

    def database = new DeployDatabase('t1', 'localhost', 'tt', 'devops', '123456')
    def gitlab = new Gitlab(script: this)



    environments = 'lab\nstage\npro'

    properties([
            parameters([
                    [$class: 'CascadeChoiceParameter',
                     choiceType: 'PT_SINGLE_SELECT',
                     description: 'Select a choice',
                     filterLength: 1,
                     filterable: true,
                     name: 'choice1',
                     referencedParameters: 'ENVIRONMENT',
                     script: [$class: 'GroovyScript',
                              fallbackScript: [
                                      classpath: [],
                                      sandbox: true,
                                      script: 'return ["ERROR"]'
                              ],
                              script: [
                                      classpath: [],
                                      sandbox: true,
                                      script: """
                        if (ENVIRONMENT == 'lab') { 
                            return['aaa','bbb']
                        }
                        else {
                            return['ccc', 'ddd']
                        }
                    """.stripIndent()
                              ]
                     ]
                    ]
            ])
    ])






    //pipeline
    pipeline {
        agent any

        options {
            timestamps()
            skipDefaultCheckout()
            disableConcurrentBuilds()
            timeout(time: 1, unit: 'HOURS')
        }

//        parameters {
//            choice(name: 'project', choices: project, description: 'Which project?')
//            choice(name: 'appName', choices: appName[params.project], description: 'Which appName?')
//            booleanParam(name: 'DeployDatabase', defaultValue: false, description: 'do?')
//            booleanParam(name: 'doDeploy', defaultValue: false, description: 'do?')
//        }


        parameters {
            choice(name: 'ENVIRONMENT', choices: "${environments}")
        }


        stages {

            stage('git clone item') {
                steps {
                    script {
                        gitlab.gitCloneItem('https://github.com/kevin197011/chatOps.git', 'master')
                    }
                }
            }

//            stage('deploy database') {
//                steps {
//                    script {
//                        if (params.DeployDatabase) {
//                            def val = database.execute()
//                            if (val) {
//                                println("sql execute succeed!")
//                            } else {
//                                error("sql execute error!")
//                            }
//                        } else {
//                            println("deploy database skip...")
//                        }
//                    }
//                }
//            }

//            stage('deploy app') {
//                steps {
//                    script {
//                        print("project => ${params.project}")
//                        print("appName => ${params.appName}")
//                    }
//                }
//            }

            stage('deploy config then restart') {
                steps {
                    script {
                        sleep(1)
                    }
                }
            }

            stage('check list') {
                steps {
                    script {
                        sleep(1)
                    }
                }
            }
        }

        post {
            always {
                script {
                    println('always')
                }
            }

            success {
                script {
                    println('success')
                }
            }

            failure {
                script {
                    println('failure')
                }
            }

            aborted {
                script {
                    println('aborted')
                }
            }
        }
    }
}
