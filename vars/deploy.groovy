#!/usr/bin/env groovy
// println(System.getProperty("java.ext.dirs"))

import io.kevin197011.cicd.Config
import io.kevin197011.cicd.Gitlab
import io.kevin197011.cicd.DeployDatabase

def call() {

    def project = Config.projectName.join('\n')
//    def appName = Config.appName
    def appPath = Config.appPath
    def appConfig = Config.appConfig

    def database = new DeployDatabase('t1', 'localhost', 'deploy', 'devops', '123456')
    def gitlab = new Gitlab(script: this)


    properties([
            parameters([
                    [$class: 'CascadeChoiceParameter',
                     choiceType: 'PT_SINGLE_SELECT',
                     description: 'Which app?',
                     filterLength: 1,
                     filterable: false,
                     name: 'AppName',
                     referencedParameters: 'ProjectName',
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
                                            if (ProjectName == 'Aproject') {
                                                return['q', 'w', 'e']
                                            } else if(ProjectName == 'Bproject') {
                                                return['a', 's', 'd']
                                            } else if(ProjectName == 'Cproject') {
                                                return['z', 'x', 'c']
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

        parameters {
            choice(name: 'ProjectName', choices: "${project}", description: 'Which project?')
            booleanParam(name: 'DeployDB', defaultValue: false, description: 'sure?')
        }


        stages {

            stage('git clone item') {
                steps {
                    script {
                        gitlab.cloneItem('https://github.com/kevin197011/chatOps.git', 'master')
                    }
                }
            }

//            stage('deploy database') {
//                steps {
//                    script {
//                        if (params.DeployDB) {
//                            def val = database.execute()
//                            if (val) {
//                                println("sql execute succeed!")
//                            } else {
//                                error("sql execute error!")
//                            }
//                        } else {
//                            println("deploy db skip...")
//                        }
//                    }
//                }
//            }

//            stage('deploy app') {
//                steps {
//                    script {
//                        // TODO
//                    }
//                }
//            }

//            stage('deploy config then restart') {
//                steps {
//                    script {
//                        // TODO
//                    }
//                }
//            }
//
//            stage('check list') {
//                steps {
//                    script {
//                        // TODO
//                    }
//                }
//            }
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
