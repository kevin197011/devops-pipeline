#!/usr/bin/env groovy

import io.kevin197011.cicd.DeployApp

def call(Closure body) {

    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

//    def projectGit = config.git
//    def projectHost = config.host

    def deploy = new DeployApp(config.git as String, config.host as String)

    //pipeline
    pipeline {
        // agent { node { label "Build"}}
        agent any

        parameters {
            string(name: 'appName', defaultValue: 'app', trim: true, description: 'appName')
            booleanParam(name: 'flag', defaultValue: false, description: 'sure?')
            choice(name: 'version', choices: ['A', 'B', 'C'], description: 'which version?')
        }

        stages {
            stage("do?") {
                steps {
                    script {
                        if (!params.flag) {
                            error "Not sure, break!"
                        }
                    }
                }
            }
            stage('test') {
                steps {
                    script {
                        for (element in config ){
                            println "${element.key} => ${element.value}"
                        }

//                        println Message.getMsg(params.appName)
//                        println Message.booleanToString(params.flag)
//                        println params.version
//                        println deploy.toString()
                    }
                }
            }

            stage('parallel') {
                parallel {
                    stage('A') {
                        steps {
                            script {
                                sleep(10)
                            }
                        }
                    }
                    stage('B') {
                        steps {
                            script {
                                sleep(10)
                            }
                        }
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
