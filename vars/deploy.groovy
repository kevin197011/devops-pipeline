#!/usr/bin/env groovy
// println(System.getProperty("java.ext.dirs"))

import io.kevin197011.cicd.Config
import io.kevin197011.cicd.Gitlab
import io.kevin197011.cicd.DeployDatabase
import io.kevin197011.cicd.DeployApp
import io.kevin197011.cicd.DeployConfig

def call() {

    def project = Config.project.join('\n')
//    def app = Config.app

    def gitlab = new Gitlab(script: this)
    def database = new DeployDatabase('t1', 'localhost', 'deploy', 'devops', '123456')
    def deployApp = new DeployApp(script: this)
    def deployConfig = new DeployConfig(script: this)

    environment {
        ssh_key = credentials('349f9a1e-b4a0-4f1a-98cf-b0574ccf1b54')
    }


    properties([
            parameters([
                    [$class              : 'CascadeChoiceParameter',
                     choiceType          : 'PT_SINGLE_SELECT',
                     description         : 'Which app?',
                     filterLength        : 1,
                     filterable          : false,
                     name                : 'AppName',
                     referencedParameters: 'ProjectName',
                     script              : [$class        : 'GroovyScript',
                                            fallbackScript: [
                                                    classpath: [],
                                                    sandbox  : true,
                                                    script   : 'return ["ERROR"]'
                                            ],
                                            script        : [
                                                    classpath: [],
                                                    sandbox  : true,
                                                    script   : """
                                            if (ProjectName == 'yz') {
                                                return['kllm', 'b1', 'c1', 'd1']
                                            } else if (ProjectName == 'xx') {
                                                return['a2', 'b2', 'c2', 'd2']
                                            } else if (ProjectName == 'yy') {
                                                return['a3', 'b4', 'c4', 'd4']
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
            string(name: 'GitRepo', defaultValue: 'None', trim: true, description: 'GitRepo')
            string(name: 'Host', defaultValue: 'None', trim: true, description: 'Host')
            choice(name: 'ProjectName', choices: "${project}", description: 'Which project?')
            booleanParam(name: 'DeployDB', defaultValue: false, description: 'sure?')
            booleanParam(name: 'UpdateJob', defaultValue: true, description: 'UpdateJob?')
        }


        stages {

            stage('git clone item') {
                steps {
                    script {
//                        gitlab.cloneItem('https://github.com/kevin197011/chatOps.git', 'master')
                        gitlab.clone(params.GitRepo, 'master')
                    }
                }
            }

            stage('deploy database') {
                steps {
                    script {
                        if ((!params.UpdateJob) && (params.DeployDB)) {
                            def val = database.execute()
                            if (val) {
                                println("sql execute succeed!")
                            } else {
                                error("sql execute error!")
                            }
                        } else {
                            println("deploy db skip...")
                        }
                    }
                }
            }

            stage('deploy app') {
                steps {
                    script {
                        if (!params.UpdateJob) {
                            println("${params.ProjectName} ${params.AppName} in ${params.Host} deploy app ...")
                            deployApp.deploy(params.ProjectName, params.AppName, params.Host)
                        }
                    }
                }
            }

            stage('deploy config then restart') {
                steps {
                    script {
                        if (!params.UpdateJob) {
                            println("${params.ProjectName} ${params.AppName} in ${params.Host} deploy config and restart ...")
                            deployConfig.deploy(params.ProjectName, params.AppName, params.Host)
                        }
                    }
                }
            }

            stage('check list') {
                steps {
                    script {
                        // TODO
                        println("check list stage!")
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
